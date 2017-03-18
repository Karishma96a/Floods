import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class register extends HttpServlet{
    
public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
    throws ServletException, IOException
  {
      // JDBC driver name and database URL
      final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost:3306/TEST";

      //  Database credentials
      final String USER = "root";
      final String PASS = "karish";
	
	Statement stmt=null;
	Connection conn =null;

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";


     
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         stmt = conn.createStatement();
         
         String n1=request.getParameter("name");
		String n2=request.getParameter("add");
		String n3=request.getParameter("areas");
		int p1=Integer.parseInt(request.getParameter("ph1"));
		int p2=Integer.parseInt(request.getParameter("ph2"));
		String n4=request.getParameter("uname1");
		String n5=request.getParameter("pass1");
String n6=request.getParameter("msg");
PreparedStatement sql =(PreparedStatement)conn.prepareStatement ("insert into member values(?,?,?,?,?,?,?,?,?)");
sql.setString(1,n1);
		sql.setString(2,n2);
		sql.setString(3,n3);
		sql.setInt(4,p1);
		sql.setInt(5,p2);
		sql.setString(6,n4);
		sql.setString(7,n5);
	sql.setString(8,n6);
sql.setString(9,"Looking for Help");
int i=sql.executeUpdate();
out.println("Succesfully posted message");

      

         // Clean-up environment
   sql.close();
         stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      } //end try
   }
} 