package com.example.food.object;

public class GIoHang {
    public int iDMonAn;
    public String TenMonAn;
    public String TenQuan;
    public String DiaChi;
    public String Email;
    public int Gia;
    public int SoLuong;

    public GIoHang(int iDMonAn, String tenMonAn, String tenQuan, String diaChi, String email, int gia, int soLuong) {
        this.iDMonAn = iDMonAn;
        TenMonAn = tenMonAn;
        TenQuan = tenQuan;
        DiaChi = diaChi;
        Email = email;
        Gia = gia;
        SoLuong = soLuong;
    }

    public int getiDMonAn() {
        return iDMonAn;
    }

    public void setiDMonAn(int iDMonAn) {
        this.iDMonAn = iDMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
