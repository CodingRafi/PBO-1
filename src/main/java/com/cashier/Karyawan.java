/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

/**
 *
 * @author Sabarni Melati K
 */
public class Karyawan {
    private String id;
    private String nama;
    private String nip;
    private String username;
    private String password;

    public Karyawan(String id, String nama, String nip, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.nip = nip;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    public String getNip(){
        return this.nip;
    }
}
