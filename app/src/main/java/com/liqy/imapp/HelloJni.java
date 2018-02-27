package com.liqy.imapp;

/**
 * Created by liqy on 2018/2/26.
 */

public class HelloJni {
    public native String fromJniString();
    public  static native String fromString();
    public  native String toString(String str,int a);

}
