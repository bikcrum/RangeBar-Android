package com.bikcrum.widget.classes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class Bar {
    private final String TAG = "biky";

    //constructor args
    private int color;
    private float height;
    private int windowWidth;
    private int windowHeight;
    private float thumbRadius;
    //calculated args
    private PointF start = new PointF();
    private PointF end = new PointF();

    private Paint paint = new Paint();

    public Bar(int color, float height, int windowWidth, int windowHeight,float thumbRadius) {
        this.color = color;
        this.height = height;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.thumbRadius = thumbRadius;

        start.x = thumbRadius;
        end.y = start.y = windowHeight / 2f;
        end.x = windowWidth - thumbRadius;

        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(height);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
    }

    public void show(Canvas canvas) {
        canvas.drawLine(start.x, start.y, end.x, end.y, paint);
    }

    protected PointF getStart() {
        return start;
    }

    protected PointF getEnd() {
        return end;
    }

    protected Paint getPaint() {
        return paint;
    }

    protected float getThumbRadius() {
        return thumbRadius;
    }

    protected int getWindowWidth() {
        return windowWidth;
    }
}
