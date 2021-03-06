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
public class Permintaan_Pelanggan extends javax.swing.JFrame {
     public Connection con;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;
    PreparedStatement pst;
    Statement statPer;
    
//    JFrame mywindow = new JFrame();
    
    public Permintaan_Pelanggan() {
        initComponents();
        setBounds(500,200,1025,710);
        setResizable(false);
         String[] header = {"No","Kode Pesanan","Nama","No HP","Jumlah Pesanan","Total Harga","Status Pesanan","DP","Jenis Pesanan"};
        model = new DefaultTableModel(header,0);
        jTable1.setModel(model);
         if (jc.isSelected()){
            jc.setText("Terpenuhi");
        }else{
            jc.setText("Tidak Terpenuhi");
        }
        tampil();
    }
    private void kosong(){
        kp.setText(null);
        nama.setText(null);
        nohp.setText(null);
        jp.setText(null);  
        th.setText(null);
        jc.setText(null);
        dp.setText(null);
        jepes.setText(null);
    }
private void cariData(String key){
        try{
            int no=1;
            Object[] judul_kolom = {"No","Kode Pesanan", "Nama", "No HP", "Jumlah Pesanan", "Total Harga", "DP", "Jenis Pesanan"};
            model=new DefaultTableModel(null,judul_kolom);
            jTable1.setModel(model);
            
            Connection conn=(Connection)configDB.configDBB();
            Statement stt=conn.createStatement();
            model.getDataVector().removeAllElements();
            
            rs=stt.executeQuery("SELECT * from permintaan WHERE kode_pesanan LIKE '%"+key+"%' OR nama LIKE '%"+key+"%' OR no_hp LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    no++,
                    rs.getString("kode_pesanan"),
                    rs.getString("nama"),
                    rs.getString("no_hp"),
                    rs.getString("jumlah_pesanan"),
                    rs.getString("total_harga"),
                    rs.getString("status_pesanan"),
                    rs.getString("dp"),
                    rs.getString("jenis_pesanan")  
                };
               model.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
private void koneksi() {
	try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/tsalisa"; //url database
            String user="root"; //user database
            String pass=""; //password database
            con = DriverManager.getConnection(url,user,pass);
            statPer = con.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            rs = statPer.executeQuery("select * from permintaan");
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.exit(0);
            }
    }
     public void tampil(){
      DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Pesanan");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Jumlah Pesanan");
        model.addColumn("Total Harga");
        model.addColumn("Status Pesanan");
        model.addColumn("DP");
        model.addColumn("Jenis Pesanan");
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from permintaan";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)});
            }
            jTable1.setModel(model);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        kp = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        jp = new javax.swing.JTextField();
        th = new javax.swing.JTextField();
        dp = new javax.swing.JTextField();
        jepes = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jc = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel3.setText("Kode Pemesanan");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 60, 130, 30);

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel4.setText("Nama");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(220, 70, 80, 20);

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel5.setText("No Hp");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 130, 46, 17);

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel6.setText("Jumlah Pesanan");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(220, 130, 120, 17);

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel7.setText("Total Harga");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 180, 100, 30);

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel8.setText("Status Pesanan");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 180, 110, 30);

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel9.setText("DP");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(40, 240, 20, 30);

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel10.setText("Jenis Pesanan");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(220, 240, 102, 30);

        kp.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        kp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kpActionPerformed(evt);
            }
        });
        getContentPane().add(kp);
        kp.setBounds(40, 90, 140, 30);

        nama.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        getContentPane().add(nama);
        nama.setBounds(220, 90, 140, 30);

        nohp.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(nohp);
        nohp.setBounds(40, 150, 140, 30);

        jp.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(jp);
        jp.setBounds(220, 150, 140, 30);

        th.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(th);
        th.setBounds(40, 210, 140, 30);

        dp.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(dp);
        dp.setBounds(40, 270, 140, 30);

        jepes.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        getContentPane().add(jepes);
        jepes.setBounds(220, 270, 140, 30);

        jButton1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_add_16px.png"))); // NOI18N
        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(40, 350, 113, 30);

        jButton2.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_edit_16px.png"))); // NOI18N
        jButton2.setText(" Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(170, 350, 90, 30);

        jButton3.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_delete_16px.png"))); // NOI18N
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(280, 350, 100, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 440, 950, 260);

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel11.setText("Cari");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(720, 400, 30, 30);

        jTextField8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField8);
        jTextField8.setBounds(770, 400, 190, 30);

        jc.setText("Terpenuhi");
        jc.setToolTipText("");
        jc.setActionCommand("");
        jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcActionPerformed(evt);
            }
        });
        getContentPane().add(jc);
        jc.setBounds(220, 210, 140, 30);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/icons8_back_26px_1.png"))); // NOI18N
        getContentPane().add(jButton4);
        jButton4.setBounds(0, 0, 30, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/LOGO HIJAB.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 50, 730, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kpActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try{
            koneksi();
            statPer = con.createStatement();
            
            String sql ="UPDATE permintaan SET nama = '"+nama.getText()+"', no_hp = '"+nohp.getText()+"', jumlah_pesanan = '"+jp.getText()+"',total_harga= '"+th.getText()+"',status_pesanan= '"+jc.getText()+"',dp= '"+dp.getText()+"',jenis_pesanan= '"+jepes.getText()+"' WHERE kode_pesanan = '"+kp.getText()+"'";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statPer.executeUpdate(sql);
            tampil();
            statPer.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil di perbaharui");
            
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           try{
            koneksi();
            statPer = con.createStatement();
           
            String sql = "INSERT INTO permintaan VALUES ('"+kp.getText()+"','"+nama.getText()+"','"+nohp.getText()+"','"+jp.getText()+"','"+th.getText()+"','"+jc.getText()+"','"+dp.getText()+"','"+jepes.getText()+"')";
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statPer.executeUpdate(sql);
            
            tampil();
            statPer.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil simpan");
            kosong();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try{
            koneksi();
            statPer = con.createStatement();
            String sql ="DELETE from permintaan where kode_pesanan='"+kp.getText()+"'";
           
            java.sql.Connection conn=(Connection)configDB.configDBB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            statPer.executeUpdate(sql);
            tampil();
            statPer.close();
            con.close();
//            Clear();
            JOptionPane.showMessageDialog(null, "berhasil dihapus");
            kosong();
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         int tampung = jTable1.rowAtPoint(evt.getPoint());
        String a=jTable1.getValueAt(tampung, 1).toString();
        kp.setText(a);
        String ab=jTable1.getValueAt(tampung, 2).toString();
        nama.setText(ab);
        String ac=jTable1.getValueAt(tampung, 3).toString();
        nohp.setText(ac);
        String ad=jTable1.getValueAt(tampung, 4).toString();
        jp.setText(ad);
        String ae=jTable1.getValueAt(tampung, 5).toString();
        th.setText(ae);
        String af=jTable1.getValueAt(tampung, 6).toString();
        jc.setText(af);
        String ag=jTable1.getValueAt(tampung, 7).toString();
        dp.setText(ag);
        String ah=jTable1.getValueAt(tampung, 8).toString();
        jepes.setText(ah);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcActionPerformed
        if (jc.isSelected()){
            jc.setText("Terpenuhi");
        }else{
            jc.setText("Tidak Terpenuhi");
        }
    }//GEN-LAST:event_jcActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
          String key=jTextField8.getText();
        System.out.println(key);  
        
        if(key!=""){
            cariData(key);
        }else{
            tampil();
        }
    }//GEN-LAST:event_jTextField8KeyReleased

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
            java.util.logging.Logger.getLogger(Permintaan_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Permintaan_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Permintaan_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Permintaan_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Permintaan_Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JCheckBox jc;
    private javax.swing.JTextField jepes;
    private javax.swing.JTextField jp;
    private javax.swing.JTextField kp;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nohp;
    private javax.swing.JTextField th;
    // End of variables declaration//GEN-END:variables
}
