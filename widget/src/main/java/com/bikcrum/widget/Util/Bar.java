package com.bikcrum.widget.Util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class Bar {
    private float width;
    private int color;
    private float length;
    private PointF startPoint;

    private Paint paint = new Paint();

    public Bar(float width, int color, float length, PointF startPoint) {
        this.width = width;
        this.color = color;
        this.length = length;
        this.startPoint = startPoint;
        initPaint();
    }

    protected Bar(float width, int color) {
        this.width = width;
        this.color = color;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
    }

    public void show(Canvas canvas) {
        canvas.drawLine(startPoint.x, startPoint.y, startPoint.x + length, startPoint.y, paint);
    }


    protected  float getY() {
        return startPoint.y;
    }

    protected  Paint getPaint() {
        return paint;
    }
}
