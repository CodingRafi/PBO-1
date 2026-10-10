/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parking;

/**
 *
 * @author Sabarni Melati K
 */
public class Karyawan {
    private String idKaryawan;
    private String nama;
    private String shift;

    public Karyawan(String idKaryawan, String nama, String shift) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.shift = shift;
    }
    public void cetakStruk(Transaksi t) {
        System.out.println("STRUK TRANSAKSI");
        System.out.println("ID Karyawan : "+ idKaryawan);
        System.out.println("Nama : "+ nama);
        System.out.println("Shift : "+ shift);
        System.out.println("");
    }
}
