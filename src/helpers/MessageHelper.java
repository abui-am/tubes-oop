package helpers;

import javax.swing.JOptionPane;

public class MessageHelper {
    public static void Success(String title, String content) {
        JOptionPane.showMessageDialog(null , content, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Error(String title, String content) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int Confirm(String title, String content) {
        return JOptionPane.showConfirmDialog(
                        null, 
                        content,
                        title, 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
    }
}
