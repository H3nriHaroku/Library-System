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

public class ManageBook extends javax.swing.JFrame {

    String book_name, author;
    int book_id, quantity;
    DefaultTableModel model;
    
    public ManageBook() {
        initComponents();
        setBookDetailsToTable(); 
    }
 
    //to set the book details into table
    public void setBookDetailsToTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");
            
            while(rs.next()){
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                
                Object[] obj = {bookId, bookName,author,quantity};
                model = (DefaultTableModel) tbl_bookDetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    // Add book to book_details database table
    public boolean addBook(){
        boolean isAdded = false;
        
        book_id = Integer.parseInt(txt_BookID.getText());
        book_name = txt_BookName.getText();
        author = txt_AuthorName.getText();
        quantity = Integer.parseInt(txt_Quantity.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            
            String sql = "insert into book_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, book_id);
            pst.setString(2, book_name);
            pst.setString(3, author);
            pst.setInt(4, quantity);
            
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
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
    }
    
    
    //Update book details
    public boolean updateBook(){
        boolean isUpdated = false;
        
        book_id = Integer.parseInt(txt_BookID.getText());
        book_name = txt_BookName.getText();
        author = txt_AuthorName.getText();
        quantity = Integer.parseInt(txt_Quantity.getText());
        
        try{
            Connection con = DBConnection.getConnection();

            String sql = "update book_details set book_name = ?, author = ?, quantity = ? where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, book_name);
            pst.setString(2, author);
            pst.setInt(3, quantity);
            pst.setInt(4, book_id);
            
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
    public boolean deleteBook(){
        boolean isDeleted = false;
        
        book_id = Integer.parseInt(txt_BookID.getText());
        
        try{
            Connection con = DBConnection.getConnection();

            String sql = "delete from book_details where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, book_id);
            
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
        txt_BookID = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_BookName = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_AuthorName = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Quantity = new app.bolivia.swing.JCTextField();
        Delete_Btn = new rojerusan.RSMaterialButtonRectangle();
        Add_Btn = new rojerusan.RSMaterialButtonRectangle();
        Update_Btn = new rojerusan.RSMaterialButtonRectangle();
        jPanel3 = new javax.swing.JPanel();
        close1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
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
        jLabel9.setText("Book ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 130, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 50, 50));

        txt_BookID.setBackground(new java.awt.Color(102, 102, 255));
        txt_BookID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_BookID.setForeground(new java.awt.Color(255, 255, 255));
        txt_BookID.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_BookID.setPlaceholder("Enter Book ID...");
        txt_BookID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_BookIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BookIDFocusLost(evt);
            }
        });
        txt_BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BookIDActionPerformed(evt);
            }
        });
        jPanel1.add(txt_BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 270, 40));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Book Name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 130, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 50, 50));

        txt_BookName.setBackground(new java.awt.Color(102, 102, 255));
        txt_BookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_BookName.setForeground(new java.awt.Color(255, 255, 255));
        txt_BookName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_BookName.setPlaceholder("Enter Name...");
        txt_BookName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_BookNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BookNameFocusLost(evt);
            }
        });
        txt_BookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BookNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 270, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Author Name");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 130, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel5.setText("Username");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 50, 50));

        txt_AuthorName.setBackground(new java.awt.Color(102, 102, 255));
        txt_AuthorName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_AuthorName.setForeground(new java.awt.Color(255, 255, 255));
        txt_AuthorName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_AuthorName.setPlaceholder("Enter Author Name...");
        txt_AuthorName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_AuthorNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_AuthorNameFocusLost(evt);
            }
        });
        txt_AuthorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AuthorNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_AuthorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 270, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Quantity");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 130, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jLabel6.setText("Username");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, 50, 50));

        txt_Quantity.setBackground(new java.awt.Color(102, 102, 255));
        txt_Quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_Quantity.setForeground(new java.awt.Color(255, 255, 255));
        txt_Quantity.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_Quantity.setPlaceholder("Enter Quantity...");
        txt_Quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_QuantityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_QuantityFocusLost(evt);
            }
        });
        txt_Quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_QuantityActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, 270, -1));

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

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Name", "Author Name", "Quantity"
            }
        ));
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_bookDetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_bookDetails.setColorBordeHead(new java.awt.Color(102, 102, 255));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_bookDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_bookDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_bookDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_bookDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_bookDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_bookDetails.setRowHeight(44);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 1010, 750));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText(" Manage Books");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

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

    private void txt_BookIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIDFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIDFocusGained

    private void txt_BookIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIDFocusLost

    }//GEN-LAST:event_txt_BookIDFocusLost

    private void txt_BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIDActionPerformed

    private void txt_BookNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookNameFocusGained

    private void txt_BookNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookNameFocusLost

    }//GEN-LAST:event_txt_BookNameFocusLost

    private void txt_BookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookNameActionPerformed

    private void txt_AuthorNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AuthorNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AuthorNameFocusGained

    private void txt_AuthorNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AuthorNameFocusLost

    }//GEN-LAST:event_txt_AuthorNameFocusLost

    private void txt_AuthorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AuthorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AuthorNameActionPerformed

    private void txt_QuantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_QuantityFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_QuantityFocusGained

    private void txt_QuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_QuantityFocusLost

    }//GEN-LAST:event_txt_QuantityFocusLost

    private void txt_QuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_QuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_QuantityActionPerformed

    private void Delete_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_BtnMouseClicked

    }//GEN-LAST:event_Delete_BtnMouseClicked

    private void Delete_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_BtnActionPerformed
        if(deleteBook() == true){
            JOptionPane.showMessageDialog(this, "Book Delted!");
            clearTable(); 
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Deletion Failed!");
        }
    }//GEN-LAST:event_Delete_BtnActionPerformed

    private void Add_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_BtnMouseClicked

    private void Add_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BtnActionPerformed
        
        if(addBook() == true){
            JOptionPane.showMessageDialog(this, "Book Added!");
            clearTable(); 
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Addition Failed!");
        }
    }//GEN-LAST:event_Add_BtnActionPerformed

    private void Update_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Update_BtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Update_BtnMouseClicked

    private void Update_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_BtnActionPerformed
        if(updateBook() == true){
            JOptionPane.showMessageDialog(this, "Book Updated!");
            clearTable(); 
            setBookDetailsToTable();
        }
        else{
            JOptionPane.showMessageDialog(this, "Book Updation  Failed!");
        }
    }//GEN-LAST:event_Update_BtnActionPerformed

    private void close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_close1MouseClicked

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        
        txt_BookID.setText(model.getValueAt(rowNo, 0).toString());
        txt_BookName.setText(model.getValueAt(rowNo, 1).toString());
        txt_AuthorName.setText(model.getValueAt(rowNo, 2).toString());
        txt_Quantity.setText(model.getValueAt(rowNo, 3).toString());
                
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Add_Btn;
    private javax.swing.JLabel Back;
    private rojerusan.RSMaterialButtonRectangle Delete_Btn;
    private rojerusan.RSMaterialButtonRectangle Update_Btn;
    private javax.swing.JLabel close1;
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
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_AuthorName;
    private app.bolivia.swing.JCTextField txt_BookID;
    private app.bolivia.swing.JCTextField txt_BookName;
    private app.bolivia.swing.JCTextField txt_Quantity;
    // End of variables declaration//GEN-END:variables
}
