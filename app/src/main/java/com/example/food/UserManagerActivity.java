package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.food.object.TaiKhoan;

import java.util.ArrayList;

import static com.example.food.MonAnActivity.dataBaseHelper;

public class UserManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        ListView list_User = findViewById(R.id.list_User);
        ArrayList<TaiKhoan> arrayUser = new  ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(UserManagerActivity.this,android.R.layout.simple_list_item_1,arrayUser);
        list_User.setAdapter(arrayAdapter);
        Cursor cursor = dataBaseHelper.GetData("Select * from TaiKhoan");
        while (cursor.moveToNext()) {
            arrayUser.add(new TaiKhoan(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(6)));
        }
        arrayAdapter.notifyDataSetChanged();
    }
}