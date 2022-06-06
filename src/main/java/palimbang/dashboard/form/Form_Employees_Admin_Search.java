package palimbang.dashboard.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import main.DBQueries;

public class Form_Employees_Admin_Search extends javax.swing.JPanel {

    Connection conn;
    private boolean textFieldEnabled = true, success = true;
    private String textFieldInput, selectedCategory;
    final String table = "UserTable";
    
    public Form_Employees_Admin_Search(Connection temp) throws SQLException {
        conn = temp;
        initComponents();
        dateChooser.setVisible(false);
        boolean loop = true;
        do {
            int result = JOptionPane.showConfirmDialog(null, this, "Search Category",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        if(textFieldEnabled) {
                            if(comboBox.getSelectedItem().toString().equals("UserID") && textField.getText().equals("0")) {
                                JOptionPane.showMessageDialog(null, "User ID is invalid!", "Error", JOptionPane.INFORMATION_MESSAGE);
                                setSuccess(false);
                            } else {
                                if(textField.getText().equals("")) {
                                    JOptionPane.showMessageDialog(null, "Please provide an input!", "Error", JOptionPane.INFORMATION_MESSAGE);
                                    setSuccess(false);
                                } else {
                                    if(!databaseCheck(textField.getText(), setCategory(comboBox.getSelectedItem().toString()))) {
                                        setText(textField.getText());
                                        setCategory(comboBox.getSelectedItem().toString());
                                        loop = false;
                                        setSuccess(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "The input is not present in the database!", "Invalid", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            
                        } else {
                            if(dateChooser.getDate() == null) {
                                    JOptionPane.showMessageDialog(null, "Please provide an input!", "Error", JOptionPane.INFORMATION_MESSAGE);
                                    setSuccess(false);
                            } else {
                                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                                if(!databaseCheck(dateFormat.format(dateChooser.getDate()), setCategory(comboBox.getSelectedItem().toString()))) {
                                    setText(dateFormat.format(dateChooser.getDate()));
                                    setCategory(comboBox.getSelectedItem().toString());
                                    loop = false;
                                    setSuccess(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "The input is not present in the database!", "Invalid", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    } else {
                        loop = false;
                        setSuccess(false);
                    }
        }while(loop);
        
    }
    
    public String returnText() {
        return textFieldInput;
    }
    
    public String returnCategory() {
        return selectedCategory;
    }
    
    public Boolean returnSuccess() {
        return success;
    }
    
    public Boolean returnTextFieldEnabled() {
        return success;
    }
    
    public ResultSet returnQuery() throws SQLException {
        String stmt = String.format("Select userID, username, userEmail, userFirstN, userLastN, userPos, userAppDate from UserTable where %s = '%s'", selectedCategory, textFieldInput);
        PreparedStatement pstmt = conn.prepareStatement(stmt);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }
    
    public void setText(String input) {
        textFieldInput = input;
    }
    
    public void setSuccess(Boolean bool) {
        success = bool;
    }
    
    public void setTextFieldEnabled(Boolean bool) {
        textFieldEnabled = bool;
    }
    
    public String setCategory(String input) {
        if(textFieldEnabled) {
            if(input.equals("UserID"))
               selectedCategory = "userID";
            else if(input.equals("Username"))
                selectedCategory = "username";
            else if(input.equals("Email"))
                selectedCategory = "userEmail";
            else if(input.equals("Firstname"))
                selectedCategory = "userFirstN";
            else if(input.equals("Lastname"))
                selectedCategory = "userLastN";
            else if(input.equals("Position"))
                selectedCategory = "userPos";
        } else 
            selectedCategory = "userAppDate";
        return selectedCategory;
    }
    
    public boolean databaseCheck(String input, String category) {
        DBQueries query = new DBQueries();
        return query.isStrUnique(conn, input, category, table);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBox = new javax.swing.JComboBox<>();
        textField = new javax.swing.JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setMinimumSize(new java.awt.Dimension(320, 31));
        setPreferredSize(new java.awt.Dimension(310, 42));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UserID", "Username", "Email", "Firstname", "Lastname", "Position", "Appointment Date" }));
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });
        add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, 150, 25));
        add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, 150, 25));
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        String selected = comboBox.getSelectedItem().toString();
        if(!selected.equals("Appointment Date")) {
            textField.setVisible(true);
            dateChooser.setVisible(false);
            setTextFieldEnabled(true);
        } else {
            textField.setVisible(false);
            dateChooser.setVisible(true);
            setTextFieldEnabled(false);
        }
    }//GEN-LAST:event_comboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
