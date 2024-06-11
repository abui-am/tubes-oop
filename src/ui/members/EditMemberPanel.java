package ui.members;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.models.requests.MemberUpdateRequest;
import core.models.responses.BaseResponse;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MapperHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JDialog;
import ui.FormLoginRegister;
import ui.FormMember;
import ui.FormUser;

public class EditMemberPanel extends javax.swing.JPanel {
    private int id;
    private JDialog dialog;
    private FormMember form;
    
    public EditMemberPanel(int id, JDialog dialog, FormMember form) {
        initComponents();
        this.id = id;
        this.dialog = dialog;
        this.form = form;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGender = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        textFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldNIK = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textFieldBirthPlace = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        textFieldAddress = new javax.swing.JTextField();

        jLabel1.setText("Nama");

        jLabel2.setText("NIK");

        jLabel3.setText("Alamat");

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Tempat Lahir");

        jLabel6.setText("Gender");

        buttonGroupGender.add(rbLakiLaki);
        rbLakiLaki.setText("Laki - Laki");
        rbLakiLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiLakiActionPerformed(evt);
            }
        });

        buttonGroupGender.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbLakiLaki)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(rbPerempuan))
                            .addComponent(textFieldName)
                            .addComponent(textFieldNIK)
                            .addComponent(textFieldAddress)
                            .addComponent(textFieldBirthPlace, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rbLakiLaki)
                    .addComponent(rbPerempuan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nik = textFieldNIK.getText();
        String name = textFieldName.getText();
        String birthPlace = textFieldBirthPlace.getText();
        String gender = buttonGroupGender.getSelection() == rbLakiLaki.getModel() ? "L" : "P";
        String address = textFieldAddress.getText();
        
        MemberUpdateRequest request = new MemberUpdateRequest(this.id, nik, name, birthPlace, gender, address);
        
        ObjectMapper mapper = MapperHelper.getMapper();
        
         try {
            String body = mapper.writeValueAsString(request);
            String token = JdbcHelper.getToken();
            String response = HttpHelper.put("members", body, token);
            BaseResponse br = mapper.readValue(response, BaseResponse.class);
            
            if (br.isSuccess()) {
                MessageHelper.Success("Success", br.getMessage());
                dialog.setVisible(false);
                form.getMembers();
            } else {
                MessageHelper.Error("Error", br.getMessage());
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rbLakiLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiLakiActionPerformed
        ButtonModel m = this.rbLakiLaki.getModel();
        buttonGroupGender.setSelected(m, true);
    }//GEN-LAST:event_rbLakiLakiActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        ButtonModel m = this.rbPerempuan.getModel();
        buttonGroupGender.setSelected(m, true);
    }//GEN-LAST:event_rbPerempuanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup buttonGroupGender;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JRadioButton rbLakiLaki;
    public javax.swing.JRadioButton rbPerempuan;
    public javax.swing.JTextField textFieldAddress;
    public javax.swing.JTextField textFieldBirthPlace;
    public javax.swing.JTextField textFieldNIK;
    public javax.swing.JTextField textFieldName;
    // End of variables declaration//GEN-END:variables
}
