/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parking;

/**
 *
 * @author Refa melani
 */
public class Produk {
    private String idProduk;
    private String namaProduk;
    private double harga;
    private int stok;
    private double persenDiskonProduk;

    public Produk(String idProduk, String namaProduk, double harga, int stok, double persenDiskonProduk)
    {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
        this.persenDiskonProduk = persenDiskonProduk;
    }

    public double getHargaSetelahDiskon()
    {
        return harga - (harga * persenDiskonProduk / 100);
    }

    public String getIdProduk()
    {
        return idProduk;
    }

    public String getNamaProduk()
    {
        return namaProduk;
    }

    public double getHarga()
    {
        return harga;
    }

    public int getStok()
    {
        return stok;
    }

    public double getPersenDiskonProduk()
    {
        return persenDiskonProduk;
    }
}
