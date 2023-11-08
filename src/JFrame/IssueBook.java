package JFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

public class IssueBook extends javax.swing.JFrame {

 
    public IssueBook() {
        initComponents();
    }
    
    //To fetch the book details from the database and display it to book details panel
    public void getBookDetails(){
        int BookId = Integer.parseInt(txt_BookId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, BookId);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_BookId.setText(rs.getString("book_id"));
                lbl_BookName.setText(rs.getString("book_name"));
                lbl_Author.setText(rs.getString("author"));
                lbl_Quantity.setText(rs.getString("quantity"));
            }else{
                lbl_BookError.setText("Invalid Book ID!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //To fetch the student details from the database and display it to book details panel
    public void getStudentDetails(){
        int StudentId = Integer.parseInt(txt_StudentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setInt(1, StudentId);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_StudentId.setText(rs.getString("student_id"));
                lbl_StudentName.setText(rs.getString("student_name"));
                lbl_Course.setText(rs.getString("course"));
                lbl_Branch.setText(rs.getString("branch"));
            }
            else{
                lbl_StudentError.setText("Invalid Student ID!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //insert issue book details to database
    public boolean issueBook(){
        boolean isIssued = false;
        
        int bookId = Integer.parseInt(txt_BookId.getText());
        int studentId = Integer.parseInt(txt_StudentId.getText());
        String bookName = lbl_BookName.getText();
        String studentName = lbl_StudentName.getText();
        
        Date uIssueDate = date_IssueDate.getDatoFecha();
        Date uDueDate = date_DueDate.getDatoFecha();
        
        Long l1 = uIssueDate.getTime();   
        long l2 = uDueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name, "
                    + "issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, studentId);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isIssued = true;
            } 
            else{
                isIssued = false;
            }
            
        }
        catch(Exception e   ){
            e.printStackTrace();
        }
        
        return isIssued;
    }
    
    
    //updating book details
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_BookId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book Count Updated!");
                int initialCount = Integer.parseInt(lbl_Quantity.getText());
                lbl_Quantity.setText(Integer.toString(initialCount - 1));
            } 
            else{
                JOptionPane.showMessageDialog(this, "Can't Update Book Count!");
            }
            
            
        }
        catch(Exception e   ){
            e.printStackTrace();
        }       
    }
    
    
    //Checking wether book already allocated or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
        
        int bookId = Integer.parseInt(txt_BookId.getText());
        int studentId = Integer.parseInt(txt_StudentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                isAlreadyIssued = true;
            } 
            else{
                isAlreadyIssued = false;
            }
   
        }
        catch(Exception e   ){
            e.printStackTrace();
        }      
        
        return isAlreadyIssued;
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
        lbl_BookId = new javax.swing.JLabel();
        lbl_Author = new javax.swing.JLabel();
        lbl_Quantity = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_BookError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_StudentId = new javax.swing.JLabel();
        lbl_Branch = new javax.swing.JLabel();
        lbl_StudentName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_Course = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_StudentError = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        close1 = new javax.swing.JLabel();
        txt_BookId = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_StudentId = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        date_IssueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        date_DueDate = new rojeru_san.componentes.RSDateChooser();
        Add_Btn = new rojerusan.RSMaterialButtonRectangle();

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
        jLabel5.setText("Author : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Book ID : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        lbl_BookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_BookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 220, 30));

        lbl_BookId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_BookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_BookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 220, 30));

        lbl_Author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_Author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_Author, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, 220, 30));

        lbl_Quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_Quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 690, 220, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Quantity : ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, -1, -1));

        lbl_BookError.setBackground(new java.awt.Color(255, 255, 255));
        lbl_BookError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_BookError.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.add(lbl_BookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 800, 350, 30));

        Panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 1050));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_StudentId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_StudentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_StudentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 220, 30));

        lbl_Branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_Branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_Branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 690, 220, 30));

        lbl_StudentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_StudentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 220, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student ID : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 5));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel10.setText("  Student Details");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        lbl_Course.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_Course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 610, 220, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Name : ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Course : ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Branch : ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 690, -1, -1));

        lbl_StudentError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_StudentError.setForeground(new java.awt.Color(255, 153, 51));
        jPanel2.add(lbl_StudentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 800, 370, 30));

        Panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 520, 1050));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel11.setText("Issue Book");
        Panel_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 210, -1, -1));

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
        Panel_main.add(txt_BookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 350, 370, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Book ID :");
        Panel_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 370, 120, -1));

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
        Panel_main.add(txt_StudentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 450, 370, 50));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Issue Date :");
        Panel_main.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 570, 120, -1));

        date_IssueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_IssueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_IssueDate.setColorSelForeground(new java.awt.Color(255, 51, 51));
        date_IssueDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_IssueDate.setPlaceholder("Select Issue Date");
        Panel_main.add(date_IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 560, 370, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Student ID :");
        Panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 470, 120, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Due Date :");
        Panel_main.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 660, 120, -1));

        date_DueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_DueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_DueDate.setColorSelForeground(new java.awt.Color(255, 51, 51));
        date_DueDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_DueDate.setPlaceholder("Select Issue Date");
        Panel_main.add(date_DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 650, 370, -1));

        Add_Btn.setBackground(new java.awt.Color(255, 51, 51));
        Add_Btn.setLabel("ISSUE BOOK");
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
        Panel_main.add(Add_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 790, 230, 60));

        getContentPane().add(Panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 1050));

        setSize(new java.awt.Dimension(1680, 1050));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        dispose();
    }//GEN-LAST:event_BackMouseClicked

    private void close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_close1MouseClicked

    private void txt_StudentIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIdFocusGained

    private void txt_StudentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_StudentIdFocusLost
        if(!txt_StudentId.getText().equals("")){
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_StudentIdFocusLost

    private void txt_StudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_StudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StudentIdActionPerformed

    private void Add_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_BtnMouseClicked

    private void Add_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BtnActionPerformed
        
        if(lbl_Quantity.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "Book Is Not Available!");
        }
        else{
            if(isAlreadyIssued() == false){               
                if(issueBook() == true){
                    JOptionPane.showMessageDialog(this, "Book Issue Sucessfully!");
                    updateBookCount();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Can't Issue The Book!");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "This Student Already Has This Book!");
            }
        }
    }//GEN-LAST:event_Add_BtnActionPerformed

    private void txt_BookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIdActionPerformed

    private void txt_BookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIdFocusLost
        if(!txt_BookId.getText().equals("")){
            getBookDetails();
        }
  
    }//GEN-LAST:event_txt_BookIdFocusLost

    private void txt_BookIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIdFocusGained

       
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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Add_Btn;
    private javax.swing.JLabel Back;
    private javax.swing.JPanel Panel_main;
    private javax.swing.JLabel close1;
    private rojeru_san.componentes.RSDateChooser date_DueDate;
    private rojeru_san.componentes.RSDateChooser date_IssueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_Author;
    private javax.swing.JLabel lbl_BookError;
    private javax.swing.JLabel lbl_BookId;
    private javax.swing.JLabel lbl_BookName;
    private javax.swing.JLabel lbl_Branch;
    private javax.swing.JLabel lbl_Course;
    private javax.swing.JLabel lbl_Quantity;
    private javax.swing.JLabel lbl_StudentError;
    private javax.swing.JLabel lbl_StudentId;
    private javax.swing.JLabel lbl_StudentName;
    private app.bolivia.swing.JCTextField txt_BookId;
    private app.bolivia.swing.JCTextField txt_StudentId;
    // End of variables declaration//GEN-END:variables
}
