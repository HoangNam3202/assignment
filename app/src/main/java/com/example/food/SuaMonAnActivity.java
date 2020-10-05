package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class SuaMonAnActivity extends AppCompatActivity {
    ImageView imageView_MonAn;
    int REQUEST_PICK = 1;
    int REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon_an);
        Button btnSua = findViewById(R.id.btnSua);
        imageView_MonAn = findViewById(R.id.imageView_MonAn);
        final EditText edtTenMon = findViewById(R.id.edtTenSp);
        final EditText edtQuan = findViewById(R.id.edtTenQuan);
        final EditText edtDiaChiQuan = findViewById(R.id.edtDiaChiQuan);
        final EditText edtGia = findViewById(R.id.edtGia);
        dataBaseHelper = new DataBaseHelper(SuaMonAnActivity.this,"CSDL1",null,1);

//        final Spinner spin = (Spinner) findViewById(R.id.spnTinhThanh);
//        final String[] country = {"","Hà Nội", "Hải Phòng", "Đà Nẵng", "Cần Thơ", "Đắk Lắk"};
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(aa);

        Intent intent = getIntent();
        String TenCanSua = intent.getStringExtra("TenMonAnCanSua");
        String TenQuanCanSua = intent.getStringExtra("TenQuanCanSua");
        String DiaChiCanSua = intent.getStringExtra("DiaChiCanSua");
        int GiaCanSua = intent.getIntExtra("GiaCanSua",0);
        final int vitri = intent.getIntExtra("Vitri",0);
        Toast.makeText(this, ""+vitri, Toast.LENGTH_SHORT).show();
        edtTenMon.setText(TenCanSua);
        edtQuan.setText(TenQuanCanSua);
        edtDiaChiQuan.setText(DiaChiCanSua);
        edtGia.setText(""+GiaCanSua);

        imageView_MonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_PICK);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView_MonAn.getDrawable();
                Bitmap bitmap =  bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] anh = byteArray.toByteArray();
//                dataBaseHelper.UpData("Update MonAn set TenMonAn = '"+edtTenMon.getText().toString()+"', " +
//                        " TenQuan = '"+edtQuan.getText().toString()+"', DiaChi = '"+edtDiaChiQuan.getText().toString()+"',Hinh = '"+anh+"',Gia = '"+ Integer.parseInt(edtGia.getText().toString())+"'" +
//                        " where Id = '"+vitri+"' ");
                dataBaseHelper.Update_DOVAT(edtTenMon.getText().toString(),edtQuan.getText().toString(),
                        edtDiaChiQuan.getText().toString(),anh,Integer.parseInt(edtGia.getText().toString()),vitri);
                Intent intent1 = new Intent(SuaMonAnActivity.this,MonAnActivity.class);
                startActivity(intent1);
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