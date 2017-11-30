package com.example.project.myretrofitexmaple.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.myretrofitexmaple.doa.User;
import com.example.project.myretrofitexmaple.utils.DbConstants;

/**
 * Created by admin on 30-Nov-17.
 */

public class UserDb extends DbHandler {
    SQLiteDatabase db = getWritableDatabase();

    public UserDb(Context context) {
        super(context);
    }

    public void insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DbConstants.NAME, user.getPlayerName());
        values.put(DbConstants.MOBILE, user.getPlayerMobile());
        values.put(DbConstants.PASSWORD, user.getPlayerPassword());

        db.insert(DbConstants.TABLE_USER, null, values);
    }

    public Cursor fetchAllUsers() {
        return db.query(
                DbConstants.TABLE_USER,
                new String[]{DbConstants.ID, DbConstants.NAME, DbConstants.MOBILE, DbConstants.PASSWORD},
                null,
                null,
                null,
                null,
                DbConstants.ID + " asc"
        );

    }
}
