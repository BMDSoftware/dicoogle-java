/**
 *
 * Copyright (C) 2015 - Luís A. Bastião Silva and Universidade de Aveiro This
 * program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.bmdsoftware.dicoogle.javaclient.responses;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@bmd-software.com>
 */
public class XMLDumpResponses {

    private Map<String, String> tags = new HashMap<String, String>();
    
    public XMLDumpResponses(String dumpXML){
        parseDump(dumpXML);
    }
    
    private void parseDump(String response )
    {
        
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InputSource is = new InputSource(new StringReader(response));
        Document doc = null;
        try {
            doc = docBuilder.parse(is);
        } catch (SAXException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doc!=null)
        {
            doc.getDocumentElement().normalize();
        }
        

        XPathFactory xpathf = XPathFactory.newInstance();
        XPath xpath = xpathf.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("/tags/tag");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object res = null;
        try {
            res = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList item = (NodeList) res;
        
        if (item.getLength()==0)
            return;
        
        
        for (int i = 0 ; i<item.getLength(); i++)
        {
            Node itemNode = item.item(i);
            
            String tagName = itemNode.getAttributes().getNamedItem("name").getNodeValue();
            String tagValue = itemNode.getNodeValue();
            getTags().put(tagName, tagValue);   
        }
    }

    /**
     * @return the tags
     */
    public Map<String, String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    
    
}
