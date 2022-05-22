package palimbang.dashboard.main;

import palimbang.dashboard.event.EventMenuSelected;
import palimbang.dashboard.form.Form_Home;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import main.Main;
import palimbang.dashboard.form.Form_Profile_Adm;
import palimbang.dashboard.form.Form_Time_Adm;

public class Main_Admin extends javax.swing.JFrame {
    
    int userid;
    Connection conn;
    
    public Main_Admin(Connection temp, int ID) {
        userid = ID;
        conn = temp;
        initComponents();
        setBackground(new Color(0,0,0,0));
        jFrame1.setLocationRelativeTo(null);
        menu_Admin.initMoving(Main_Admin.this);
        menu_Admin.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    try {
                        setForm(new Form_Profile_Adm(conn, userid));
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 1){
                    try {
                        setForm(new Form_Time_Adm(conn, userid));
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_Employee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (index == 2){
                    setForm(new Form_Home());
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
            setForm(new Form_Profile_Adm(conn, userid));
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
        menu_Admin = new palimbang.dashboard.component.Menu_Admin();

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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private palimbang.dashboard.component.Menu_Admin menu_Admin;
    private palimbang.dashboard.swing.panelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
