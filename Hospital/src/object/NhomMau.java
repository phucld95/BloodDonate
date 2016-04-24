/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author TIENNH
 */
public class NhomMau {

    private String tenNhomMau;
    private ArrayList<String> nhomMauTT;

    public String getTenNhomMau() {
        return tenNhomMau;
    }

    public void setTenNhomMau(String tenNhomMau) {
        this.tenNhomMau = tenNhomMau;
    }

    public ArrayList<String> getNhomMauTT() {
        return nhomMauTT;
    }

    public void setNhomMauTT(ArrayList<String> nhomMauTT) {
        this.nhomMauTT = nhomMauTT;
    }

    public NhomMau(String tenNhomMau, ArrayList<String> nhomMauTT) {
        this.tenNhomMau = tenNhomMau;
        this.nhomMauTT = nhomMauTT;
    }
}
