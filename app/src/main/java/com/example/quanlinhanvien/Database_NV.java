package com.example.quanlinhanvien;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database_NV extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "QUANLYNHANVIEN.db";
    private static  final String TABLE_NAME = "nhanvien";
    private static  final String ID = "ID";
    private static  final String TEN = "TEN";
    private static  final String SDT = "SDT";
    private static  final String DIACHI = "DIACHi";
    private static  final String CONGVIEC = "CONGVIEC";
    private static  final String NGAYSINH = "NGAYSINH ";
    private static  final String PHONG = "PHONG";
    private static  final String GIOITINH = "GIOITINH";
    private Context context;

    public Database_NV (@Nullable Context myContext) {
        super(myContext,DATABASE_NAME, null, 1);
        this.context = myContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = " CREATE TABLE IF NOT EXISTS nhanvien(ID INTEGER PRIMARY KEY AUTOINCREMENT,  TEN TEXT,SDT TEXT, DIACHI TEXT, NGAYSINH TEXT, CONGVIEC TEXT, PHONG TEXT,GIOITINH INTEGER )";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void themNhanVien(NhanVien NhanVienMoi,String table_name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TEN,NhanVienMoi.getTenNV());
        values.put(SDT,NhanVienMoi.getSdt());
        values.put(DIACHI,NhanVienMoi.getDiaChi());
        values.put(NGAYSINH,NhanVienMoi.getNgaysinh());
        values.put(CONGVIEC,NhanVienMoi.getCongviec());
        values.put(PHONG,NhanVienMoi.getPhonglam());
        values.put(GIOITINH,NhanVienMoi.getGioitinh());

        database.insert(table_name ,null,values);
        database.close();
    }

//    public Cursor ReadDatabase(String sql)
//    {
//        SQLiteDatabase db=this.getReadableDatabase();
//        return db.rawQuery(sql, null);
//    }
    // lấy danh sách nhân viên trong database
    public ArrayList<NhanVien> getAllNhanVien(String table_name) {
        ArrayList<NhanVien>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + table_name;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            NhanVien nhanVien = new NhanVien(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7)
                    );
            studentList.add(nhanVien);
        }
        return studentList;
    }
// tìm kiếm danh sách những nhân viên có tên gần giống
    public ArrayList<NhanVien> Search_NV(String search)
    {
        ArrayList<NhanVien>  arrayList = new ArrayList<>();
        search.trim();
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE TEN LIKE '%"+search+"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor!=null)
        {
            while(cursor.moveToNext()) {
                NhanVien nhanVien = new NhanVien(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7)
                );
                //trả lại 1 mảng danh sách
                arrayList.add(nhanVien);
            }
        }
        return arrayList;
    }
// trả về 1 nhân viên
    public NhanVien nhanVien(int id){
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE ID = +'"+id+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        NhanVien nhanVien = null;
        if (cursor!=null)
        {
            cursor.moveToFirst();
                nhanVien = new NhanVien(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7)
            );
        }
        return nhanVien;
    }
    public int update(int id, String ten, String sdt, String diaChi, String ngaySinh, String congViec, String phongLam, int gioiTinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,ten);
        values.put(SDT,sdt);
        values.put(DIACHI,diaChi);
        values.put(NGAYSINH,ngaySinh);
        values.put(CONGVIEC,congViec);
        values.put(PHONG,phongLam);
        values.put(GIOITINH,gioiTinh);
        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void deleteNV(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,null ,null);
        db.close();
    }

    public void delete_ID_NV(int manv,String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, ID + " = ?", new String[] { String.valueOf(manv) });
        db.close();
    }

}
