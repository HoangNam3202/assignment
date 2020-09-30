package com.example.food.object;

public class MonAn {
    public int iDMonAn;
    public String TenMonAn;
    public String TenQuan;
    public String DiaChi;
    public byte[] HinhAnh;
    public int Gia;



    public MonAn(int iDMonAn, String tenMonAn, String tenQuan, String diaChi, byte[] hinhAnh,int gia) {
        this.iDMonAn = iDMonAn;
        TenMonAn = tenMonAn;
        TenQuan = tenQuan;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        Gia = gia;
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

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
}
