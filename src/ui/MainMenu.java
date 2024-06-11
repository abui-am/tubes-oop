package ui;

import helpers.JdbcHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class MainMenu extends javax.swing.JFrame {
    
    public MainMenu() {
        initComponents();
        int roleId = -1;
        
        try {
            roleId = JdbcHelper.getRoleId();
            
            if (roleId == 1) {
               bUser.setEnabled(false);
               bDashboard.setEnabled(false);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bUser = new javax.swing.JButton();
        bProduct = new javax.swing.JButton();
        bMember = new javax.swing.JButton();
        bDashboard = new javax.swing.JButton();
        bDashboard1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bUser.setText("User");
        bUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUserActionPerformed(evt);
            }
        });

        bProduct.setText("Product");
        bProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProductActionPerformed(evt);
            }
        });

        bMember.setText("Member");
        bMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMemberActionPerformed(evt);
            }
        });

        bDashboard.setText("Dashboard");
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });

        bDashboard1.setText("Transaksi");
        bDashboard1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboard1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bUser, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bMember, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bDashboard1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bUser, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bMember, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(bDashboard1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUserActionPerformed
        FormUser formUser = new FormUser();
        formUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formUser.setVisible(true);
        
        super.setVisible(false);
        
    }//GEN-LAST:event_bUserActionPerformed

    private void bProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProductActionPerformed
        FormProduct formProduct = new FormProduct();
        formProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formProduct.setVisible(true);
        
        super.setVisible(false);
    }//GEN-LAST:event_bProductActionPerformed

    private void bMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMemberActionPerformed
        FormMember formMember = new FormMember();
        formMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formMember.setVisible(true);
        
        super.setVisible(false);
    }//GEN-LAST:event_bMemberActionPerformed

    private void bDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboardActionPerformed
        // TODO add your handling code here:
        
        Dashboard d = new Dashboard();
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setVisible(true);
        
        super.setVisible(false);
    }//GEN-LAST:event_bDashboardActionPerformed

    private void bDashboard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboard1ActionPerformed
        FormTransaction ft = new FormTransaction();
        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ft.setVisible(true);
        
        super.setVisible(false);
    }//GEN-LAST:event_bDashboard1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bDashboard1;
    private javax.swing.JButton bMember;
    private javax.swing.JButton bProduct;
    private javax.swing.JButton bUser;
    // End of variables declaration//GEN-END:variables
}
