
// 4 SQL method imports + io.File for checking if DB Exists
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class DBConnect {
        
        final String TABLE_NAME = "UserTestTable";
	public Connection dbCheck() throws SQLException{
		
		// Initialize DB path
		File file = new File ("resources/test.db");
		
		// No DB? Create
		if(!file.exists()) {
			SQLiteDataSource ds = null;
			
			try {
				ds = new SQLiteDataSource();
		        ds.setUrl("jdbc:sqlite:resources/test.db");

		        //Add methods for creating all tables here. Preferably a different class for recycling purposes
		        DBQueries query = new DBQueries();
		        query.createUserTable(ds.getConnection(), TABLE_NAME);
		        
		        return ds.getConnection();
		    } catch ( Exception e ) {
		        e.printStackTrace();
		        System.exit(0);
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