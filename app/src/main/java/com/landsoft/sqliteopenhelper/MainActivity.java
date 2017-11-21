package com.landsoft.sqliteopenhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.landsoft.sqliteopenhelper.Adapter.CustomAdapterStudent;
import com.landsoft.sqliteopenhelper.ManagerSQL.DBManager;
import com.landsoft.sqliteopenhelper.Model.Students;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText edtId, edtName, edtPhone, edtAddress, edtEmail;
    Button btnSave, btnUpdate;
    ListView lvStudents;
    CustomAdapterStudent adapterStudent;
    List<Students> studentsList;
    DBManager dbManager;
    final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappedWidget();
        setHidden(btnUpdate);
        btnSave.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        dbManager = new DBManager(getApplicationContext());
        setAdapter();
        lvStudents.setOnItemClickListener(this);
        registerForContextMenu(lvStudents);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_student, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mn_delete:
                Students students = studentsList.get(info.position);
                if (students != null){
                  if ( dbManager.delStudent(students) ){
                      studentsList.clear();
                      studentsList.addAll(dbManager.sqlStudentAll());
                      setAdapter();
                      setHidden(btnUpdate);
                      Toast.makeText(getApplicationContext(), " delete success", Toast.LENGTH_SHORT).show();
                  }else
                      Toast.makeText(getApplicationContext(), " delete not success", Toast.LENGTH_SHORT).show();
                }
               return  true;
            case R.id.mn_cancle:
                Log.d(TAG, "onContextItemSelected: delete" +info.position);
                return  true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void setHidden(Button btnPara) {
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(true);
        btnPara.setEnabled(false);
    }

    private void setAdapter() {
        if (adapterStudent == null) {
            studentsList = new ArrayList<>();
            studentsList.addAll(dbManager.sqlStudentAll());
            adapterStudent = new CustomAdapterStudent(getApplicationContext(), studentsList);
            lvStudents.setAdapter(adapterStudent);
        } else {
            adapterStudent.notifyDataSetChanged();
            lvStudents.setSelection(adapterStudent.getCount() - 1);
        }
    }

    private void mappedWidget() {
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_adress);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        btnSave = findViewById(R.id.btn_save);
        btnUpdate = findViewById(R.id.btn_update);
        lvStudents = findViewById(R.id.lv_students);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                Students students = inertStudent();
                if (students != null) {
                    if (dbManager.addStudent(students)) {
                        studentsList.clear();
                        studentsList.addAll(dbManager.sqlStudentAll());
                        setAdapter();
                        Toast.makeText(getApplicationContext(), " insert success", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), " insert not success", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_update:
                Students students1 = inertStudent();
                if (students1 != null) {
                    if (dbManager.updateStudent(students1)) {
                        studentsList.clear();
                        studentsList.addAll(dbManager.sqlStudentAll());
                        setAdapter();
                        setHidden(btnUpdate);
                        Toast.makeText(getApplicationContext(), " update success", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), " update not success", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public Students inertStudent() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phone = edtEmail.getText().toString().trim();
        String mail = edtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(mail)) {
            Toast.makeText(getApplicationContext(), " Vui long nhap day du thong tin ", Toast.LENGTH_SHORT).show();
        } else {
            Students students = new Students();
            if (!TextUtils.isEmpty(edtId.getText().toString().trim())){
                students.setmId(Integer.parseInt(edtId.getText().toString().trim()));
            }
            students.setmName(name);
            students.setmAddress(address);
            students.setmPhone(phone);
            students.setmEmail(mail);
            return students;

        }
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        setHidden(btnSave);
        Log.d(TAG, "onItemClick: " + adapterView.getItemAtPosition(position));
        Students students = studentsList.get(position);
        edtId.setText(String.valueOf(students.getmId()));
        edtName.setText(String.valueOf(students.getmName()));
        edtAddress.setText(String.valueOf(students.getmAddress()));
        edtEmail.setText(String.valueOf(students.getmEmail()));
        edtPhone.setText(String.valueOf(students.getmPhone()));
    }
}
