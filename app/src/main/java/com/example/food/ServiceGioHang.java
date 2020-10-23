package com.example.food;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.widget.Toast;

import static com.example.food.MonAnActivity.dataBaseHelper;

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
        dataBaseHelper = new DataBaseHelper(this,"CSDL1",null,1);
        Cursor cursor = dataBaseHelper.GetData("");

        return START_NOT_STICKY;
    }
}
