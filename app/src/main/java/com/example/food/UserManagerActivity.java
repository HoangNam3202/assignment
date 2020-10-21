package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.food.adapter.UserAdapter;
import com.example.food.object.TaiKhoan;

import java.util.ArrayList;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class UserManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        ListView list_User = findViewById(R.id.list_User);
        final ArrayList<TaiKhoan> arrayUser = new  ArrayList<>();
        final UserAdapter arrayAdapter = new UserAdapter(UserManagerActivity.this,R.layout.row_of_user,arrayUser);
        list_User.setAdapter(arrayAdapter);
        Cursor cursor = dataBaseHelper.GetData("Select * from TaiKhoan");
        while (cursor.moveToNext()) {
            arrayUser.add(new TaiKhoan(cursor.getString(1),cursor.getString(2),cursor.getString(3)+", "+cursor.getString(4),cursor.getString(6)));
        }

        final EditText edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
        edtTenNguoiDung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Cursor cursor = dataBaseHelper.GetData("Select * from TaiKhoan where TenNguoiDung LIKE '%"+edtTenNguoiDung.getText().toString()+"%' or Email LIKE '%"+edtTenNguoiDung.getText().toString()+"%'");
                arrayUser.clear();
                while (cursor.moveToNext()) {
                    arrayUser.add(new TaiKhoan(cursor.getString(1),cursor.getString(2),cursor.getString(3)+", "+cursor.getString(4),cursor.getString(6)));
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}