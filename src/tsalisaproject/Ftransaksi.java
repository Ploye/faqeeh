/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsalisaproject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;





import javax.swing.table.DefaultTableModel;
public class Ftransaksi extends javax.swing.JFrame {
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;
    PreparedStatement pst;
    Statement statBrg;
     public void tanggal(){
        
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyy-MM-dd");
        tgl.setText(s.format (ys));
        tgl.setEditable(false);
    
    }
      public int simpan(){
            for(int a=0;a<=dgv3.getRowCount();a++){
        String query;
            query = "INSERT INTO t_transaksi "+
                    "VALUES"+
                    "('"+dgv3.getValueAt(a,0).toString() +"', "+
                    "'"+dgv3.getValueAt(a,1).toString() +"', "+
                    "'"+dgv3.getValueAt(a,2).toString() +"', "+
                    "'"+dgv3.getValueAt(a,3).toString() +"', "+
                    "'"+dgv3.getValueAt(a,4).toString() +"', "+
                    "'"+dgv3.getValueAt(a,5).toString() +"', "+
                    "'"+dgv3.getValueAt(a,6).toString() +"', "+
                    "'"+dgv3.getValueAt(a,7).toString() +"')";
   
 
//        executeSQLQuery(query,"Insert");
 
}
return 0;
}
    int tot,jml,ya,byr,kem;
    KoneksiDB k=new KoneksiDB();
    
