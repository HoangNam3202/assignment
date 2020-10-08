package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemMonAnActivity extends AppCompatActivity {
    int REQUEST_PICK = 2;
    int REQUEST_CODE = 1;
    ImageView imageView_MonAn;
    String TenTinhThanh;
    public static boolean check_NutSua = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        final String[] country = {"Hà Nội", "Hải Phòng", "Đà Nẵng", "Cần Thơ", "Đắk Lắk"};
        MonAnActivity.dataBaseHelper = new DataBaseHelper(ThemMonAnActivity.this,"CSDL1",null,1);

        Button btnDang = findViewById(R.id.btnDang);
        imageView_MonAn = findViewById(R.id.imageView_MonAn);
        final EditText edtTenMon = findViewById(R.id.edtTenSp);
        final EditText edtQuan = findViewById(R.id.edtTenQuan);
        final EditText edtDiaChiQuan = findViewById(R.id.edtDiaChiQuan);
        final EditText edtGia = findViewById(R.id.edtGia);

        final Spinner spin = (Spinner) findViewById(R.id.spnTinhThanh);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 TenTinhThanh = spin.getSelectedItem().toString();
                if(country[i].equals("")){
                    spin.setBackgroundResource(R.drawable.bg_error);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        imageView_MonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK);
            }
        });
        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ten_Mon = edtTenMon.getText().toString();
                String Ten_Quan = edtQuan.getText().toString();
                String Dia_Chi = edtDiaChiQuan.getText().toString() +", "+ TenTinhThanh;
                int Gia = Integer.parseInt(edtGia.getText().toString());
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView_MonAn.getDrawable();
                Bitmap bitmap =  bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] anh = byteArray.toByteArray();
                MonAnActivity.dataBaseHelper.INSERT_DOVAT(Ten_Mon,Ten_Quan,Dia_Chi,anh,Gia);
                Toast.makeText(ThemMonAnActivity.this, "Đăng thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThemMonAnActivity.this,MonAnActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
            imageView_MonAn.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_PICK && resultCode == RESULT_OK && data != null) {
            Uri uri =   data.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView_MonAn.setImageBitmap(bitmap);
                imageView_MonAn.setScaleType(ImageView.ScaleType.CENTER_CROP);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}