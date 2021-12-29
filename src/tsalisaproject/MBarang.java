/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsalisaproject;


public class MBarang {
    private int kdbrg;
    private String nmbrg;
    private String jenisbrg;
    private int qtybrg;
    private int hargabrg;
    
    public  MBarang (int kd_brg,String nm_brg,String jenis_brg,int qty_brg,int harga_brg){
        this.kdbrg = kd_brg;
        this.nmbrg = nm_brg;
        this.jenisbrg = jenis_brg;
        this.qtybrg = qty_brg;
        this.hargabrg = harga_brg;
        
    }
     public int getkdbrg(){
        return kdbrg;
    }
    public String getnmbrg(){
        return nmbrg;
    }
    public String getjenisbrg(){
        return jenisbrg;
    }
     public int getqty(){
        return qtybrg;
    }
    public int gethargabrg(){
        return hargabrg;
    }
   
}
