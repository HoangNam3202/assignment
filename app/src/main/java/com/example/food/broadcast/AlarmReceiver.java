package com.example.food.broadcast;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.food.MainActivity;
import com.example.food.ServiceThongBao;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        intent = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        Toast.makeText(context, "testBroadcastReceiver", Toast.LENGTH_SHORT).show();
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Intent intent1 = new Intent(context, ServiceThongBao.class);
        context.startService(intent1);
    }
}
