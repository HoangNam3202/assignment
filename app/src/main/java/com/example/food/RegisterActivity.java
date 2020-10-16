package com.example.food;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        dataBaseHelper = new DataBaseHelper(RegisterActivity.this,"CSDL1",null,1);
        TextView textview_Login = findViewById(R.id.textview_Login);
        final EditText edittext_username = findViewById(R.id.edittext_username);
        final EditText edittext_email = findViewById(R.id.edittext_email);
        final EditText edittext_address = findViewById(R.id.edittext_address);
//        final EditText edittext_provice = findViewById(R.id.edittext_tinhthanh);
        final EditText edittext_number = findViewById(R.id.edittext_number);
        final EditText edittext_password = findViewById(R.id.edittext_password);
        final EditText edittext_cnf_password = findViewById(R.id.edittext_cnf_password);
        Button btnDangKi = findViewById(R.id.button_register);
        final String[] country = {"Hà Nội", "Hải Phòng", "Đà Nẵng", "Cần Thơ", "Đắk Lắk"};
        final Spinner spin = (Spinner) findViewById(R.id.spnTinhThanh);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
//        dataBaseHelper.UpData("Drop table TaiKhoan");
        dataBaseHelper.UpData("Create table if not exists TaiKhoan(Hinh Blob,TenNguoiDung varchar(35), Email varchar(30) ," +
                "DiaChi varchar(50),Tinh varchar(20), SDT integer, Pass varchar(20))");

        final String check_Mail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserName = edittext_username.getText().toString();
                final String Email = edittext_email.getText().toString();
                final String Address = edittext_address.getText().toString();
                final String Province = spin.getSelectedItem().toString();
                final String PhoneNB = edittext_number.getText().toString();
                final String Pass = edittext_password.getText().toString();
                final String CFPass = edittext_cnf_password.getText().toString();
//                String Location = Address + "," + Province;
                if (!edittext_username.getText().toString().equals("")) {
                    if (!edittext_email.getText().toString().equals("")) {
                        if (!edittext_address.getText().toString().equals("")) {
                            if(!Province.equals("")){
                                if (!edittext_number.getText().toString().equals("") && PhoneNB.length() <= 11) {
                                    if (!edittext_password.getText().toString().equals("") && Pass.length() >= 5) {
                                        if (!edittext_cnf_password.getText().toString().equals("")) {
                                            Cursor cursor = dataBaseHelper.GetData("Select * from TaiKhoan where Email = '"+edittext_email.getText().toString()+"'");
                                            if (cursor.getCount()>0) {
                                                edittext_email.setError("Email đã tồn tại");
                                            }
                                            else {
                                                dataBaseHelper.UpData("Insert into TaiKhoan values(null,'"+UserName+"','"+Email+"'," +
                                                        "'"+Address+"','"+Province+"','"+PhoneNB+"','"+Pass+"')");
                                                Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(RegisterActivity.this,MainDangNhap.class);
                                                finish();
                                            }
                                        }
                                        else {
                                            edittext_cnf_password.setError("Chưa điền hoặc không khớp với mật khẩu");
                                        }
                                    }
                                    else {
                                            edittext_password.setError("Không để rỗng và phải lớn hơn 5 ký tự");
                                    }
                                }
                                else {
                                    edittext_number.setError("Chưa điền hoặc sai định dạng");
                                }
                            }
                            else {
                                spin.setBackgroundResource(R.drawable.bg_error);
                            }
                        }
                        else {
                            edittext_address.setError("Chưa điền");
                        }
                    }
                    else {
                        edittext_email.setError("Chưa điền hoặc sai định dạng");
                    }
                }
                else {
                    edittext_username.setError("Chưa điền");
                }
            }
        });
        textview_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainDangNhap.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
