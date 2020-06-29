package com.example.quanlinhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Them_Nv extends AppCompatActivity {
    private EditText editTen,edtSDT,editDiaChi,editCongViec,editNgaySinh,editPhongLam;
    private RadioButton radNam,radNu;
    Button butNhapLai, butNhap;
    Database_NV database_nv;
    String table_name = "nhanvien";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them__nv);
        anhXa();
        database_nv = new Database_NV(this);
        Event();
    }

    private void anhXa() {
        editTen = findViewById(R.id.editTen);
        edtSDT =  findViewById(R.id.edtSDT);
        editDiaChi = findViewById(R.id.editDiaChi);
        editCongViec= findViewById(R.id.editCongViec);
        editNgaySinh= findViewById(R.id.editNgaySinh);
        editPhongLam = findViewById(R.id.editPhongLam);
        radNam= findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        butNhap = findViewById(R.id.butNhap);
        butNhapLai = findViewById(R.id.butNhapLai);
    }
    private  void Event(){
        butNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gt = 1;

                 String phongLam = editPhongLam.getText().toString();
                 String ngaySinh = editNgaySinh.getText().toString();
                 String tenNV = editTen.getText().toString();
                 String diaChi = editDiaChi.getText().toString();
                 String SDT = edtSDT.getText().toString();
                 String congViec = editCongViec.getText().toString();
                 if(radNu.isChecked()==true)
                 {
                     gt =0;
                 }
                 Log.e("Ten",tenNV);
                 NhanVien nv = new NhanVien( tenNV,SDT, diaChi, ngaySinh, congViec, phongLam ,gt);
                 database_nv.themNhanVien(nv,table_name);
                 database_nv.close();
                Toast.makeText(Them_Nv.this,"Đã thêm nhân viên",Toast.LENGTH_SHORT).show();
            }
        });
        butNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Them_Nv.this,"Đã nhập lại",Toast.LENGTH_SHORT).show();
                edtSDT.setText("");
                editTen.setText("");
                editCongViec.setText("");
                editDiaChi.setText("");
                editNgaySinh.setText("");
                editPhongLam.setText("");
                radNam.setChecked(false);
                radNu.setChecked(false);
            }
        });
    }

}
