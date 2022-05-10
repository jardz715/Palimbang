import galingpook.dashboard.main.Main_Admin;
import galingpook.dashboard.main.Main_Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Main extends javax.swing.JFrame{

    public Main() throws SQLException {
        initComponents();   
    }
    
    int xMouse = 0, yMouse = 0;
    DBConnect dc = new DBConnect();
    Connection conn = dc.dbCheck();
    final String TABLE_NAME = "UserTestTable";
    
    //Method to check if username or password field is empty (after getting input from the fields
    protected boolean isEmpty(String x, String y){
       boolean emp = false;
       if(x.equals("") || y.equals("")){
           emp = true;
       }
       else{
           emp = false;
       }    
       return emp;
    } 
    
    //Method to check user inputs for validity
    protected boolean checker(Connection conn, String user, String pass){
        boolean validUName = false, passCheck = false;
        boolean check = false;
        DBQueries query = new DBQueries();
        
        // Regex check on username
        final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        Pattern pat = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pat.matcher(user);
        if(matcher.matches()){
            validUName = true;
        }
        else{
            validUName = false;
            System.out.println("uname checker fail");
        }
        
        // Checks DB to see if password matches username
        ResultSet rs = query.getRows(conn, "*", TABLE_NAME, "userN = '" + user + "'");
        try {
            if(rs.next() != false) {
                if(rs.getString("userN").equals(user) && rs.getString("passW").equals(pass)){
                    passCheck = true;
                }
                else{
                    passCheck = false;
                    System.out.println("Password does not match username!");
                }
            }else{
                passCheck = false;
                System.out.println("Either username does not exist or is incorrect!");
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
        
        // Checks to see if all above checks are true before proceeding
        if(validUName == true && passCheck == true){
            check = true;
        }
        else{
            check = false;
        }
        
        return check;
    }
    
    // Method to check whether user is tagged as ADMIN or EMPLOYEE in database
    protected boolean isAdmin(Connection conn, String user, String pass){
        DBQueries query = new DBQueries();
        int admStatus = 0;
        boolean isAdmin = false;
        String queryStmt = String.format("userN = '%s' AND passW = '%s'", user, pass);
        ResultSet rs = query.getRows(conn, "admin", TABLE_NAME, queryStmt);
        try {
            while(rs.next()){
                admStatus = rs.getInt("admin");
            }
            
            if(admStatus == 1){
                isAdmin = true;
            } else isAdmin = false;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdmin;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dragPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        dragPanel2 = new javax.swing.JPanel();
        jLabelMini = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        signinLabel = new javax.swing.JLabel();
        unameField = new swing.MyTextField();
        passField = new swing.MyPasswordField();
        regLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        dragPanel.setBackground(new java.awt.Color(32, 157, 90));
        dragPanel.setAutoscrolls(true);
        dragPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragPanelMouseDragged(evt);
            }
        });
        dragPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragPanelMousePressed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLabel.setText("Municipality of Palimbang");
        titleLabel.setAlignmentX(0.5F);
        titleLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        titleLabel.setFocusTraversalPolicyProvider(true);
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        subtitleLabel.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        subtitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        subtitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitleLabel.setText("HR and Admin System");
        subtitleLabel.setAlignmentX(0.5F);
        subtitleLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        subtitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dragPanel2.setBackground(new java.awt.Color(255, 255, 255));
        dragPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dragPanel2.setEnabled(false);
        dragPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragPanel2MouseDragged(evt);
            }
        });
        dragPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragPanel2MousePressed(evt);
            }
        });

        jLabelMini.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMini.setText("-");
        jLabelMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMini.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMiniMouseClicked(evt);
            }
        });

        jLabelClose.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabelClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        signinLabel.setBackground(new java.awt.Color(231, 244, 241));
        signinLabel.setFont(new java.awt.Font("Nirmala UI", 1, 48)); // NOI18N
        signinLabel.setForeground(new java.awt.Color(7, 164, 121));
        signinLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        signinLabel.setText("Sign In");
        signinLabel.setAlignmentX(0.5F);
        signinLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signinLabel.setFocusTraversalPolicyProvider(true);
        signinLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        unameField.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        unameField.setHint("Username");
        unameField.setPrefixIcon(new javax.swing.ImageIcon("resources/username.png"));

        passField.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        passField.setHint("Password");
        passField.setPrefixIcon(new javax.swing.ImageIcon("resources/pass.png"));

        regLabel.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        regLabel.setForeground(new java.awt.Color(7, 164, 121));
        regLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regLabel.setText("No Account? Click here to Sign Up");
        regLabel.setFocusTraversalPolicyProvider(true);
        regLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regLabelMouseClicked(evt);
            }
        });

        loginLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setIcon(new javax.swing.ImageIcon("resources/login.png"));
        loginLabel.setText("Login");
        loginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dragPanel2Layout = new javax.swing.GroupLayout(dragPanel2);
        dragPanel2.setLayout(dragPanel2Layout);
        dragPanel2Layout.setHorizontalGroup(
            dragPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dragPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dragPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelMini)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelClose)
                        .addGap(22, 22, 22))
                    .addGroup(dragPanel2Layout.createSequentialGroup()
                        .addComponent(regLabel)
                        .addGap(0, 234, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dragPanel2Layout.createSequentialGroup()
                        .addGroup(dragPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(unameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dragPanel2Layout.createSequentialGroup()
                                .addComponent(signinLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(dragPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50))))
        );
        dragPanel2Layout.setVerticalGroup(
            dragPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dragPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMini, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(signinLabel)
                .addGap(18, 18, 18)
                .addComponent(unameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(regLabel)
                .addGap(18, 18, 18)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dragPanelLayout = new javax.swing.GroupLayout(dragPanel);
        dragPanel.setLayout(dragPanelLayout);
        dragPanelLayout.setHorizontalGroup(
            dragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanelLayout.createSequentialGroup()
                .addGroup(dragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dragPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel))
                    .addGroup(dragPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(subtitleLabel)))
                .addGap(18, 18, 18)
                .addComponent(dragPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dragPanelLayout.setVerticalGroup(
            dragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragPanelLayout.createSequentialGroup()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitleLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(dragPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dragPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dragPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to exit?",
            "EXIT",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMiniMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMiniMouseClicked

    private void dragPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragPanelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_dragPanelMouseDragged

    private void dragPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragPanelMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_dragPanelMousePressed

    private void dragPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragPanel2MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_dragPanel2MouseDragged

    private void dragPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragPanel2MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX() + 495;
        yMouse = evt.getY();
    }//GEN-LAST:event_dragPanel2MousePressed

    private void regLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regLabelMouseClicked
        try {
            // TODO add your handling code here:
            this.dispose();
            regEmployee re = new regEmployee();
            re.setVisible(true);
            re.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_regLabelMouseClicked

    private void loginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseClicked
        String un, pw;
        un = unameField.getText().toString();
        pw = String.valueOf(passField.getPassword());
        
        if(isEmpty(un , pw)){
            JOptionPane.showMessageDialog(null, "Username and password fields cannot be empty.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(checker(conn, un, pw)){
                System.out.println("Login Success!");
                //If statement to redirect to admin dashboard when adm in DB is set to 1
                // and redirect to employee dashboard when adm in DB is set to 0
                //System.out.println(isAdmin(conn, un, pw));
                if(isAdmin(conn, un, pw)){
                    // proceed to admin dashboard
                    this.dispose();
                    Main_Admin ma = new Main_Admin();
                    ma.setVisible(true);
                    ma.setLocationRelativeTo(null);
                }
                else{
                    // proceed to employee dashboard
                    this.dispose();
                    Main_Employee me = new Main_Employee();
                    me.setVisible(true);
                    me.setLocationRelativeTo(null);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password. Please try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
                unameField.setText("");
                passField.setText("");
            }
        }
    }//GEN-LAST:event_loginLabelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dragPanel;
    private javax.swing.JPanel dragPanel2;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMini;
    private javax.swing.JLabel loginLabel;
    private swing.MyPasswordField passField;
    private javax.swing.JLabel regLabel;
    private javax.swing.JLabel signinLabel;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JLabel titleLabel;
    private swing.MyTextField unameField;
    // End of variables declaration//GEN-END:variables
}
