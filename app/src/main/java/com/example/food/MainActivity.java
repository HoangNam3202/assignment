package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.food.object.HoaDon;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMonAn = findViewById(R.id.btnMonAn);
        dataBaseHelper = new DataBaseHelper(MainActivity.this,"CSDL1",null,1);
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS GioHang(Id Integer primary key autoincrement," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), EmailnNguoiDung varchar(35), Gia Integer)");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Cursor cursor = dataBaseHelper.GetData("Select * from DangNhap");
        if(cursor.getCount() >0){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }
        btnMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MonAnActivity.class);
                startActivity(intent);
            }
        });
        Button btnGioHang = findViewById(R.id.btnGioHang);
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
        Button btnHoaDon = findViewById(R.id.btnHoaDon);
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HoaDonActivity.class);
                startActivity(intent);
            }
        });
    }
}