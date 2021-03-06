package com.example.project.myretrofitexmaple;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class ContentProviderActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {
    //http://wptrafficanalyzer.in/blog/android-populating-listview-from-sqlite-database-using-cursorloader-loadermanager-and-content-provider/
    //http://mrbool.com/android-content-provider-how-to-use-content-provider-for-data-access/30446

    //---- Cursor loader
    //https://developer.android.com/reference/android/support/v4/content/CursorLoader.html

    //---- Custom cursor adapter
    //http://www.gustekdev.com/2013/05/custom-cursoradapter-and-why-not-use.html
    //http://www.mysamplecode.com/2013/03/android-simplecursoradapter-alternate-row-color.html
    SimpleCursorAdapter mAdapter;
    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        mListView = (ListView) findViewById(R.id.listview);

        mAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.listview_item_layout,
                null,
                new String[]{CustomerDB.KEY_CODE, CustomerDB.KEY_NAME, CustomerDB.KEY_PHONE},
                new int[]{R.id.code, R.id.name, R.id.phone},
                0);

        mListView.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * A callback method invoked by the loader when initLoader() is called
     */
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Customer.CONTENT_URI;
        return new CursorLoader(this, uri, null, null, null, null);
    }

    /**
     * A callback method, invoked after the requested content provider returned all the data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAdapter.swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }
}
