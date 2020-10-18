package com.example.food;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.food.adapter.MonAnAdapter;
import com.example.food.object.GIoHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;

import static com.example.food.MainActivity.Email;
import static com.example.food.ThemMonAnActivity.check_NutSua;

public class MonAnActivity extends AppCompatActivity {
    public static DataBaseHelper dataBaseHelper;
    public static ArrayList<MonAn> arr;
    boolean check_list = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        ListView listView_MonAn = findViewById(R.id.listMonAn);
        arr = new ArrayList<>();
        final MonAnAdapter monAnAdapter = new MonAnAdapter(MonAnActivity.this,R.layout.row_of_mon_an,arr);
        listView_MonAn.setAdapter(monAnAdapter);

        dataBaseHelper = new DataBaseHelper(MonAnActivity.this,"CSDL1",null,1);
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS MonAn(Id Integer primary key autoincrement," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), Hinh Blob, Gia Integer)");
//        dataBaseHelper.UpData("drop table GioHang");
        dataBaseHelper.UpData("CREATE TABLE IF NOT EXISTS GioHang(Id Integer primary key autoincrement," +
                "TenMonAn varchar(35), TenQuan varchar(20), DiaChi varchar(50), EmailnNguoiDung varchar(35), Gia Integer, SoLuong Integer)");
//        dataBaseHelper.UpData("Delete from MonAn where TenQuan ='Năm Chân'");
//        dataBaseHelper.UpData("Insert into MonAn Values(null,'Cơm Gà','','82 Hùng Vương, phường Tự An, TP Buôn Ma Thuột, Đắk Lắk','"+R.drawable.comga+"',35)");
        Intent intent = getIntent();
        String TinhThanh = intent.getStringExtra("TinhThanh");
        String User = intent.getStringExtra("Email");
        if (Email.equals("admin")) {
            Cursor cursor = dataBaseHelper.GetData("Select * from MonAn");
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String TenMonAn = cursor.getString(1);
                String TenQuan = cursor.getString(2);
                String DiaChi = cursor.getString(3);
                byte[] Hinh = cursor.getBlob(4);
                int Gia = cursor.getInt(5);

                arr.add(new MonAn(id,TenMonAn,TenQuan,DiaChi,Hinh,Gia));
            }
        }
        else {
            Cursor cursor = dataBaseHelper.GetData("Select * from MonAn where DiaChi LIKE '%"+TinhThanh+"%'");
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String TenMonAn = cursor.getString(1);
                String TenQuan = cursor.getString(2);
                String DiaChi = cursor.getString(3);
                byte[] Hinh = cursor.getBlob(4);
                int Gia = cursor.getInt(5);

                arr.add(new MonAn(id,TenMonAn,TenQuan,DiaChi,Hinh,Gia));
            }
        }
        listView_MonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (Email.equals("admin")) {
                    Toast.makeText(MonAnActivity.this, ""+arr.get(i).TenMonAn, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MonAnActivity.this,SuaMonAnActivity.class);
                    intent.putExtra("TenMonAnCanSua",arr.get(i).TenMonAn);
                    intent.putExtra("TenQuanCanSua",arr.get(i).TenQuan);
                    intent.putExtra("DiaChiCanSua",arr.get(i).DiaChi);
                    intent.putExtra("HinhCanSua",arr.get(i).HinhAnh);
                    intent.putExtra("GiaCanSua",arr.get(i).Gia);
                    intent.putExtra("Vitri",arr.get(i).iDMonAn);
                    if (!check_list) {
                        startActivity(intent);
                    }
                }
            }
        });
        listView_MonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (Email.equals("admin")) {
                    check_list = true;
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MonAnActivity.this);
                    builder.setTitle("Xoá đơn hàng");
                    builder.setMessage("Bạn có thực sự muốn xoá "+arr.get(i).TenMonAn+"?");
                    final int vitri1 = i;
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataBaseHelper.UpData("Delete from MonAn where Id = '"+arr.get(vitri1).iDMonAn+"'");
                            Cursor cursor = dataBaseHelper.GetData("Select * from MonAn");
                            arr.clear();
                            while (cursor.moveToNext()){
                                int id = cursor.getInt(0);
                                String TenMonAn = cursor.getString(1);
                                String TenQuan = cursor.getString(2);
                                String DiaChi = cursor.getString(3);
                                byte[] Hinh = cursor.getBlob(4);
                                int Gia = cursor.getInt(5);

                                arr.add(new MonAn(id,TenMonAn,TenQuan,DiaChi,Hinh,Gia));
                            }
                            monAnAdapter.notifyDataSetChanged();
                            check_list = false;
                        }
                    });
                    builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            check_list = false;
                        }
                    });

                    builder.create().show();
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mon_an, menu);
        MenuItem mnu_Them = menu.findItem(R.id.mnu_Them);
        Intent intent = getIntent();
        String TenDangNhap = intent.getStringExtra("Email");
        if (Email.toLowerCase().equals("admin")) {
            mnu_Them.setEnabled(true);
        }
        else {
            mnu_Them.setEnabled(false);
        }
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnu_Them:
                Intent intent = new Intent(this,ThemMonAnActivity.class);
                startActivity(intent);
                break;
            case R.id.mnu_GioHang:
                Intent inten = new Intent(this,GioHangActivity.class);
                Intent in = getIntent();
                String Email = in.getStringExtra("Email");
                inten.putExtra("Email", Email);
                startActivity(inten);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}