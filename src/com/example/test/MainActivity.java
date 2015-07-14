
package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button btn1 = (Button) rootView.findViewById(R.id.test_btn1);
            Button btn2 = (Button) rootView.findViewById(R.id.test_btn2);
            Button btn3 = (Button) rootView.findViewById(R.id.test_btn3);
            Button btn4 = (Button) rootView.findViewById(R.id.test_btn4);
            btn1.setText("µÚÒ»¸öactivity");
            btn1.setOnClickListener(onClickListener);

            btn2.setText("Hanlder test");
            btn2.setOnClickListener(onClickListener);

            btn3.setText("remote service test");
            btn3.setOnClickListener(onClickListener);

            btn4.setVisibility(View.GONE);
            return rootView;
        }

        OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = PlaceholderFragment.this.getActivity();

                switch (v.getId()) {
                    case R.id.test_btn1:
                        Intent intent = new Intent(context, LijiaServiceTestActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.test_btn2:
                        Intent intent2 = new Intent(context, HandlerTestActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.test_btn3:
                        Intent intent3 = new Intent(context, RemoteServiceActivity.class);
                        context.startActivity(intent3);
                        break;

                    default:
                        break;
                }

            }
        };
    }

}
