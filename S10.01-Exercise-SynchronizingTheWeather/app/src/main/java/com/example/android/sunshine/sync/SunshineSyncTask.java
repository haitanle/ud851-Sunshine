package com.example.android.sunshine.sync;

//  done (1) Create a class called SunshineSyncTask
//  done (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      done (3) Within syncWeather, fetch new weather data
//      done (4) If we have valid results, delete the old data and insert the new

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.data.WeatherDbHelper;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineSyncTask{
    synchronized public static void syncWeather(Context context){

        URL url = NetworkUtils.getUrl(context);
        String weatherData = null;
        try {
            weatherData = NetworkUtils.getResponseFromHttpUrl(url);
            ContentValues[] content = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context,weatherData);

            // Bulk Insert our new weather data into Sunshine's Database
            if (content != null && !content.equals("")){
                deleteAllRecordsFromWeatherTable(context);
                context.getContentResolver().bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, content);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private static void deleteAllRecordsFromWeatherTable(Context context) {

        context.getContentResolver().delete(
                WeatherContract.WeatherEntry.CONTENT_URI,
                null,
                null);
    }
}