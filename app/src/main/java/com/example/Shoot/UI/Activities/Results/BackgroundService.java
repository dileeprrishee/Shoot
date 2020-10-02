package com.example.Shoot.UI.Activities.Results;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.Shoot.Storage.PreferenceController;

public class BackgroundService extends Service {
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PreferenceController.setPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_WINNER_STATUS,true);
          return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceController.setPreference(getApplicationContext(),PreferenceController.PreferenceKeys.PREFERENCE_WINNER_STATUS,false);

    }
}
