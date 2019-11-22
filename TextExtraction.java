package com.textextraction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.*;

public class TextExtraction{

   public static void main(String args[])throws IOException {
      Connection c = null;
      Statement stmt = null;
BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("customer.txt")));
BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("publicity.txt")));
BufferedReader br3 =new BufferedReader(new InputStreamReader(new FileInputStream("product.txt")));
BufferedReader br4 =new BufferedReader(new InputStreamReader(new FileInputStream("time.txt")));





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
while((line=br1.readLine())!=null){
         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");

int s1=Integer.parseInt(s[0]);
         String sql = "INSERT INTO customer(customer_key,address,city,state,zip) "+ "VALUES ('"+s1+"','"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"');";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();
}
//2
while((line=br2.readLine())!=null){
         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");

int s1=Integer.parseInt(s[0]);
         String sql = "INSERT INTO publicity (publicity_key,publicity_type,publicity_cost,start_date,end_date,manager) "+ "VALUES ('"+s1+"','"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"' );";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();
}
//3
while((line=br1.readLine())!=null){
         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");

int s1=Integer.parseInt(s[0]);
         String sql = "INSERT INTO product(product_key,product_name,product_code,brand_name,category,department,units_available) "+ "VALUES ('"+s1+"','"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"','"+s[5]+"','"+s[6]+"' );";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();
}
//4
while((line=br3.readLine())!=null){
         stmt = c.createStatement();


          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");

int s1=Integer.parseInt(s[0]);
         String sql = "INSERT INTO time(time_key,date,month,year) "+ "VALUES ('"+s1+"','"+s[1]+"','"+s[2]+"','"+s[3]+"');";
         stmt.executeUpdate(sql);



         stmt.close();
         c.commit();
}
//5

}

      catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}
