package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args) throws SQLException {
		
		String userName = "SYS as sysdba";
		String password = "SYS";
		String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
		String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		   
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//Register driver:
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Open a conection:
			conn = DriverManager.getConnection(DB_URL, userName, password);
			
			//execute a query:
			stmt = conn.createStatement();
			String sql = "select * from CUSTOMERS";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	String first = rs.getString("FIRSTNAME");
		    	String last = rs.getString("LASTNAME");
		    	
		    	System.out.print(", First: " + first);
		        System.out.println(", Last: " + last);
		    	
		    }
		    
		    rs.close();
		
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
	}

}
