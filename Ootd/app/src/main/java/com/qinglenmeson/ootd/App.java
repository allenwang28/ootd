package com.qinglenmeson.ootd;

import android.app.Application;
import android.content.Context;

/**
 * Created by Allen Wang on 3/21/2017.
 * Used so that all components can have access to resources
 */

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
