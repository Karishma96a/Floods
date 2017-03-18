import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class login extends HttpServlet{
    
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
         sql = "SELECT * FROM member";
         ResultSet rs = stmt.executeQuery(sql);

         String uname1=request.getParameter("uname");
String pass1=request.getParameter("upass");
          int flag=0;
         while(rs.next()){
            //Retrieve by column name
            
            
            String name= rs.getString("user");
String pas=rs.getString("pass");
          
            if(uname1.equals(name) && pass1.equals(pas))
            {
              out.println("logged in");
              flag=1;
              break;             
             }
                                     
         }
         if(flag==0)
         out.println("<html><body>Not authenticated</body></html>");

         

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