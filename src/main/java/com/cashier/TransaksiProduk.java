/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

/**
 *
 * @author muham
 */
public class TransaksiProduk {
    private int id;
    private Produk produk;
    private int qty;
    private int persenDiskonProduk;
    private double totalDiskonProduk;
    private double totalKotor;
    private double totalBayar;
    
    public TransaksiProduk(Produk produk, int qty){
        this.id = Data.incrementTransaksiProduk;
        this.produk = produk; 
        this.qty = qty;
        HitungDiskon(this.produk, this.qty);
        
        Data.incrementTransaksiProduk++;
    }
    
    private void HitungDiskon(Produk produk, int qty){
        this.persenDiskonProduk = produk.getPersenDiskon();
        double diskonProduk = produk.getHarga() * this.persenDiskonProduk / 100;
        this.totalDiskonProduk = diskonProduk * qty;
        this.totalKotor = produk.getHarga() * qty;
        this.totalBayar = this.totalKotor - this.totalDiskonProduk;
    }
    
    public Produk getProduk(){
        return this.produk;
    }
    
    public int getQty(){
        return this.qty;
    }
    
    public double getPersenDiskonProduk(){
        return this.persenDiskonProduk;
    }
    
    public double getTotalDiskonProduk(){
        return this.totalDiskonProduk;
    }
 
    public double getTotalBayar(){
        return this.totalBayar;
    }
    
    public double getTotalKotor(){
        return this.totalKotor;
    }
    
    public int getId(){
        return this.id;
    }
}
