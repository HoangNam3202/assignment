package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.food.adapter.HoaDonAdapter;
import com.example.food.object.HoaDon;

import java.util.ArrayList;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class HoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        dataBaseHelper = new DataBaseHelper(HoaDonActivity.this,"CSDL1",null,1);
        final ArrayList<HoaDon> hoaDonArrayList = new ArrayList<>();
        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this,R.layout.row_of_hoa_don,hoaDonArrayList);
        ListView listView_HoaDon = findViewById(R.id.list_HoaDon);
        listView_HoaDon.setAdapter(hoaDonAdapter);

        Cursor cursor = dataBaseHelper.GetData("Select * from HoaDon");
        while (cursor.moveToNext()) {
            int MaHoaDon = cursor.getInt(0);
            String EmailNguoiDung = cursor.getString(1);
            int TongTien = cursor.getInt(2);
            String Ngay = cursor.getString(3);
            hoaDonArrayList.add(new HoaDon(MaHoaDon,EmailNguoiDung,TongTien,Ngay));
        }
        listView_HoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HoaDonActivity.this,ChiTietHDActivity.class);
                intent.putExtra("IDhoaDon",hoaDonArrayList.get(i).getMaHoaDon());
                intent.putExtra("ThoiGian",hoaDonArrayList.get(i).getThoiGian());
                startActivity(intent);
            }
        });
    }
}