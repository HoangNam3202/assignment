package com.example.food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.adapter.GioHangAdapter;
import com.example.food.adapter.MonAnAdapter;
import com.example.food.object.GIoHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.example.food.MainActivity.Address;
import static com.example.food.MainActivity.Email;
import static com.example.food.MainActivity.Province;
import static com.example.food.MainActivity.Pw;
import static com.example.food.MainActivity.SDT;
import static com.example.food.MainActivity.TenNguoiDung;
import static com.example.food.MainDangNhap.check_internet;
import static com.example.food.MonAnActivity.dataBaseHelper;
import static com.example.food.adapter.GioHangAdapter.MangCanXoa;
import static com.example.food.adapter.GioHangAdapter.edtSoLuong;
import static com.example.food.adapter.MonAnAdapter.ItemGiohang;

public class GioHangActivity extends AppCompatActivity {
    public  static int Tong = 0;
    int vitri ;
    public static ArrayList<GIoHang> gIoHangArrayList;
    public static GioHangAdapter gioHangAdapter;
    static GioHangActivity gioHangActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        gioHangActivity = this;
        dataBaseHelper = new DataBaseHelper(GioHangActivity.this,"CSDL1",null,1);
        final TextView tvTongTien = findViewById(R.id.tvTongTien);
        gIoHangArrayList = new ArrayList<>();
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_of_gio_hang,gIoHangArrayList);
        ListView listView_GioHang = findViewById(R.id.list_GioHang);
        listView_GioHang.setAdapter(gioHangAdapter);

        Button btnThanhToan = findViewById(R.id.btnThanhToan);

        Intent intent = getIntent();
