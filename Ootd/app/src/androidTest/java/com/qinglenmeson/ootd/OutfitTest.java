package com.qinglenmeson.ootd;

import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by James Park on 5/3/2017.
 */

public class OutfitTest {

    @Test
    public void emptyClosetTest() throws Exception {
        String weatherInfo = "{\"coord\":{\"lon\":-122.08,\"lat\":37.39},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.14,\"pressure\":1025,\"humidity\":86,\"temp_min\":275.15,\"temp_max\":279.15},\"visibility\":16093,\"wind\":{\"speed\":1.67,\"deg\":53.0005},\"clouds\":{\"all\":1},\"dt\":1485788160,\"sys\":{\"type\":1,\"id\":471,\"message\":0.0116,\"country\":\"US\",\"sunrise\":1485789140,\"sunset\":1485826300},\"id\":5375480,\"name\":\"Mountain View\",\"cod\":200}";
        Outfit outfit = Outfit.generate();

        assertNull(outfit.getClothingMap().get(Category.JACKET));
        assertNull(outfit.getClothingMap().get(Category.SHIRT));
        assertNull(outfit.getClothingMap().get(Category.TSHIRT));
        assertNull(outfit.getClothingMap().get(Category.PANTS));
        assertNull(outfit.getClothingMap().get(Category.SOCKS));
        assertNull(outfit.getClothingMap().get(Category.SHOES));
    }

}
