package com.bikcrum.widget.Util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import com.bikcrum.widget.RangeBar;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class Bar {
    private final String TAG = "biky";

    //constructor args
    private int color;
    private int width;
    private int windowWidth;
    private int windowHeight;
    private int hPadding;

    //calculated args
    private PointF start = new PointF();
    private PointF end = new PointF();

    private Paint paint = new Paint();

    public Bar(int color, int width, int windowWidth, int windowHeight, int hPadding) {
        this.color = color;
        this.width = width;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.hPadding = hPadding;

        start.x = hPadding;
        end.y = start.y = windowHeight/2f;
        end.x = windowWidth - hPadding;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
    }

    public void show(Canvas canvas) {
        //Log.d(TAG, "x1 = " + hPadding + ",y1=" + (windowHeight / 2f) + ",x2=" + (width - hPadding) + "y,2=" + (windowHeight / 2f));
        canvas.drawLine(start.x,start.y,end.x,end.y, paint);
    }

    public void update(float x1, float x2, int max) {
        float stepGap = (windowWidth - hPadding * 2f) / max;

        int startIndex = Math.round((x1 - hPadding) / stepGap);

        Log.d(TAG, "start iundex = " + startIndex);

        start.x = hPadding + startIndex * stepGap;
    }
}
