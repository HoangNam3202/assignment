package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.adapter.ChiTietHDAdapter;
import com.example.food.object.ChiTietHD;

import java.util.ArrayList;
import java.util.List;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class ChiTietHDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_h_d);
        dataBaseHelper = new DataBaseHelper(ChiTietHDActivity.this,"CSDL1",null,1);
        Intent intent = getIntent();
        int MaHoaDon_ChiTiet = intent.getIntExtra("IDhoaDon",0);
        String ThoiGian = intent.getStringExtra("ThoiGian");
        Toast.makeText(this, ""+MaHoaDon_ChiTiet, Toast.LENGTH_SHORT).show();
        ArrayList<ChiTietHD> chiTietHDArrayList = new ArrayList<>();
        ChiTietHDAdapter chiTietHDAdapter = new ChiTietHDAdapter(ChiTietHDActivity.this,R.layout.row_of_chi_tiet_hoa_don,chiTietHDArrayList);
        ListView lv_ChiTiet = findViewById(R.id.list_ChiTietHD);
        lv_ChiTiet.setAdapter(chiTietHDAdapter);
        TextView tvMaHoaDon = findViewById(R.id.tvMaHoaDon);
        TextView tvNgayDat = findViewById(R.id.tvNgayDat);
        tvMaHoaDon.setText("Mã hóa đơn : "+MaHoaDon_ChiTiet);
        tvNgayDat.setText("Ngày đặt : "+ThoiGian);

        Cursor cursor = dataBaseHelper.GetData("Select * from ChiTietHD where MaHoaDon = '"+MaHoaDon_ChiTiet+"'");
        while (cursor.moveToNext()) {
            chiTietHDArrayList.add(new ChiTietHD(cursor.getInt(0),cursor.getInt(5),cursor.getInt(6),
                    cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(7)));
        }
    }
}