
package com.example.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.test.RemoteServiceActivity;

public class RemoteService extends Service {

    public static final int REMOTE_MSG = 0;
    private static final String TAG = RemoteService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        print("onBind");
        return msger.getBinder();
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.replyTo != null) {
                Message rMsg = this.obtainMessage();
                rMsg.what = RemoteServiceActivity.REMOTE_ACTIVITY_TAG;
                try {
                    print("2RemoteService replyTo");
                    msg.replyTo.send(rMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            switch (msg.what) {
                case REMOTE_MSG:
                    print("4handle Remote_msg");
                    break;

                default:
                    break;
            }

        };
    };
    Messenger msger = new Messenger(handler);

    private static void print(String str) {
        Log.i(TAG, str);
    }

}
