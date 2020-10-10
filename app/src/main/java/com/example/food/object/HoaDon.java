package com.example.food.object;

public class HoaDon  {
    public int MaHoaDon;
    public String EmailNguoiDung;
    public int TongTien;
    public String ThoiGian;

    public HoaDon(int maHoaDon, String emailNguoiDung, int tongTien, String thoiGian) {
        MaHoaDon = maHoaDon;
        EmailNguoiDung = emailNguoiDung;
        TongTien = tongTien;
        ThoiGian = thoiGian;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getEmailNguoiDung() {
        return EmailNguoiDung;
    }

    public void setEmailNguoiDung(String emailNguoiDung) {
        EmailNguoiDung = emailNguoiDung;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }
}
