package com.example.food.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.food.R;
import com.example.food.ServiceGioHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.food.MonAnActivity.arr;
import static com.example.food.MonAnActivity.dataBaseHelper;

public class MonAnAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<MonAn> monAnList;
    public static ArrayList<Integer> ItemGiohang = new ArrayList();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MonAnAdapter(Context context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        ImageView imageMonAn = view.findViewById(R.id.imgHinhMonAn);
        TextView tvTenMonAn = view.findViewById(R.id.tvTenMon);
        TextView tvTenQuan = view.findViewById(R.id.tvTenQuan);
        TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
        TextView tvGia = view.findViewById(R.id.tvGia);
        TextView btnDatMon = view.findViewById(R.id.btnDatHang);


        final MonAn monAn = monAnList.get(i);

        byte[] hinhAnh = monAn.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imageMonAn.setImageBitmap(bitmap);
        tvTenMonAn.setText(monAn.getTenMonAn());
        tvTenQuan.setText(monAn.getTenQuan());
        tvDiaChi.setText(monAn.getDiaChi());
        tvGia.setText(""+monAn.getGia());

        btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemGiohang.add(monAn.getiDMonAn());
                String TenMonAn = "";
                String TenQuan = "";
                String DiaChi = "";
                int Gia = 0 ;
                for(int i = 0 ; i < ItemGiohang.size(); i++){
                    Cursor cursor = dataBaseHelper.GetData("Select * from MonAn where Id = '"+ItemGiohang.get(i)+"'");
                    while (cursor.moveToNext()){
                        int id = cursor.getInt(0);
                        TenMonAn = cursor.getString(1);
                        TenQuan = cursor.getString(2);
                        DiaChi = cursor.getString(3);
                        Gia = cursor.getInt(5);
                    }
                    dataBaseHelper.UpData("Insert into GioHang values(null,'"+TenMonAn+"','"+TenQuan+"','"+DiaChi+"','hoangnam1101@gmail.com','"+Gia+"')");
                    Toast.makeText(context, "Đặt thành công "+TenMonAn, Toast.LENGTH_SHORT).show();
                    ItemGiohang.clear();
                }
            }
        });
        return view;
    }
    private void initPreferences() {
        sharedPreferences = context.getSharedPreferences("mylogin",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

}