//        final String Email = intent.getStringExtra("Email");
        Tong = 0;
        final Cursor cursor = dataBaseHelper.GetData("Select * from GioHang where EmailnNguoiDung = '"+Email+"'");
        while (cursor.moveToNext()){
            gIoHangArrayList.add(new GIoHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
            Tong += cursor.getInt(5) * cursor.getInt(6);
        }
        listView_GioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xoá đơn hàng");
                builder.setMessage("Bạn có thực sự muốn xoá "+gIoHangArrayList.get(i).TenMonAn+"?");
                vitri = i;
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHelper.UpData("Delete from GioHang where Id = '"+gIoHangArrayList.get(vitri).iDMonAn+"' and EmailnNguoiDung = '"+Email+"'");
                        Cursor cursor = dataBaseHelper.GetData("Select * from GioHang where EmailnNguoiDung = '"+Email+"'");
                        gIoHangArrayList.clear();
                        Tong = 0;
                        while (cursor.moveToNext()){
                            gIoHangArrayList.add(new GIoHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                                    cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
                            Tong += cursor.getInt(5) * cursor.getInt(6);
                        }
                        gioHangAdapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.create().show();
                return false;
            }
        });
        listView_GioHang.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                tvTongTien.setText(""+Tong+" $");
            }
        });
        if (cursor.getCount() > 0) {
            btnThanhToan.setEnabled(true);
            btnThanhToan.setTextColor(Color.parseColor("#000"));
        }
        else {
            btnThanhToan.setEnabled(false);
            btnThanhToan.setTextColor(Color.parseColor("#FF817D7D"));
        }
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_internet) {
                    Intent intent = new Intent(GioHangActivity.this,HoaDonActivity.class);
                    final int random = new Random().nextInt(1000000000);
                    Calendar c = Calendar.getInstance();
                    Date date = c.getTime();
//                Toast.makeText(GioHangActivity.this, ""+date, Toast.LENGTH_SHORT).show();
//                dataBaseHelper.UpData("drop table HoaDon");
//                dataBaseHelper.UpData("drop table ChiTietHD");
                    dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS ChiTietHD(MaHoaDon Integer ," +
                            "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), EmailnNguoiDung varchar(35), Gia Integer, SoLuong Integer,ThoiGian char(30))");
                    dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS HoaDon(MaHoaDon Integer ," +
                            "EmailnNguoiDung varchar(35), TongTien Integer, ThoiGian char(30))");
                    dataBaseHelper.UpData("Insert into HoaDon values('"+random+"','"+Email+"','"+Tong+"','"+date+"')");
                    for(int i =0 ; i < gIoHangArrayList.size(); i++){
                        dataBaseHelper.UpData("Insert into ChiTietHD values('"+random+"','"+gIoHangArrayList.get(i).getTenMonAn()+"'," +
                                "'"+gIoHangArrayList.get(i).getTenQuan()+"','"+gIoHangArrayList.get(i).getDiaChi()+"'," +
                                "'"+Email+"','"+gIoHangArrayList.get(i).getGia()+"','"+gIoHangArrayList.get(i).getSoLuong()+"','"+date+"')");
                    }
//                    Cursor contro = dataBaseHelper.GetData("Select *from HoaDon");
//                    while (contro.moveToNext()){
//                        Toast.makeText(GioHangActivity.this, ""+contro.getString(3), Toast.LENGTH_SHORT).show();
//                    }
                    tvTongTien.setText("0"+" $");
                    Tong = 0;
                    dataBaseHelper.UpData("Delete from GioHang where EmailnNguoiDung = '"+Email+"'");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(GioHangActivity.this, "Vui Lòng Kết Nối Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView tvXoaItem = findViewById(R.id.tvXoaItem);
        tvXoaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MangCanXoa.size() <= 0) {
                    View inflatedView = getLayoutInflater().inflate(R.layout.row_of_gio_hang, null);
                    CheckBox checkBox = (CheckBox)inflatedView.findViewById(R.id.checkBox);
                    checkBox.setVisibility(View.VISIBLE);
                }
                else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                    builder.setTitle("Xoá đơn hàng");
                    builder.setMessage("Bạn có thực sự muốn xoá ?");
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i=0; i < MangCanXoa.size();i++){
                                dataBaseHelper.UpData("delete from GioHang where Id = '"+MangCanXoa.get(i)+"' and EmailnNguoiDung = '"+Email+"'");
                            }
                            Tong = 0;
                            Cursor cursor = dataBaseHelper.GetData("select * from GioHang where EmailnNguoiDung = '"+Email+"'");
                            gIoHangArrayList.clear();
                            while (cursor.moveToNext()){
                                gIoHangArrayList.add(new GIoHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                                        cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
                                Tong += cursor.getInt(5) * cursor.getInt(6);
                            }
                            gioHangAdapter.notifyDataSetChanged();
                            MangCanXoa.clear();
                        }
                    });
                    builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();

                }
            }
        });

        TextView tvNguoiDung_GioHang = findViewById(R.id.tvTenNguoiDung_GioHang);
        TextView tvDiaChi_GioHang = findViewById(R.id.tvDiaChi_GioHang);
        TextView tvSDT_GioHang = findViewById(R.id.tvSDT);
        TextView btnChinhSuaThongTin = findViewById(R.id.btnChinhSuaThongTin);

        Cursor cursor1 = dataBaseHelper.GetData("Select * from TaiKhoan where Email = '"+Email+"'");
        while (cursor1.moveToNext()) {
            tvNguoiDung_GioHang.setText(cursor1.getString(1));
            tvDiaChi_GioHang.setText(cursor1.getString(3));
            tvSDT_GioHang.setText(""+cursor1.getInt(5));
        }

        btnChinhSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GioHangActivity.this,ChinhsuaThongTin.class);
                Cursor cursor1 = dataBaseHelper.GetData("Select * from TaiKhoan where Email = '"+Email+"'");
                while (cursor1.moveToNext()) {
                    intent1.putExtra("Email",cursor1.getString(2));
                    intent1.putExtra("TenNguoiDung",cursor1.getString(1));
                    intent1.putExtra("Address",cursor1.getString(3));
                    intent1.putExtra("SDT",cursor1.getInt(5));
                    intent1.putExtra("Pass",cursor1.getString(6));
                    intent1.putExtra("TinhThanh",cursor1.getString(4));
                }
                startActivity(intent1);
            }
        });
    }
    public static GioHangActivity getInstance(){
        return   gioHangActivity;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(GioHangActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}