    String sql;
    public Ftransaksi() {
       
       // this.setExtendedState(MAXIMIZED_BOTH);
        initComponents();
     tampilBarang();
     nofaktur();
    
        tbjenisbrg.setVisible(false);
        tbstruk.setVisible(false);
     
        dgv2.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = dgv2.rowAtPoint(evt.getPoint());
        int col = dgv2.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            tbkdbrg.setText(dgv2.getModel().getValueAt(row,0).toString());
            tbnmbrg.setText(dgv2.getModel().getValueAt(row,1).toString());
            tbjenisbrg.setText(dgv2.getModel().getValueAt(row,2).toString());
            tbhrg.setText(dgv2.getModel().getValueAt(row,4).toString());

        }   
    }
    

});
        
    }
  
     private void nofaktur()
    {
       try {
            tbnofak.setText("F0001");
            sql="select * from t_transaksi order by no_fak desc";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if (rs.next()) {
                String nofak = rs.getString("no_fak").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               tbnofak.setText("F" + Nol + AN);
            } else {
               tbnofak.setText("F0001");
            }

           }
       catch(Exception e){
//           JOptionPane.showMessageDialog(null, e);
           }
     }
       public Connection getConnection(){
        Connection con;
        try {            
            con = DriverManager.getConnection("jdbc:mysql://localhost/tsalisa","root","");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<MBarang> getUserList(){
        ArrayList<MBarang> userList = new ArrayList<MBarang>();
        Connection connection = getConnection();
        String query = "SELECT * FROM `barang` ";
        
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            MBarang brg;
            while (rs.next()) {                
                brg = new MBarang(rs.getInt("kode_barang"),rs.getString("nama_barang"),rs.getString("jenis_brg"),rs.getInt("qty"),rs.getInt("harga_brg"));
                userList.add(brg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
        
    }
     public void Show_Users_In_Tampil(){
        ArrayList<MBarang> list = getUserList();
       
                
        DefaultTableModel model = (DefaultTableModel)dgv2.getModel();
        
        Object[] row = new Object[5];
        for(int i=0; i<list.size() ; i++){
            row[0] = list.get(i).getkdbrg();
            row[1] = list.get(i).getnmbrg();
            row[2] = list.get(i).getjenisbrg();
            row[3] = list.get(i).getqty();
            row[4] = list.get(i).gethargabrg();
           
            
            model.addRow(row);
        }
    }
     public void executeSQLQuery(String query,String message){
        
        Connection con = getConnection();
        Statement st;
        try {
            st = con.createStatement();
            if((st.executeUpdate(query)) == 1)
            {
                DefaultTableModel model = (DefaultTableModel)dgv2.getModel();
                model.setRowCount(0);
                Show_Users_In_Tampil();
                
                JOptionPane.showMessageDialog(null,"Data "+message+"Succefully");
            }else{
                JOptionPane.showMessageDialog(null,"Data Not "+message);
            }
        } catch (Exception ex) {
        ex.printStackTrace();
        }
      }
       public void tampilBarang(){
             tanggal();

        DefaultTableModel model= (DefaultTableModel) dgv2.getModel();
        String query = "SELECT * FROM barang";
        Statement st;
        ResultSet rs;
        
        try{
            st=con.createStatement();
            rs=st.executeQuery(query);
            Object[] row = new Object[5];
            //int no=0;
            while(rs.next()){
                row[0] = rs.getString("kode_barang");;
                row[1] = rs.getString("nama_barang");
                row[2] = rs.getString("jenis_barang");
                row[3] = rs.getInt("warna");
                row[4] = rs.getInt("ukuran_barang");
                row[5] = rs.getInt("jenis_kain");
                row[6] = rs.getInt("harga_satuan");
                model.addRow(row);
                
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
      
    
       public void total(){
       int a = 0;
         for (int i =0; i< dgv3.getRowCount(); i++){
         int amount = Integer.parseInt((String)dgv3.getValueAt(i, 7));
         a += amount;
   }
   tbtotal.setText(""+a);
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jptransaksi = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnpros1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgv3 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgv2 = new javax.swing.JTable();
        tbtotal = new javax.swing.JTextField();
        tbbayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbkembali = new javax.swing.JTextField();
        tbkdbrg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tbnmbrg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbhrg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tbjmlbrg = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tbsubtot = new javax.swing.JTextField();
        tbjenisbrg = new javax.swing.JTextField();
        tbnofak = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbstruk = new javax.swing.JTextArea();
        tgl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaksi");

        jLabel8.setText("Kembalian :");

        btnpros1.setText("Proses");
        btnpros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpros1ActionPerformed(evt);
            }
        });

        dgv3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Tanggal", "Kode Barang", "Nama Barang", "Jenis Barang", "Jumlah", "Harga", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgv3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgv3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dgv3);
        if (dgv3.getColumnModel().getColumnCount() > 0) {
            dgv3.getColumnModel().getColumn(0).setResizable(false);
            dgv3.getColumnModel().getColumn(1).setResizable(false);
            dgv3.getColumnModel().getColumn(2).setResizable(false);
            dgv3.getColumnModel().getColumn(3).setResizable(false);
            dgv3.getColumnModel().getColumn(4).setResizable(false);
            dgv3.getColumnModel().getColumn(5).setResizable(false);
            dgv3.getColumnModel().getColumn(6).setResizable(false);
            dgv3.getColumnModel().getColumn(7).setResizable(false);
        }

        dgv2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jenis Barang", "Jumlah", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgv2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgv2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgv2);
        if (dgv2.getColumnModel().getColumnCount() > 0) {
            dgv2.getColumnModel().getColumn(0).setResizable(false);
            dgv2.getColumnModel().getColumn(1).setResizable(false);
            dgv2.getColumnModel().getColumn(2).setResizable(false);
            dgv2.getColumnModel().getColumn(3).setResizable(false);
            dgv2.getColumnModel().getColumn(4).setResizable(false);
        }

        tbtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtotalActionPerformed(evt);
            }
        });

        tbbayar.setBackground(new java.awt.Color(153, 153, 255));
        tbbayar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tbbayarMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbbayarMouseMoved(evt);
            }
        });
        tbbayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbbayarMouseExited(evt);
            }
        });
        tbbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbbayarKeyTyped(evt);
            }
        });

        jLabel9.setText("Total :");

        jLabel7.setText("Bayar :");

        tbkembali.setEditable(false);
        tbkembali.setText("0");

        tbkdbrg.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                tbkdbrgAncestorRemoved(evt);
            }
        });
        tbkdbrg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbkdbrgMouseMoved(evt);
            }
        });
        tbkdbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkdbrgMouseClicked(evt);
            }
        });
        tbkdbrg.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tbkdbrgInputMethodTextChanged(evt);
            }
        });
        tbkdbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbkdbrgActionPerformed(evt);
            }
        });
        tbkdbrg.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbkdbrgPropertyChange(evt);
            }
        });
        tbkdbrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbkdbrgKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbkdbrgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbkdbrgKeyTyped(evt);
            }
        });

        jLabel1.setText("Kode/Nama  Barang :");

        jLabel2.setText("Nama Barang :");

        tbnmbrg.setEditable(false);
        tbnmbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnmbrgMouseClicked(evt);
            }
        });
        tbnmbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnmbrgActionPerformed(evt);
            }
        });
        tbnmbrg.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbnmbrgPropertyChange(evt);
            }
        });
        tbnmbrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbnmbrgKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbnmbrgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbnmbrgKeyTyped(evt);
            }
        });

        jLabel5.setText("Harga :");

        tbhrg.setEditable(false);

        jLabel4.setText("Jumlah :");

        jButton2.setText(">");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbjmlbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbjmlbrgActionPerformed(evt);
            }
        });
        tbjmlbrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbjmlbrgKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbjmlbrgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbjmlbrgKeyTyped(evt);
            }
        });

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Sub Total :");

        tbsubtot.setEditable(false);

        tbjenisbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbjenisbrgActionPerformed(evt);
            }
        });

        tbnofak.setEditable(false);

        jLabel10.setText("No Faktur :");

        tbstruk.setEditable(false);
        tbstruk.setColumns(20);
        tbstruk.setRows(5);
        jScrollPane4.setViewportView(tbstruk);

        javax.swing.GroupLayout jptransaksiLayout = new javax.swing.GroupLayout(jptransaksi);
        jptransaksi.setLayout(jptransaksiLayout);
        jptransaksiLayout.setHorizontalGroup(
            jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jptransaksiLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jptransaksiLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tbtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jptransaksiLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(245, 245, 245))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jptransaksiLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jptransaksiLayout.createSequentialGroup()
                                    .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tbjenisbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnpros1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tbbayar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tbkembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jptransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbsubtot, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2)
                        .addComponent(tbnofak, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jptransaksiLayout.createSequentialGroup()
                .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jptransaksiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbkdbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbnmbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbhrg, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbjmlbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(527, 527, 527))
        );
        jptransaksiLayout.setVerticalGroup(
            jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jptransaksiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tbkdbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(tbnmbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(tbhrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(tbjmlbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jptransaksiLayout.createSequentialGroup()
                        .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbnofak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(tbsubtot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(11, 11, 11)
                        .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, 0)
                        .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpros1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jptransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbjenisbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jptransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jptransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
        DefaultTableModel model = (DefaultTableModel)dgv3.getModel();
        int SelectedRowIndex = dgv3.getSelectedRow();
        model.removeRow(SelectedRowIndex);
         total();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Table Kosong !");
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbjmlbrgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbjmlbrgKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbjmlbrgKeyTyped

    private void tbjmlbrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbjmlbrgKeyReleased
        tot=Integer.parseInt(tbhrg.getText());
        jml=Integer.parseInt(tbjmlbrg.getText());
        ya=tot*jml;
        tbsubtot.setText(String.valueOf(ya));
    }//GEN-LAST:event_tbjmlbrgKeyReleased

    private void tbjmlbrgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbjmlbrgKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbjmlbrgKeyPressed

    private void tbjmlbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbjmlbrgActionPerformed

    }//GEN-LAST:event_tbjmlbrgActionPerformed

    private void tbtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbtotalActionPerformed

    private void tbjenisbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbjenisbrgActionPerformed

    }//GEN-LAST:event_tbjenisbrgActionPerformed

    private void tbkdbrgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkdbrgKeyTyped

    }//GEN-LAST:event_tbkdbrgKeyTyped

    private void tbkdbrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkdbrgKeyReleased

        //String cari = "SELECT * FROM t_barang WHERE kd_brg like'"+neangan+"%';";
        //tbnmbrg.setText();
        // Statement st;

    }//GEN-LAST:event_tbkdbrgKeyReleased

    private void tbkdbrgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbkdbrgKeyPressed
        DefaultTableModel model = (DefaultTableModel) dgv2.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_tbkdbrgKeyPressed

    private void tbkdbrgPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbkdbrgPropertyChange

    }//GEN-LAST:event_tbkdbrgPropertyChange

    private void tbkdbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbkdbrgActionPerformed

        DefaultTableModel model= (DefaultTableModel) dgv2.getModel();
        Statement st;
        ResultSet rs;
        try{
            String kode = tbkdbrg.getText();
            //String nama = tbnmbrg.getText();
            String sql =("SELECT * FROM t_barang WHERE kd_brg like'"+kode+"'"+"or nm_brg like'"+kode+"'");
            st=con.createStatement();
            rs=st.executeQuery(sql);
            Object[] row = new Object[5];

            while(rs.next()){
                row[0] = rs.getString("kd_brg");
                row[1] = rs.getString("nm_brg");tbnmbrg.setText(rs.getString("nm_brg"));
                row[2] = rs.getString("jenis_brg");tbjenisbrg.setText(rs.getString("jenis_brg"));
                row[3] = rs.getInt("qty");//tbjmlbrg.setText("1");
                row[4] = rs.getInt("harga_brg");tbhrg.setText(rs.getString("harga_brg"));
                
                model.addRow(row);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_tbkdbrgActionPerformed

    private void tbkdbrgInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbkdbrgInputMethodTextChanged

    }//GEN-LAST:event_tbkdbrgInputMethodTextChanged

    private void tbkdbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkdbrgMouseClicked
        DefaultTableModel model = (DefaultTableModel) dgv2.getModel();
        model.setRowCount(0);
        tbnmbrg.setText("");
        tbhrg.setText("");
        
    }//GEN-LAST:event_tbkdbrgMouseClicked

    private void tbkdbrgMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkdbrgMouseMoved

    }//GEN-LAST:event_tbkdbrgMouseMoved

    private void tbkdbrgAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbkdbrgAncestorRemoved

    }//GEN-LAST:event_tbkdbrgAncestorRemoved

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tbjmlbrg.getText().equals("")||tbkdbrg.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data ada yang kosong!");
        }else{

            //sub();

            DefaultTableModel model =(DefaultTableModel)dgv3.getModel();
            model.addRow(new Object[]{tbnofak.getText(),tgl.getText(),tbkdbrg.getText(),tbnmbrg.getText(),tbjenisbrg.getText(),tbjmlbrg.getText(),tbhrg.getText(),tbsubtot.getText()});
            //tbnmbrg.setText("");
           // tbhrg.setText("");
            //tbjmlbrg.setText("");
           // tbsubtot.setText("");
        }
        total();
        tbjmlbrg.setText("");
        tbsubtot.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dgv2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgv2MouseClicked

    }//GEN-LAST:event_dgv2MouseClicked

    private void dgv3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgv3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dgv3MouseClicked

    private void btnpros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpros1ActionPerformed
