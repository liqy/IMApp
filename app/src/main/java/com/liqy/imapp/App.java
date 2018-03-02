package com.liqy.imapp;

import android.app.Application;

import com.tencent.bugly.Bugly;

/**
 * Created by liqy on 2018/2/27.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "07baa419f3", BuildConfig.DEBUG);

    }
}
