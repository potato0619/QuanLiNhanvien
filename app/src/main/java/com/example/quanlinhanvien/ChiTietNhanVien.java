package com.example.quanlinhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChiTietNhanVien extends AppCompatActivity {

    EditText ten,sdt,ngaysinh,diachi,congviec,plamviec;
    RadioButton rdNam,rdNu;
    Button luu,nhaplai;
    String table_name = "lichsu";
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nhan_vien);
        connect();
        // lấy id từ danh sách
        getDataFromDanhSach();
        setText();
        click();

    }

    private void setText() {
        Database_NV database_nv = new Database_NV(ChiTietNhanVien.this);
        NhanVien nv = database_nv.nhanVien(id);

        ten.setText(nv.getTenNV());
        sdt.setText(nv.getSdt());
        ngaysinh.setText(nv.getNgaysinh());
        diachi.setText(nv.getDiaChi());
        congviec.setText(nv.getCongviec());
        plamviec.setText(nv.getPhonglam());
        if (nv.getGioitinh()==0)
        {
            rdNu.setChecked(true);
        }
        else{
            rdNam.setChecked(true);
        }

        SQLiteDatabase db = openOrCreateDatabase("QUANLYNHANVIEN.db", MODE_PRIVATE, null);
        String sql = " CREATE TABLE IF NOT EXISTS lichsu(ID INTEGER PRIMARY KEY AUTOINCREMENT,  TEN TEXT,SDT TEXT, DIACHI TEXT, NGAYSINH TEXT, CONGVIEC TEXT, PHONG TEXT,GIOITINH INTEGER )";
        db.execSQL(sql);
        db.close();

        database_nv.themNhanVien(nv,table_name);
        database_nv.close();

    }
// lấy id gửi từ bên danh sách nhân viên
    private void getDataFromDanhSach() {

            Bundle bundle = getIntent().getExtras();

            if (bundle!=null)
            {
                id = bundle.getInt("id",0);
                Log.e("id nhan", String.valueOf(id));
            }
    }

    private void click() {
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database_NV database_nv = new Database_NV(ChiTietNhanVien.this);
                int gt = 1;

                String phongLam = plamviec.getText().toString();
                String ngaySinh = ngaysinh.getText().toString();
                String tenNV = ten.getText().toString();
                String diaChi = diachi.getText().toString();
                String SDT = sdt.getText().toString();
                String congViec = congviec.getText().toString();

                if(rdNu.isChecked()==true)
                {
                    gt =0;
                }
                Log.e("Ten",tenNV);

                database_nv.update(id,tenNV,SDT,diaChi,ngaySinh,congViec,phongLam,gt);
                database_nv.close();

                Toast.makeText(ChiTietNhanVien.this,"Đã lưu!",Toast.LENGTH_SHORT).show();

            }
        });
         nhaplai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt.setText("");
                ten.setText("");
                congviec.setText("");
                diachi.setText("");
                ngaysinh.setText("");
                plamviec.setText("");
                rdNam.setChecked(false);
                rdNu.setChecked(false);
            }
        });
    }

    private void connect() {
        ten = (EditText) findViewById(R.id.chitietTen);

        sdt = (EditText) findViewById(R.id.chitietSDT);
        ngaysinh = (EditText) findViewById(R.id.chitietNS);

        diachi = (EditText) findViewById(R.id.chitietDC);

        congviec = (EditText) findViewById(R.id.chitietCV);
        plamviec = (EditText) findViewById(R.id.chitietPLV);

        rdNam = (RadioButton) findViewById(R.id.chitietNam);
        rdNu = (RadioButton) findViewById(R.id.chitietNu);

        luu = (Button) findViewById(R.id.chitietLuu);
        nhaplai = (Button) findViewById(R.id.chitietNhapLai);

    }

}
