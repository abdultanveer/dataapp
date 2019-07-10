package com.example.dataapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {
    public static String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent (Intent intent) {
        //receives intent -- he'll spawn a bg thread and delegate that job to bg thread
        String data = intent.getExtras().getString(MainActivity.KEY);
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        Log.i(TAG,"onHandleIntent");
    }
}
