/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author TIENNH
 */
public class User {

    private String id;
    private String userName;
    private String passWord;
    private NhomMau nhomMau;
    private String sdt;
    private Date ngayHienMau;
    private Date ngayXetNghiem;
    private String idBV;
    private String diaChi;
    private String thoiGianRanh;
    private String tinhTrang;
    private String ten;
    private String diemSucKhoe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public NhomMau getNhomMau() {
        return nhomMau;
    }

    public void setNhomMau(NhomMau nhomMau) {
        this.nhomMau = nhomMau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayHienMau() {
        return ngayHienMau;
    }

    public void setNgayHienMau(Date ngayHienMau) {
        this.ngayHienMau = ngayHienMau;
    }

    public Date getNgayXetNghiem() {
        return ngayXetNghiem;
    }

    public void setNgayXetNghiem(Date ngayXetNghiem) {
        this.ngayXetNghiem = ngayXetNghiem;
    }

    public String getIdBV() {
        return idBV;
    }

    public void setIdBV(String idBV) {
        this.idBV = idBV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThoiGianRanh() {
        return thoiGianRanh;
    }

    public void setThoiGianRanh(String thoiGianRanh) {
        this.thoiGianRanh = thoiGianRanh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiemSucKhoe() {
        return diemSucKhoe;
    }

    public void setDiemSucKhoe(String diemSucKhoe) {
        this.diemSucKhoe = diemSucKhoe;
    }

 

    public User(String id, String userName, String passWord, NhomMau nhomMau, String sdt, Date ngayHienMau,
            Date ngayXetNghiem, String idBV, String diaChi, String thoiGianRanh, String tinhTrang, String ten,String diemSucKhoe) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nhomMau = nhomMau;
        this.sdt = sdt;
        this.ngayHienMau = ngayHienMau;
        this.ngayXetNghiem = ngayXetNghiem;
        this.idBV = idBV;
        this.diaChi = diaChi;
        this.thoiGianRanh = thoiGianRanh;
        this.tinhTrang = tinhTrang;
        this.ten = ten;
        this.diemSucKhoe=diemSucKhoe;
    }

}
