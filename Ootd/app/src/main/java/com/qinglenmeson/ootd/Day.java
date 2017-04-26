package com.qinglenmeson.ootd;

/**
 * Created by James Park on 4/25/2017.
 */

public class Day {
    int month;
    int day;
    int temperature;
    boolean rain;

    public void day(int m, int d)
    {
        month = m;
        day = d;
        //call weather api to set temperature and rain;
    }
}
