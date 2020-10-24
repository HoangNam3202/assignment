package com.example.food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.food.adapter.HoaDonAdapter;
import com.example.food.object.GIoHang;
import com.example.food.object.HoaDon;

import java.util.ArrayList;

import static com.example.food.MainActivity.Email;
import static com.example.food.MonAnActivity.dataBaseHelper;
import static com.example.food.adapter.GioHangAdapter.MangCanXoa;

public class HoaDonActivity extends AppCompatActivity {
    boolean check_list_click = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        dataBaseHelper = new DataBaseHelper(HoaDonActivity.this,"CSDL1",null,1);
        final ArrayList<HoaDon> hoaDonArrayList = new ArrayList<>();
        final HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this,R.layout.row_of_hoa_don,hoaDonArrayList);
        ListView listView_HoaDon = findViewById(R.id.list_HoaDon);
        listView_HoaDon.setAdapter(hoaDonAdapter);
        if (Email.equals("admin")) {
            Cursor cursor = dataBaseHelper.GetData("Select * from HoaDon ORDER BY ThoiGian asc");
            while (cursor.moveToNext()) {
                int MaHoaDon = cursor.getInt(0);
                String EmailNguoiDung = cursor.getString(1);
                int TongTien = cursor.getInt(2);
                String Ngay = cursor.getString(3);
                hoaDonArrayList.add(new HoaDon(MaHoaDon,EmailNguoiDung,TongTien,Ngay));
            }
        }
        else {
            Cursor cursor = dataBaseHelper.GetData("Select * from HoaDon where EmailnNguoiDung = '"+Email+"' ORDER BY ThoiGian desc");
            while (cursor.moveToNext()) {
                int MaHoaDon = cursor.getInt(0);
                String EmailNguoiDung = cursor.getString(1);
                int TongTien = cursor.getInt(2);
                String Ngay = cursor.getString(3);
                hoaDonArrayList.add(new HoaDon(MaHoaDon,EmailNguoiDung,TongTien,Ngay));
            }
        }
        listView_HoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (check_list_click) {
                    Intent intent = new Intent(HoaDonActivity.this,ChiTietHDActivity.class);
                    intent.putExtra("IDhoaDon",hoaDonArrayList.get(i).getMaHoaDon());
                    intent.putExtra("ThoiGian",hoaDonArrayList.get(i).getThoiGian());
                    startActivity(intent);
                }
            }
        });
        listView_HoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this);
                builder.setTitle("Xoá đơn hàng");
                builder.setMessage("Bạn có thực sự muốn xoá ?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHelper.UpData("delete from HoaDon where MaHoaDon = '"+hoaDonArrayList.get(i).getMaHoaDon()+"' and EmailnNguoiDung = '"+Email+"'");
                        if (Email.equals("admin")) {
                            Cursor cursor = dataBaseHelper.GetData("Select * from HoaDon where EmailnNguoiDung = '"+Email+"' ORDER BY ThoiGian desc");
                            while (cursor.moveToNext()) {
                                int MaHoaDon = cursor.getInt(0);
                                String EmailNguoiDung = cursor.getString(1);
                                int TongTien = cursor.getInt(2);
                                String Ngay = cursor.getString(3);
                                hoaDonArrayList.add(new HoaDon(MaHoaDon,EmailNguoiDung,TongTien,Ngay));
                            }
                        }
                        else {
                            Cursor cursor = dataBaseHelper.GetData("Select * from HoaDon where EmailnNguoiDung = '"+Email+"' ORDER BY ThoiGian desc");
                            while (cursor.moveToNext()) {
                                int MaHoaDon = cursor.getInt(0);
                                String EmailNguoiDung = cursor.getString(1);
                                int TongTien = cursor.getInt(2);
                                String Ngay = cursor.getString(3);
                                hoaDonArrayList.add(new HoaDon(MaHoaDon,EmailNguoiDung,TongTien,Ngay));
                            }
                        }
                        hoaDonAdapter.notifyDataSetChanged();
                        check_list_click = true;
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        check_list_click = true;
                    }
                });
                builder.create().show();
                check_list_click = false;
                return false;
            }
        });
    }
}