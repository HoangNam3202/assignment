package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TheLoaiMonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_mon);
        ImageView imageView = findViewById(R.id.imgTheLoaiMon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TheLoaiMonActivity.this,MonAnActivity.class);
                startActivity(intent);
            }
        });
    }
}