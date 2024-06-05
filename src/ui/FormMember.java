package ui;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.models.requests.MemberRequest;
import core.models.responses.BaseResponse;
import core.models.responses.MemberListResponse;
import helpers.HttpHelper;
import helpers.JdbcHelper;
import helpers.MapperHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ui.action.TableActionCellEditor;
import ui.action.TableActionCellRender;
import ui.action.TableActionEvent;

public class FormMember extends javax.swing.JFrame {
    
    private List<MemberListResponse> members;
    
    public FormMember() {
        initComponents();
        getMembers();
    }
    
    private void getMembers () {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                
            }

            @Override
            public void onDelete(int row) {
                int answer = MessageHelper.Confirm("Confirm", "Apakah yakin ingin menghapus data tersebut?");
                
                if (answer == JOptionPane.YES_OPTION) {
                    int id = members.get(row).getId();
                    
                    try {
                        String token = JdbcHelper.getToken();
                        String response = HttpHelper.delete("members/" + id, token);
                        ObjectMapper mapper = MapperHelper.getMapper();
                        
                        BaseResponse br = mapper.readValue(response, BaseResponse.class);
                        
                        if (br.isSuccess()) {
                            MessageHelper.Success("Success", "Data deleted");
                            getMembers();
                        } else {
                            MessageHelper.Error("Error", br.getMessage());
                        }
                        
                        getMembers();
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
        
        tableUser.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        tableUser.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
        
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        model.setRowCount(0);
        
        try {
            String token = JdbcHelper.getToken();
            String response = HttpHelper.get("members", token);
            
            ObjectMapper mapper = MapperHelper.getMapper();
            BaseResponse<List<MemberListResponse>> br = mapper.readValue(response, new TypeReference<BaseResponse<List<MemberListResponse>>>() {});
            
            List<MemberListResponse> members = br.getData();
            
            for (MemberListResponse user : members) {
                String data[] = {user.getMemberNumber(), user.getJoinDate(), String.valueOf(user.getPoint()),user.getNik(), user.getName(), user.getBirthPlace(), user.getGender(), user.getAddress()};
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        tfNIK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfBirthplace = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfGender = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        btnAddMember = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Member", "Tanggal Join", "Poin", "NIK", "Nama", "Tempat Lahir", "Gender", "Alamat", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            tableUser.getColumnModel().getColumn(2).setPreferredWidth(20);
            tableUser.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jLabel1.setText("NIK");

        jLabel3.setText("Nama");

        jLabel4.setText("Tempat Lahir");

        jLabel5.setText("Gender");

        jLabel6.setText("Alamat");

        btnAddMember.setText("TAMBAH MEMBER");
        btnAddMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(tfName)
                        .addComponent(jLabel4)
                        .addComponent(tfBirthplace)
                        .addComponent(jLabel5)
                        .addComponent(tfGender)
                        .addComponent(jLabel6)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBirthplace, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMemberActionPerformed
        // TODO add your handling code here:
        String buttonLabel = btnAddMember.getLabel();
        btnAddMember.setEnabled(false);
        btnAddMember.setLabel("Loading...");

        ObjectMapper mapper = new ObjectMapper()
        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);


        MemberRequest request
        = new MemberRequest(
            tfNIK.getText(),
            tfName.getText(),
            tfBirthplace.getText(),
            tfGender.getText(),
            tfAddress.getText());

        try {
            String body = mapper.writeValueAsString(request);
            String response = HttpHelper.post("members", body, JdbcHelper.getToken());
            BaseResponse br = mapper.readValue(response, BaseResponse.class);

            if (br.getCode().equals("0000")) {
                JOptionPane.showMessageDialog(null, br.getMessage());
                getMembers();
            } else {
                JOptionPane.showMessageDialog(null, br.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FormLoginRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            btnAddMember.setEnabled(true);
            btnAddMember.setLabel(buttonLabel);
        }
    }//GEN-LAST:event_btnAddMemberActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfBirthplace;
    private javax.swing.JTextField tfGender;
    private javax.swing.JTextField tfNIK;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
