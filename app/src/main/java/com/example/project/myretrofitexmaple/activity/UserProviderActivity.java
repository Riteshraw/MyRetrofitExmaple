package com.example.project.myretrofitexmaple.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.project.myretrofitexmaple.R;
import com.example.project.myretrofitexmaple.provider.UserProvider;
import com.example.project.myretrofitexmaple.utils.DbConstants;

public class UserProviderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listview;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_provider);

        listview = (ListView) findViewById(R.id.listview);
        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.listview_item_layout,
                null,
                new String[]{DbConstants.NAME, DbConstants.PASSWORD, DbConstants.MOBILE},
                new int[]{R.id.code, R.id.name, R.id.phone},
                0);

        listview.setAdapter(cursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = UserProvider.CONTENT_URI;
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
