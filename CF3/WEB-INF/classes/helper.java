import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class helper extends HttpServlet{
    
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
         String sql;
String a=request.getParameter("areas1");
         sql = "SELECT * FROM member";
         ResultSet rs = stmt.executeQuery(sql);

         

          int flag=0;
         while(rs.next()){
String n=rs.getString("name");
String ad=rs.getString("addr");
int p1=rs.getInt("ph1");
int p2=rs.getInt("ph2");
             String m= rs.getString("message");
String ar=rs.getString("area");
if(a.equals(ar)){
              out.println("<html><body><br>Name : '"+n+"'<br> Address: '"+ad+"'<br> Phone 1: '"+p1+"'<br> Phone 2: '"+p2+"'<br> Message: '"+m+"' <br> Area: '"+ar+"'----<form action='final.html'><input type='checkbox' name='in' value='In progress'>In progress<br><input type='checkbox' name='in1' value='Looking for help'>Looking for help");
		out.println("<br><br><br>");
           } 
               flag=1;                      
         }
	
		out.println("<input type='submit' value='Go'></form><br><br></body></html>");
         if(flag==0)
         out.println("<html><body>No messages</body></html>");

         

         // Clean-up environment
         rs.close();
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