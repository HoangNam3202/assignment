package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.food.object.MonAn;
import com.example.food.adapter.MonAnAdapter;

import java.util.ArrayList;

public class MonAnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        ListView listView_MonAn = findViewById(R.id.listMonAn);
        ArrayList<MonAn> arr = new ArrayList<>();
        MonAnAdapter monAnAdapter = new MonAnAdapter(MonAnActivity.this,R.layout.row_of_mon_an,arr);
        listView_MonAn.setAdapter(monAnAdapter);
    }
}