/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author TIENNH
 */
public class Hospital {
        
private String idBV;
private String userName;
private String passWord;
private String sdt;
private String diaChi;
private String ten;

    public String getIdBV() {
        return idBV;
    }

    public void setIdBV(String idBV) {
        this.idBV = idBV;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Hospital(String idBV,String userName,String passWord,String sdt,String diaChi,String ten){
    this.idBV=idBV;
    this.userName=userName;
    this.passWord=passWord;
    this.sdt=sdt;
    this.diaChi=diaChi;
    this.ten=ten;
    }


}
