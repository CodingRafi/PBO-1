/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

import java.util.ArrayList;
/**
 *
 * @author muham
 */
public class Transaksi {
    private String id;
    private String tanggal;
    private Karyawan karyawan;
    private Member member;
    private BadgeMember badgeMember;
    private double totalKotor;
    private double diskonMember;
    private double diskonProduk;
    private double totalDiskon;
    private double totalBayar;
    private ArrayList<TransaksiProduk> transaksiProduk = new ArrayList<>();
    
    public Transaksi(String id, String tanggal){
        this.id = id;
        this.tanggal = tanggal;
    }
}
