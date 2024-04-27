/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class JdbcHelper {
    static final String DB_URL = "jdbc:mysql://localhost/kasir_client";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    
    private static Connection getConnection() {
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static String getToken() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM `logged_in`");
        
        String token = "";
        
        while (result.next()) {
            token = result.getString("token");
        }
        
        connection.close();
        
        return token;
    }
    
    public static int insertToken(int userId, String token) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement(
                            "INSERT INTO logged_in (user_id, token)\n" +
                            "VALUES (?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE token = VALUES(token);");
            
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, token);
            
            int result = preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
