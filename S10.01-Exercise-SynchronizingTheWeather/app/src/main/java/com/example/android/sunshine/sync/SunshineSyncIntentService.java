package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

// done (5) Create a new class called SunshineSyncIntentService that extends IntentService
//  done (6) Create a constructor that calls super and passes the name of this class
//  done (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather
public class SunshineSyncIntentService extends IntentService{

    public SunshineSyncIntentService() {
        super(SunshineSyncIntentService.class.getName());
    }

    /**
     * Perform the Service when called startService()
     * @param intent Intent could contain extra action
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SunshineSyncTask.syncWeather(this);
    }
}