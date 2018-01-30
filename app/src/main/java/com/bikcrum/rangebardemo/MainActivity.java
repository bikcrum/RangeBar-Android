package com.bikcrum.rangebardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.bikcrum.widget.RangeBar;
import com.bikcrum.widget.interfaces.OnRangeChangeListener;

public class MainActivity extends AppCompatActivity {

    private RangeBar bar;
    private final static String TAG = "biky";
    private ToggleButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RangeBar bar = findViewById(R.id.range_bar);
        button = findViewById(R.id.toogle_btn);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bar.setEnabled(b);
            }
        });

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
