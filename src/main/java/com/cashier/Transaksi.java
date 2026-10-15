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
    private int id;
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
    
    public Transaksi(int id, String tanggal, Karyawan karyawan){
        this.id = id;
        this.tanggal = tanggal;
        this.karyawan = karyawan;
    }
    
    public void TambahTranskasiProduk(TransaksiProduk tp){
        this.transaksiProduk.add(tp);
        
        this.totalKotor += tp.getTotalKotor();
        
        if (this.member != null) {
            this.diskonMember = this.member.getBadge().hitungPotonganMember(this.totalKotor);
        }
        
        double disProduk = tp.getTotalDiskonProduk();
        
        this.diskonProduk += disProduk;
        this.totalDiskon = this.diskonProduk + this.diskonMember;
        this.totalBayar = this.totalKotor - this.totalDiskon;
    }
    
    public void HapusTransaksiProduk(int idTransaksiProduk){
        for(int j = 0; j < this.transaksiProduk.size(); j++){
            TransaksiProduk delProduk = this.transaksiProduk.get(j);
            
            if(delProduk.getId() == idTransaksiProduk){
                this.totalKotor -= delProduk.getTotalKotor();
                
                if (this.member != null) {
                    this.diskonMember = this.member.getBadge().hitungPotonganMember(this.totalKotor);
                }
                
                this.diskonProduk -= delProduk.getTotalDiskonProduk();
                this.totalDiskon = this.diskonProduk + this.diskonMember;
                this.totalBayar = this.totalKotor - this.totalDiskon;
                
                this.transaksiProduk.remove(j);
                break;
            }
        }
    }
    
    public void SetMember(Member m){
        this.member = m;
        this.badgeMember = m.getBadge();
        double potonganMember = this.member.getBadge().hitungPotonganMember(this.totalKotor);
        
        this.diskonMember = potonganMember;
        this.totalDiskon = this.diskonProduk + this.diskonMember;
        this.totalBayar = this.totalKotor - this.totalDiskon;
    }

    
    public ArrayList<TransaksiProduk> GetTransaksiProduk(){
        return this.transaksiProduk;
    }
    
    public double getTotalKotor(){
        return this.totalKotor;
    }
    
    public double getDiskonProduk(){
        return this.diskonProduk;
    }
    
    public double getDiskonMember(){
        return this.diskonMember;
    }
    
    public double getTotalDiskon(){
        return this.totalDiskon;
    }
    
    public double getTotalBayar(){
        return this.totalBayar;
    }
    
    public Member getMember(){
        return this.member;
    }
    
    public String getTanggal(){
        return this.tanggal;
    }
    
    public BadgeMember getBadgeMember(){
        return this.badgeMember;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Karyawan getKaryawan(){
        return this.karyawan;
    }
}
