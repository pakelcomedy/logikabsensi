package login;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.Main;


public class login extends javax.swing.JFrame {

    public login() {
        initComponents();
    txt_password.setText("Password");
    txt_password.setForeground(new Color(153, 153, 153));
    txt_username.setText("Username or Email");
    txt_username.setForeground(new Color(153, 153, 153));
    
     login.requestFocusInWindow();
    }
    
    private void handleKeyPress(java.awt.event.KeyEvent evt) {
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        login.doClick();
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_username.setBackground(new java.awt.Color(237, 245, 224));
        txt_username.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_username.setBorder(null);
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 220, 20));

        txt_password.setBackground(new java.awt.Color(237, 245, 224));
        txt_password.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_password.setBorder(null);
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 220, 20));

        login.setBackground(new java.awt.Color(255, 102, 204));
        login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login/Button Login.png"))); // NOI18N
        login.setBorder(null);
        login.setBorderPainted(false);
        login.setOpaque(true);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 180, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusGained
        if(txt_username.getText().equals("Username or Email")) {
            txt_username.setText("");
            txt_username.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_usernameFocusGained

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        if(txt_username.getText().equals("")) {
            txt_username.setText("Username or Email");
            txt_username.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed

    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusGained
        if(txt_password.getText().equals("Username or Email")) {
            txt_password.setText("");
            txt_password.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txt_passwordFocusGained

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        if(txt_password.getText().equals("")) {
            txt_password.setText("Username or Email");
            txt_password.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed

    }//GEN-LAST:event_txt_passwordActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
    String loginQuery = "SELECT id_user, username FROM user WHERE (username=? OR email=?) AND password=?";
    try (java.sql.Connection connLogin = koneksi.configDB();
         PreparedStatement pstLogin = connLogin.prepareStatement(loginQuery)) {
        
        pstLogin.setString(1, txt_username.getText());
        pstLogin.setString(2, txt_username.getText()); // Use username or email for the second parameter
        pstLogin.setString(3, new String(txt_password.getPassword()));

        try (ResultSet rs = pstLogin.executeQuery()) {
            if (rs.next()) {
                // Login successful
                String username = rs.getString("username");
                String userID = rs.getString("id_user"); // Retrieve id_user from the result set
                
                // Inside loginActionPerformed method after successful login
                Main mainFrame = new Main(username, userID);
                mainFrame.setVisible(true); // Make sure the frame is visible
                dispose(); // Close the login frame
            } else {
                // Login failed, show an error message
                JOptionPane.showMessageDialog(this, "Invalid username/email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while executing the login query", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        // Handle SQL exception related to connection
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_loginActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton login;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
