package com.example.dataapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class BoundService extends Service {
    private final Random mGenerator = new Random();
    private final IBinder mBinder = new LocalBinder();
    public static String TAG = BoundService.class.getSimpleName();


    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {//2
        return mBinder;//3
    }

    /**
     * this method is connecting to the server and getting the score
     * @return
     */
    public int getScore() {//8
        return mGenerator.nextInt(100);
    }

    public class LocalBinder extends Binder {

        BoundService getService() {//6
            // Return this instance of LocalService so clients can call public methods
            return BoundService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"destroyed");
    }
}
