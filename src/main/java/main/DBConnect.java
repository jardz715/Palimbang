package main;
// 4 SQL method imports + io.File for checking if DB Exists
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    
	public Connection dbCheck() throws SQLException{
            
		// Initialize DB path
		File file = new File ("resources/test.db");
		
		// No DB? Create
		if(!file.exists()) {
			
			try {
                            Class.forName("org.sqlite.JDBC");
                            Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/test.db");

                            //Add methods for creating all tables here. Preferably a different class for recycling purposes
                            DBQueries query = new DBQueries();
                            query.createTables(conn);
		        
		        return conn;
		    } catch ( Exception e ) {
		        e.printStackTrace();
		        System.exit(0);
                        //System.out.println("Exception");
		    }   
			
			// return statement will never reach here unless errors.
		    return null;
		    
		// Yes DB? Connect    
		} else {	
			try {
				return DriverManager.getConnection("jdbc:sqlite:resources/test.db");
				
		    } catch ( Exception e ) {
		        e.printStackTrace();
		        System.exit(0);
		    }
			
			// return statement will never reach here unless errors.
			return null;
		}
	}
	
	
}