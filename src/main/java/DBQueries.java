

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBQueries {
	
	private ResultSet selectFromTable(Connection conn, String selectFF, String table) {
		if(selectFF == null  || table == null)
			return null;
		
		String sql = "SELECT " + selectFF + " FROM " + table;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {  
			e.printStackTrace();
        }  
		return null;
	}
	
	// prior to be changed, just a quick attempt
	protected void createUserTable(Connection conn, String table) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + table + " " +
	                   "(id INTEGER not NULL, " +
	                   " userN VARCHAR(255), " +
                           " passW VARCHAR(255), " +
	                   " firstN VARCHAR(255), " + 
                           " middleN VARCHAR(255), " +
	                   " lastN VARCHAR(255), " +
	                   " age INTEGER, " +
                           " email VARCHAR(255), " + 
                           " number VARCHAR(255), " + 
	                   " gender CHARACTER(20), " +  
	                   " admin BOOLEAN, " +
	                   " PRIMARY KEY ( id ))"; 
	         stmt.executeUpdate(sql);  
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	} 
	
	// prior to be changed, just a quick attempt. must hash password
	protected void registerUser(Connection conn, List<String> list) {
		String sql = "INSERT INTO UserTestTable(id, userN, passW, firstN, middleN, lastN, age, email, number, gender, admin) VALUES(?,?,?,?,?,?,?,?,?                     ,?,0)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
            	pstmt.setString(i+1, list.get(i));
            	//System.out.println(list.get(i));
			}
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	// will change the functionality. probably for email validation. should be boolean. Can be reused for password validation as well.
	protected boolean isStrUnique(Connection conn, String str, String column, String table) {
		if(str == null  || column == null || table == null)
			return false; // java prompt instead

		try {
			ResultSet rs = selectFromTable(conn, "*", table);
			while (rs.next()) {  
				if(str.equals(rs.getString(column)))
					return false;
				continue;
	        }  
			
		} catch (SQLException e) {  
			e.printStackTrace();  
        }  
		return true;
	}
	
	protected ResultSet getRows(Connection conn, String selectFF, String table, String where) {
		if(selectFF == null  || table == null || where == null)
			return null;
		
		String sql = "SELECT " + selectFF + " FROM " + table + " WHERE " + where;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {  
			e.printStackTrace();  
        } 
		return null;
	}
	
}
