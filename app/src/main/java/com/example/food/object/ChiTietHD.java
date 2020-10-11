package com.example.food.object;

public class ChiTietHD {
    public int MaHoaDon,Gia,SoLuong;
    public String TenMonAn,TenQuan,DiaChi,Email,ThoiGian;

    public ChiTietHD(int maHoaDon, int gia, int soLuong, String tenMonAn, String tenQuan, String diaChi, String email, String thoiGian) {
        MaHoaDon = maHoaDon;
        Gia = gia;
        SoLuong = soLuong;
        TenMonAn = tenMonAn;
        TenQuan = tenQuan;
        DiaChi = diaChi;
        Email = email;
        ThoiGian = thoiGian;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
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

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }
}
