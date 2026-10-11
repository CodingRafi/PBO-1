/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parking;

/**
 *
 * @author impst
 */
public class DiskonMember {
    String levelMember;
    double persenDiskon;
    double maxDiskon;

    public DiskonMember(String levelMember, double persenDiskon, double maxDiskon) {
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
}
