package com.hyphenate.easeuisimpledemo;

import android.app.Application;

import com.hyphenate.easeui.EaseUI;


public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化UI
        EaseUI.getInstance().init(this, null);
    }
    
}
