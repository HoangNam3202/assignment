package com.example.food.object;

public class MonAn {
    public int iDMonAn;
    public String TenMonAn;
    public String TenQuan;
    public String DiaChi;
    public int HinhAnh;

    public MonAn(int iDMonAn, String tenMonAn, String tenQuan, String diaChi, int hinhAnh) {
        this.iDMonAn = iDMonAn;
        TenMonAn = tenMonAn;
        TenQuan = tenQuan;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
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

    public int getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
