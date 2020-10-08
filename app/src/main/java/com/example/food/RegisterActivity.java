package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        final String name = "[a-zA-Z._\\d]+$";
        db = new DataBaseHelper(this, "dangnhap.sqlite", null, 1);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_Login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainDangNhap.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                boolean res = db.check(user);
                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (!cnf_pwd.equals(pwd)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }else if (res == true){
                    Toast.makeText(RegisterActivity.this, "Tài khoản này đã có!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(cnf_pwd)) {
                        long val = db.addUser(user, pwd);

                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "Bạn đã đăng kí", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, MainDangNhap.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        mTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mTextUsername.setError("Không được để trống tên đăng nhập!");
                } else {
                    if (charSequence.length() >= 5) {
                        if (charSequence.toString().trim().matches(name)) {
                            mTextUsername.setError(null);
                        } else {
                            mTextUsername.setError("Vui lòng không chứa ký tự đặc biệt!");
                        }
                    } else {
                        mTextUsername.setError("Vui lòng nhập lớn hơn 5 kí tự");
                    }
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
                if (charSequence.length() == 0) {
                    mTextPassword.setError("Không được để trống tên đăng nhập!");
                } else {
                    if (charSequence.length() >= 5) {
                        if (charSequence.toString().trim().matches(name)) {
                            mTextPassword.setError(null);
                        } else {
                            mTextPassword.setError("Vui lòng không chứa ký tự đặc biệt!");
                        }
                    } else {
                        mTextPassword.setError("Vui lòng nhập lớn hơn 5 kí tự");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mTextCnfPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mTextCnfPassword.setError("Không được để trống tên đăng nhập!");
                } else {
                    if (charSequence.length() >= 5) {
                        if (charSequence.toString().trim().matches(name)) {
                            mTextCnfPassword.setError(null);
                        } else {
                            mTextCnfPassword.setError("Vui lòng không chứa ký tự đặc biệt!");
                        }
                    } else {
                        mTextCnfPassword.setError("Vui lòng nhập lớn hơn 5 kí tự");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
