
package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LijiaService extends Service {

    private static final String TAG = LijiaService.class.getSimpleName();

    public class DlBinder extends Binder {
        public LijiaService getService() {
            return LijiaService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        print("onBind");
        return new DlBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        print("onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // print("onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        print("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        print("onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        print("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        print("onRebind");
        super.onRebind(intent);
    }

    private void print(String str) {
        Log.i(TAG, str);
    }

}
