package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
    // done (15) Add a class file called ForecastAdapter
    // done (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>

    // done (23) Create a private string array called mWeatherData
    private String[] mWeatherData;

    // done (47) Create the default constructor (we will pass in parameters in a later lesson)
    public ForecastAdapter(){

    }

    // done (24) Override onCreateViewHolder
    // done (25) Within onCreateViewHolder, inflate the list item xml into a view
    // done (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ForecastAdapterViewHolder viewHolder = new ForecastAdapterViewHolder(view);
        return viewHolder;
    }

    // done (27) Override onBindViewHolder
    // done (28) Set the text of the TextView to the weather for this list item's position
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder viewHolder, int position){
            viewHolder.bind(mWeatherData[position]);
    }

    // done (29) Override getItemCount
    // done (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
    @Override
    public int getItemCount(){
        if (mWeatherData == null){
            return 0;
        }
        return mWeatherData.length;
    }

    // done (31) Create a setWeatherData method that saves the weatherData to mWeatherData
    // done (32) After you save mWeatherData, call notifyDataSetChanged
    // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////


    public void setWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }

    //--- done (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
    // done (17) Extend RecyclerView.ViewHolder
    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{

        // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////
        // done (18) Create a public final TextView variable called mWeatherTextView
        TextView mWeatherTextView;

        // done (19) Create a constructor for this class that accepts a View as a parameter
        // done (20) Call super(view) within the constructor for ForecastAdapterViewHolder
        // done (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
        // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////

        public ForecastAdapterViewHolder(View itemView){
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }

        public void bind(String weatherReport){
            mWeatherTextView.setText(weatherReport);
        }


    }



}
