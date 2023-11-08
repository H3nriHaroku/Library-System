package JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class HomePage extends javax.swing.JFrame {

    
    DefaultTableModel model;
    
    public HomePage() {
        initComponents();
        
        showPieChart();
        setStudentDetailsToTable(); 
        setBookDetailsToTable();
        setDataToCards();
    }
    
    Color mouseEnterColor = new Color(0,0,0);
    Color mouseExitColor = new Color(51,51,51);
    
    public void setDataToCards(){
        
        Statement st = null;
        ResultSet rs = null;
        
        long l = System.currentTimeMillis();
        Date todays = new Date(l);
        
        try{
            Connection con = DBConnection.getConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select * from book_details");
            rs.last();
            lbl_NoBooks.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from student_details");
            rs.last();
            lbl_NoStudents.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details");
            rs.last();
            lbl_IssueBooks.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details where due_date < '"+todays+"' and status = '"+"pending"+"' ");
            rs.last();
            lbl_DefaulterLists.setText(Integer.toString(rs.getRow()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void showPieChart(){
        
        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset( );
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select book_name, count(*) as issue_count from issue_book_details group by book_id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                barDataset.setValue(rs.getString("book_name"), new Double(rs.getDouble("issue_count")));
            }
        }
        catch(Exception e){
        
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Book Sales",barDataset, true,true,false);//explain

        PiePlot piePlot =(PiePlot) piechart.getPlot();

         //changing pie chart blocks colors
        piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));


        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        PanelPieChart.removeAll();
        PanelPieChart.add(barChartPanel, BorderLayout.CENTER);
        PanelPieChart.validate();
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
                model = (DefaultTableModel) tbl_StudentDetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
                model = (DefaultTableModel) tbl_BookDetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ManageBook_panel = new javax.swing.JPanel();
        ManageBookBtn = new javax.swing.JLabel();
        ManageStudent_panel = new javax.swing.JPanel();
        ManageStudent_label = new javax.swing.JLabel();
        panel_IssueBook = new javax.swing.JPanel();
        lbl_IssueBook = new javax.swing.JLabel();
        panel_ViewIssuedBook = new javax.swing.JPanel();
        lbl_ViewIssuedBook = new javax.swing.JLabel();
        panel_ReturnBook = new javax.swing.JPanel();
        lbl_ReturnBook = new javax.swing.JLabel();
        panel_DefaulterList = new javax.swing.JPanel();
        lbl_DefaulterList = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        panel_ViewRecords = new javax.swing.JPanel();
        lbl_ViewRecords = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lbl_NoBooks = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lbl_DefaulterLists = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lbl_IssueBooks = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lbl_NoStudents = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_StudentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_BookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel18 = new javax.swing.JLabel();
        PanelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel2.setText("Welcome, Admin");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Library Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        close.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setText("X");
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 20, 40, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 5, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 70));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel4.setText("  Dashboard");
        jLabel4.setToolTipText("");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel5.setText("  Home");
        jLabel5.setToolTipText("");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Features");
        jLabel6.setToolTipText("");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        ManageBook_panel.setBackground(new java.awt.Color(51, 51, 51));
        ManageBook_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ManageBook_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ManageBook_panelMouseExited(evt);
            }
        });
        ManageBook_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ManageBookBtn.setBackground(new java.awt.Color(255, 255, 255));
        ManageBookBtn.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        ManageBookBtn.setForeground(new java.awt.Color(153, 153, 153));
        ManageBookBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_26px.png"))); // NOI18N
        ManageBookBtn.setText("  Manage Book");
        ManageBookBtn.setToolTipText("");
        ManageBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManageBookBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ManageBookBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ManageBookBtnMouseExited(evt);
            }
        });
        ManageBook_panel.add(ManageBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        ManageStudent_panel.setBackground(new java.awt.Color(51, 51, 51));
        ManageStudent_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ManageStudent_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ManageStudent_panelMouseExited(evt);
            }
        });
        ManageStudent_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ManageStudent_label.setBackground(new java.awt.Color(255, 255, 255));
        ManageStudent_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        ManageStudent_label.setForeground(new java.awt.Color(153, 153, 153));
        ManageStudent_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        ManageStudent_label.setText("  Manage Students");
        ManageStudent_label.setToolTipText("");
        ManageStudent_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManageStudent_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ManageStudent_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ManageStudent_labelMouseExited(evt);
            }
        });
        ManageStudent_panel.add(ManageStudent_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panel_IssueBook.setBackground(new java.awt.Color(51, 51, 51));
        panel_IssueBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_IssueBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_IssueBookMouseExited(evt);
            }
        });
        panel_IssueBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_IssueBook.setBackground(new java.awt.Color(255, 255, 255));
        lbl_IssueBook.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        lbl_IssueBook.setForeground(new java.awt.Color(153, 153, 153));
        lbl_IssueBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        lbl_IssueBook.setText("  Issue Book");
        lbl_IssueBook.setToolTipText("");
        lbl_IssueBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_IssueBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_IssueBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_IssueBookMouseExited(evt);
            }
        });
        panel_IssueBook.add(lbl_IssueBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panel_ViewIssuedBook.setBackground(new java.awt.Color(51, 51, 51));
        panel_ViewIssuedBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_ViewIssuedBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_ViewIssuedBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_ViewIssuedBookMouseExited(evt);
            }
        });
        panel_ViewIssuedBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ViewIssuedBook.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ViewIssuedBook.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        lbl_ViewIssuedBook.setForeground(new java.awt.Color(153, 153, 153));
        lbl_ViewIssuedBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        lbl_ViewIssuedBook.setText("  View Issued Book");
        lbl_ViewIssuedBook.setToolTipText("");
        lbl_ViewIssuedBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ViewIssuedBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_ViewIssuedBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_ViewIssuedBookMouseExited(evt);
            }
        });
        panel_ViewIssuedBook.add(lbl_ViewIssuedBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panel_ReturnBook.setBackground(new java.awt.Color(51, 51, 51));
        panel_ReturnBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_ReturnBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_ReturnBookMouseExited(evt);
            }
        });
        panel_ReturnBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ReturnBook.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ReturnBook.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        lbl_ReturnBook.setForeground(new java.awt.Color(153, 153, 153));
        lbl_ReturnBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        lbl_ReturnBook.setText("  Return Book");
        lbl_ReturnBook.setToolTipText("");
        lbl_ReturnBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ReturnBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_ReturnBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_ReturnBookMouseExited(evt);
            }
        });
        panel_ReturnBook.add(lbl_ReturnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panel_DefaulterList.setBackground(new java.awt.Color(51, 51, 51));
        panel_DefaulterList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_DefaulterListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_DefaulterListMouseExited(evt);
            }
        });
        panel_DefaulterList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_DefaulterList.setBackground(new java.awt.Color(255, 255, 255));
        lbl_DefaulterList.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        lbl_DefaulterList.setForeground(new java.awt.Color(153, 153, 153));
        lbl_DefaulterList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        lbl_DefaulterList.setText("  Defaulter List");
        lbl_DefaulterList.setToolTipText("");
        lbl_DefaulterList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DefaulterListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_DefaulterListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_DefaulterListMouseExited(evt);
            }
        });
        panel_DefaulterList.add(lbl_DefaulterList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel13.setBackground(new java.awt.Color(102, 102, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel13.setText("  Log Out");
        jLabel13.setToolTipText("");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel13.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panel_ViewRecords.setBackground(new java.awt.Color(51, 51, 51));
        panel_ViewRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_ViewRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_ViewRecordsMouseExited(evt);
            }
        });
        panel_ViewRecords.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ViewRecords.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ViewRecords.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        lbl_ViewRecords.setForeground(new java.awt.Color(153, 153, 153));
        lbl_ViewRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        lbl_ViewRecords.setText("  View Records");
        lbl_ViewRecords.setToolTipText("");
        lbl_ViewRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ViewRecordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_ViewRecordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_ViewRecordsMouseExited(evt);
            }
        });
        panel_ViewRecords.add(lbl_ViewRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ManageBook_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_IssueBook, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_ViewIssuedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ManageStudent_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_ReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_DefaulterList, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_ViewRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ManageBook_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ManageStudent_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_IssueBook, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_ReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_ViewRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_ViewIssuedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_DefaulterList, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(860, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 240, 980));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel16.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NoBooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        lbl_NoBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_NoBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_NoBooks.setText("10");
        jPanel16.add(lbl_NoBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("No. Of Books");

        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel18.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_DefaulterLists.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        lbl_DefaulterLists.setForeground(new java.awt.Color(102, 102, 102));
        lbl_DefaulterLists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_DefaulterLists.setText("10");
        jPanel18.add(lbl_DefaulterLists, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Defaulter List");

        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel19.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_IssueBooks.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        lbl_IssueBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_IssueBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_IssueBooks.setText("10");
        jPanel19.add(lbl_IssueBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("No. Of Students");

        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel20.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NoStudents.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        lbl_NoStudents.setForeground(new java.awt.Color(102, 102, 102));
        lbl_NoStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_NoStudents.setText("10");
        jPanel20.add(lbl_NoStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Issued Books");

        tbl_StudentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_StudentDetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_StudentDetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_StudentDetails.setColorBordeHead(new java.awt.Color(102, 102, 255));
        tbl_StudentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_StudentDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_StudentDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_StudentDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_StudentDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_StudentDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_StudentDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_StudentDetails.setRowHeight(44);
        jScrollPane1.setViewportView(tbl_StudentDetails);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Students Details");

        tbl_BookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Name", "Author Name", "Quantity"
            }
        ));
        tbl_BookDetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbl_BookDetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_BookDetails.setColorBordeHead(new java.awt.Color(102, 102, 255));
        tbl_BookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_BookDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_BookDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_BookDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_BookDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_BookDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_BookDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_BookDetails.setRowHeight(44);
        jScrollPane2.setViewportView(tbl_BookDetails);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Students Details");

        PanelPieChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(jLabel17)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180))))
        );

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1450, 990));

        setSize(new java.awt.Dimension(1680, 1050));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void ManageBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageBookBtnMouseClicked
        ManageBook manageBook = new ManageBook();
        manageBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_ManageBookBtnMouseClicked

    private void ManageBookBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageBookBtnMouseEntered
        ManageBook_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_ManageBookBtnMouseEntered

    private void ManageBookBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageBookBtnMouseExited
        ManageBook_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_ManageBookBtnMouseExited

    private void ManageBook_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageBook_panelMouseEntered
        ManageBook_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_ManageBook_panelMouseEntered

    private void ManageBook_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageBook_panelMouseExited
         ManageBook_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_ManageBook_panelMouseExited

    private void ManageStudent_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStudent_panelMouseEntered
        ManageStudent_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_ManageStudent_panelMouseEntered

    private void ManageStudent_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStudent_panelMouseExited
        ManageStudent_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_ManageStudent_panelMouseExited

    private void ManageStudent_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStudent_labelMouseEntered
        ManageStudent_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_ManageStudent_labelMouseEntered

    private void ManageStudent_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStudent_labelMouseExited
        ManageStudent_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_ManageStudent_labelMouseExited

    private void ManageStudent_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStudent_labelMouseClicked
        ManageStudents manageStudent = new ManageStudents();
        manageStudent.setVisible(true);
        dispose();
    }//GEN-LAST:event_ManageStudent_labelMouseClicked

    private void lbl_IssueBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_IssueBookMouseClicked
        IssueBook issueBook = new IssueBook();
        issueBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_lbl_IssueBookMouseClicked

    private void lbl_IssueBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_IssueBookMouseEntered
        panel_IssueBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_lbl_IssueBookMouseEntered

    private void lbl_IssueBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_IssueBookMouseExited
        panel_IssueBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_lbl_IssueBookMouseExited

    private void panel_IssueBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_IssueBookMouseEntered
        panel_IssueBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_panel_IssueBookMouseEntered

    private void panel_IssueBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_IssueBookMouseExited
        panel_IssueBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_panel_IssueBookMouseExited

    private void panel_ReturnBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ReturnBookMouseEntered
        panel_ReturnBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_panel_ReturnBookMouseEntered

    private void panel_ReturnBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ReturnBookMouseExited
        panel_ReturnBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_panel_ReturnBookMouseExited

    private void lbl_ReturnBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ReturnBookMouseClicked
        ReturnBook returnBook = new ReturnBook();
        returnBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_lbl_ReturnBookMouseClicked

    private void lbl_ReturnBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ReturnBookMouseEntered
        panel_ReturnBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_lbl_ReturnBookMouseEntered

    private void lbl_ReturnBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ReturnBookMouseExited
        panel_ReturnBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_lbl_ReturnBookMouseExited

    private void panel_ViewRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ViewRecordsMouseEntered
        panel_ViewRecords.setBackground(mouseEnterColor);
    }//GEN-LAST:event_panel_ViewRecordsMouseEntered

    private void panel_ViewRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ViewRecordsMouseExited
        panel_ViewRecords.setBackground(mouseExitColor);
    }//GEN-LAST:event_panel_ViewRecordsMouseExited

    private void lbl_ViewRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewRecordsMouseClicked
        ViewAllRecords viewAllRecords = new ViewAllRecords();
        viewAllRecords.setVisible(true);
        dispose();
    }//GEN-LAST:event_lbl_ViewRecordsMouseClicked

    private void lbl_ViewRecordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewRecordsMouseEntered
        panel_ViewRecords.setBackground(mouseEnterColor);
    }//GEN-LAST:event_lbl_ViewRecordsMouseEntered

    private void lbl_ViewRecordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewRecordsMouseExited
        panel_ViewRecords.setBackground(mouseExitColor);
    }//GEN-LAST:event_lbl_ViewRecordsMouseExited

    private void panel_ViewIssuedBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ViewIssuedBookMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_ViewIssuedBookMouseClicked

    private void panel_ViewIssuedBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ViewIssuedBookMouseEntered
        panel_ViewIssuedBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_panel_ViewIssuedBookMouseEntered

    private void panel_ViewIssuedBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_ViewIssuedBookMouseExited
        panel_ViewIssuedBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_panel_ViewIssuedBookMouseExited

    private void lbl_ViewIssuedBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewIssuedBookMouseClicked
        IssueBookDetails issueBookDetails = new IssueBookDetails();
        issueBookDetails.setVisible(true);
        dispose();
    }//GEN-LAST:event_lbl_ViewIssuedBookMouseClicked

    private void lbl_ViewIssuedBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewIssuedBookMouseEntered
        panel_ViewIssuedBook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_lbl_ViewIssuedBookMouseEntered

    private void lbl_ViewIssuedBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ViewIssuedBookMouseExited
        panel_ViewIssuedBook.setBackground(mouseExitColor);
    }//GEN-LAST:event_lbl_ViewIssuedBookMouseExited

    private void panel_DefaulterListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_DefaulterListMouseEntered
        panel_DefaulterList.setBackground(mouseEnterColor);
    }//GEN-LAST:event_panel_DefaulterListMouseEntered

    private void panel_DefaulterListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_DefaulterListMouseExited
        panel_DefaulterList.setBackground(mouseExitColor);
    }//GEN-LAST:event_panel_DefaulterListMouseExited

    private void lbl_DefaulterListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DefaulterListMouseClicked
        DefaulterList defaulterList = new DefaulterList();
        defaulterList.setVisible(true);
        dispose();
    }//GEN-LAST:event_lbl_DefaulterListMouseClicked

    private void lbl_DefaulterListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DefaulterListMouseEntered
        panel_DefaulterList.setBackground(mouseEnterColor);
    }//GEN-LAST:event_lbl_DefaulterListMouseEntered

    private void lbl_DefaulterListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DefaulterListMouseExited
        panel_DefaulterList.setBackground(mouseExitColor);
    }//GEN-LAST:event_lbl_DefaulterListMouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel13MouseClicked
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ManageBookBtn;
    private javax.swing.JPanel ManageBook_panel;
    private javax.swing.JLabel ManageStudent_label;
    private javax.swing.JPanel ManageStudent_panel;
    private javax.swing.JPanel PanelPieChart;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_DefaulterList;
    private javax.swing.JLabel lbl_DefaulterLists;
    private javax.swing.JLabel lbl_IssueBook;
    private javax.swing.JLabel lbl_IssueBooks;
    private javax.swing.JLabel lbl_NoBooks;
    private javax.swing.JLabel lbl_NoStudents;
    private javax.swing.JLabel lbl_ReturnBook;
    private javax.swing.JLabel lbl_ViewIssuedBook;
    private javax.swing.JLabel lbl_ViewRecords;
    private javax.swing.JPanel panel_DefaulterList;
    private javax.swing.JPanel panel_IssueBook;
    private javax.swing.JPanel panel_ReturnBook;
    private javax.swing.JPanel panel_ViewIssuedBook;
    private javax.swing.JPanel panel_ViewRecords;
    private rojeru_san.complementos.RSTableMetro tbl_BookDetails;
    private rojeru_san.complementos.RSTableMetro tbl_StudentDetails;
    // End of variables declaration//GEN-END:variables

    private Date newDate(long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}