
package com.example.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.handler.TestHandler;
import com.example.test.service.RemoteService;

public class RemoteServiceActivity extends Activity {

    private static final String TAG = RemoteServiceActivity.class.getSimpleName();
    public static final int REMOTE_ACTIVITY_TAG = 0;

    private TestHandler testhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_service_layout);
        Button bindService = (Button) this.findViewById(R.id.remote_service_btn);
        bindService.setText("点我bindservice");
        bindService.setOnClickListener(onClickListener);

        Button unbindService = (Button) this.findViewById(R.id.remote_service_unbind_btn);
        unbindService.setText("点我unbindservice");
        unbindService.setOnClickListener(onClickListener);

        testhandler = new TestHandler(this);
        testhandler.sendEmptyMessage(0);
    }

    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.remote_service_btn:
                    Intent intent = new Intent(RemoteServiceActivity.this, RemoteService.class);
                    RemoteServiceActivity.this.bindService(intent, conn, Context.BIND_AUTO_CREATE);
                    break;
                case R.id.remote_service_unbind_btn:
                    try {
                        RemoteServiceActivity.this.unbindService(conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }
    };

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REMOTE_ACTIVITY_TAG:
                    print("3handle REMOTE_ACTIVITY_TAG");
                    Toast.makeText(RemoteServiceActivity.this, "RemoteServiceActivity1",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };

    private final Messenger messengerReceiver = new Messenger(handler);

    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            print("onServiceConnected");
            Messenger msger = new Messenger(service);
            Message rMsg = new Message();
            rMsg.what = RemoteService.REMOTE_MSG;
            rMsg.replyTo = messengerReceiver;
            try {
                print("1send RemoteService.REMOTE_MSG");
                msger.send(rMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private void print(String str) {
        Log.i(TAG, str);
    }
}
