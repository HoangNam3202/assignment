package com.example.food.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.GioHangActivity;
import com.example.food.R;
import com.example.food.object.GIoHang;
import com.example.food.object.MonAn;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.food.GioHangActivity.Tong;
import static com.example.food.GioHangActivity.gIoHangArrayList;
import static com.example.food.GioHangActivity.gioHangAdapter;
import static com.example.food.MainActivity.Email;
import static com.example.food.MonAnActivity.dataBaseHelper;

public class GioHangAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public List<GIoHang> gIoHangList;
    public static ArrayList<Integer> ItemGiohang = new ArrayList();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static EditText edtSoLuong;
    public static ArrayList<Integer> MangCanXoa = new ArrayList();
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
        final TextView tvGia = view.findViewById(R.id.tvGia);
//        final TextView tvSoLuong = view.findViewById(R.id.tvSoLuong);
//        Button btnDatMon = view.findViewById(R.id.btnDatHang);

        final GIoHang GioHang = gIoHangList.get(i);

//        byte[] hinhAnh = monAn.getHinhAnh();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
//        imageMonAn.setImageBitmap(bitmap);
        tvTenMonAn.setText(GioHang.getTenMonAn());
        tvTenQuan.setText(GioHang.getTenQuan());
        tvDiaChi.setText(GioHang.getDiaChi());
        tvGia.setText(""+GioHang.getGia());
//        tvSoLuong.setText(""+GioHang.getSoLuong());
        final EditText edtSoLuong = view.findViewById(R.id.edtSoLuong);
        edtSoLuong.setText("0"+GioHang.getSoLuong());
        edtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


//                Toast.makeText(context, ""+xyz, Toast.LENGTH_SHORT).show();
//                tvGia.setText(""+xyz);

            }
            @Override
            public void afterTextChanged(Editable editable) {
                int abc = GioHang.getSoLuong();
                int xyz = GioHang.getGia();
                abc = Integer.parseInt(edtSoLuong.getText().toString());
                if (abc != 0) {
                    xyz *= Integer.parseInt(edtSoLuong.getText().toString());
                    dataBaseHelper.UpData("Update GioHang set SoLuong = '"+abc+"' where Id = '"+GioHang.getiDMonAn()+"' and EmailnNguoiDung = '"+Email+"'");
                    Cursor cursor = dataBaseHelper.GetData("Select * from GioHang where EmailnNguoiDung = '"+Email+"'");
                    gIoHangArrayList.clear();
                    Tong = 0;
                    while (cursor.moveToNext()){
                        gIoHangArrayList.add(new GIoHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                                cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
                        Tong += cursor.getInt(5) * cursor.getInt(6);
                    }
                    gioHangAdapter.notifyDataSetChanged();
                }
            }
        });
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    MangCanXoa.add(GioHang.getiDMonAn());
                }
                else {
                    MangCanXoa.remove(new Integer(String.valueOf(GioHang.getiDMonAn())));
//                    abc.clear();
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
