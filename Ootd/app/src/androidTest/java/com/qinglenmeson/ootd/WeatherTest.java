package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Qing Wang on 5/7/2017.
 */

public class WeatherTest {

    @Test
    public void existsTest() {
        try {
            String weatherInfo = new GetWeatherInfo().execute().get();
            assertTrue(weatherInfo.length() > 0);
        } catch (Exception e){
        }
    }

    @Test
    public void partsTest() {
        try {
            String weatherInfo = new GetWeatherInfo().execute().get();
            Day day = Day.getInstance(weatherInfo);
            assertTrue(day.getIcon().length() == 3);
            assertTrue(day.getDay() > 0 && day.getDay() < 32);
            assertTrue(day.getMonth() >= 0 && day.getMonth() < 12);
            assertTrue(day.getTempLow() < 200 && day.getTempLow() > -50); //Reasonable bounds
            assertTrue(day.getTempHigh() < 200 && day.getTempHigh() > -50); //Reasonable bounds
        } catch (Exception e){
        }
    }
}
