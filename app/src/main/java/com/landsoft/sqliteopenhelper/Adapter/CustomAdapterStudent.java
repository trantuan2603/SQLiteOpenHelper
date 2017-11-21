package com.landsoft.sqliteopenhelper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.landsoft.sqliteopenhelper.Model.Students;
import com.landsoft.sqliteopenhelper.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by TRANTUAN on 18-Nov-17.
 */

public class CustomAdapterStudent extends BaseAdapter {

    private Context context;
    private List<Students> studentsList;

    public CustomAdapterStudent(Context context, List<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHoder {
        ImageView imgAvatar;
        TextView tv_id;
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        TextView tv_email;

        public ViewHoder(View view) {
            imgAvatar = view.findViewById(R.id.img_avatar);
            tv_id = view.findViewById(R.id.tv_id);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_address = view.findViewById(R.id.tv_address);
            tv_email = view.findViewById(R.id.tv_email);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_list_student,viewGroup,false);
            viewHoder = new ViewHoder(view);
            view.setTag(viewHoder);
        }
        else
        {
            viewHoder = (ViewHoder) view.getTag();
        }

        Students students = studentsList.get(i);
        Log.d(TAG, "getView: "+ students.getmId() + students.getmName() + students.getmEmail() + students.getmAddress());
        viewHoder.tv_id.setText(students.getmId()+"");

        viewHoder.tv_name.setText(students.getmName());
//        viewHoder.tv_name.setText("tuan");
        viewHoder.tv_phone.setText(students.getmPhone());
        viewHoder.tv_address.setText(students.getmAddress());
        viewHoder.tv_email.setText(students.getmEmail());
        viewHoder.imgAvatar.setImageResource(R.mipmap.ic_launcher_round);
        return view;
    }
}
