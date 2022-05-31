package palimbang.dashboard.main;

import palimbang.dashboard.event.EventMenuSelected;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import main.Main;
import main.TimeInTimeOut;
import palimbang.dashboard.form.Form_Time_Emp;
import palimbang.dashboard.form.Form_Profile_Emp;
import palimbang.dashboard.form.Form_Doc_Emp;

public class Main_Employee extends javax.swing.JFrame {
    
    private boolean TimeIn, TimeOut;
    int userid;
    Connection conn;
    
    public Main_Employee(Connection temp, int ID){
        userid = ID;
        conn = temp;
        initComponents();
        setBackground(new Color(0,0,0,0));
        jFrame1.setLocationRelativeTo(null);
        menu_Employee.initMoving(Main_Employee.this);
        menu_Employee.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    try {
                        setForm(new Form_Profile_Emp(conn, userid));
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 1){
                    try {
                        setForm(new Form_Time_Emp(conn, userid));
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 2){
                    try {
                        setForm(new Form_Doc_Emp(conn, userid));
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 12){
                    int response = JOptionPane.showConfirmDialog(rootPane,
                    "Are you sure you want to log out?",
                    "EXIT",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(response == 0)
                    {
                        dispose();
                        try {
                            conn.close(); //closes connection to avoid DB lock 
                            Main m = new Main();
                            m.setVisible(true);
                            m.setLocationRelativeTo(null);
                        } catch (SQLException ex) {
                            Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        try {
                setForm(new Form_Profile_Emp(conn, userid));
            } catch (SQLException ex) {
                Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panelBorder1 = new palimbang.dashboard.swing.panelBorder();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        menu_Employee = new palimbang.dashboard.component.Menu_Employee();
        timeOut = new javax.swing.JToggleButton();
        timeIn = new javax.swing.JToggleButton();

        jFrame1.setLocation(new java.awt.Point(10, -5));
        jFrame1.setResizable(false);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("MS PGothic", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 122, 116));
        jLabel1.setText("Dashboard");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        timeOut.setBackground(new java.awt.Color(29, 122, 116));
        timeOut.setForeground(new java.awt.Color(255, 255, 255));
        timeOut.setText("Time out");
        timeOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                timeOutMouseReleased(evt);
            }
        });
        timeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOutActionPerformed(evt);
            }
        });

        timeIn.setBackground(new java.awt.Color(29, 122, 116));
        timeIn.setForeground(new java.awt.Color(255, 255, 255));
        timeIn.setText("Time in");
        timeIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                timeInMouseReleased(evt);
            }
        });
        timeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(menu_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeIn)
                            .addComponent(timeOut))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void timeInMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeInMouseReleased
//        if(TimeIn== false) {
//            timeIn.setSelected(false);
//            
//        }
//        else
//            timeIn.setSelected(true);

    }//GEN-LAST:event_timeInMouseReleased

    private void timeOutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeOutMouseReleased
//        if(TimeOut== false) {
//            timeOut.setSelected(false);
//            
//        }
//        else
//            timeOut.setSelected(true);
    }//GEN-LAST:event_timeOutMouseReleased

    private void timeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeInActionPerformed
        int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to time in now?",
            "",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0){
            TimeInTimeOut t = new TimeInTimeOut();  
            t.timeIn(conn, userid);
            timeIn.setSelected(true);
            TimeIn = true;
	}
    }//GEN-LAST:event_timeInActionPerformed

    private void timeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOutActionPerformed
        int response = JOptionPane.showConfirmDialog(rootPane,
            "Are you sure you want to time out now?",
            "",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(response == 0){
            TimeInTimeOut t = new TimeInTimeOut();   
            t.timeOut(conn, userid);
            timeOut.setSelected(true);
            TimeOut = true;
        }
    }//GEN-LAST:event_timeOutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private palimbang.dashboard.component.Menu_Employee menu_Employee;
    private palimbang.dashboard.swing.panelBorder panelBorder1;
    private javax.swing.JToggleButton timeIn;
    private javax.swing.JToggleButton timeOut;
    // End of variables declaration//GEN-END:variables
}
