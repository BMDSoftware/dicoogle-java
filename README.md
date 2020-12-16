# dicoogle-java: dicoogle API client

**dicoogle-java** is the official `dicoogle`_ API client for Java.



Dicoogle is an extensible, platform-independent and open-source PACS archive software that replaces the traditional centralized database with a more agile indexing and retrieval mechanism. It was designed to support automatic extraction, indexing and storage of all meta-data detected in medical images, including private DICOM attribute tags, without re-engineering or reconfiguration requirements. This package is provided to better use their APIs in Java code.

www.dicoogle.com


How to use? 
---------

If you use maven: 
```
 <dependency>
  <groupId>pt.ua.ieeta</groupId>
  <artifactId>Dicoogle-Java</artifactId>
  <version>0.5</version>
</dependency>
```

In your application: 
```
DicoogleClient client = new DicoogleClient("http://localhost:6060/");
List<Image> images = client.searchFreeText("CT", QueryLevel.IMAGE, false);
System.out.println(client.dump("0.0.0.0.1.8811.2.1.20010413115754.12432"));
```


# Resources


* https://www.dicoogle.com


Contributing
------------

The open source project is maintained by [BMD Software](https://www.bmd-software.com/). Your contributions to the software are also welcome. Dicoogle is sought to be useful for R&D and the industry alike. For tech support, please prefer contacting the maintainers instead of creating an issue.


## Support and consulting
[<img src="https://raw.githubusercontent.com/wiki/BMDSoftware/dicoogle/images/bmd.png" height="64" alt="BMD Software">](https://www.bmd-software.com)

Please contact [BMD Software](https://www.bmd-software.com) for professional support and consulting services.


# License

MIT
