package com.liqy.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class RemoteActivity extends AppCompatActivity {

    IRemoteService mIRemoteService;

    /**
     * Flag indicating whether we have called bind on the service.
     */
    boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);




    }

    public void fromRemote(View view){
        if (mBound){
            try {
                mIRemoteService.getPid();
                mIRemoteService.basicTypes(0,1,true,3.5F,2.55,"调用");

                mIRemoteService.getSrudentInfo();

                Log.d(getLocalClassName(),mIRemoteService.getSrudentInfo().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIRemoteService = IRemoteService.Stub.asInterface(service);
            mBound = true;
            Toast.makeText(RemoteActivity.this,"已连接",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIRemoteService = null;
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
        Intent intent=new Intent();
        intent.setAction("com.liqy.remote.REMOTE_SERVICE_ACTION");
        bindService(intent, mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


}
