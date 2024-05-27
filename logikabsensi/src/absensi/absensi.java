package absensi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import koneksi.koneksi;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import main.Main;

public class absensi extends javax.swing.JFrame {
    private Timer timer;
    private Timer t;
    private boolean maxTimeSet = false;
    private Connection conn; // java.sql.Connection;
    public String userID;
    
    public absensi() {
        initComponents();
        setTimeFromDatabase();
        dt();
        times();
        setupTimer();
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String currentTime = timeFormat.format(currentDate);
        this.userID = userID; 

//if (currentTime.compareTo("05:00") >= 0 && currentTime.compareTo("12:00") <= 0) {
//    // Enable the timein button and disable the timeout button
//    timein.setEnabled(true);
//    timeout.setEnabled(false);
//} else if (currentTime.compareTo("12:01") >= 0 && currentTime.compareTo("23:59") <= 0) {
//    // Enable the timeout button and disable the timein button
//    timein.setEnabled(false);
//    timeout.setEnabled(true);
//} else {
//    // Disable both buttons
//    timein.setEnabled(false);
//    timeout.setEnabled(false);
//}

    }
        private void setupTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
    }
    
        private void updateClock() {
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = timeFormat.format(currentDate);
        lbl_time.setText(formattedTime);
    }
        
        public void times() {
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
                String tt = st.format(dt);
                lbl_time.setText(tt);
            }
        });
        t.start();
    }
        
        public void dt() {
        Date currentDate = new Date();
        Locale indonesianLocale = new Locale("id", "ID");

        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE dd MMMM yyyy", indonesianLocale);
        String formattedDate = sdfDate.format(currentDate);
        lbl_date.setText(formattedDate);
    }
        
