package com.example.dataapp.data.remote;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.dataapp.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"; // Base URI for the  Books API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
    private static final String PRINT_TYPE = "printType"; // Parameter to filter by print type



   public static String getBookInfo(String queryString){//3
       String bookJSONString = "";
       HttpURLConnection urlConnection = null;
       BufferedReader reader = null;

       //String url = "https://www.googleapis.com/books/v1/volumes?q=pride+prejudice&maxResults=5&printType=books"

       Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
               .appendQueryParameter(QUERY_PARAM, queryString)
               .appendQueryParameter(MAX_RESULTS, "10")
               .appendQueryParameter(PRINT_TYPE, "books")
               .build();

       try {
           URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
           urlConnection.setRequestMethod("GET");
           urlConnection.connect();

           InputStream inputStream = urlConnection.getInputStream();


           StringBuffer buffer = new StringBuffer();
           if (inputStream == null) {
               return null;
           }
            reader = new BufferedReader(new InputStreamReader(inputStream));
           String line;
           while ((line = reader.readLine()) != null) {
/* Since it's JSON, adding a newline isn't necessary (it won't affect
parsing) but it does make debugging a *lot* easier if you print out the
completed buffer for debugging. */
               buffer.append(line + "\n");
           }
           if (buffer.length() == 0) {
// Stream was empty. No point in parsing.
               return null;
           }
           bookJSONString = buffer.toString();
           Log.d(LOG_TAG, bookJSONString);


       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (ProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
           if (reader != null) {
               try {
                   reader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       return bookJSONString; //4
   }

    public static String getBookInfoVolley(String queryString){
        String  tag_string_req = "string_req";

        String url = "https://www.googleapis.com/books/v1/volumes?q=pride+prejudice&maxResults=5&printType=books";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG, response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(LOG_TAG, "Error: " + error.getMessage());
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return "";
    }


}
