package com.example.dataapp.data;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.dataapp.data.remote.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchBook extends AsyncTask<String,Void,String> {

    private TextView mTitleText;
    private TextView mAuthorText;


    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }


    @Override
    protected String doInBackground(String... strings) {// 5 returning bookStringjson
        return NetworkUtils.getBookInfo(strings[0]);//2  mQueryString
    }

    @Override
    protected void onPostExecute(String jsonString) {//6 bookStringjson
        super.onPostExecute(jsonString);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            for(int i = 0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i); //Get the current item
                String title=null;
                String authors=null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }
//If both a title and author exist, update the TextViews and return
                if (title != null && authors != null){
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    return;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
