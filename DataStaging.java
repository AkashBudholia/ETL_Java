package com.transform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.*;
public class DataStaging{
   public static void main(String args[])throws IOException {
      Connection c = null;
      Statement stmt = null;
BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("publicity.txt")));
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
while((line=br.readLine())!=null){
         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       


         String sql = "DELETE FROM  publicity WHERE publicity_key( NOT IN (SELECT * FROM (SELECT MIN(n.publicity_key) FROM publicity  GROUP BY n.publicity_type) x))";
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