//        String Nama=null,Harga=null,Qty=null,Sub=null;
//         
//         tbstruk.setVisible(false);
//            
//         tbstruk.append("======================================================================\n");
//         tbstruk.append("\t\t\t\nNama\t\tHarga\t\tQty\tSubtotal\n");
//         tbstruk.append("======================================================================\n");   
//         
//         int a = 0;
//             for (int i =0; i< dgv3.getRowCount(); i++){
//         Nama = String.format((String)dgv3.getValueAt(i, 3));
//         Harga = String.format((String)dgv3.getValueAt(i, 6));
//         Qty = String.format((String)dgv3.getValueAt(i, 4));
//         Sub = String.format((String)dgv3.getValueAt(i, 7));
//       
////        String query = "INSERT INTO `t_transaksi`(`kd_trans`, `tgl_trans`, `nama_barang`, `qty_trans`, `harga_trans`, `total_trans`) VALUES ('"+tbnofak.getText()+"','"+tgl.getText()+"','"+ Nama+"','"+ Qty+"','"+ Harga+"','"+ Sub+"')";
////        executeSQLQuery(query,"Insert");
//         
//        
//         tbstruk.append(Nama+"\t\t"+Harga+"\t\t"+Qty+"\t"+Sub+"\n");
//             }
//         
//        try{
//          
//            ya =0;
//            byr=Integer.parseInt(tbbayar.getText());
//            ya=Integer.parseInt(tbtotal.getText());
//            kem=byr-ya;
//              tbkembali.setText(String.valueOf(kem));
//             
//            String s = String.format(tbtotal.getText());
//            String zcx = String.format(tbbayar.getText());
//            String c = String.format(tbkembali.getText());    
//            
//         tbstruk.append("======================================================================\n");   
//         tbstruk.append("                                                      \t\tTotal\t: "+s+"\n");
//         tbstruk.append("                                                    \t\tBayar\t: "+zcx+"\n");
//         tbstruk.append("                                                  \t\tKembalian\t: "+c+"\n");
//             boolean d = tbstruk.print();
//             
//             
//            if(d){
//                JOptionPane.showMessageDialog(null, "Done");
//               
//            }else{
//                JOptionPane.showMessageDialog(null, "Printing");
//            }
//           
//        }catch(HeadlessException | PrinterException | NumberFormatException e){
//            JOptionPane.showMessageDialog(this,"Data Belum Lengkap!");  
//        }
         
        simpan();
     
