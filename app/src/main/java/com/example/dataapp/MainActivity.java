package com.example.dataapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static int COOLIE_ID = 007;
    Bundle specialInstructions;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(COOLIE_ID,specialInstructions,this);

        /*ContentResolver contentResolver = getContentResolver();
        Uri allCalls = Uri.parse("content://call_log/calls");
        Uri contacts = ContactsContract.Contacts.CONTENT_URI;

        Cursor dataCursor = contentResolver.query(contacts, //querying a db on main thread
                null,null,null,null);*/
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
               // R.layout.my_list_item,
                null,
                new String[]{"body"},
                //new int[]{R.id.textViewTitle,R.id.textViewSubtitle},
               new int[]{android.R.id.text1},
               0 );

        ListView dataListView =  findViewById(R.id.cpListview);
        dataListView.setAdapter(adapter);

    }

    @NonNull
    @Override    //when you hire a coolie -- you give him some instructions -- like from which warehouse[db] he has to get the goods
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle instructions) {
        if(id == COOLIE_ID){
            Uri uriSms = Uri.parse("content://sms/inbox");  //TABLE_NAME

            return new CursorLoader(this,uriSms,null,null,null,null);
        }
        return null;
    }

    // coolie is done with his job  -- where to put the goods
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor dataCursor) {
            adapter.swapCursor(dataCursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
