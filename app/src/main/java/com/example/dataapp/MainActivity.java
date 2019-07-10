package com.example.dataapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
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

        Cursor dataCursor = contentResolver.query(uriSms,
                null,null,null,null);
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
               // R.layout.my_list_item,
                dataCursor,
                new String[]{"body"},
                //new int[]{R.id.textViewTitle,R.id.textViewSubtitle},
               new int[]{android.R.id.text1},
               0 );

        ListView dataListView =  findViewById(R.id.cpListview);
        dataListView.setAdapter(adapter);

    }
}
