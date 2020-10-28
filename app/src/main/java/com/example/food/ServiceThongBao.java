package com.example.food;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.food.MainActivity.Email;
import static com.example.food.MonAnActivity.dataBaseHelper;

public class ServiceThongBao extends Service {
    public ServiceThongBao() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        String EmailNhan = "";
//        if(intent != null){
//            EmailNhan = intent.getStringExtra("Email");
//        }
//        Toast.makeText(this, "testServiceThongBao", Toast.LENGTH_SHORT).show();
        dataBaseHelper = new DataBaseHelper(this,"CSDL1",null,1);
        Cursor cursor = dataBaseHelper.GetData("Select * from ThongBao where Email = '"+Email+"'");
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("Xác nhận đơn hàng "+cursor.getString(0)+" !")
                        .setContentText("Đơn hàng "+cursor.getInt(1)+" của bạn đã được xác nhận")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0, builder.build());
                dataBaseHelper.UpData("delete from ThongBao where MaDonHang = '"+cursor.getInt(1)+"'");
            }
        }
        return START_NOT_STICKY;
    }
}
