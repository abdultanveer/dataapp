package com.example.dataapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dataapp.data.FetchBook;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

    }

    public void search(View view) {
        EditText mBookInput = findViewById(R.id.editText);
        String mQueryString = mBookInput.getText().toString();
        TextView mAuthorText = findViewById(R.id.mAuthorText);
        TextView mTitleText = findViewById(R.id.mTitleText);
        new FetchBook(mTitleText, mAuthorText).execute(mQueryString);//1
    }
}
