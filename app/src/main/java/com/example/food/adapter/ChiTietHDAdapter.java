package com.example.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.food.R;
import com.example.food.object.ChiTietHD;
import com.example.food.object.HoaDon;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ChiTietHDAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<ChiTietHD> chiTietHDArrayList;

    public ChiTietHDAdapter(Context context, int layout, List<ChiTietHD> chiTietHDArrayList) {
        this.context = context;
        this.layout = layout;
        this.chiTietHDArrayList = chiTietHDArrayList;
    }

    @Override
    public int getCount() {
        return chiTietHDArrayList.size();
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

        TextView tvTenMonAn = view.findViewById(R.id.tvTenMon);
        TextView tvTenQuan = view.findViewById(R.id.tvTenQuan);
        TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
        final TextView tvGia = view.findViewById(R.id.tvGia);
        TextView tvSoLuong = view.findViewById(R.id.edtSoLuong);

        ChiTietHD chiTietHD = chiTietHDArrayList.get(i);

        tvTenMonAn.setText(chiTietHD.getTenMonAn());
        tvTenQuan.setText(chiTietHD.getTenQuan());
        tvDiaChi.setText(chiTietHD.getDiaChi());
        tvGia.setText(""+chiTietHD.getGia());
        tvSoLuong.setText(""+chiTietHD.getSoLuong() );

        return view;
    }
}
