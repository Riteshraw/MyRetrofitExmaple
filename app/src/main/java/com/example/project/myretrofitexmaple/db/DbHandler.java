package com.example.project.myretrofitexmaple.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.myretrofitexmaple.utils.DbConstants;

/**
 * Created by admin on 30-Nov-17.
 */

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "user_db";


    private static final String CREATE_USER_TABLE = "create table " + DbConstants.TABLE_USER + "("
            + DbConstants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbConstants.NAME + " TEXT, "
            + DbConstants.MOBILE + " TEXT, "
            + DbConstants.PASSWORD + " TEXT )";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_USER);
    }
}
