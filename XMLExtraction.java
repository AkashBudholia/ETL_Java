package com.XMLExtraction;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class XMLExtraction {

  public static void main(String argv[]) {
Connection c = null;
      Statement stmt = null;
	  try {
    try {

	File fXmlFile = new File("time.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
	NodeList nList = doc.getElementsByTagName("rows");
			
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			String s1=eElement.getAttribute("time_key");
			String s2=eElement.getElementsByTagName("date").item(0).getTextContent();
			String s3=eElement.getElementsByTagName("month").item(0).getTextContent();
			String s4=eElement.getElementsByTagName("year").item(0).getTextContent();
			System.out.println(s1+s2+s3+s4);

	
	
	
			String url = "jdbc:mysql://localhost:3306/";
	  		String dbName = "test";
	  		String driver = "com.mysql.jdbc.Driver";
	  		String userName = "root";
	  		String password = "root";
	    	  Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url + dbName, userName, password);
				
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
String line="";

//2

         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");


         String sql = "INSERT INTO time(time_key,date,month,year) "+ "VALUES ('"+s2+"','"+s3+"','"+s4+"');";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();

	
    c.close();
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}

