package ui;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.models.requests.UserRequest;
import core.models.responses.BaseResponse;
import core.models.responses.UserListResponse;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ui.action.TableActionCellEditor;
import ui.action.TableActionCellRender;
import ui.action.TableActionEvent;
import ui.users.EditUserPanel;

public class FormUser extends javax.swing.JFrame {
    
    private List<UserListResponse> usr;
    
    public FormUser() {
        initComponents();
        getUsers();
    }
    
    public void getUsers() {
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = usr.get(row).getId();
                String name = usr.get(row).getName();
                String email = usr.get(row).getEmail();
                int roleId =  usr.get(row).getRoleId() == 1 ? 0 : 1;
                
                JDialog dialog = new JDialog();
                
                EditUserPanel panel = new EditUserPanel(id, dialog);
                panel.textFieldName.setText(name);
                panel.textFieldEmail.setText(email);
                panel.jComboBox1.setSelectedIndex(roleId);
                
                dialog.add(panel);
                dialog.setSize(300, 300);
                dialog.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                int answer = MessageHelper.Confirm("Confirm", "Apakah yakin ingin menghapus data tersebut?");
                
                if (answer == JOptionPane.YES_OPTION) {
                    int id = usr.get(row).getId();
                    
                    try {
                        String token = JdbcHelper.getToken();
                        String response = HttpHelper.delete("users/" + id, token);
                        ObjectMapper mapper = new ObjectMapper()
                            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
                        
                        BaseResponse br = mapper.readValue(response, new TypeReference<BaseResponse>(){});
                        
                        if (br.isSuccess()) {
                            MessageHelper.Success("Success", "Data deleted");
                            getUsers();
                        } else {
                            MessageHelper.Error("Error", br.getMessage());
                        }
                        
                        getUsers();
                    } catch (SQLException ex) {
                         Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (IOException ex) {
                        Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        tableUser.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        tableUser.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));

        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        model.setRowCount(0);
        
        try {
            String token = JdbcHelper.getToken();
            String response = HttpHelper.get("users", token);
            
            ObjectMapper mapper = new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            BaseResponse<List<UserListResponse>> br = mapper.readValue(response, new TypeReference<BaseResponse<List<UserListResponse>>>() {
            });
            
            List<UserListResponse> users = br.getData();
            this.usr = users;
            
            for (UserListResponse user : users) {
                String data[] = {user.getNip(), user.getName(), user.getRoleName()};
                model.addRow(data);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        fieldName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        fieldEmailInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fieldPasswordInput = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIP", "Nama", "Role", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.setRowHeight(50);
        jScrollPane1.setViewportView(tableUser);
        if (tableUser.getColumnModel().getColumnCount() > 0) {
            tableUser.getColumnModel().getColumn(3).setResizable(false);
            tableUser.getColumnModel().getColumn(3).setPreferredWidth(170);
        }

        fieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNameActionPerformed(evt);
            }
        });

        jLabel1.setText("Nama");

        fieldEmailInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmailInputActionPerformed(evt);
            }
        });

        jLabel3.setText("Email");

        btnAddUser.setText("TAMBAH USER");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel4.setText("Password");

        jLabel5.setText("Role");

        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operator", "Manager" }));
        cbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldEmailInput, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(fieldName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(fieldPasswordInput)
                    .addComponent(cbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldEmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNameActionPerformed

    private void fieldEmailInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmailInputActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        String buttonLabel = btnAddUser.getLabel();
        btnAddUser.setEnabled(false);
        btnAddUser.setLabel("Loading...");
        
        ObjectMapper mapper = new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        // 1 -> Operator
        // 3 -> manager
        int roleId = cbRole.getSelectedIndex() == 0 ? 1 : 3;
        
        UserRequest request
                = new UserRequest(
                        fieldName.getText(),
                        fieldEmailInput.getText(),
                        roleId,
                        fieldPasswordInput.getText());
        
        try {
            String body = mapper.writeValueAsString(request);
            String response = HttpHelper.post("users", body, JdbcHelper.getToken());
            BaseResponse br = mapper.readValue(response, BaseResponse.class);
            
            if (br.getCode().equals("0000")) {
                JOptionPane.showMessageDialog(null, br.getMessage());
                getUsers();
            } else {
                JOptionPane.showMessageDialog(null, br.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            btnAddUser.setEnabled(true);
            btnAddUser.setLabel(buttonLabel);
        }
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void cbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRoleActionPerformed

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
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JTextField fieldEmailInput;
    private javax.swing.JTextField fieldName;
    private javax.swing.JPasswordField fieldPasswordInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    // End of variables declaration//GEN-END:variables
}
