package com.example.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.food.R;
import com.example.food.object.MonAn;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<MonAn> monAnList;

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

        MonAn monAn = monAnList.get(i);
        imageMonAn.setImageResource(monAn.getHinhAnh());
        tvTenMonAn.setText(monAn.getTenMonAn());
        tvTenQuan.setText(monAn.getTenQuan());
        tvDiaChi.setText(monAn.getDiaChi());
        tvGia.setText(""+monAn.getGia());


        return view;
    }
}
