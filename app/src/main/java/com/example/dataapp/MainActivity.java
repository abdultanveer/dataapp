package com.example.dataapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver contentResolver = getContentResolver();
        Uri uriSms = Uri.parse("content://sms/inbox");  //TABLE_NAME
        Uri allCalls = Uri.parse("content://call_log/calls");
        Uri contacts = ContactsContract.Contacts.CONTENT_URI;

        Cursor dataCursor = contentResolver.query(contacts,
                null,null,null,null);
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
               // R.layout.my_list_item,
                dataCursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},
                //new int[]{R.id.textViewTitle,R.id.textViewSubtitle},
               new int[]{android.R.id.text1,android.R.id.text2},
               0 );

        ListView dataListView =  findViewById(R.id.cpListview);
        dataListView.setAdapter(adapter);

    }
}
