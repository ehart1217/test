
package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.handler.TestHandler;

public class HandlerTestActivity extends Activity {

    private static final String TAG = HandlerTestActivity.class.getSimpleName();
    private TestHandler outerHandler;
    Button innerBtn;
    Button outerBtn;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_service_layout);
        mContext = this;
        innerBtn = (Button) findViewById(R.id.remote_service_btn);
        innerBtn.setText("内部类的延时handler");
        innerBtn.setOnClickListener(onClickListener);

        outerBtn = (Button) findViewById(R.id.remote_service_unbind_btn);
        outerBtn.setText("外部类的延时handler");
        outerBtn.setOnClickListener(onClickListener);
        outerHandler = new TestHandler(this);
    }

    private final OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.remote_service_btn:
                    startInnerHanlder();
                    break;
                case R.id.remote_service_unbind_btn:
                    startOuterHandler();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        print("onDestroy");
        super.onDestroy();
    };

    private void print(String string) {
        Log.i(TAG, string);
    }

    private void startInnerHanlder() {
        new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                Toast.makeText(mContext, "延迟执行了内部handler", Toast.LENGTH_SHORT).show();
            };
        }.sendEmptyMessageDelayed(0, 5 * 1000);
    }

    private void startOuterHandler() {
        outerHandler.sendEmptyMessageDelayed(0, 5 * 1000);
    }
}
