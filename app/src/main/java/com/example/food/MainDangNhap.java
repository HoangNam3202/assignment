package com.example.food;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food.broadcast.MyReceiver;
import com.google.android.material.textfield.TextInputLayout;

public class MainDangNhap extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DataBaseHelper db;
    CheckBox cbRemember;
    SharedPreferences mySharedPreferences;
    String pwd;
    String user;
    ConnectivityManager connectivityManager;
    NetworkInfo WIFI,my3G;
    public static boolean check_internet;
    BroadcastReceiver broadcastReceiver = new MyReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        WIFI = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my3G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        filter.addAction(WifiManager.EXTRA_RESULTS_UPDATED);
        this.registerReceiver(broadcastReceiver, filter);
        if (WIFI != null && WIFI.isConnected()) {
//            Toast.makeText(this, "Wifi", Toast.LENGTH_SHORT).show();
            check_internet = true;
        }
        else if (my3G != null && my3G.isConnected()) {
//            Toast.makeText(this, "3G", Toast.LENGTH_SHORT).show();
            check_internet = true;
        }
        else {
//            Toast.makeText(this, "Khong co mang", Toast.LENGTH_SHORT).show();
            check_internet = false;
        }
        db = new DataBaseHelper(this,"CSDL1",null,1);
//        db.QueryData("CREATE TABLE IF NOT EXISTS DangNhap (Id INTEGER PRIMARY KEY AUTOINCREMENT, User VARCHAR(200), Pass VARCHAR(200))");
        mySharedPreferences =getSharedPreferences("dataLogin", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mySharedPreferences.edit();

        cbRemember = (CheckBox)findViewById(R.id.checkboxRemember);
        mTextUsername =(EditText)findViewById(R.id.edittext_username);
        mTextPassword =(EditText)findViewById(R.id.edittext_password);
        mButtonLogin =(Button) findViewById(R.id.botton_login);
        mTextViewRegister =(TextView) findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_internet) {
                    Intent registerIntent = new Intent(MainDangNhap.this, RegisterActivity.class);
                    startActivity(registerIntent);
                }
                else {
                    Toast.makeText(MainDangNhap.this, "Vui Lòng Kết Nối Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String Mail_DangNhap = mySharedPreferences.getString("taikhoan","");
        String Pass_DangNhap = mySharedPreferences.getString("matkhau","");
        if (!Mail_DangNhap.equals("") && !Pass_DangNhap.equals("") && check_internet) {
            Intent intent = new Intent(MainDangNhap.this,MainActivity.class);
            Cursor cursor = db.GetData("Select * from TaiKhoan where Email = '"+user+"' and Pass = '"+pwd+"'");
            while (cursor.moveToNext()) {
                editor.putString("TenNguoiDung", cursor.getString(1));
                editor.putString("DiaChi", cursor.getString(3));
                editor.putString("TinhThanh", cursor.getString(4));
                editor.putInt("SDT", cursor.getInt(5));
            }
            startActivity(intent);
        }

        mTextUsername.setText(mySharedPreferences.getString("taikhoan" ,""));
        mTextPassword.setText(mySharedPreferences.getString("matkhau",""));
        cbRemember.setChecked(mySharedPreferences.getBoolean("checked", false));
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = mTextUsername.getText().toString().trim();
                pwd = mTextPassword.getText().toString().trim();
                if(check_internet){
                    if (!user.equals("")) {
                        if (!pwd.equals("")) {
                            Cursor cursor = db.GetData("Select * from TaiKhoan where Email = '"+user+"' and Pass = '"+pwd+"'");
                            if (cursor.getCount() > 0){
                                if (cbRemember.isChecked()){
                                    editor.putString("taikhoan", user);
                                    editor.putString("matkhau", pwd);
                                    while (cursor.moveToNext()) {
                                        editor.putString("TenNguoiDung", cursor.getString(1));
                                        editor.putString("DiaChi", cursor.getString(3));
                                        editor.putString("TinhThanh", cursor.getString(4));
                                        editor.putInt("SDT", cursor.getInt(5));
                                    }
                                    editor.putBoolean("checked", true);
                                    editor.commit();
                                }
                                else {
                                    editor.remove("taikhoan");
                                    editor.remove("matkhau");
                                    editor.putBoolean("checked", false);
                                    editor.commit();
                                }
                                Intent Homepage = new Intent(MainDangNhap.this, MainActivity.class);
                                startActivity(Homepage);
                                finish();
                            }else{
                                Toast.makeText(MainDangNhap.this, "Email hoặc mật khẩu không chính xác",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate3);
                            TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
                            textInputLayout.startAnimation(animation);
                            mTextPassword.setBackgroundResource(R.drawable.bg_error);
                        }
                    }
                    else {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate3);
                        mTextUsername.startAnimation(animation);
                        mTextUsername.setBackgroundResource(R.drawable.bg_error);
                    }
                }
                else {
                    Toast.makeText(MainDangNhap.this, "Vui Lòng Kết Nối Internet", Toast.LENGTH_SHORT).show();
                }


            }
        });
        mTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mTextUsername.getText().toString().equals("")) {
                    mTextUsername.setBackgroundResource(R.drawable.bg_dndk);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mTextPassword.getText().toString().equals("")) {
                    mTextPassword.setBackgroundResource(R.drawable.bg_dndk);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}
