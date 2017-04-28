package com.qinglenmeson.ootd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


/**
 * Created by James Park on 4/25/2017.
 */

public class Day {
    int month;
    int day;
    double tempHigh;
    double tempLow;
    boolean rain;

    boolean cloudy;

    String icon;
    String iconURL;
    String iconDescription;
    

    String weatherInfo;

    public Day(String weatherInfo) {
        //set month and day varibles to current month and day
        Calendar cal = Calendar.getInstance();
        this.month = cal.get(Calendar.DAY_OF_MONTH);
        this.day = cal.get(Calendar.MONTH);

        this.weatherInfo = weatherInfo;

        //TODO: Uncomment (doesn't run as is)
        //parse weather data
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(weatherInfo);

            JSONObject mainOBj = (JSONObject) jObj.getJSONObject("main");

            tempLow = mainOBj.getDouble("temp_min");
            tempHigh = mainOBj.getDouble("temp_max");
            System.out.println(tempLow);
            System.out.println(tempHigh);

            rain = false;
            if (mainOBj.has("rain")) {
                rain = true;
            }

            icon = mainOBj.getString("icon");
            iconURL = "http://openweathermap.org/img/w/" + icon + ".png";
            iconDescription = getIconDescription(icon);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Month: %d, Day: %d, High: %f, Low: %f, Rain? %s", month, day, tempHigh, tempLow, rain);
    }

    public String getIconDescription(String icon){
        String description;
        switch(icon.toLowerCase()){
            case "01d":
                description = "clear sky";
                break;
            case "02d":
                description = "few clouds";
                break;
            case "03d":
                description = "scattered clouds";
                break;
            case "04d":
                description = "broken clouds";
                break;
            case "05d":
                description = "shower rain";
                break;
            case "06d":
                description = "rain";
                break;
            case "07d":
                description = "thunderstorm";
                break;
            case "08d":
                description = "snow";
                break;
            case "09d":
                description = "mist";
                break;
            default:
                description = "";
                break;
        }

        return description;
    }



}
