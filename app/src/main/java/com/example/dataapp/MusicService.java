package com.example.dataapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
    public static String TAG = MusicService.class.getSimpleName();
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
        Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
        Log.i(TAG,"onStartCommand");
        Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();
        MediaPlayer player = MediaPlayer.create(this, R.raw.music);
        player.start();
       // stopSelf();
        return  Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();

    }
}
