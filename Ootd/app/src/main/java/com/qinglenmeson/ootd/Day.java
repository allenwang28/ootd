package com.qinglenmeson.ootd;

import android.widget.ImageView;

import org.json.JSONArray;
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

    boolean cloudy;

    String icon;
    String iconURL;

    private static Day today = null;

    String weatherInfo;

    public static Day getInstance() {
        if (today == null) {
            today = new Day();
        }
        return today;
    }

    public static Day getInstance(String weatherInfo) {
            today = new Day(weatherInfo);
        return today;
    }

    private Day() {
        Calendar cal = Calendar.getInstance();
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.month = cal.get(Calendar.MONTH) + 1;
    }

    private Day(String weatherInfo) {
        //set month and day variables to current month and day
        Calendar cal = Calendar.getInstance();
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.month = cal.get(Calendar.MONTH) + 1;

        this.weatherInfo = weatherInfo;
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(weatherInfo);

            JSONObject mainOBj = (JSONObject) jObj.getJSONObject("main");

            tempLow = KtoF(mainOBj.getDouble("temp_min"));
            tempHigh = KtoF(mainOBj.getDouble("temp_max"));
            System.out.println(tempLow);
            System.out.println(tempHigh);

            rain = false;
            if (mainOBj.has("rain")) {
                rain = true;
            }

            JSONArray iconOBj = jObj.getJSONArray("weather");

            icon = iconOBj.getString(0).split("\"icon\":")[1].substring(1, 4);
            System.out.println(icon);
            iconURL = "http://openweathermap.org/img/w/" + icon + ".png";

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Month: %d, Day: %d, High: %f, Low: %f, Rain? %s", month, day, tempHigh, tempLow, rain);
    }

    public void setIcon(ImageView weatherIcon) {
        if(icon == null) {
            return;
        }
        switch(icon.toLowerCase()){
            case "01d":
                weatherIcon.setImageResource(R.drawable.w01d);
                break;
            case "01n":
                weatherIcon.setImageResource(R.drawable.w01n);
                break;
            case "02d":
                weatherIcon.setImageResource(R.drawable.w02d);
                break;
            case "02n":
                weatherIcon.setImageResource(R.drawable.w02n);
                break;
            case "03d":
                weatherIcon.setImageResource(R.drawable.w03d);
                break;
            case "03n":
                weatherIcon.setImageResource(R.drawable.w03n);
                break;
            case "04d":
                weatherIcon.setImageResource(R.drawable.w04d);
                break;
            case "04n":
                weatherIcon.setImageResource(R.drawable.w04n);
                break;
            case "09d":
                weatherIcon.setImageResource(R.drawable.w09d);
                break;
            case "09n":
                weatherIcon.setImageResource(R.drawable.w09n);
                break;
            case "10d":
                weatherIcon.setImageResource(R.drawable.w01d);
                break;
            case "10n":
                weatherIcon.setImageResource(R.drawable.w10n);
                break;
            case "11d":
                weatherIcon.setImageResource(R.drawable.w11d);
                break;
            case "11n":
                weatherIcon.setImageResource(R.drawable.w11n);
                break;
            case "13d":
                weatherIcon.setImageResource(R.drawable.w13d);
                break;
            case "13n":
                weatherIcon.setImageResource(R.drawable.w13n);
                break;
            case "50d":
                weatherIcon.setImageResource(R.drawable.w50d);
                break;
            case "50n":
                weatherIcon.setImageResource(R.drawable.w50n);
                break;
            default:
                weatherIcon.setImageResource(R.drawable.clear);
                break;
        }

    }

    public int getMonth(){
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getTempLow() {
        return (int) tempLow;
    }

    public int getTempHigh() {
        return (int) tempHigh;
    }

    private int KtoF(double K) {
        return (int) (K*1.8 - 459.67);
    }

}
