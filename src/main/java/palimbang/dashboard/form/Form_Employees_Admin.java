package palimbang.dashboard.form;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.DBQueries;
import net.proteanit.sql.DbUtils;

public class Form_Employees_Admin extends javax.swing.JPanel {
    
    Connection conn;
    DBQueries query = new DBQueries();
    private Component rootPane;
    final String TABLE_NAME = "UserTable";
    String user, pass, email, fName, lName, mName, pos, appDate;
    
    public Form_Employees_Admin(Connection temp) throws SQLException {
        conn = temp;
        initComponents();
        setOpaque(false);
        table1.addTableStyle(jScrollPane1);
        initTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableModel tableModel = table1.getModel();
        centerTableComponents();
    }
    
    private void initTable() {
        try {
            String sql = "SELECT userID, username, userEmail, userFirstN, userLastN, userPos, userAppDate FROM UserTable WHERE userIsAdmin = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int emptyCellCount = 10 - table1.getRowCount();
            if(table1.getRowCount() < 10)
                for(int i=0; i<emptyCellCount; i++)
                    model.addRow(new Object[] {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        table1.setEnabled(false);
    }
    
    private void centerTableComponents() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableModel tableModel = table1.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table1.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
        table1.getColumnModel().getColumn(0).setHeaderValue("User ID");
        table1.getColumnModel().getColumn(1).setHeaderValue("Username");
        table1.getColumnModel().getColumn(2).setHeaderValue("Email");
        table1.getColumnModel().getColumn(3).setHeaderValue("Firstname");
        table1.getColumnModel().getColumn(4).setHeaderValue("Lastname");
        table1.getColumnModel().getColumn(5).setHeaderValue("Position");
        table1.getColumnModel().getColumn(6).setHeaderValue("Appointment Date");
        table1.setEnabled(false);
    }
    
    protected boolean isUnameValid(String user){
        boolean valid = false;
        final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pat = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pat.matcher(user);
        if(matcher.matches()){
            valid = true;
        }
        else{
            valid = false;
        }
        return valid;
    }
    
    protected void getDataFromDB(int uID){
        String queryStmt = String.format("userID = '%s'", uID);
        ResultSet rs = query.getRow(conn, "*", TABLE_NAME, queryStmt);
        try {
            while(rs.next()){
                fName = rs.getString("userFirstN");
                mName = rs.getString("userMiddleN");
                lName = rs.getString("userLastN");
                user = rs.getString("username");
                pass = String.valueOf(rs.getObject("userPass"));
                email = rs.getString("userEmail");
                pos = rs.getString("userPos");
//                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                appDate = rs.getString("userAppDate");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Employees_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new palimbang.dashboard.swing.Table();
        userEdit = new javax.swing.JButton();
        userField = new palimbang.dashboard.swing.MyTextFieldAdmin();
        userFilter = new javax.swing.JButton();
        filterField = new palimbang.dashboard.swing.MyTextFieldAdmin();
        resetTable = new javax.swing.JButton();
        userDelete = new javax.swing.JButton();
        deleteField = new palimbang.dashboard.swing.MyTextFieldAdmin();

        setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table1.getTableHeader().setResizingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        userEdit.setBackground(new java.awt.Color(153, 153, 153));
        userEdit.setText("Edit");
        userEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditActionPerformed(evt);
            }
        });

        userField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        userField.setForeground(new java.awt.Color(102, 102, 102));
        userField.setHint("User ID");

        userFilter.setBackground(new java.awt.Color(153, 153, 153));
        userFilter.setText("Search");
        userFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFilterActionPerformed(evt);
            }
        });

        filterField.setForeground(new java.awt.Color(102, 102, 102));
        filterField.setHint("Email");

        resetTable.setBackground(new java.awt.Color(153, 153, 153));
        resetTable.setText("Reset Table");
        resetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTableActionPerformed(evt);
            }
        });

        userDelete.setBackground(new java.awt.Color(153, 153, 153));
        userDelete.setText("Delete");
        userDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDeleteActionPerformed(evt);
            }
        });

        deleteField.setForeground(new java.awt.Color(102, 102, 102));
        deleteField.setHint("User ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(userEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(resetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(userEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(userField, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addComponent(filterField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditActionPerformed
        boolean loop = true;
        if(!userField.getText().equals("") && !userField.getText().equals("0")) {
            if(!query.isStrUnique(conn, userField.getText(), "userID", "UserTable")) {
                do {
                    getDataFromDB(Integer.parseInt(userField.getText()));
                    JTextField field1 = new JTextField(user);
                    JTextField field2 = new JTextField(pass);
                    JTextField field3 = new JTextField(email);
                    JTextField field4 = new JTextField(fName);
                    JTextField field5 = new JTextField(lName);
                    JTextField field6 = new JTextField(mName);
                    JTextField field7 = new JTextField(pos);
                    JDateChooser appDateChooser = new JDateChooser();
                    ((JTextField) appDateChooser.getDateEditor().getUiComponent()).setText(appDate);
                    ((JTextField) appDateChooser.getDateEditor().getUiComponent()).setForeground(java.awt.Color.black);
                    field1.setPreferredSize(new Dimension( 200, 24 ));

                    JPanel panel = new JPanel(new GridLayout(0, 2, -70, 5));
                    panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
                    panel.add(new JLabel("Username"));
                    panel.add(field1);
                    panel.add(new JLabel("Password"));
                    panel.add(field2);
                    panel.add(new JLabel("Email"));
                    panel.add(field3);
                    panel.add(new JLabel("Firstname"));
                    panel.add(field4);
                    panel.add(new JLabel("Middlename"));
                    panel.add(field5);
                    panel.add(new JLabel("Lastname"));
                    panel.add(field6);
                    panel.add(new JLabel("Position"));
                    panel.add(field7);
                    panel.add(new JLabel("Date of Appointment"));
                    panel.add(appDateChooser);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Employee Edit",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        // Gets input from the edit fields
                String  user2 = field1.getText(), pass2 = field2.getText(), email2 = field3.getText(), 
                        fName2 = field4.getText(), lName2 = field5.getText(), mName2 = field6.getText(),
                        pos2 = field7.getText();
                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                String appDate2;
                if (appDateChooser.getDate() == null){          // if no date is selected
                    appDate2 = "";                              // set to blank to prevent nullptrexception
                    if(appDate != null){                        // if existing date is not to be changed
                        appDate2 = appDate;                     // use existing date as current
                    }
                } else appDate2 = dateFormat.format(appDateChooser.getDate()); // else use newly set date as current date
                
                        if(user2.equals("") && pass2.equals("") && email2.equals("") && fName2.equals("") && lName2.equals("") && mName2.equals("") && pos2.equals("") && appDateChooser.getDate() == null) {
                            JOptionPane.showMessageDialog(null, "No input provided!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if(user2.equals("")) {
                                int response = JOptionPane.showConfirmDialog(rootPane,
                                        "Are you sure you want to update?",
                                        "",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                        if(response == 0)
                                        {
                                            String stmt = String.format("username = '%s', userPass = '%s', userEmail = '%s', userFirstN = '%s', userLastN = '%s', userMiddleN = '%s', userPos = '%s', userAppDate = '%s'", user2, pass2, email2, fName2, lName2, mName2, pos2, appDate2);
                                            String stmt2 = "userID = '" + userField.getText() + "'";
                                            query.updateRow(conn, TABLE_NAME, stmt, stmt2);
                                            userField.setText("");
                                            filterField.setText("");
                                            deleteField.setText("");
                                            user = ""; pass = ""; email = ""; fName = ""; lName = ""; mName = ""; pos = ""; 
                                            initTable();
                                            centerTableComponents();
                                            loop = false;
                                        }
                            } else {
                                if(isUnameValid(user2)) {
                                    if(query.isStrUnique(conn, user2, "username", "UserTable") || user.equals(user2)) {
                                        int response = JOptionPane.showConfirmDialog(rootPane,
                                        "Are you sure you want to update?",
                                        "",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                        if(response == 0) {
                                            String stmt = String.format("username = '%s', userPass = '%s', userEmail = '%s', userFirstN = '%s', userLastN = '%s', userMiddleN = '%s', userPos = '%s', userAppDate = '%s'", user2, pass2, email2, fName2, lName2, mName2, pos2, appDate2);
                                            String stmt2 = "userID = '" + userField.getText() + "'";
                                            query.updateRow(conn, TABLE_NAME, stmt, stmt2);
                                            userField.setText("");
                                            filterField.setText("");
                                            deleteField.setText("");
                                            user = ""; pass = ""; email = ""; fName = ""; lName = ""; mName = ""; pos = ""; appDate = null;
                                            initTable();
                                            centerTableComponents();
                                            loop = false;
                                        }
                                    } else {
                                        System.out.println(query.getRow(conn, "username", TABLE_NAME, "userID = " + userField.getText()));
                                        JOptionPane.showMessageDialog(null, "Username is already used. Please enter a different username.", "Error", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Username is not valid. Please enter a username with numbers AND/OR special characters.", "Error", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    } else {
                        user = ""; pass = ""; email = ""; fName = ""; lName = ""; mName = ""; pos = ""; appDate = null;
                        loop = false;
                    }
                }while(loop);
            }
            else {
                JOptionPane.showMessageDialog(null, "User ID is invalid!", "Error", JOptionPane.INFORMATION_MESSAGE); 
            }
        }
        else {
            if(userField.getText().equals("0")){
                JOptionPane.showMessageDialog(null, "User ID is invalid!", "Error", JOptionPane.INFORMATION_MESSAGE);   
            } else {
                JOptionPane.showMessageDialog(null, "No input provided!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }   
        }
            
    }//GEN-LAST:event_userEditActionPerformed

    private void userFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFilterActionPerformed
        if(!filterField.getText().equals("")) {
            if(!query.isStrUnique(conn, filterField.getText(), "userEmail", "UserTable") && !filterField.getText().equals("0")) {
                try {
                String sql = "Select userID, username, userEmail, userFirstN, userLastN, userPos, userAppDate from UserTable where userEmail = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, filterField.getText());
                ResultSet rs = pstmt.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                int emptyCellCount = 10 - table1.getRowCount();
                if(table1.getRowCount() < 10)
                    for(int i=0; i<emptyCellCount; i++)
                        model.addRow(new Object[] {});
                centerTableComponents();
                filterField.setText("");
                userField.setText("");
                deleteField.setText("");
                JOptionPane.showMessageDialog(null, "Table has been filtered!", "", JOptionPane.INFORMATION_MESSAGE); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email is invalid!", "Error", JOptionPane.INFORMATION_MESSAGE); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No input provided!", "Error", JOptionPane.INFORMATION_MESSAGE);   
        }
        
    }//GEN-LAST:event_userFilterActionPerformed

    private void resetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetTableActionPerformed
    int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to reset the table?",
            "",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0)
        {
            initTable();
            centerTableComponents();
        }
    }//GEN-LAST:event_resetTableActionPerformed

    private void userDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDeleteActionPerformed
        if(!deleteField.getText().equals("")) {
            if(!query.isStrUnique(conn, deleteField.getText(), "userID", "UserTable") && !deleteField.getText().equals("0")) {
                int response = JOptionPane.showConfirmDialog(rootPane,
                "Are you sure you want to delete the selected employee?",
                "",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                if(response == 0)
                {
                    try {
                        String sql = "DELETE FROM UserTable where userID = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, deleteField.getText());
                        pstmt.executeUpdate();
                        userField.setText("");
                        filterField.setText("");
                        deleteField.setText("");
                        initTable();
                        centerTableComponents();
                        JOptionPane.showMessageDialog(null, "Deletion was successful!", "", JOptionPane.INFORMATION_MESSAGE); 
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "User ID is invalid!", "Error", JOptionPane.INFORMATION_MESSAGE); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No input provided!", "Error", JOptionPane.INFORMATION_MESSAGE);   
        }
    }//GEN-LAST:event_userDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private palimbang.dashboard.swing.MyTextFieldAdmin deleteField;
    private palimbang.dashboard.swing.MyTextFieldAdmin filterField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton resetTable;
    private palimbang.dashboard.swing.Table table1;
    private javax.swing.JButton userDelete;
    private javax.swing.JButton userEdit;
    private palimbang.dashboard.swing.MyTextFieldAdmin userField;
    private javax.swing.JButton userFilter;
    // End of variables declaration//GEN-END:variables
}
