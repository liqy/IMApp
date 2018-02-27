package com.liqy.imapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meituan.android.walle.WalleChannelReader;

/**
 * 升级修改BUG
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello-jni");
    }

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.hello);
        HelloJni jni=new HelloJni();
        textView.setText(jni.fromJniString());

        String channel = WalleChannelReader.getChannel(this.getApplicationContext());

        textView.setText(channel);


    }
}
