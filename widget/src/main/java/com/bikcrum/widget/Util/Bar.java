package com.bikcrum.widget.Util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.preference.PreferenceManager;
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

    protected enum Action {
        PRESS_START_THUMB,
        PRESS_END_THUMB,
        RELEASE
    }

    private Action action;

    public Bar(int color, int width, int windowWidth, int windowHeight, int hPadding) {
        this.color = color;
        this.width = width;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.hPadding = hPadding;

        start.x = hPadding;
        end.y = start.y = windowHeight / 2f;
        end.x = windowWidth - hPadding;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(width);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
    }

    public void show(Canvas canvas) {
        canvas.drawLine(start.x, start.y, end.x, end.y, paint);
    }

    public void update(float x, int max) {

        float stepGap = (windowWidth - hPadding * 2f) / max;

        int index = Math.round((x - hPadding) / stepGap);

        float newX = hPadding + index * stepGap;

        if (newX < 0 || newX > windowWidth) {
            return;
        }

        if (action == Action.PRESS_START_THUMB) {
            start.x = newX;
        } else if (action == Action.PRESS_END_THUMB) {
            end.x = newX;
        }
    }

    public void press(float x) {
        if (Math.abs(start.x - x) < Math.abs(end.x - x)) {
            action = Action.PRESS_START_THUMB;
        } else {
            action = Action.PRESS_END_THUMB;
        }

    }

    public void release() {
        action = Action.RELEASE;
    }

    public int getIndex(float x, int max) {
        float stepGap = (windowWidth - hPadding * 2f) / max;
        return Math.round((x - hPadding) / stepGap);
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

    public int gethPadding() {
        return hPadding;
    }

    protected Action getAction() {
        return action;
    }

}
