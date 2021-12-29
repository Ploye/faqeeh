/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsalisaproject;

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Yorha
 */
public class KoneksiDB {

     Connection connection;
    Statement stm;
      public  KoneksiDB(){
          
       try{
           connection = DriverManager.getConnection("jdbc:mysql://localhost/db_akademik","root","");
           stm = connection.createStatement();
       
           // mysqlconfig=DriverManager.getConnection(url, user, pass);    
          // JOptionPane.showMessageDialog(null, "Terkoneksi Database!");          
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Terkoneksi!");
           
       }catch(Exception e){
           e.printStackTrace();
       }
       
   }
      
      
}
   
