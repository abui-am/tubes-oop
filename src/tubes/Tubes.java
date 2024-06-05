/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import helpers.JdbcHelper;
import helpers.MessageHelper;
import java.sql.SQLException;
import ui.FormLoginRegister;
import javax.swing.JFrame;
import ui.MainMenu;
/**
 *
 * @author Abui
 */
public class Tubes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String token = "";
//        
//        try {
//            token = JdbcHelper.getToken();
//        } catch (SQLException ex) {
//            MessageHelper.Error("Error", ex.getMessage());
//        }
//        
//        if (token.isBlank() || token.isEmpty()) {
            FormLoginRegister frame = new FormLoginRegister();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
//        } else {
//            MainMenu main = new MainMenu();
//            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            main.setVisible(true);
//        }
    }
    
}
