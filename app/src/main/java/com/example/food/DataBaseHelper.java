package com.example.food;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
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
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
