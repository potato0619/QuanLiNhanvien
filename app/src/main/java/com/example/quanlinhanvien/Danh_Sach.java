package com.example.quanlinhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Danh_Sach extends AppCompatActivity {
    public ListView lvDanhSach;
    public ListNhanVienAdapter adapter;
    private ArrayList<NhanVien> arrayList;
    String table_name = "nhanvien";
    public int maNV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh__sach);
        lvDanhSach =  findViewById(R.id.lvDanhSach);

        loadData(table_name);
       clickListView();


    }

    private void clickListView() {
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(Danh_Sach.this,ChiTietNhanVien.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("id",arrayList.get(position).getMaNV());
//                intent.putExtras(bundle);
//                startActivity(intent);
//                Log.e("id gui", String.valueOf(arrayList.get(position).getMaNV()));

                // get id nhân viên
                 maNV = arrayList.get(position).getMaNV();
                ShowDiaLogConfirm();
            }
        });

    }

    private void ShowDiaLogConfirm() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btSua = dialog.findViewById(R.id.lvSua);
        Button btXoa = dialog.findViewById(R.id.lvXoa);
        dialog.show();
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Danh_Sach.this,ChiTietNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",maNV);
                intent.putExtras(bundle);
                startActivity(intent);
                Log.e("id gui", String.valueOf(maNV));
                dialog.cancel();

            }
        });
        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xoa();
                dialog.cancel();
            }
        });



    }

    private void Xoa() {
        Database_NV database_nv = new Database_NV(this);
        database_nv.delete_ID_NV(maNV,table_name);
        adapter.notifyDataSetChanged();
        arrayList.clear();
        loadData(table_name);


    }

    private void loadData(String table_name) {
        arrayList = new ArrayList<>();
        Database_NV database_nv = new Database_NV(this);

        arrayList  =  database_nv.getAllNhanVien(table_name);

        adapter = new ListNhanVienAdapter(this,R.layout.item,arrayList);
        lvDanhSach.setAdapter(adapter);
        database_nv.close();
    }

}
