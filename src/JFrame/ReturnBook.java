package JFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

public class ReturnBook extends javax.swing.JFrame {

 
    public ReturnBook() {
        initComponents();
    }
    
    //To fetch the issue book details from the database and display it to panel
    public void getIssueBookDetails(){
        
        int bookId = Integer.parseInt(txt_BookId.getText());
        int studentId = Integer.parseInt(txt_StudentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1 , bookId);
            pst.setInt(2 , studentId);
            pst.setString(3 , "pending");
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                lbl_IssueId.setText(rs.getString("id"));
                lbl_BookName.setText(rs.getString("book_name"));
                lbl_StudentName.setText(rs.getString("student_name"));
                lbl_IssueDate.setText(rs.getString("issue_date"));
                lbl_DueDate.setText(rs.getString("due_date"));
                lbl_BookError.setText(rs.getString(""));
            }
            else{    
                lbl_BookError.setText("No Record Found!");
                
                lbl_IssueId.setText(rs.getString(""));
                lbl_BookName.setText(rs.getString(""));
                lbl_StudentName.setText(rs.getString(""));
                lbl_IssueDate.setText(rs.getString(""));
                lbl_DueDate.setText(rs.getString(""));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    //Return the Book
    public boolean returnBook(){
        boolean isReturned = false;
        
        int bookId = Integer.parseInt(txt_BookId.getText());
        int studentId = Integer.parseInt(txt_StudentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update issue_book_details set status = ? where student_id = ? and book_id = ? and status = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1 , "returned");
            pst.setInt(2 , studentId);
            pst.setInt(3 , bookId);
            pst.setString(4 , "pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isReturned = true;
            }
            else{
                isReturned = false;
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
        }       
        
        return isReturned;
    }

    //updating book details
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_BookId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity + 1 where book_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book Count Updated!");     
            } 
            else{
                JOptionPane.showMessageDialog(this, "Can't Update Book Count!");
            }    
            
        }
        catch(Exception e   ){
            e.printStackTrace();
        }       
    }
    

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Back = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_BookName = new javax.swing.JLabel();
        lbl_IssueId = new javax.swing.JLabel();
        lbl_StudentName = new javax.swing.JLabel();
        lbl_DueDate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_BookError = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_IssueDate = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        close1 = new javax.swing.JLabel();
        txt_BookId = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_StudentId = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        Add_Btn = new rojerusan.RSMaterialButtonRectangle();
        Add_Btn1 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_main.setBackground(new java.awt.Color(255, 255, 255));
        Panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        Back.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        Back.setText("Back");
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Back, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Back, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("  Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 380, 5));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Book Name : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Issue ID : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        lbl_BookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_BookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 220, 30));

        lbl_IssueId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_IssueId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_IssueId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 220, 30));

        lbl_StudentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_StudentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, 220, 30));

        lbl_DueDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_DueDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 770, 220, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Due Date :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 770, -1, -1));

        lbl_BookError.setBackground(new java.awt.Color(255, 255, 255));
        lbl_BookError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_BookError.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.add(lbl_BookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 850, 350, 30));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Issue Date :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, -1, -1));

        lbl_IssueDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_IssueDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 690, 220, 30));

        Panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 1050));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel11.setText("  Return Book");
        Panel_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 270, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Panel_main.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 290, -1, 5));

        close1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        close1.setForeground(new java.awt.Color(102, 102, 255));
        close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close1.setText("X");
        close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close1MouseClicked(evt);
            }
        });
        Panel_main.add(close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 10, 40, -1));

        txt_BookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_BookId.setForeground(new java.awt.Color(51, 51, 51));
        txt_BookId.setDoubleBuffered(true);
        txt_BookId.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_BookId.setPlaceholder("Enter Book ID...");
        txt_BookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_BookIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BookIdFocusLost(evt);
            }
        });
        txt_BookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BookIdActionPerformed(evt);
            }
        });
        Panel_main.add(txt_BookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 410, 370, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Book ID :");
        Panel_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 430, 120, -1));

        txt_StudentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_StudentId.setForeground(new java.awt.Color(51, 51, 51));
        txt_StudentId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_StudentId.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_StudentId.setPlaceholder("Enter Student ID...");
        txt_StudentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_StudentIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_StudentIdFocusLost(evt);
            }
        });
        txt_StudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_StudentIdActionPerformed(evt);
            }
        });
        Panel_main.add(txt_StudentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 510, 370, 50));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Student ID :");
        Panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 530, 120, -1));

        Add_Btn.setBackground(new java.awt.Color(255, 51, 51));
        Add_Btn.setText("RETURN BOOK");
        Add_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_BtnMouseClicked(evt);
            }
        });
        Add_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_BtnActionPerformed(evt);
            }
        });
        Panel_main.add(Add_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 720, 230, 60));

        Add_Btn1.setBackground(new java.awt.Color(102, 102, 255));
        Add_Btn1.setText("FIND");
        Add_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Add_Btn1MouseClicked(evt);
            }
        });
        Add_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Btn1ActionPerformed(evt);
            }
        });
        Panel_main.add(Add_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 640, 230, 60));

        getContentPane().add(Panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 1050));

        setSize(new java.awt.Dimension(1680, 1050));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Add_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BtnActionPerformed
        if(returnBook() == true){
            JOptionPane.showMessageDialog(this, "Book Return Successfully!");
            updateBookCount();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Returned Failed!");
        }
    }//GEN-LAST:event_Add_BtnActionPerformed

    private void Add_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_BtnMouseClicked

    private void txt_StudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_StudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIdActionPerformed

    private void txt_StudentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIdFocusLost
        
    }//GEN-LAST:event_txt_StudentIdFocusLost

    private void txt_StudentIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIdFocusGained

    private void txt_BookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIdActionPerformed

    private void txt_BookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIdFocusLost
        
    }//GEN-LAST:event_txt_BookIdFocusLost

    private void txt_BookIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIdFocusGained

    private void close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_close1MouseClicked

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        dispose();
    }//GEN-LAST:event_BackMouseClicked

    private void Add_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_Btn1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_Btn1MouseClicked

    private void Add_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Btn1ActionPerformed
        getIssueBookDetails();
    }//GEN-LAST:event_Add_Btn1ActionPerformed

       
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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Add_Btn;
    private rojerusan.RSMaterialButtonRectangle Add_Btn1;
    private javax.swing.JLabel Back;
    private javax.swing.JPanel Panel_main;
    private javax.swing.JLabel close1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_BookError;
    private javax.swing.JLabel lbl_BookName;
    private javax.swing.JLabel lbl_DueDate;
    private javax.swing.JLabel lbl_IssueDate;
    private javax.swing.JLabel lbl_IssueId;
    private javax.swing.JLabel lbl_StudentName;
    private app.bolivia.swing.JCTextField txt_BookId;
    private app.bolivia.swing.JCTextField txt_StudentId;
    // End of variables declaration//GEN-END:variables
}
