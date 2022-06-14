package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DEFAULT SQLITE DATETIME FORMAT YYYY-MM-DD HH:MM:SS
// DEFAULT SQLITE TIME FORMAT HH:MM:SS

public class TimeInTimeOut {

	public void timeIn(Connection conn, int ID) {
		DBQueries query = new DBQueries();
                if(!query.isIDTimedIn(conn, ID)){
                    ResultSet rs = query.getRow(conn, "*", "UserTable", "userID = '" + ID + "'");
                    List<String> timeList = new ArrayList<String>();
                    try {
                            timeList.add(rs.getString("userID"));
                            timeList.add(rs.getString("userIn"));
                            timeList.add(rs.getString("userOut"));
                            timeList.add(rs.getString("userAftIn"));
                            timeList.add(rs.getString("userAftOut"));
                            query.insertTimeIn(conn, timeList);

                    } catch (SQLException e) {
                            e.printStackTrace();
                    }catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                    }
                }else
                    System.out.println("No Time Out");
		
	}
	
	public void timeOut(Connection conn, int ID) {
            DBQueries query = new DBQueries();
            if(query.isIDTimedIn(conn, ID)) {
                query.insertTimeOut(conn, ID);
                query.transferToTimeHistory(conn, ID);
            }else
                   System.out.println("No Time In");
        }
}