private void setTimeFromDatabase() {
    // Get the current date
    LocalDate currentDate = LocalDate.now();
    // Format the current date
    String formattedCurrentDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    try (Connection conn = koneksi.configDB();
         PreparedStatement pstmt = conn.prepareStatement("SELECT time_in, time_out FROM absensi WHERE DATE(time_in) = ? ORDER BY id_absensi DESC LIMIT 1")) {
         
        // Set the current date as a parameter for the query
        pstmt.setString(1, formattedCurrentDate);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Get the time_in and time_out values from the ResultSet as strings
                String timeInString = rs.getString("time_in");
                String timeOutString = rs.getString("time_out");

                // Check if time_in and time_out are not null before parsing
                LocalTime timeIn = null;
                if (timeInString != null && !timeInString.isEmpty()) {
                    timeIn = LocalTime.parse(timeInString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                }
                LocalTime timeOut = null;
                if (timeOutString != null && !timeOutString.isEmpty()) {
                    timeOut = LocalTime.parse(timeOutString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                }

                // Format the time portions as strings or placeholders
                String formattedTimeIn = (timeIn != null) ? timeIn.format(DateTimeFormatter.ofPattern("HH:mm")) : "N/A";
                String formattedTimeOut = (timeOut != null) ? timeOut.format(DateTimeFormatter.ofPattern("HH:mm")) : "N/A";

                // Set the retrieved time values in the corresponding text fields
                time1.setText(formattedTimeIn);
                time2.setText(formattedTimeOut);
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
private String generateUserId(Connection conn) throws SQLException {
    String prefix = "KAP"; 
    int number = 1; 
    String idAbsen = ""; 

    boolean idFound = false;
    while (!idFound) {
        String userIdToCheck = prefix + number;
        String query = "SELECT COUNT(*) FROM absensi WHERE id_absensi = ?";
        PreparedStatement checkStmt = conn.prepareStatement(query);
        checkStmt.setString(1, userIdToCheck);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            if (count == 0) {
                idAbsen = userIdToCheck;
                idFound = true;
            } else {
                number++; // Jika ID sudah digunakan, tambahkan nomor
            }
        }

        rs.close();
        checkStmt.close();
    }

    return idAbsen;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser2 = new com.raven.datechooser.DateChooser();
        time2 = new javax.swing.JLabel();
        time1 = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        timein = new javax.swing.JButton();
        timeout = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(dateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 840, -1));

        time2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        time2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time2.setText("-");
        getContentPane().add(time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 220, 40));

        time1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        time1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time1.setText("-");
        getContentPane().add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 220, 40));

        lbl_time.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_time.setText("-");
        getContentPane().add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 220, 30));

        lbl_date.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_date.setText("-");
        getContentPane().add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 220, 40));

        timein.setBorder(null);
        timein.setContentAreaFilled(false);
        timein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeinActionPerformed(evt);
            }
        });
        getContentPane().add(timein, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 270, 40));

        timeout.setBorder(null);
        timeout.setContentAreaFilled(false);
        timeout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeoutActionPerformed(evt);
            }
        });
        getContentPane().add(timeout, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 270, 40));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/absensi/time.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timeinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeinActionPerformed
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = now.format(dateFormatter);
    
    boolean recordExists = checkRecordExists(formattedDate);
            
    if (!recordExists) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(dateTimeFormatter);
        
        try (java.sql.Connection conn = koneksi.configDB(); 
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO absensi (status_absensi, id_absensi, id_user, time_in) VALUES (?, ?, ?, ?)")) {
            // Set values for status_absensi, id_user, and time_in columns
            String idAbsen = generateUserId(conn);
            pstmt.setString(1, getTimeStatus(formattedDateTime.substring(11, 16))); // Set status based on time_in value (hours and minutes only)
            pstmt.setString(2, idAbsen);
            pstmt.setString(3, userID); // Replace someUserId with the actual user ID
            pstmt.setString(4, formattedDateTime); // Insert the current date and time
            
            // Execute the INSERT query
            pstmt.executeUpdate();
            setTimeFromDatabase();
            
            // Display a message indicating successful insertion
            JOptionPane.showMessageDialog(this, "Time inserted into database successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        // Display a message indicating that a record already exists for the current date
        JOptionPane.showMessageDialog(this, "A record for today already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
}

// Method to check if a record for the specified date already exists in the database
private boolean checkRecordExists(String date) {
    boolean recordExists = false;
    try (java.sql.Connection conn = koneksi.configDB();
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM absensi WHERE DATE(time_in) = ?")) {
        pstmt.setString(1, date);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    recordExists = true;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return recordExists;
}
    
private String getTimeStatus(String time) {
    String[] parts = time.split(":");
    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1]);

    if (hours > 7 || (hours == 7 && minutes > 0)) {
        return "Terlambat";
    } else {
        return "Tepat Waktu"; // You can set another status here if needed
    }
    }//GEN-LAST:event_timeinActionPerformed

    private void timeoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeoutActionPerformed
    LocalDateTime now = LocalDateTime.now();
    
    // Format date
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = now.format(dateFormatter);
    
    // Check if a timeout has already been recorded for the current date and user
    boolean timeoutRecorded = checkTimeoutRecorded(formattedDate, userID);

    
    if (!timeoutRecorded) {
        // If no timeout has been recorded for the current date and user, proceed with update
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(dateTimeFormatter);
        
        try (java.sql.Connection conn = koneksi.configDB(); 
             PreparedStatement pstmt = conn.prepareStatement("UPDATE absensi SET time_out = ? WHERE DATE(time_in) = ? AND id_user = ?")) {
            
            pstmt.setString(1, formattedDateTime); // Insert the current date and time
            pstmt.setString(2, formattedDate); // Update the row with the current date
            pstmt.setString(3, userID); // Update the row for the current user
            
            // Execute the UPDATE query
            int rowsAffected = pstmt.executeUpdate();
            setTimeFromDatabase();
            
            if (rowsAffected > 0) {
                // Display a message indicating successful update
                JOptionPane.showMessageDialog(this, "Time out updated in database successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display a message indicating failure to update
                JOptionPane.showMessageDialog(this, "Failed to update time out.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        // Display a message indicating that a timeout has already been recorded for the current date and user
        JOptionPane.showMessageDialog(this, "A timeout has already been recorded for today.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
}

// Method to check if a timeout has already been recorded for the specified date and user in the database
private boolean checkTimeoutRecorded(String date, String userID) {
    boolean timeoutRecorded = false;
    try (java.sql.Connection conn = koneksi.configDB();
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM absensi WHERE DATE(time_out) = ? AND id_user = ? AND time_in IS NOT NULL")) {
        pstmt.setString(1, date);
        pstmt.setString(2, userID);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    timeoutRecorded = true;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return timeoutRecorded;
}


private String getLastInsertedId() throws SQLException {
    String lastInsertedId = null;

    try (Connection conn = koneksi.configDB();
         PreparedStatement pstmt = conn.prepareStatement("SELECT id_absensi FROM absensi ORDER BY id_absensi DESC LIMIT 1")) {
        
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            lastInsertedId = rs.getString("id_absensi"); // Retrieve the value from the result set
        }
    }

    return lastInsertedId;
    }//GEN-LAST:event_timeoutActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new absensi().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel time1;
    private javax.swing.JLabel time2;
    private javax.swing.JButton timein;
    private javax.swing.JButton timeout;
    // End of variables declaration//GEN-END:variables
}