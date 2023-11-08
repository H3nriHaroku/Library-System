package JFrame;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManageStudents extends javax.swing.JFrame {

    String studentName, course, branch;
    int studentId;
    DefaultTableModel model;
    
    public ManageStudents() {
        initComponents();
        setStudentDetailsToTable(); 
    }
 
    //to set the student details into table
    public void setStudentDetailsToTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next()){
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String course = rs.getString("course");
                String branch = rs.getString("branch");
                
                Object[] obj = {studentId, studentName, course, branch};
                model = (DefaultTableModel) tbl_studentDetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    // Add book to student_details database table
    public boolean addStudent(){
        boolean isAdded = false;
        
        studentId = Integer.parseInt(txt_StudentID.getText());
        studentName = txt_StudentName.getText();
        course = combo_Course.getSelectedItem().toString();
        branch = combo_Branch.getSelectedItem().toString();
        
        try{
            Connection con = DBConnection.getConnection();
            
            String sql = "insert into student_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, studentId);
            pst.setString(2, studentName);
            pst.setString(3, course);
            pst.setString(4, branch);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isAdded = true;
            }
            else{
                isAdded = false;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isAdded;
            
    }
    
    
    //Method to clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);
    }
    
    
    //Update book details
    public boolean updateStudent(){
        boolean isUpdated = false;
        
        studentId = Integer.parseInt(txt_StudentID.getText());
        studentName = txt_StudentName.getText();
        course = combo_Course.getSelectedItem().toString();
        branch = combo_Branch.getSelectedItem().toString();
        
        try{
            Connection con = DBConnection.getConnection();

            String sql = "update student_details set student_name = ?, course = ?, branch = ? where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, studentName);
            pst.setString(2, course);
            pst.setString(3, branch);
            pst.setInt(4, studentId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isUpdated = true;
            }
            else{
                isUpdated = false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    
    //Method to delete book details
    public boolean deleteStudent(){
        boolean isDeleted = false;
        
        studentId = Integer.parseInt(txt_StudentID.getText());
        
        try{
            Connection con = DBConnection.getConnection();

            String sql = "delete from student_details where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, studentId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isDeleted = true;
            }
            else{
                isDeleted = false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isDeleted;
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Back = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_StudentID = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_StudentName = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Delete_Btn = new rojerusan.RSMaterialButtonRectangle();
        Add_Btn = new rojerusan.RSMaterialButtonRectangle();
        Update_Btn = new rojerusan.RSMaterialButtonRectangle();
        combo_Branch = new javax.swing.JComboBox<>();
        combo_Course = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        close1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        Back.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        Back.setText("Back");
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Back, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Back, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Student ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 130, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 50, 50));

        txt_StudentID.setBackground(new java.awt.Color(102, 102, 255));
        txt_StudentID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_StudentID.setForeground(new java.awt.Color(255, 255, 255));
        txt_StudentID.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_StudentID.setPlaceholder("Enter Student ID...");
        txt_StudentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_StudentIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_StudentIDFocusLost(evt);
            }
        });
        txt_StudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_StudentIDActionPerformed(evt);
            }
        });
        jPanel1.add(txt_StudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 270, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Student Name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 130, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 50, 50));

        txt_StudentName.setBackground(new java.awt.Color(102, 102, 255));
        txt_StudentName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_StudentName.setForeground(new java.awt.Color(255, 255, 255));
        txt_StudentName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_StudentName.setPlaceholder("Enter Student Name...");
        txt_StudentName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_StudentNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_StudentNameFocusLost(evt);
            }
        });
        txt_StudentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_StudentNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 270, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Course");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 130, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel5.setText("Username");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 50, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Branch");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 130, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jLabel6.setText("Username");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 50, 50));

        Delete_Btn.setBackground(new java.awt.Color(255, 51, 51));
        Delete_Btn.setText("DELETE");
        Delete_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Delete_BtnMouseClicked(evt);
            }
        });
        Delete_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(Delete_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 770, 130, 60));

        Add_Btn.setBackground(new java.awt.Color(255, 51, 51));
        Add_Btn.setText("ADD");
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
        jPanel1.add(Add_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 770, 130, 60));

        Update_Btn.setBackground(new java.awt.Color(255, 51, 51));
        Update_Btn.setText("UPDATE");
        Update_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Update_BtnMouseClicked(evt);
            }
        });
        Update_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(Update_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 770, 130, 60));

        combo_Branch.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        combo_Branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT", "CS", "PLAIN", "ELECTRONIC" }));
        jPanel1.add(combo_Branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, 270, 30));

        combo_Course.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        combo_Course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSC", "MSC", "PHD" }));
        jPanel1.add(combo_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 270, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 1050));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        close1.setForeground(new java.awt.Color(102, 102, 255));
        close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close1.setText("X");
        close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close1MouseClicked(evt);
            }
        });
        jPanel3.add(close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 40, -1));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Course", "Branch"
            }
        ));
        tbl_studentDetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_studentDetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_studentDetails.setColorBordeHead(new java.awt.Color(102, 102, 255));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_studentDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_studentDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_studentDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_studentDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_studentDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_studentDetails.setRowHeight(44);
        tbl_studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_studentDetails);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 1010, 750));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel1.setText(" Manage Students");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 410, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 1070, 1050));

        setSize(new java.awt.Dimension(1680, 1050));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        dispose();
    }//GEN-LAST:event_BackMouseClicked

    private void txt_StudentIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIDFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIDFocusGained

    private void txt_StudentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIDFocusLost

    }//GEN-LAST:event_txt_StudentIDFocusLost

    private void txt_StudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_StudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIDActionPerformed

    private void txt_StudentNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentNameFocusGained

    private void txt_StudentNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentNameFocusLost

    }//GEN-LAST:event_txt_StudentNameFocusLost

    private void txt_StudentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_StudentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentNameActionPerformed

    private void Delete_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_BtnMouseClicked

    }//GEN-LAST:event_Delete_BtnMouseClicked

    private void Delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_BtnActionPerformed
        if(deleteStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Delted!");
            clearTable(); 
            setStudentDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Student Deletion Failed!");
        }
    }//GEN-LAST:event_Delete_BtnActionPerformed

    private void Add_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_BtnMouseClicked

    private void Add_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BtnActionPerformed
        
        if(addStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Added!");
            clearTable(); 
            setStudentDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Student Addition Failed!");
        }
    }//GEN-LAST:event_Add_BtnActionPerformed

    private void Update_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Update_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Update_BtnMouseClicked

    private void Update_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_BtnActionPerformed
        if(updateStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Updated!");
            clearTable(); 
            setStudentDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Student Updation  Failed!");
        }
    }//GEN-LAST:event_Update_BtnActionPerformed

    private void close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_close1MouseClicked

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        int rowNo = tbl_studentDetails.getSelectedRow();
        TableModel model = tbl_studentDetails.getModel();
        
        txt_StudentID.setText(model.getValueAt(rowNo, 0).toString());
        txt_StudentName.setText(model.getValueAt(rowNo, 1).toString());
        combo_Course.setSelectedItem(model.getValueAt(rowNo, 2).toString());
        combo_Branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());
                
    }//GEN-LAST:event_tbl_studentDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Add_Btn;
    private javax.swing.JLabel Back;
    private rojerusan.RSMaterialButtonRectangle Delete_Btn;
    private rojerusan.RSMaterialButtonRectangle Update_Btn;
    private javax.swing.JLabel close1;
    private javax.swing.JComboBox<String> combo_Branch;
    private javax.swing.JComboBox<String> combo_Course;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    private app.bolivia.swing.JCTextField txt_StudentID;
    private app.bolivia.swing.JCTextField txt_StudentName;
    // End of variables declaration//GEN-END:variables
}
