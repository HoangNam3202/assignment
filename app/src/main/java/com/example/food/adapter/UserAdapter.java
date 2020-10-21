package com.example.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.food.R;
import com.example.food.object.HoaDon;
import com.example.food.object.TaiKhoan;

import java.util.List;

import static com.example.food.MainActivity.Email;

public class UserAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<TaiKhoan> taiKhoanList;

    public UserAdapter(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
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

        TextView tvNameOf_User = view.findViewById(R.id.tvNameOf_User);
        TextView tvPass_User = view.findViewById(R.id.tvPass_User);
        TextView tvAddress_User = view.findViewById(R.id.tvAddress_User);
        TextView tvEmail_User = view.findViewById(R.id.tvEmail_User);

        TaiKhoan taiKhoan = taiKhoanList.get(i);

        tvNameOf_User.setText(""+taiKhoan.getTenNguoiDung());
        tvPass_User.setText(""+taiKhoan.getPass());
        tvAddress_User.setText(""+taiKhoan.getDiaChi());
        tvEmail_User.setText(""+taiKhoan.getEmail());


        return view;
    }
}
