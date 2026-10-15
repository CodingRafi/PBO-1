/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

/**
 *
 * @author Refa melani
 */
public class Produk {
    private String id;
    private String kode;
    private String nama;
    private double harga;
    private int persenDiskon;

    public Produk(String id, String kode, String nama, double harga, int persenDiskon)
    {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.persenDiskon = persenDiskon;
    }
   
    public String getNama()
    {
        return this.nama;
    }

    public double getHarga()
    {
        return this.harga;
    }

    public int getPersenDiskon()
    {
        return this.persenDiskon;
    }
    
    public String getKode(){
        return this.kode;
    }
}
