/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;

import java.net.URI;
import java.net.URL;


public class SunshineSyncUtils {

//  done (1) Declare a private static boolean field called sInitialized
    private static boolean sInitialized = false;
    private static AsyncTask mBackgroundTask;

    //  done (2) Create a synchronized public static void method called initialize
    //  done (3) Only execute this method body if sInitialized is false
    //  done (4) If the method body is executed, set sInitialized to true
    //  done (5) Check to see if our weather ContentProvider is empty
        //  done (6) If it is empty or we have a null Cursor, sync the weather now!

    /**
     * Perform the sync once on app startup, if it is already done then don't perform again
     * We perform the sync if the cursor is empty or null.
     * @param context
     */
    synchronized public static void initialize(final Context context){
        if (sInitialized) return;

            mBackgroundTask = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    ContentResolver resolver = context.getContentResolver();
                    Cursor cursor = resolver.query(
                                        WeatherContract.WeatherEntry.CONTENT_URI,
                                        null,
                                        null,
                                        null,
                                        null);

                    if (cursor == null || cursor.equals("") || cursor.getCount()==0){
                        startImmediateSync(context);
                    }
                    cursor.close();
                    return null;
                }
            };
        mBackgroundTask.execute();
        sInitialized = true;
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}