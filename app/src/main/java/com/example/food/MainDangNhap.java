package com.example.food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainDangNhap extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DataBaseHelper db;
    CheckBox cbRemember;
    SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        db = new DataBaseHelper(this,"CSDL1",null,1);
        db.QueryData("CREATE TABLE IF NOT EXISTS DangNhap (Id INTEGER PRIMARY KEY AUTOINCREMENT, User VARCHAR(200), Pass VARCHAR(200))");

        mTextUsername =(EditText)findViewById(R.id.edittext_username);
        mTextPassword =(EditText)findViewById(R.id.edittext_password);
        mButtonLogin =(Button) findViewById(R.id.botton_login);
        mTextViewRegister =(TextView) findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainDangNhap.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        AnhXa();
        mySharedPreferences =getSharedPreferences("dataLogin", MODE_PRIVATE);

        mTextUsername.setText(mySharedPreferences.getString("taikhoan" ,""));
        mTextPassword.setText(mySharedPreferences.getString("matkhau",""));
        cbRemember.setChecked(mySharedPreferences.getBoolean("checked", false));
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if (res == true){
                    if (cbRemember.isChecked()){
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("taikhoan", user);
                        editor.putString("matkhau", pwd);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.putBoolean("checked", false);
                        editor.commit();
                    }
                    Intent Homepage = new Intent(MainDangNhap.this, MainActivity.class);
                    startActivity(Homepage);
                }else{
                    Toast.makeText(MainDangNhap.this, "Đăng nhập thất bại ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AnhXa(){
        cbRemember = (CheckBox)findViewById(R.id.checkboxRemember);
    }

}
