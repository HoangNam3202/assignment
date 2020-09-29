package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.food.adapter.MonAnAdapter;
import com.example.food.object.MonAn;

import java.util.ArrayList;

public class MonAnActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        ListView listView_MonAn = findViewById(R.id.listMonAn);
        final ArrayList<MonAn> arr = new ArrayList<>();
        MonAnAdapter monAnAdapter = new MonAnAdapter(MonAnActivity.this,R.layout.row_of_mon_an,arr);
        listView_MonAn.setAdapter(monAnAdapter);

        dataBaseHelper = new DataBaseHelper(MonAnActivity.this,"CSDL",null,1);
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS MonAn(Id Integer primary key autoincrement," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), Hinh Integer, Gia Integer)");
        dataBaseHelper.UpData("Delete from MonAn where TenMonAn = 'Com Hai San'");

        dataBaseHelper.UpData("Insert into MonAn Values(null,'Com Hai San','Muoi Kho','82 Hung Vuong','"+R.drawable.comga+"',351)");
        Cursor cursor = dataBaseHelper.GetData("Select * from MonAn");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String TenMonAn = cursor.getString(1);
            String TenQuan = cursor.getString(2);
            String DiaChi = cursor.getString(3);
            int Hinh = cursor.getInt(4);
            int Gia = cursor.getInt(5);

            arr.add(new MonAn(id,TenMonAn,TenQuan,DiaChi,Hinh,Gia));
        }
        listView_MonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MonAnActivity.this, ""+arr.get(i).TenMonAn, Toast.LENGTH_SHORT).show();
            }
        });
    }
}