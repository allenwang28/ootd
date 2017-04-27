package com.qinglenmeson.ootd;

import java.util.ArrayList;
import java.math.*;

/**
 * Created by James Park on 4/25/2017.
 */

public class Outfit {

    public void Outfit() {

    }

    public static Outfit generate(ArrayList<Clothing> clothes, Day day) {
        Outfit outfit = new Outfit();

        if (day.rain == true) {
            //rain jacket
        } else {//no rain
            if ((day.tempHigh - day.tempLow) >= 30) {
                if (day.tempHigh >= 70) {
                    //layers
                }
            } else {//no layers
                //no layers
            }
        }
        return outfit;
    }

    private int randomNum(int min, int max) {
        int random = (int) (Math.random() * max + min);
        return random;
    }
}