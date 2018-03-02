package com.liqy.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liqy.aidldemo.IRemoteService;
import com.liqy.aidldemo.Student;

/**
 * Created by liqy on 2018/3/2.
 */

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 多进程，多线程
     * <p>
     * 根据自己的要求去实现
     */
    private IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return 5;
        }

        @Override
        public Student getSrudentInfo() throws RemoteException {
            return new Student("abc", "晓红");
        }

        @Override
        public void toStudent(Student student) throws RemoteException {

        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d(getPackageName(), "多进程，多线程" + aString);
        }
    };
}
