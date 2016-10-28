package com.scottlindley.joins_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott Lindley on 10/28/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper{
    private static SQLiteHelper sInstance;

    public static final String DATA_BASE_NAME = "joins-lab.db";
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String JOB_TABLE_NAME = "job";

    public static final String COL_SSN = "ssn";
    public static final String COL_FIRST = "first";
    public static final String COL_LAST = "last";
    public static final String COL_BIRTH_YEAR = "birth_year";
    public static final String COL_CITY = "city";

    public static final String COL_COMPANY = "company";
    public static final String COL_SALARY = "salary";
    public static final String COL_EXPERIENCE = "experience";

    public static final String CREATE_EMPLOYEE_TABLE =
            "CREATE TABLE "+EMPLOYEE_TABLE_NAME+" ("+
                    COL_SSN+" TEXT PRIMARY KEY, "+
                    COL_FIRST+" TEXT, "+
                    COL_LAST+" TEXT, "+
                    COL_BIRTH_YEAR+" INT, "+
                    COL_CITY+" TEXT)";

    public static final String CREAT_JOB_TABLE =
            "CREATE TABLE "+JOB_TABLE_NAME+" ("+
                    COL_SSN+" TEXT PRIMARY KEY, "+
                    COL_COMPANY +" TEXT, "+
                    COL_SALARY+" INT, "+
                    COL_EXPERIENCE+" INT)";

    private SQLiteHelper (Context context){
        super(context, DATA_BASE_NAME, null, 1);
    }

    public static SQLiteHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new SQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);
        sqLiteDatabase.execSQL(CREAT_JOB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+EMPLOYEE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+JOB_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertEmployeeRow(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SSN, employee.getSSN());
        values.put(COL_FIRST, employee.getFirst());
        values.put(COL_LAST, employee.getLast());
        values.put(COL_BIRTH_YEAR, employee.getBirthYear());
        values.put(COL_CITY, employee.getCity());
        db.insert(EMPLOYEE_TABLE_NAME, null, values);
        db.close();
    }

    public void insertJobRow(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SSN, job.getSSN());
        values.put(COL_COMPANY, job.getCompany());
        values.put(COL_SALARY, job.getSalary());
        values.put(COL_EXPERIENCE, job.getExperience());
        db.insert(JOB_TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getMacysEmployees(){
        SQLiteDatabase db = getReadableDatabase();

        String query =
                "SELECT "+COL_FIRST+", "+COL_LAST+" FROM "+
                        EMPLOYEE_TABLE_NAME+" INNER JOIN "+
                        JOB_TABLE_NAME+" ON "+ EMPLOYEE_TABLE_NAME+"."+COL_SSN+
                        " = "+JOB_TABLE_NAME+"."+COL_SSN+" WHERE "+ COL_COMPANY +
                        " = 'Macys'";
        Cursor cursor = db.rawQuery(query, null);
        List<String> names = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex(COL_FIRST))+
                        " "+cursor.getString(cursor.getColumnIndex(COL_LAST)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return names;
    }

    public List<String> getBostonCompanies(){
        SQLiteDatabase db = getReadableDatabase();

        String query =
                "SELECT "+ COL_COMPANY +" FROM "+
                        EMPLOYEE_TABLE_NAME+" INNER JOIN "+
                        JOB_TABLE_NAME+" ON "+ EMPLOYEE_TABLE_NAME+"."+COL_SSN+
                        " = "+JOB_TABLE_NAME+"."+COL_SSN+" WHERE "+COL_CITY+
                        " = 'Boston'";
        Cursor cursor = db.rawQuery(query, null);

        List<String> names = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                names.add(cursor.getString(cursor.getColumnIndex(COL_COMPANY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return names;
    }

    public List<String> getHighestSalary(){
        SQLiteDatabase db = getReadableDatabase();

        String query =
                "SELECT "+COL_FIRST+", "+COL_LAST+" FROM "+
                        EMPLOYEE_TABLE_NAME+" INNER JOIN "+
                        JOB_TABLE_NAME+" ON "+ EMPLOYEE_TABLE_NAME+"."+COL_SSN+
                        " = "+JOB_TABLE_NAME+"."+COL_SSN+" ORDER BY "+COL_SALARY;
        Cursor cursor = db.rawQuery(query, null);

        List<String> names = new ArrayList<>();

        if(cursor.moveToFirst()){
                names.add(cursor.getString(cursor.getColumnIndex(COL_FIRST))+
                        " "+cursor.getString(cursor.getColumnIndex(COL_LAST)));
        }
        cursor.close();
        return names;
    }

}
