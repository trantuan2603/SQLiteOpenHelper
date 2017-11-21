package com.landsoft.sqliteopenhelper.ManagerSQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.landsoft.sqliteopenhelper.Model.Students;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by TRANTUAN on 18-Nov-17.
 */

public class DBManager extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_NAME = "trantuan.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "student_list";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String NUMBER = "number";
    private static final String ADDRESS = "address";


    private String SQL = " CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            NUMBER + " TEXT, " +
            ADDRESS + " TEXT)";

    public DBManager(Context context) {
        super(context, DATA_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL);
        Log.d(TAG, "onCreate: " + " Tao bang thanh cong ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onUpgrade: ");
    }

//    add new student
    public boolean addStudent(Students students) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,students.getmName());
        values.put(EMAIL,students.getmEmail());
        values.put(NUMBER,students.getmPhone());
        values.put(ADDRESS,students.getmAddress());
        long temp = db.insert(TABLE_NAME,null, values);
        Log.d(TAG, "addStudent: " + db);
        Log.d(TAG, "SQL: " +SQL);
        values.clear();
        db.close();
        if (temp >0 ){
            return true;
        }
        return false;
    }
//   update
    public boolean updateStudent(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,students.getmName());
        values.put(EMAIL,students.getmEmail());
        values.put(NUMBER,students.getmPhone());
        values.put(ADDRESS,students.getmAddress());
        int temp = db.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(students.getmId())});
        values.clear();
        db.close();
        if( temp > 0){
            Log.d(TAG, "updateStudent: successfully");
            return true;
        }
        return false;
    }

//    delete
    public boolean delStudent(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        int temp =  db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(students.getmId())});
        db.close();
        if (temp > 0){
            Log.d(TAG, "delStudent: successfully");
            return true;
        }
        Log.d(TAG, "delStudent: not success");
        return false;
    }
//    count student
    public long countStudent(){
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        // return count
        return cursor.getCount();
    }

//    truy van du lieu
    public List<Students> sqlStudentAll(){
        List<Students> studentsList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Students students = new Students();
            students.setmId(cursor.getInt(0));
            students.setmName(cursor.getString(1));
            students.setmPhone(cursor.getString(2));
            students.setmAddress(cursor.getString(3));
            students.setmEmail(cursor.getString(4));
            studentsList.add(students);
            Log.d(TAG, "sqlStudentAll: " + students.getmId()+ students.getmName()+students.getmEmail());
            cursor.moveToNext();
        }
//            if (cursor.moveToFirst())
//                do {
//                    Students students = new Students();
//                    students.setmId(cursor.getInt(0));
//                    students.setmName(cursor.getString(1));
//                    students.setmPhone(cursor.getString(2));
//                    students.setmAddress(cursor.getString(3));
//                    students.setmEmail(cursor.getString(4));
//                    studentsList.add(students);
//                    Log.d(TAG, "sqlStudentAll: " + students.getmId()+ students.getmName()+students.getmEmail());
//                } while (cursor.moveToNext());
            cursor.close();
            database.close();
        return studentsList;
    }
}
