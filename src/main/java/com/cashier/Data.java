package com.cashier;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;

/**
 *
 * @author muham
 */
public class Data {
    public static ArrayList<Karyawan> karyawan = new ArrayList<>();
    public static ArrayList<Member> member = new ArrayList<>();
    public static ArrayList<BadgeMember> diskonMember = new ArrayList<>();
    public static ArrayList<Produk> produk = new ArrayList<>();
    public static ArrayList<Transaksi> transaksi = new ArrayList<>();
    public static Karyawan karyawanLogin = null;
    public static int incrementTransaksi = 1;
    public static int incrementTransaksiProduk = 1;
    
    public static void seedData(){
        // Inisiasi data awal karyawan
        karyawan.add(new Karyawan("1", "Karyawan 1", "11111", "karyawan_1", "000000"));
        karyawan.add(new Karyawan("2", "Karyawan 2", "22222", "karyawan_2", "000000"));
       
        // Inisiasi data awal Diskon Member
        diskonMember.add(new BadgeMember("Bronze", 5, 50000));
        diskonMember.add(new BadgeMember("Silver", 10, 100000));
        diskonMember.add(new BadgeMember("Gold", 15, 150000));
        
        // Inisiasi data awal Member
        member.add(new Member("001", "Muhamad Rafi Prasetya", "081574434628", diskonMember.get(2)));
        member.add(new Member("002", "Sabarni Melati Kasuma", "089637049229", diskonMember.get(2)));
        member.add(new Member("003", "Sarah", "089669560516", diskonMember.get(0)));
        member.add(new Member("004", "Dwi Nuryanto", "089698043922", diskonMember.get(1)));
        
        // Inisiasi data awal Produk
        produk.add(new Produk("1", "001", "Produk 1", 100000, 5));
        produk.add(new Produk("2", "002", "Produk 2", 130000, 6));
        produk.add(new Produk("3", "003", "Produk 3", 200000, 3));
        produk.add(new Produk("4", "004", "Produk 4", 40000, 10));
        produk.add(new Produk("5", "005", "Produk 5", 160000, 4));
        produk.add(new Produk("6", "006", "Produk 6", 200000, 7));
        produk.add(new Produk("7", "007", "Produk 7", 300000, 2));
    }
}
