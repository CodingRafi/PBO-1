/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cashier;

/**
 *
 * @author impst
 */
public class BadgeMember {
    private String levelMember;
    private int persenDiskon;
    private double maxDiskon;

    public BadgeMember(String levelMember, int persenDiskon, double maxDiskon) {
        this.levelMember = levelMember;
        this.persenDiskon = persenDiskon;
        this.maxDiskon = maxDiskon;
    }
    
    public double hitungPotonganMember(double totalKotor) {
        double potongan = totalKotor * persenDiskon / 100;

        if (potongan > maxDiskon) {
            potongan = maxDiskon;
        }

        return potongan;
    }
    
    public String getLevelMember(){
        return levelMember;
    }
    
    public int parsenDiskon(){
        return persenDiskon;
    }
}
