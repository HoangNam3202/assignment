package com.example.food.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.R;
import com.example.food.object.GIoHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class GioHangAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<GIoHang> gIoHangList;
    public static ArrayList<Integer> ItemGiohang = new ArrayList();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public GioHangAdapter(Context context, int layout, List<GIoHang> gIoHangList) {
        this.context = context;
        this.layout = layout;
        this.gIoHangList = gIoHangList;
    }

    @Override
    public int getCount() {
        return gIoHangList.size();
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

//        ImageView imageMonAn = view.findViewById(R.id.imgHinhMonAn);
        TextView tvTenMonAn = view.findViewById(R.id.tvTenMon);
        TextView tvTenQuan = view.findViewById(R.id.tvTenQuan);
        TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
        TextView tvGia = view.findViewById(R.id.tvGia);
//        Button btnDatMon = view.findViewById(R.id.btnDatHang);


        final GIoHang GioHang = gIoHangList.get(i);

//        byte[] hinhAnh = monAn.getHinhAnh();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
//        imageMonAn.setImageBitmap(bitmap);
        tvTenMonAn.setText(GioHang.getTenMonAn());
        tvTenQuan.setText(GioHang.getTenQuan());
        tvDiaChi.setText(GioHang.getDiaChi());
        tvGia.setText(""+GioHang.getGia());


        return view;
    }
    private void initPreferences() {
        sharedPreferences = context.getSharedPreferences("mylogin",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

}
