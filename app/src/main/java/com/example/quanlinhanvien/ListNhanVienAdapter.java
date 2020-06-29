package com.example.quanlinhanvien;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListNhanVienAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    ArrayList<NhanVien> nhanVienList;

    public ListNhanVienAdapter(Context context, int myLayout, ArrayList<NhanVien> nhanViens) {
        this.myContext = context;
        this.myLayout = myLayout;
        this.nhanVienList = nhanViens;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null)
        {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(myContext).inflate(R.layout.item,parent,false);
            holder.AnhNV = convertView.findViewById(R.id.itemsAnh);
            holder.tenNV  = convertView.findViewById(R.id.itemsTenNV);
            holder.CongViec = convertView.findViewById(R.id.itemsCongViec);
            holder.PhongLam = convertView.findViewById(R.id.itemsPhongLam);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        NhanVien  nv = nhanVienList.get(position);

        holder.tenNV.setText(nv.getTenNV());
        holder.PhongLam.setText(nv.getPhonglam());
        holder.CongViec.setText(nv.getCongviec());

        // set anh
        if (nv.getGioitinh()==0)
        {
            holder.AnhNV.setBackgroundResource(R.drawable.woman);

        }else{
            holder.AnhNV.setBackgroundResource(R.drawable.man);
        }
//        byte [] Anh = nv.getAnh();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(Anh,0,Anh.length);
//        holder.AnhNV.setImageBitmap(bitmap);
//
//        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(myLayout,null);

        return convertView;
    }
    // view holder giúp listview custom không phải load lại danh sách
    // nếu dùng listview custom cũ thì load lại danh sách rất lag
    public class ViewHolder{
        ImageView AnhNV;
        TextView tenNV,PhongLam,CongViec;

    }

}

