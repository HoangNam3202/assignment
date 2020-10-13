package com.example.food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import static com.example.food.MonAnActivity.dataBaseHelper;
import static com.example.food.adapter.GioHangAdapter.MangCanXoa;
import static com.example.food.adapter.GioHangAdapter.edtSoLuong;
import static com.example.food.adapter.MonAnAdapter.ItemGiohang;

public class GioHangActivity extends AppCompatActivity {
    public  static int Tong = 0;
    int vitri ;
    public static ArrayList<GIoHang> gIoHangArrayList;
    public static GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        dataBaseHelper = new DataBaseHelper(GioHangActivity.this,"CSDL1",null,1);
        final TextView tvTongTien = findViewById(R.id.tvTongTien);
        gIoHangArrayList = new ArrayList<>();
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_of_gio_hang,gIoHangArrayList);
        ListView listView_GioHang = findViewById(R.id.list_GioHang);
        listView_GioHang.setAdapter(gioHangAdapter);

        Button btnThanhToan = findViewById(R.id.btnThanhToan);
        Tong = 0;
        final Cursor cursor = dataBaseHelper.GetData("Select * from GioHang");
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
                        dataBaseHelper.UpData("Delete from GioHang where Id = '"+gIoHangArrayList.get(vitri).iDMonAn+"'");
                        Cursor cursor = dataBaseHelper.GetData("Select * from GioHang");
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
        }
        else {
            btnThanhToan.setEnabled(false);
        }
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                dataBaseHelper.UpData("Insert into HoaDon values('"+random+"','hoangnam1101@gmail.com','"+Tong+"','"+date+"')");
                for(int i =0 ; i < gIoHangArrayList.size(); i++){
                    dataBaseHelper.UpData("Insert into ChiTietHD values('"+random+"','"+gIoHangArrayList.get(i).getTenMonAn()+"'," +
                            "'"+gIoHangArrayList.get(i).getTenQuan()+"','"+gIoHangArrayList.get(i).getDiaChi()+"'," +
                            "'hoangnam1101@gmail.com','"+gIoHangArrayList.get(i).getGia()+"','"+gIoHangArrayList.get(i).getSoLuong()+"','"+date+"')");
                }
//                    Cursor contro = dataBaseHelper.GetData("Select *from HoaDon");
//                    while (contro.moveToNext()){
//                        Toast.makeText(GioHangActivity.this, ""+contro.getString(3), Toast.LENGTH_SHORT).show();
//                    }
                tvTongTien.setText("0"+" $");
                Tong = 0;
                dataBaseHelper.UpData("Delete from GioHang");
                startActivity(intent);
                finish();
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
                                dataBaseHelper.UpData("delete from GioHang where Id = '"+MangCanXoa.get(i)+"'");
                            }
                            Tong = 0;
                            Cursor cursor = dataBaseHelper.GetData("select * from GioHang");
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
    }

}