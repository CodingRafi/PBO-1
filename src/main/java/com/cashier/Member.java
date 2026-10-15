/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

/**
 *
 * @author impst
 */
public class Member {
    private String kode;
    private String nama;
    private String noHp;
    private BadgeMember badge;
    
    public Member(String kode, String nama, String noHp, BadgeMember badge) {
        this.kode = kode;
        this.nama = nama;
        this.noHp = noHp;
        this.badge = badge;
    }
    
    public String getKode(){
        return this.kode;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    public String getNoHp(){
        return this.noHp;
    }
    
    public BadgeMember getBadge(){
        return this.badge;
    }
}
