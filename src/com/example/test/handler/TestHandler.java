
package com.example.test.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class TestHandler extends Handler {

    private final Context mContext;

    public TestHandler(Context context) {
        mContext = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Toast.makeText(mContext, "延迟执行了外部handler", Toast.LENGTH_SHORT).show();
    }
}
