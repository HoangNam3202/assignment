package com.example.food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.adapter.GioHangAdapter;
import com.example.food.adapter.MonAnAdapter;
import com.example.food.object.GIoHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;

import static com.example.food.MonAnActivity.dataBaseHelper;
import static com.example.food.adapter.MonAnAdapter.ItemGiohang;

public class GioHangActivity extends AppCompatActivity {
    int Tong = 0;
    int vitri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        dataBaseHelper = new DataBaseHelper(GioHangActivity.this,"CSDL1",null,1);
        final TextView tvTongTien = findViewById(R.id.tvTongTien);
        final ArrayList<GIoHang> gIoHangArrayList = new ArrayList<>();
        final GioHangAdapter gioHangAdapter = new GioHangAdapter(GioHangActivity.this,R.layout.row_of_gio_hang,gIoHangArrayList);
        ListView listView_GioHang = findViewById(R.id.list_GioHang);
        listView_GioHang.setAdapter(gioHangAdapter);

        Button btnThanhToan = findViewById(R.id.btnThanhToan);
        Cursor cursor = dataBaseHelper.GetData("Select * from GioHang");
        while (cursor.moveToNext()){
            gIoHangArrayList.add(new GIoHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getInt(5)));
            Tong += cursor.getInt(5);
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
                                    cursor.getString(3),cursor.getString(4),cursor.getInt(5)));
                            Tong += cursor.getInt(5);
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
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioHangActivity.this,HoaDonActivity.class);
                dataBaseHelper.UpData("Delete from GioHang");
                startActivity(intent);
            }
        });
    }

}