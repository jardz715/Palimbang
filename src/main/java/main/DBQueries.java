package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBQueries {
	
    // Select from table without WHERE clause
	public ResultSet selectFromTable(Connection conn, String selectFF, String table) {
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
                           " userAftIn text CHECK (userAftIn IS time(userAftIn))," +
	                   " userAftOut text CHECK (userAftOut IS time(userAftOut))," +
                           " userAdd VARCHAR(255), " +
                           " userStatus VARCHAR(255), " +
                           " userAppDate VARCHAR(255), " + //Placeholder until admin can actually set the date from their dashboard
                           " userNat VARCHAR(255), " +
                           " userPos VARCHAR (255), " +
	                   " PRIMARY KEY ( userID ))"; 
			stmt.executeUpdate(sql);
	         
	         sql = "CREATE TABLE IF NOT EXISTS TimeTable " +
	                   "(timeID INTEGER not NULL, " +
	                   " userID INTEGER, " +
	                   " userIn text CHECK (userIn IS time(userIn)), " +
	                   " userOut text CHECK (userOut IS time(userOut)), " +
                           " userAftIn text CHECK (userAftIn IS time(userAftIn))," +
	                   " userAftOut text CHECK (userAftOut IS time(userAftOut))," +
	                   " timeIn text, " +
	                   " timeOut text, " +
	                   " timeDiff INTEGER, " +
	                   " timeOT INTEGER, " + 
                           " timeUT INTEGER, " + 
                           " timeType text, " +
	                   " PRIMARY KEY ( timeID ))"; 
	         stmt.executeUpdate(sql);
	         
	         sql = "CREATE TABLE IF NOT EXISTS TimeHistoryTable " +
	                   "(timeHistID INTEGER not NULL, " +
	                   " userID INTEGER, " +
	                   " userIn text CHECK (userIn IS time(userIn)), " +
	                   " userOut text CHECK (userOut IS time(userOut)), " +
                           " userAftIn text CHECK (userAftIn IS time(userAftIn))," +
	                   " userAftOut text CHECK (userAftOut IS time(userAftOut))," +
	                   " timeHistIn text, " +
	                   " timeHistOut text, " + 
	                   " timeHistDiff INTEGER, " +
	                   " timeHistOT INTEGER, " + 
                           " timeHistUT INTEGER, " + 
                           " timeHistType text, " +
	                   " PRIMARY KEY ( timeHistID ))"; 
	         stmt.executeUpdate(sql);
                 
                 sql = "CREATE TABLE IF NOT EXISTS DocTemplateTable " +
	                   "(dTemplateID INTEGER not NULL, " +
	                   " dTemplatePath text, " +
	                   " dTemplateTitle text, " +
	                   " PRIMARY KEY ( dTemplateID ))"; 
	         stmt.executeUpdate(sql);
                 
                 sql = "CREATE TABLE IF NOT EXISTS DocumentTable " +
	                   "(docID INTEGER not NULL, " +
                           " userID INTEGER, " +
                           " dTemplateID INTEGER, " +
	                   " docPath text, " +
	                   " docTitle text, " +
                           " docSubmitted BOOLEAN, " +
                           " docValidated BOOLEAN, " +
	                   " PRIMARY KEY ( docID ))"; 
	         stmt.executeUpdate(sql);
                 
                 sql = "INSERT INTO UserTable VALUES (0,'admin123', 'admin123@aer.ph', 'admin', 'admin', 'admin', 69, 091234567891, null,'admin123', 1, '08:00:00', '12:00:00', '13:00:00', '17:00:00', null, null, null, null, null)";
                 stmt.executeUpdate(sql);     
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	} 
	
        public void deleteRows(Connection conn, String table, String where){
            String sql = "DELETE FROM " + table + " WHERE " + where;
            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
	// prior to be changed, just a quick attempt. must hash password
	protected void registerUser(Connection conn, List<String> list) {
		String sql = "INSERT INTO UserTable(username, userPass, userFirstN, userMiddleN, userLastN, userAge, userEmail, userContact, userGender, userIsAdmin, userIn, userOut, userAftIn, userAftOut) VALUES(?,?,?,?,?,?,?,?,?,0,'08:00:00','12:00:00','13:00:00','17:00:00')";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
            	pstmt.setString(i+1, list.get(i));
			}
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("If you see this, wrong format on time in or time out. but on swing easy fix");
                e.printStackTrace();
        }
	}
	
        // Registers admin accounts to database
	public void registerAdmin(Connection conn, List<String> list) {
		String sql = "INSERT INTO UserTable(username, userPass, userFirstN, userMiddleN, userLastN, userEmail, userContact, userIsAdmin, userPos) VALUES(?,?,?,?,?,?,?,1,'Admin')";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
            	pstmt.setString(i+1, list.get(i));
			}
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	//System.out.println("If you see this, wrong format on time in or time out. but on swing easy fix");
                e.printStackTrace();
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
        
        // will change the functionality. probably for email validation. should be boolean. Can be reused for password validation as well.
	public boolean isStrUnique(Connection conn, int userID, String str, String column, String table) {
		if(str == null  || column == null || table == null)
			return false; // java prompt instead

		try {
			ResultSet rs = getRow(conn, "*", table, column + " = '" + str + "'");
                        while (rs.next()) {  
                            if(!Integer.toString(userID).equals(rs.getString("userID"))){
                                if(str.equals(rs.getString(column)))
                                        return false;
                                continue;
                            } 
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
        
        public void updateEmp(Connection conn, String table, String set, String where){
            String sql = "UPDATE " + table + " SET " + set + " WHERE " + where;
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate(); 
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Please enter correct formats", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        public boolean isTimeWithinDay(Connection conn, int id) throws ParseException{
            ResultSet rs = getRow(conn, "*", "TimeHistoryTable", "timeHistIn >= date('now', 'localtime', 'start of day') AND userID = " + id + " ORDER BY timeHistID DESC");
            try{
                if(rs.next() != false){
                    if(rs.getString("timeHistType").equals("Morning")){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return false;
                }
            }catch (SQLException ex) {
                Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        
        protected void insertTimeIn(Connection conn, List<String> list, String time){
            String sql;
            try {
                if(time.equals("Morning")){
                    sql = "INSERT INTO TimeTable (userID, timeIn, userIn, userOut, userAftIn, userAftOut, timeType) VALUES(?, datetime('now', 'localtime'),?,?,?,?,'Morning')";
                }else{
                    sql = "INSERT INTO TimeTable (userID, timeIn, userIn, userOut, userAftIn, userAftOut, timeType) VALUES(?, datetime('now', 'localtime'),?,?,?,?,'Afternoon')";
                }
                PreparedStatement pstmt = conn.prepareStatement(sql);
                for (int i = 0; i < list.size(); i++) {
                    pstmt.setString(i+1, list.get(i));
		}
                pstmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        public void insertDocTemplate(Connection conn, List<String> list) {
            String sql = "INSERT INTO DocTemplateTable (dTemplatePath, dTemplateTitle) VALUES (?,?)";
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
        
        public void insertDocument(Connection conn, List<String> list) {
            String sql = "INSERT INTO DocumentTable (userID, dTemplateID ,docPath, docTitle, docValidated, docSubmitted) VALUES (?,?,?,?,?,?)";
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
		String sql2 = "UPDATE TimeTable SET timeDiff = ?, timeOT = ?, timeUT = ? WHERE userID = " + ID;
		try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    PreparedStatement pstmt = conn.prepareStatement(sql2);
                    pstmt.setString(1, String.valueOf(getTimeDiff(conn, ID)));
                    pstmt.setString(2, String.valueOf(getOT(conn, ID)));
                    pstmt.setString(3, String.valueOf(getUT(conn, ID)));
                    pstmt.executeUpdate();

		}catch (SQLException e) {
        	e.printStackTrace();
        }
	}
        
        protected void forceTimeOut(Connection conn, int ID) { 
		String sql = "UPDATE TimeTable SET timeOut = datetime('now', 'localtime') WHERE userID = " + ID;
		String sql2 = "UPDATE TimeTable SET timeDiff = ?, timeOT = ?, timeUT = ? WHERE userID = " + ID;
		try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    PreparedStatement pstmt = conn.prepareStatement(sql2);
                    pstmt.setString(1, String.valueOf(getTimeDiff(conn, ID)));
                    pstmt.setString(2, String.valueOf(0));
                    pstmt.setString(3, String.valueOf(0));
                    pstmt.executeUpdate();

		}catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	protected void transferToTimeHistory (Connection conn, int ID) {
		String sql = "INSERT INTO TimeHistoryTable(userID, userIn, userOut, userAftIn, userAftOut, timeHistIn, timeHistOut, timeHistDiff, timeHistOT, timeHistUT, timeHistType) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                ResultSet rs = getRow(conn, "userID, userIn, userOut, userAftIn, userAftOut, timeIn, timeOut, timeDiff, timeOT, timeUT, timeType", "TimeTable", "userID = " + ID);
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
            try {
                String sql = "SELECT ROUND((JULIANDAY(timeOut) - JULIANDAY(timeIn)) * 86400) AS difference FROM TimeTable WHERE userID = " + ID;
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
            String sql;
            int compare;
            ResultSet rs = getRow(conn, "timeType, ROUND((JULIANDAY(userOut) - JULIANDAY(userIn)) * 24 * 60) as userDiff, ROUND((JULIANDAY(userAftOut) - JULIANDAY(userAftIn)) * 24 * 60) as userAftDiff", "TimeTable", "userID = " + ID);
            try {
                sql = "SELECT ROUND((JULIANDAY(strftime('%H:%M:%S' ,timeOut)) - JULIANDAY(strftime('%H:%M:%S' ,timeIn))) * 24 * 60) AS overtime FROM TimeTable WHERE userID = " + ID;
                if(rs.next() == false || rs.getString("timeType").equals("Morning")){
                    compare = rs.getInt("userDiff");
                }else{
                    compare = rs.getInt("userAftDiff");
                }
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql);
                if(compare < rs2.getInt("overtime")){
                    int temp = rs2.getInt("overtime") - compare;
                    if(temp > 0) {
                            return temp; 
                    }
                }else{
                    return 0;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return 0;
        }
        protected int getUT(Connection conn, int ID) {
            String sql;
            int compare;
            ResultSet rs = getRow(conn, "timeType, ROUND((JULIANDAY(userOut) - JULIANDAY(userIn)) * 24 * 60) as userDiff, ROUND((JULIANDAY(userAftOut) - JULIANDAY(userAftIn)) * 24 * 60) as userAftDiff", "TimeTable", "userID = " + ID);
            try {
                sql = "SELECT ROUND((JULIANDAY(strftime('%H:%M:%S' ,timeOut)) - JULIANDAY(strftime('%H:%M:%S' ,timeIn))) * 24 * 60) AS undertime FROM TimeTable WHERE userID = " + ID;
                if(rs.next() == false || rs.getString("timeType").equals("Morning")){
                    compare = rs.getInt("userDiff");
                }else{
                    compare = rs.getInt("userAftDiff");
                }
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql);
                if(compare > rs2.getInt("undertime")){
                    int temp = compare - rs2.getInt("undertime");
                    if(temp > 0) {
                            return temp; 
                    }
                }else{
                    return 0;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return 0;
        }
		
        
        public boolean isIDTimedIn(Connection conn, int ID) {
		ResultSet rs = getRow(conn, "userID", "TimeTable", ID + " = userID" );
		try {
                    return rs.next() != false;
		}catch (SQLException e) {  
			e.printStackTrace();  
        }
		return false;
	}
        
}
