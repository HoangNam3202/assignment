package com.example.food.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.R;

import static com.example.food.MainDangNhap.check_internet;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";
    public  static boolean check;
    @Override
    public void onReceive(Context context, Intent intent) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Action: " + intent.getAction() + "\n");
//        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
//        String log = sb.toString();
//        Log.d(TAG, log);
//        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
//        throw new UnsupportedOperationException("Not yet implemented");
//        LayoutInflater mInflater = LayoutInflater.from(context);
//        View myView = mInflater.inflate(R.layout.activity_main, null);
//        final String action = intent.getAction();
//        if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
//            if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
////                Toast.makeText(context, "Wifi", Toast.LENGTH_SHORT).show();
//                check_internet = true;
//            } else {
////                Toast.makeText(context, "Khong co mang", Toast.LENGTH_SHORT).show();
//                check_internet = false;
//            }
//        }
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null && info.isConnected()) {
            // Do your work.
            check_internet = true;

            // e.g. To check the Network Name or other info:
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
        }
        else {
//                Toast.makeText(context, "Khong co mang", Toast.LENGTH_SHORT).show();
            check_internet = false;
        }
    }
}
