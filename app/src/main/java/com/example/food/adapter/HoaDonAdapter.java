package com.example.food.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.R;
import com.example.food.object.GIoHang;
import com.example.food.object.HoaDon;

import java.util.List;

import static com.example.food.MainActivity.Email;
import static com.example.food.MonAnActivity.dataBaseHelper;

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
        TextView tvMaHoaDon = view.findViewById(R.id.tvMaHoaDon);
        TextView tvTenNguoiDung_HoaDon = view.findViewById(R.id.tvTenNguoiDung_HoaDon);
        final TextView btnXacNhan = view.findViewById(R.id.btnXacNhan);

        final HoaDon hoaDon = hoaDonList.get(i);

        tvThoiGian.setText(""+hoaDon.getThoiGian());
        tvGia_HoaDon.setText(""+hoaDon.getTongTien());
        tvMaHoaDon.setText("Mã hóa đơn : "+hoaDon.getMaHoaDon());

        if (Email.equals("admin")) {
            tvTenNguoiDung_HoaDon.setText(hoaDon.getEmailNguoiDung());
            tvTenNguoiDung_HoaDon.setVisibility(View.VISIBLE);
            btnXacNhan.setVisibility(View.VISIBLE);
        }
        else {
            tvTenNguoiDung_HoaDon.setText("");
            btnXacNhan.setVisibility(View.GONE);
            tvTenNguoiDung_HoaDon.setVisibility(View.GONE);
        }
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "123", Toast.LENGTH_SHORT).show();
                dataBaseHelper.UpData("Insert into ThongBao values('"+hoaDon.getEmailNguoiDung()+"','"+hoaDon.getMaHoaDon()+"')");
                btnXacNhan.setEnabled(false);
                btnXacNhan.setTextColor(Color.parseColor("#FFD1CDCD"));
            }
        });
        Cursor cursor = dataBaseHelper.GetData("Select * from ThongBao where MaDonHang = '"+hoaDon.getMaHoaDon()+"'");
        if (cursor.getCount() > 0 ) {
            btnXacNhan.setEnabled(false);
            btnXacNhan.setTextColor(Color.parseColor("#FFD1CDCD"));
        }
        else {
            btnXacNhan.setEnabled(true);
            btnXacNhan.setTextColor(Color.parseColor("#ED3232"));
        }
        return view;
    }
}
