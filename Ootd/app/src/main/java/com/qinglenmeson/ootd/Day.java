package com.qinglenmeson.ootd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


/**
 * Created by James Park on 4/25/2017.
 */

public class Day {
    int month;
    int day;
    double tempHigh;
    double tempLow;
    boolean rain;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Month: %d, Day: %d, High: %f, Low: %f, Rain? %s", month, day, tempHigh, tempLow, rain);
    }
}
