package com.example.vlad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class SQLLiteDBHelper extends SQLiteOpenHelper {
    public SQLLiteDBHelper(Context context) {
        super(context, "SQLiteDb", null, 1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        Log.e("SQLLiteDBHelper", "--- onCreate dataabase ---");
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "firstname text,"
                + "lastname text,"
                + "age integer"
                + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