//         DefaultTableModel model = (DefaultTableModel) dgv3.getModel();
//            model.setRowCount(0);
//            tbbayar.setText("");
//           tbtotal.setText("");
//          tbkembali.setText("");
//        tbstruk.setText("");
    }//GEN-LAST:event_btnpros1ActionPerformed

    private void tbnmbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnmbrgActionPerformed
       
    }//GEN-LAST:event_tbnmbrgActionPerformed

    private void tbnmbrgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbnmbrgKeyPressed
       
    }//GEN-LAST:event_tbnmbrgKeyPressed

    private void tbnmbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnmbrgMouseClicked
         
    }//GEN-LAST:event_tbnmbrgMouseClicked

    private void tbnmbrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbnmbrgKeyReleased
     
    }//GEN-LAST:event_tbnmbrgKeyReleased

    private void tbnmbrgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbnmbrgKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnmbrgKeyTyped

    private void tbnmbrgPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbnmbrgPropertyChange
         
    }//GEN-LAST:event_tbnmbrgPropertyChange

    private void tbbayarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbayarMouseMoved
       
    }//GEN-LAST:event_tbbayarMouseMoved

    private void tbbayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbayarMouseExited
         
    }//GEN-LAST:event_tbbayarMouseExited

    private void tbbayarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbayarMouseDragged
       
    }//GEN-LAST:event_tbbayarMouseDragged

    private void tbbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbbayarKeyTyped
      
    }//GEN-LAST:event_tbbayarKeyTyped
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ftransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ftransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpros1;
    private javax.swing.JTable dgv2;
    private javax.swing.JTable dgv3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jptransaksi;
    private javax.swing.JTextField tbbayar;
    private javax.swing.JTextField tbhrg;
    private javax.swing.JTextField tbjenisbrg;
    private javax.swing.JTextField tbjmlbrg;
    private javax.swing.JTextField tbkdbrg;
    private javax.swing.JTextField tbkembali;
    private javax.swing.JTextField tbnmbrg;
    private javax.swing.JTextField tbnofak;
    private javax.swing.JTextArea tbstruk;
    private javax.swing.JTextField tbsubtot;
    private javax.swing.JTextField tbtotal;
    private javax.swing.JTextField tgl;
    // End of variables declaration//GEN-END:variables
}
