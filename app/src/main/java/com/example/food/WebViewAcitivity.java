package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_acitivity);
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.foody.vn/");
        webView.setWebViewClient(new WebViewClient());
    }
}