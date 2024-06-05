package ui.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.models.requests.UserUpdateRequest;
import core.models.responses.BaseResponse;
import core.models.responses.UserListResponse;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MapperHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ui.FormLoginRegister;
import ui.FormUser;
import ui.action.TableActionCellEditor;
import ui.action.TableActionCellRender;
import ui.action.TableActionEvent;

public class EditUserPanel extends javax.swing.JPanel {
    private int id;
    private JDialog dialog;
    private JTable tableUser;
    private TableActionEvent event;
    
    public EditUserPanel(int id, JDialog dialog, JTable tableUser, TableActionEvent event) {
        initComponents();
        
        this.id = id;
        this.dialog = dialog;
        this.tableUser = tableUser;
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Nama");

        jLabel2.setText("Email");

        jLabel3.setText("Password");

        jLabel4.setText("Role");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Operator" }));

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldPassword)
                            .addComponent(textFieldName)
                            .addComponent(textFieldEmail)
                            .addComponent(jComboBox1, 0, 194, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = textFieldName.getText();
        String email = textFieldEmail.getText();
        String password = textFieldPassword.getText();
        // 1 -> Operator
        // 3 -> manager
        int roleId = jComboBox1.getSelectedIndex() == 0 ? 1 : 3;
        
        UserUpdateRequest request;
        
        if (password.isBlank() || password.isEmpty()) {
            request = new UserUpdateRequest(this.id, name, email, roleId);
        } else {
            request = new UserUpdateRequest(this.id, name, email, roleId, password);
        }
        
        ObjectMapper mapper = MapperHelper.getMapper();
        
         try {
            String body = mapper.writeValueAsString(request);
            String token = JdbcHelper.getToken();
            String response = HttpHelper.put("users", body, token);
            BaseResponse br = mapper.readValue(response, BaseResponse.class);
            
            if (br.isSuccess()) {
                MessageHelper.Success("Success", br.getMessage());
                dialog.setVisible(false);
                
                tableUser.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
                tableUser.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));

                DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
                model.setRowCount(0);

                try {
                    response = HttpHelper.get("users", token);

                    BaseResponse<List<UserListResponse>> br1 = mapper.readValue(response, new TypeReference<BaseResponse<List<UserListResponse>>>() {});

                    List<UserListResponse> users = br1.getData();

                    for (UserListResponse user : users) {
                        String data[] = {user.getNip(), user.getName(), user.getRoleName()};
                        model.addRow(data);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageHelper.Error("Error", br.getMessage());
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JTextField textFieldEmail;
    public javax.swing.JTextField textFieldName;
    private javax.swing.JPasswordField textFieldPassword;
    // End of variables declaration//GEN-END:variables
}
