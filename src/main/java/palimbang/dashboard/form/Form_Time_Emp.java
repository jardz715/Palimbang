package palimbang.dashboard.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.DBQueries;

public class Form_Time_Emp extends javax.swing.JPanel {

    Connection conn;
    int userid;
   
    
    public Form_Time_Emp(Connection temp, int ID) throws SQLException{
        conn = temp;
        userid = ID;
        initComponents();
        getDataFromDB(conn, userid); 
        
    }
    
     
    protected void getDataFromDB(Connection conn, int userID){
        DBQueries query = new DBQueries();
        
        ResultSet rs = query.getRow(conn, "userIn, userOut", "UserTable", "userID =" + userID);
        ResultSet rs2 = query.getRow(conn, "timeIn", "TimeTable", "userID =" + userID);
        ResultSet rs3 = query.getRow(conn, "timeHistIn as TimeIn, timeHistOut as TimeOut, timeHistDiff as TotalTimeInMinutes, timeHistOT as Overtime", "TimeHistoryTable", "userID =" + userid);
        try{
            timeInLabel.setText("Time In: " + rs.getString("userIn"));
            timeOutLabel.setText("Time Out: " + rs.getString("userOut"));
            if(rs2.next() != false){
                currentLabel.setText("Current: " + rs2.getString("timeIn"));
            }else{
                currentLabel.setText("Current: Not Timed In.");
            }
            jTable = new JTable(startTable(rs3));
            jScrollPane2.setViewportView(jTable);
        }catch (SQLException ex) {
            Logger.getLogger(Form_Profile_Emp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected DefaultTableModel startTable(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        attendanceLabel = new javax.swing.JLabel();
        timeInLabel = new javax.swing.JLabel();
        timeOutLabel = new javax.swing.JLabel();
        currentLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable);

        attendanceLabel.setFont(new java.awt.Font("MS PGothic", 1, 24)); // NOI18N
        attendanceLabel.setForeground(new java.awt.Color(29, 122, 116));
        attendanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attendanceLabel.setText("Attendance Summary");

        timeInLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        timeInLabel.setForeground(new java.awt.Color(29, 122, 116));
        timeInLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeInLabel.setText("Time In:");

        timeOutLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        timeOutLabel.setForeground(new java.awt.Color(29, 122, 116));
        timeOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeOutLabel.setText("Time Out:");

        currentLabel.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        currentLabel.setForeground(new java.awt.Color(29, 122, 116));
        currentLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        currentLabel.setText("Current:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(timeInLabel)
                                .addGap(122, 122, 122)
                                .addComponent(timeOutLabel)
                                .addGap(160, 160, 160)
                                .addComponent(currentLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(attendanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attendanceLabel;
    private javax.swing.JLabel currentLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel timeInLabel;
    private javax.swing.JLabel timeOutLabel;
    // End of variables declaration//GEN-END:variables
}
