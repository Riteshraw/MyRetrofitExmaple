package com.example.project.myretrofitexmaple.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.project.myretrofitexmaple.db.UserDb;
import com.example.project.myretrofitexmaple.doa.User;

/**
 * Created by admin on 30-Nov-17.
 */

public class UserProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.example.project.myretrofitexmaple.user";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/users");

    private static final int CUSTOMERS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "customers", CUSTOMERS);
    }

    private UserDb userDb;

    @Override
    public boolean onCreate() {
        userDb = new UserDb(getContext());
        userDb.insertUser(new User("Ritesh", "12345", "9716927520"));
        userDb.insertUser(new User("Ritesh", "12345", "9716927521"));
        userDb.insertUser(new User("Ritesh", "12345", "9716927522"));
        userDb.insertUser(new User("Ritesh", "12345", "9716927523"));
        userDb.insertUser(new User("Ritesh", "12345", "9716927524"));
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return userDb.fetchAllUsers();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
