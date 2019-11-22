package com.XSLExtraction;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
public class XSLExtraction {
	
       public static void main(String args[]) throws BiffException, IOException {
					Connection c = null;
      Statement stmt = null;
	   try {
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


        String FilePath = "customer.xls";
                                FileInputStream fs = new FileInputStream(FilePath);
                                Workbook wb = Workbook.getWorkbook(fs);
 
                                // TO get the access to the sheet
                                Sheet sh = wb.getSheet("Sheet1");
 
                                // To get the number of rows present in sheet
                                int totalNoOfRows = sh.getRows();
 
                                // To get the number of columns present in sheet
                                int totalNoOfCols = sh.getColumns();
 String s[]=new String[3];
                                for (int row = 0; row < totalNoOfRows; row++) {
									int i=0;
                                                for (int col = 0; col < totalNoOfCols; col++) {
                                                                s[i++]=sh.getCell(col, row).getContents() ;
                                                }
                                                System.out.println();
												
												 stmt = c.createStatement(); 
         


         String sql = "INSERT INTO customer(customer_key,address,city,state,zip) "+ "VALUES ('"+s[0]+"','"+s[1]+"','"+s[2]+"', '"+s[3]+"','"+s[4]+"');";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();
												
												
                                }
        

    c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   
	  
	  
	  
                                
                }
}




Screen shots for XLS:

