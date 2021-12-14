/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsalisaproject;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author faqih
 */
public class Kelola_Barang extends javax.swing.JFrame {
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;
    PreparedStatement pst;
    Statement statBrg;

    /**
     * Creates new form Kelola_Barang
     */
    public Kelola_Barang() {
        initComponents();
        setBounds(500,200,1025,710);
        setResizable(false);
        jComboBox1.setVisible(false);
        
        String[] header = {"kode_barang","Nama_barang","Jenis_barang","warna","ukuran_kerudung","ukuran_wajah","Jenis_kain","harga_satuan"};
        model = new DefaultTableModel(header,0);
        table1.setModel(model);
        tampil();
    }
private void koneksi() {
	try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/tsalisa"; //url database
            String user="root"; //user database
            String pass=""; //password database
            con = DriverManager.getConnection(url,user,pass);
            statBrg = con.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            rs = statBrg.executeQuery("select * from barang");
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.exit(0);
            }
    }
private void kosong(){
        tkb.setText(null);
        tcb.setSelectedItem(this);
        jComboBox1.setSelectedItem(this);
        jComboBox2.setSelectedItem(this);  
        jComboBox3.setSelectedItem(this);
        jComboBox4.setSelectedItem(this);
        chs.setText(null);
    }
private void cariData(String key){
        try{
            int no=1;
            Object[] judul_kolom = {"No","Kode Barang", "Nama Barang", "Jenis Barang", "Warna", "Ukuran Kerudung", "Jenis Kain", "Harga Satuan"};
            model=new DefaultTableModel(null,judul_kolom);
            table1.setModel(model);
            
            Connection conn=(Connection)configDB.configDBB();
            Statement stt=conn.createStatement();
            model.getDataVector().removeAllElements();
            
            rs=stt.executeQuery("SELECT * from barang WHERE kode_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%' OR jenis_barang LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    no++,
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_barang"),
                    rs.getString("warna"),
                    rs.getString("ukuran_kerudung"),
                    rs.getString("jenis_kain"),
                    rs.getString("harga_satuan")  
                };
               model.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
    public void tampil(){
      DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("Warna");
        model.addColumn("Ukuran Kerudung");
        model.addColumn("Jenis Kain");
        model.addColumn("Harga Satuan");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from barang";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
            }
            table1.setModel(model);
        } catch (Exception e) { 
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        tkb = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lala = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chs = new javax.swing.JTextField();
        btambah = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        tcb = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel3.setText("Jenis Barang");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 120, 110, 16);

        tkb.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        tkb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkbActionPerformed(evt);
            }
        });
        getContentPane().add(tkb);
        tkb.setBounds(50, 80, 150, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_back_26px_1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(0, 0, 30, 40);

        lala.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lala.setText("Kode Barang ");
        getContentPane().add(lala);
        lala.setBounds(90, 30, 102, 17);

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel5.setText("Warna");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 180, 110, 17);

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel7.setText("Pilih Barang");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(220, 60, 120, 20);

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel8.setText("Jenis Kain");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 180, 110, 20);

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel9.setText("Harga Satuan");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 240, 110, 20);

        chs.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(chs);
        chs.setBounds(50, 260, 150, 30);

        btambah.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_add_16px.png"))); // NOI18N
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah);
        btambah.setBounds(50, 380, 113, 30);

        bedit.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        bedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_edit_16px.png"))); // NOI18N
        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit);
        bedit.setBounds(180, 380, 90, 30);

        bhapus.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_delete_16px.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus);
        bhapus.setBounds(290, 380, 100, 30);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 470, 980, 250);

        tcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Kerudung", "Cadar", "Masker" }));
        tcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcbActionPerformed(evt);
            }
        });
        getContentPane().add(tcb);
        tcb.setBounds(220, 80, 160, 30);

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel11.setText("Ukuran");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(220, 120, 120, 20);

        jLabel12.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel12.setText("Cari");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(760, 430, 40, 30);

        cari.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });
        getContentPane().add(cari);
        cari.setBounds(800, 430, 170, 30);

        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(50, 140, 150, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL", "XXL" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(220, 140, 160, 30);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Merah", "Kuning", "Biru", "Coklat", "Pink", "Oren", "Hijau", "Unggu" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3);
        jComboBox3.setBounds(50, 200, 150, 30);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Katun", "Linen", "Drill", "Lyica", "Organiza", "Polyester", "Swede", "Sutra", "Wool", "Sipon", "Rayon" }));
        getContentPane().add(jComboBox4);
        jComboBox4.setBounds(220, 200, 160, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/LOGO HIJAB.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 50, 730, 570);

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel4.setText("Kode Barang ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 60, 102, 17);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox5);
        jComboBox5.setBounds(470, 60, 56, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tkbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkbActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        try{
            koneksi();
            statBrg = con.createStatement();
            
            String sql = "INSERT INTO barang VALUES ('"+tkb.getText()+"','"+tcb.getSelectedItem()+"','"+jComboBox1.getSelectedItem()+"','"+jComboBox3.getSelectedItem()+"','"+jComboBox2.getSelectedItem()+"','"+jComboBox4.getSelectedItem()+"','"+chs.getText()+"')";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statBrg.executeUpdate(sql);
            
            tampil();
            statBrg.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil simpan");
            kosong();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_btambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void tcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcbActionPerformed
    if (tcb.getSelectedItem().equals("Kerudung")) {
        jComboBox1.setVisible(true);
        String[] place= {"Segitiga", "Segi Empat","Pasmina","Bergo","Syiria"};  
        DefaultComboBoxModel mod = new DefaultComboBoxModel(place);
        jComboBox1.setModel(mod);
    }else if(tcb.getSelectedItem().equals("Masker")){
        jComboBox1.setVisible(true);
        String[] place= {"Scuba", "Buff","Duckbill","Kain Double","Kain Triple"};  
        DefaultComboBoxModel mod = new DefaultComboBoxModel(place);
        jComboBox1.setModel(mod);
    }else if(tcb.getSelectedItem().equals("Cadar")){
        jComboBox1.setVisible(true);
        String[] place= {"Massir", "Yaman","Poni","Bandana","Hadramaut Beast"};  
        DefaultComboBoxModel mod = new DefaultComboBoxModel(place);
        jComboBox1.setModel(mod);
    }else{
    jComboBox1.setVisible(false);
    }
    
    }//GEN-LAST:event_tcbActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        try{
            koneksi();
            statBrg = con.createStatement();
            
            String sql ="UPDATE barang SET nama_barang = '"+tcb.getSelectedItem()+"', jenis_barang = '"+jComboBox1.getSelectedItem()+"', warna = '"+jComboBox3.getSelectedItem()+"',ukuran_kerudung= '"+jComboBox2.getSelectedItem()+"',jenis_kain= '"+jComboBox4.getSelectedItem()+"',harga_satuan= '"+chs.getText()+"' WHERE kode_barang = '"+tkb.getText()+"'";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statBrg.executeUpdate(sql);
            tampil();
            statBrg.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil di perbaharui");
            
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_beditActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int tampung = table1.rowAtPoint(evt.getPoint());
        String a=table1.getValueAt(tampung, 1).toString();
        tkb.setText(a);
        String ab=table1.getValueAt(tampung, 2).toString();
        tcb.setSelectedItem(ab);
        String ac=table1.getValueAt(tampung, 3).toString();
        jComboBox1.setSelectedItem(ac);
        String ad=table1.getValueAt(tampung, 4).toString();
        jComboBox2.setSelectedItem(ad);
        String ae=table1.getValueAt(tampung, 5).toString();
        jComboBox3.setSelectedItem(ae);
        String af=table1.getValueAt(tampung, 6).toString();
        jComboBox4.setSelectedItem(af);
        String ag=table1.getValueAt(tampung, 7).toString();
        chs.setText(ag);
    }//GEN-LAST:event_table1MouseClicked

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
 try{
            koneksi();
            statBrg = con.createStatement();
            String sql ="DELETE from barang where kode_barang='"+tkb.getText()+"'";
           
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statBrg.executeUpdate(sql);
            tampil();
            statBrg.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil dihapus");
            kosong();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        String key=cari.getText();
        System.out.println(key);  
        
        if(key!=""){
            cariData(key);
        }else{
            tampil();
        }
    }//GEN-LAST:event_cariKeyReleased

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
            java.util.logging.Logger.getLogger(Kelola_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kelola_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kelola_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kelola_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kelola_Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton btambah;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField chs;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lala;
    private javax.swing.JTable table1;
    private javax.swing.JComboBox<String> tcb;
    private javax.swing.JTextField tkb;
    // End of variables declaration//GEN-END:variables
}