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
    private String id;
    private String nama;
    private String noHp;
    public BadgeMember diskonMember;
    
    public Member(String id, String nama, String noHp, BadgeMember diskonMember) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.diskonMember = diskonMember;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getNoHp(){
        return noHp;
    }
}
