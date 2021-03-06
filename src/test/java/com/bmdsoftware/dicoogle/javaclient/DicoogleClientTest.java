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


package com.bmdsoftware.dicoogle.javaclient;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bmdsoftware.dicoogle.javaclient.dicom.Image;
import com.bmdsoftware.dicoogle.javaclient.dicom.QueryLevel;

/**
 *
 * @author Luís A. Bastião Silva <bastiao@bmd-software.com>
 */
public class DicoogleClientTest {
    private int NUMBER_OF_EXECUTIONS = 100;
    
    public DicoogleClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSearch()
    {
        
        for (int i = 0 ; i< NUMBER_OF_EXECUTIONS ; i++)
        {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    DicoogleClient client_T = new DicoogleClient("http://localhost:6060/");
                    List<Object> xxx = client_T.searchAdvanced("Modality:CT", QueryLevel.IMAGE, false);
                    client_T.searchFreeText("CT");
                }
            });
            
        }
        
        
    }
    @Test
    public void testSearchAdv()
    {
        DicoogleClient client = new DicoogleClient("http://localhost:6060/");
        for (int i = 0 ; i< NUMBER_OF_EXECUTIONS ; i++)
        {
            List<Object> xxx = client.searchAdvanced("Modality:CT", QueryLevel.IMAGE, false);
            for (Object o : xxx)
            {
                Image img  = (Image) o ;
                System.out.println(img);
                System.out.println(img.getTag("ViewPosition"));
                System.out.println(img.getTag("InstanceNumber"));
                System.out.println(img.getTag("NumberOfFrames"));
            }
        }
        
        
    }
    
    @Test
    public void testDump()
    {
        DicoogleClient client = new DicoogleClient("http://localhost:6060/");
        for (int i = 0 ; i< NUMBER_OF_EXECUTIONS ; i++)
        {
            //System.out.println(client.dump("0.0.0.0.1.8811.2.1.20010413115754.12432"));
        }
        
        
    }
    
    
    
    
}