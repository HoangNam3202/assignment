package com.example.food.object;

public class TaiKhoan {
    public String TenNguoiDung;
    public String Email;
    public String DiaChi;
    public String Pass;

    public TaiKhoan(String tenNguoiDung, String email, String diaChi, String pass) {
        TenNguoiDung = tenNguoiDung;
        Email = email;
        DiaChi = diaChi;
        Pass = pass;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
