package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.object.HoaDon;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int REQUEST_PICK = 3;
    int REQUEST_CODE = 4;
    ImageView imageView_Avatar;
    public static String Email;
    public static String TenNguoiDung;
    public static String Address;
    public static String Province;
    public static String Pw;
    public static int SDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPreferences();
        Button btnMonAn = findViewById(R.id.btnMonAn);
        dataBaseHelper = new DataBaseHelper(MainActivity.this,"CSDL1",null,1);
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS GioHang(Id Integer primary key autoincrement," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), EmailnNguoiDung varchar(35), Gia Integer)");
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS ChiTietHD(MaHoaDon Integer ," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), EmailnNguoiDung varchar(35), Gia Integer, SoLuong Integer,ThoiGian char(30))");
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS HoaDon(MaHoaDon Integer ," +
                "EmailnNguoiDung varchar(35), TongTien Integer, ThoiGian char(30))");
        dataBaseHelper.UpData("Create table if not exists TaiKhoan(Hinh Blob,TenNguoiDung varchar(35), Email varchar(30) ," +
                "DiaChi varchar(50),Tinh varchar(20), SDT integer, Pass varchar(20))");
        dataBaseHelper.UpData("Create table if not exists ThongBao(Email varchar(35), MaDonHang Integer)");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MonAnActivity.class);
                intent.putExtra("Email", Email);
                intent.putExtra("Address", Address);
                intent.putExtra("TinhThanh", Province);
                startActivity(intent);
            }
        });
        Button btnGioHang = findViewById(R.id.btnGioHang);
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GioHangActivity.class);
                intent.putExtra("Email", Email);
                startActivity(intent);
            }
        });
        Button btnHoaDon = findViewById(R.id.btnHoaDon);
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HoaDonActivity.class);
                intent.putExtra("Email", Email);
                startActivity(intent);
            }
        });
        Button btnCaiDat = findViewById(R.id.btnCaiDat);
        btnCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("TenNguoiDung");
                editor.remove("DiaChi");
//                editor.remove("taikhoan");
                editor.remove("matkhau");
                editor.commit();
                Intent intent = new Intent(MainActivity.this, MainDangNhap.class);
                startActivity(intent);
                finish();
            }
        });

        TextView tvTenNguoiDung = findViewById(R.id.tvTenNguoiDung);
        TextView tvAddress = findViewById(R.id.tvAddress);
        imageView_Avatar = findViewById(R.id.imageView);
         TenNguoiDung = sharedPreferences.getString("TenNguoiDung","");
        Address = sharedPreferences.getString("DiaChi","");
        Province = sharedPreferences.getString("TinhThanh","");
        Email = sharedPreferences.getString("taikhoan","");
        SDT = sharedPreferences.getInt("SDT",0);
        Pw = sharedPreferences.getString("matkhau","");

        Button btnNguoiDung = findViewById(R.id.btnNguoiDung);
        btnNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserManagerActivity.class);
                startActivity(intent);
            }
        });
        if (Email.equals("admin")) {
            btnNguoiDung.setEnabled(true);
        }
        else {
            btnNguoiDung.setEnabled(false);
        }

        tvTenNguoiDung.setText(TenNguoiDung);
        tvAddress.setText(Address+", "+Province);

        imageView_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK);
            }
        });
        Cursor contro = dataBaseHelper.GetData("Select * from TaiKhoan where Email = '"+Email+"'");
        while (contro.moveToNext()){
            byte[] hinh = contro.getBlob(0);
            if(hinh != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length);
                imageView_Avatar.setImageBitmap(bitmap);
            }
        }
    }
    private void initPreferences() {
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView_Avatar.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_PICK && resultCode == RESULT_OK && data != null) {
            Uri uri =   data.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView_Avatar.setImageBitmap(bitmap);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView_Avatar.getDrawable();
                Bitmap bitmap1 =  bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] anh = byteArray.toByteArray();
                dataBaseHelper.Update_AVATAR(anh,Email);
//                imageView_Avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}