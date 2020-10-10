package com.example.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.food.R;
import com.example.food.object.GIoHang;
import com.example.food.object.HoaDon;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<HoaDon> hoaDonList;

    public HoaDonAdapter(Context context, int layout, List<HoaDon> hoaDonList) {
        this.context = context;
        this.layout = layout;
        this.hoaDonList = hoaDonList;
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
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

        TextView tvThoiGian = view.findViewById(R.id.tvThoiGian);
        TextView tvGia_HoaDon = view.findViewById(R.id.tvGia_HoaDon);

        HoaDon hoaDon = hoaDonList.get(i);

        tvThoiGian.setText(""+hoaDon.getThoiGian());
        tvGia_HoaDon.setText(""+hoaDon.getTongTien());


        return view;
    }
}
