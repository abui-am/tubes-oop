package ui;

import javax.swing.JFrame;

public class MainMenu extends javax.swing.JFrame {
    
    public MainMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bUser = new javax.swing.JButton();
        bProduct = new javax.swing.JButton();
        bMember = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bMember, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bUser, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(bProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bUser, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(bMember, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUserActionPerformed
        FormUser formUser = new FormUser();
        formUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formUser.setVisible(true);
        
    }//GEN-LAST:event_bUserActionPerformed

    private void bProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProductActionPerformed
        FormProduct formProduct = new FormProduct();
        formProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formProduct.setVisible(true);
    }//GEN-LAST:event_bProductActionPerformed

    private void bMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMemberActionPerformed
        FormMember formMember = new FormMember();
        formMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formMember.setVisible(true);
    }//GEN-LAST:event_bMemberActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bMember;
    private javax.swing.JButton bProduct;
    private javax.swing.JButton bUser;
    // End of variables declaration//GEN-END:variables
}
