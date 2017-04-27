package com.qinglenmeson.ootd;

import android.location.Location;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import static android.R.attr.data;

import java.util.Date;
//import java.time.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;

import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by James Park on 4/25/2017.
 */

public class Day {
    int month;
    int day;
    int tempHigh;
    int tempLow;
    boolean rain;


    public void day() {
        //set month and day varibles to current month and day
        Calendar cal = Calendar.getInstance();
        this.month = cal.get(Calendar.DAY_OF_MONTH);
        this.day = cal.get(Calendar.MONTH);
        //TODO: Uncomment (doesn't run as is)
        /*
        //parse weather data
        JSONObject jObj = new JSONObject(getWeather());

        // We start extracting the info
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));
        weather.location = loc;
        */
    }

    private String getWeather() {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://samples.openweathermap.org/data/2.5/weather?zip=78705,us&appid=ab6c9e595fe584dc2765d622f3b62da0")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
}
