package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class ChinhsuaThongTin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsua_thong_tin);
        dataBaseHelper = new DataBaseHelper(ChinhsuaThongTin.this,"CSDL1",null,1);
        final EditText edittext_username = findViewById(R.id.edittext_username);
        final EditText edittext_address = findViewById(R.id.edittext_address);
        final EditText edittext_number = findViewById(R.id.edittext_number);
        final EditText edittext_password = findViewById(R.id.edittext_password);
        Button btnChinhsua = findViewById(R.id.button_register);
        final String[] country = {"Hà Nội", "Hải Phòng", "Đà Nẵng", "Cần Thơ", "Đắk Lắk"};
        final Spinner spin = (Spinner) findViewById(R.id.spnTinhThanh);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        final Intent intent = getIntent();
        String Ten = intent.getStringExtra("TenNguoiDung");
        final String Mail = intent.getStringExtra("Email");
        String DiaChi = intent.getStringExtra("Address");
        int SDT = intent.getIntExtra("SDT",0);
        String TinhThanh = intent.getStringExtra("TinhThanh");

        edittext_username.setText(Ten);
        edittext_address.setText(DiaChi);
        edittext_number.setText("0"+SDT);
        int spinnerPosition = aa.getPosition(TinhThanh);
        spin.setSelection(spinnerPosition);

        btnChinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass = intent.getStringExtra("Pass");
                final String check_Phone =  "0\\d{9,10}";
                if (!edittext_username.getText().toString().equals("")) {
                    if (!edittext_address.getText().toString().equals("")) {
                        if (!edittext_number.getText().toString().equals("") && edittext_number.getText().toString().matches(check_Phone)) {
                            Cursor cursor = dataBaseHelper.GetData("Select * from TaiKhoan where Email = '"+Mail+"' and Pass = '"+edittext_password.getText().toString()+"'");
                            if (cursor.getCount() > 0) {
                                dataBaseHelper.UpData("Update TaiKhoan set TenNguoiDung = '"+edittext_username.getText().toString()+"'," +
                                        "DiaChi = '"+edittext_address.getText().toString()+"', Tinh = '"+spin.getSelectedItem().toString()+"'," +
                                        "SDT = '"+edittext_number.getText().toString()+"' where Email = '"+Mail+"'");
                                Toast.makeText(ChinhsuaThongTin.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(ChinhsuaThongTin.this,GioHangActivity.class);
                                startActivity(intent1);
                                finish();
                                GioHangActivity.getInstance().finish();
                            }
                            else {
                                edittext_password.setError("Không đúng mật khẩu");
                            }
                        }
                        else {
                            edittext_number.setError("Chưa điền hoặc sai định dạng");
                        }
                    }
                    else {
                        edittext_address.setError("Chưa điền");
                    }
                }
                else {
                    edittext_username.setError("Chưa điền");
                }
            }
        });

    }
}