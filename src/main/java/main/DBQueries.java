package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQueries {
	
    // Select from table without WHERE clause
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
	protected void createTables(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS UserTable " +
	                   "(userID INTEGER not NULL, " +
                           " username VARCHAR(255), " +
	                   " userEmail VARCHAR(255), " +
	                   " userFirstN VARCHAR(255), " + 
	                   " userLastN VARCHAR(255), " +
	                   " userMiddleN VARCHAR(255), " +
	                   " userAge INTEGER, " +
	                   " userContact INTEGER, " +
	                   " userGender CHARACTER(20), " +
	                   " userPass VARCHAR(255), " +  
	                   " userIsAdmin BOOLEAN, " +
	                   " userIn text CHECK (userIn IS time(userIn))," +
	                   " userOut text CHECK (userOut IS time(userOut))," +
                           " userAdd VARCHAR(255), " +
                           " userStatus VARCHAR(255), " +
                           " userAppDate DATE, " + //Placeholder until admin can actually set the date from their dashboard
                           " userNat VARCHAR(255), " +
                           " userPos VARCHAR (255) " +
	                   " PRIMARY KEY ( userID ))"; 
			stmt.executeUpdate(sql);
	         
	         sql = "CREATE TABLE IF NOT EXISTS TimeTable " +
	                   "(timeID INTEGER not NULL, " +
	                   " userID INTEGER, " +
	                   " userIn text CHECK (userIn IS time(userIn)), " +
	                   " userOut text CHECK (userOut IS time(userOut)), " +
	                   " timeIn text, " +
	                   " timeOut text, " +
	                   " timeDiff INTEGER, " +
	                   " timeOT INTEGER, " + 
	                   " PRIMARY KEY ( timeID ))"; 
	         stmt.executeUpdate(sql);
	         
	         sql = "CREATE TABLE IF NOT EXISTS TimeHistoryTable " +
	                   "(timeHistID INTEGER not NULL, " +
	                   " userID INTEGER, " +
	                   " userIn text CHECK (userIn IS time(userIn)), " +
	                   " userOut text CHECK (userOut IS time(userOut)), " +
	                   " timeHistIn text, " +
	                   " timeHistOut text, " + 
	                   " timeHistDiff INTEGER, " +
	                   " timeHistOT INTEGER, " + 
	                   " PRIMARY KEY ( timeHistID ))"; 
	         stmt.executeUpdate(sql);
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	} 
	
	// prior to be changed, just a quick attempt. must hash password
	protected void registerUser(Connection conn, List<String> list) {
		String sql = "INSERT INTO UserTable(username, userPass, userFirstN, userMiddleN, userLastN, userAge, userEmail, userContact, userGender, userIsAdmin, userIn, userOut) VALUES(?,?,?,?,?,?,?,?,?,0,'09:00:00','18:00:00')";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
            	pstmt.setString(i+1, list.get(i));
			}
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("If you see this, wrong format on time in or time out. but on swing easy fix");
        }
	}
	
	// will change the functionality. probably for email validation. should be boolean. Can be reused for password validation as well.
	public boolean isStrUnique(Connection conn, String str, String column, String table) {
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
	
	public ResultSet getRow(Connection conn, String selectFF, String table, String where) {
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
        
        // create a method to update table row
        public void updateRow(Connection conn, String table, String set, String where){
            String sql = "UPDATE " + table + " SET " + set + " WHERE " + where;
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        protected void insertTimeIn(Connection conn, List<String> list) {
		String sql = "INSERT INTO TimeTable (userID, timeIn, userIn, userOut) VALUES(?, datetime('now', 'localtime'),?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
            	pstmt.setString(i+1, list.get(i));
			}
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	protected void insertTimeOut(Connection conn, int ID) {
		String sql = "UPDATE TimeTable SET timeOut = datetime('now', 'localtime') WHERE userID = " + ID;
		String sql2 = "UPDATE TimeTable SET timeDiff = ?, timeOT = ? WHERE userID = " + ID;
		try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    PreparedStatement pstmt = conn.prepareStatement(sql2);
                    pstmt.setString(1, String.valueOf(getTimeDiff(conn, ID)));
                    pstmt.setString(2, String.valueOf(getOT(conn, ID)));
                    pstmt.executeUpdate();

		}catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	protected void transferToTimeHistory (Connection conn, int ID) {
		String sql = "INSERT INTO TimeHistoryTable(userID, userIn, userOut, timeHistIn, timeHistOut, timeHistDiff, timeHistOT) VALUES (?,?,?,?,?,?,?)";
                ResultSet rs = getRow(conn, "userID, userIn, userOut, timeIn, timeOut, timeDiff, timeOT", "TimeTable", "userID = " + ID);
                String sql2 = "DELETE FROM TimeTable WHERE userID = " + ID;
		try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    Statement stmt = conn.createStatement();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    for(int i = 0; i < rsmd.getColumnCount(); i++){
                        pstmt.setString(i+1, rs.getString(i+1));
                    }
                    
                    pstmt.executeUpdate();
                    stmt.executeUpdate(sql2);
		}catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	//calculates minutes
	protected int getTimeDiff(Connection conn, int ID) {
		String sql = "SELECT ROUND((JULIANDAY(timeOut) - JULIANDAY(timeIn)) * 86400) AS difference FROM TimeTable WHERE userID = " + ID;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			double temp = Double.parseDouble(rs.getString("difference"));
			return (int) temp / 60;
		}catch (SQLException e) {
        	e.printStackTrace();
        }
		return 0;
	}
	
	protected int getOT(Connection conn, int ID) {
		String sql = "SELECT ROUND((JULIANDAY(strftime('%H:%M:%S' ,timeOut)) - JULIANDAY(userOut)) * 86400) AS overtime FROM TimeTable WHERE userID = " + ID;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			double temp = Double.parseDouble(rs.getString("overtime"));
			if(temp > 0) {
				return (int) temp / 60; 
			}
		}catch (SQLException e) {
        	e.printStackTrace();
        }
		return 0;
	}
        
        protected boolean isIDTimedIn(Connection conn, int ID) {
		ResultSet rs = getRow(conn, "userID", "TimeTable", ID + " = userID" );
		try {
                    return rs.next() != false;
		}catch (SQLException e) {  
			e.printStackTrace();  
        }
		return false;
	}
        
}
