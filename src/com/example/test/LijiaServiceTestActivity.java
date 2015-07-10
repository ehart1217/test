
package com.example.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LijiaServiceTestActivity extends Activity {

    private static final String TAG = LijiaServiceTestActivity.class.getSimpleName();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        mContext = this;
        Button bindBtn = (Button) findViewById(R.id.test_btn1);
        bindBtn.setText("点我bindService");
        bindBtn.setOnClickListener(onClickListener);

        Button startBtn = (Button) findViewById(R.id.test_btn2);
        startBtn.setText("点我startService");
        startBtn.setOnClickListener(onClickListener);

        Button unBindBtn = (Button) findViewById(R.id.test_btn3);
        unBindBtn.setText("点我unBindService");
        unBindBtn.setOnClickListener(onClickListener);

        Button stopBtn = (Button) findViewById(R.id.test_btn4);
        stopBtn.setText("点我stopService");
        stopBtn.setOnClickListener(onClickListener);

    }

    OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.test_btn1:
                    print("点我bindService");
                    Intent bindIntent = new Intent(mContext, LijiaService.class);
                    mContext.bindService(bindIntent, conn,
                            Context.BIND_AUTO_CREATE);
                    break;
                case R.id.test_btn2:
                    print("点我startService");
                    Intent startIntent = new Intent(mContext, LijiaService.class);
                    mContext.startService(startIntent);
                    break;
                case R.id.test_btn3:
                    print("点我unbindService");
                    try {
                        mContext.unbindService(conn);
                    } catch (Exception e) {
                        print(e.toString());
                    }
                    break;
                case R.id.test_btn4:
                    print("点我stopService");
                    Intent stopIntent = new Intent(mContext, LijiaService.class);
                    mContext.stopService(stopIntent);
                    break;
                default:
                    break;
            }
        }
    };

    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            print("onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            print("onServiceConnected");
        }
    };

    private void print(String str) {
        Log.i(TAG, str);
    }

}
