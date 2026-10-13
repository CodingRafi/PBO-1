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

    public double getHargaSetelahDiskon()
    {
        return harga - (harga * persenDiskon / 100);
    }

    public String getNama()
    {
        return nama;
    }

    public double getHarga()
    {
        return harga;
    }

    public double getPersenDiskon()
    {
        return persenDiskon;
    }
    
    public String getKode(){
        return kode;
    }
}
