package com.bikcrum.rangebardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.bikcrum.widget.RangeBar;
import com.bikcrum.widget.interfaces.OnRangeChangeListener;

public class MainActivity extends AppCompatActivity {

    private RangeBar bar;
    private final static String TAG = "biky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.range_bar);

        bar.setOnRangeChangeListener(new OnRangeChangeListener() {
            @Override
            public void onRangeChanged(RangeBar bar, int startIndex, int endIndex, boolean fromUser) {
                Log.d(TAG, "start = " + startIndex + ", end = " + endIndex + ", from user = " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(RangeBar bar) {
                Log.d(TAG, "on start tracking touch");

            }

            @Override
            public void onStopTrackingTouch(RangeBar bar) {
                Log.d(TAG, "on end tracking touch");
            }
        });
    }
}
