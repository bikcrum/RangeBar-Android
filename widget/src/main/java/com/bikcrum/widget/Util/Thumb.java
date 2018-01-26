package com.bikcrum.widget.Util;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class Thumb {
    private float width;
    private float widthPressed;
    private int color;

    private boolean pressed;

    private Paint paint;

    public Thumb(int color, float width, float widthPressed) {
        this.color = color;
        this.width = width;
        this.widthPressed = widthPressed;

        pressed = false;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
    }



    public void press() {
        pressed = true;
    }

    public void release() {
        pressed = false;
    }

    public float getWidth() {
        return width;
    }

    public float getWidthPressed() {
        return widthPressed;
    }
}