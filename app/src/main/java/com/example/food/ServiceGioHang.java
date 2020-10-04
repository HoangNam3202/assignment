package com.example.food;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceGioHang extends Service {
    public ServiceGioHang() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intent.getIntArrayExtra("Chuoi");
        Toast.makeText(this, ""+intent.getIntArrayExtra("Chuoi"), Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
}
