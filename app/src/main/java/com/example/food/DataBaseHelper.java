package com.example.food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "register.db";
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database =getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void UpData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public void INSERT_DOVAT(String TenMonAn,String TenQuan,String DiaChi,byte[] hinh,Integer Gia){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MonAn VALUES(null,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,TenMonAn);
        statement.bindString(2,TenQuan);
        statement.bindString(3,DiaChi);
        statement.bindBlob(4,hinh);
        statement.bindDouble(5, (double)Gia);

        statement.executeInsert();
    }
    public void Update_DOVAT(String TenMonAn,String TenQuan,String DiaChi,byte[] hinh,Integer Gia,Integer Id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "Update MonAn set  TenMonAn = ?, TenQuan = ? , DiaChi = ? , Hinh = ?,Gia = ? where Id = ?" ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(6, (double)Id);
        statement.bindString(1,TenMonAn);
        statement.bindString(2,TenQuan);
        statement.bindString(3,DiaChi);
        statement.bindBlob(4,hinh);
        statement.bindDouble(5, (double)Gia);

        statement.executeInsert();
    }
    public void Update_AVATAR(byte[] hinh,String Email){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "Update TaiKhoan set  Hinh = ? where Email = ?" ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,Email);

        statement.executeInsert();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User", user);
        contentValues.put("Pass", password);
        long res = db.insert("DangNhap", null, contentValues);
        db.close();
        return res;
    }
    public boolean check(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from DangNhap where User=?", new String[]{name});
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) return true;
        else return false;
    }

//    public boolean checkUser(String name, String pass) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("select * from DangNhap where User=? and Pass=?", new String[]{name, pass});
//
//        if (cursor.getCount() > 0) return true;
//        else return false;
//    }
}
