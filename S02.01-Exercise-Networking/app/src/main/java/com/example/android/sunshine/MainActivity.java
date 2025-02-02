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
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // COMPLETE (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.
        /*
         * This String array contains dummy weather data. Later in the course, we're going to get
         * real weather data. For now, we want to get something on the screen as quickly as
         * possible, so we'll display this dummy data.
         */
//        String[] dummyWeatherData = {
//                "Today, May 17 - Clear - 17°C / 15°C",
//                "Tomorrow - Cloudy - 19°C / 15°C",
//                "Thursday - Rainy- 30°C / 11°C",
//                "Friday - Thunderstorms - 21°C / 9°C",
//                "Saturday - Thunderstorms - 16°C / 7°C",
//                "Sunday - Rainy - 16°C / 8°C",
//                "Monday - Partly Cloudy - 15°C / 10°C",
//                "Tue, May 24 - Meatballs - 16°C / 18°C",
//                "Wed, May 25 - Cloudy - 19°C / 15°C",
//                "Thu, May 26 - Stormy - 30°C / 11°C",
//                "Fri, May 27 - Hurricane - 21°C / 9°C",
//                "Sat, May 28 - Meteors - 16°C / 7°C",
//                "Sun, May 29 - Apocalypse - 16°C / 8°C",
//                "Mon, May 30 - Post Apocalypse - 15°C / 10°C",
//        };

        // COMPLETE (3) Delete the for loop that populates the TextView with dummy data
        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
//        for (String dummyWeatherDay : dummyWeatherData) {
//            mWeatherTextView.append(dummyWeatherDay + "\n\n\n");
//        }

        // COMPLETE (9) Call loadWeatherData to perform the network request to get the weather
//        String userLocation = mWeatherTextView.getText().toString();
        loadWeatherData();

    }

    // COMPLETE (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
    public void loadWeatherData(){

        String location = SunshinePreferences.getPreferredWeatherLocation(MainActivity.this);

        URL dataAPIUrl = NetworkUtils.buildUrl(location);
        new GetWeatherData().execute(dataAPIUrl);
    }

    // COMPLETE (5) Create a class that extends AsyncTask to perform network requests
    private class GetWeatherData extends AsyncTask<URL, Void, String>{
        // COMPLETE (6) Override the doInBackground method to perform your network requests
        @Override
        protected String doInBackground(URL...urls){
            URL urlQuery = urls[0];

            URL urlString = NetworkUtils.buildUrl(urlQuery.toString());
            String results = null;
            try{
                results = NetworkUtils.getResponseFromHttpUrl(urlString);
            } catch (IOException e){
                e.printStackTrace();
            }

            return results;

        }

        // COMPLETE (7) Override the onPostExecute method to display the results of the network request
        @Override
        protected void onPostExecute(String apiResults){
            if (apiResults != null && !apiResults.equals("")){
                mWeatherTextView.setText(apiResults);
                //TODO create method to show/hide error
            }
        }

    }

}