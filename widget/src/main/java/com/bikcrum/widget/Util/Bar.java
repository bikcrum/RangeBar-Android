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
    private float width;
    private int color;

    private PointF startPoint;
    private PointF endPoint;

    private Paint paint = new Paint();

    private Bound bound;

    public Bar(int color, int width, Bound bound) {
        this.width = width;
        this.color = color;
        this.bound = new Bound(bound);
        this.startPoint = this.bound.start;
        this.endPoint = this.bound.end;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
    }

    public void show(Canvas canvas) {
        canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, startPoint.y, paint);
    }

    public void update(float x1, float x2, int max) {
        float stepGap = (bound.end.x - bound.start.x) / max;
        startPoint.x = x1;

        int startIndex = Math.round((bound.end.x - bound.start.x) / stepGap);
        Log.d(TAG, "start index = " + startIndex);
        // endPoint.x = bound.start.x + Math.round(x2 / stepGap) * stepGap;
    }
}
