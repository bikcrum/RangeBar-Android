package com.bikcrum.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bikcrum.widget.Util.Bar;
import com.bikcrum.widget.Util.ConnectingLine;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class RangeBar extends View {

    //attrs
    private int max;
    private int startIndex;
    private int endIndex;
    private int colorTint;

    //local vars

    //local vars
    private int thumbRadius;
    private int barHeight;

    private Bar bar;
    private ConnectingLine connectingLine;


    public RangeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeBar, defStyleAttr, 0);

        max = a.getInteger(R.styleable.RangeBar_max, 100);
        startIndex = a.getInteger(R.styleable.RangeBar_startIndex, 0);
        endIndex = a.getInteger(R.styleable.RangeBar_endIndex, max - 1);
        colorTint = a.getColor(R.styleable.RangeBar_colorTint, Color.parseColor("#FF4081"));
        thumbRadius = a.getDimensionPixelSize(R.styleable.RangeBar_thumbRadius, 25);
        barHeight = a.getDimensionPixelSize(R.styleable.RangeBar_barHeight, 8);

        a.recycle();

        if (max < 3) {
            max = 3;
        }

        if (endIndex >= max) {
            endIndex = max - 1;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 80;
        int desiredHeight = 40;


        if (Math.max(thumbRadius * 2, barHeight) > desiredHeight) {
            desiredHeight = Math.max(thumbRadius * 2, barHeight);
        }

        if (Math.max(thumbRadius * 2, barHeight) > desiredWidth) {
            desiredWidth = Math.max(thumbRadius * 2, barHeight);
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Widget Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Widget Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bar.show(canvas);
        connectingLine.show(canvas);

    }

    /*
        private int getThumbColor(boolean pressed) {
            TypedValue typedValue = new TypedValue();
            TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[] { colorAttr });
            int color = a.getColor(0, 0);
            a.recycle();
            return color;

            if (thumbDrawable == null) {
                if (isEnabled()) {
                    if (pressed) {
                        return getResources().getColor(R.color.colorPrimary);
                    }
                    return Color.;
                } else {
                    return Color.parseColor("#eeeeee");
                }
            } else {
                mTempStates[0] = isEnabled() ? android.R.attr.state_enabled : -android.R.attr.state_enabled;
                mTempStates[1] = pressed ? android.R.attr.state_pressed : -android.R.attr.state_pressed;
                return sliderColor.getColorForState(mTempStates, 0);
            }
        }
    */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bar = new Bar(Color.LTGRAY, barHeight, w, h, thumbRadius);
        connectingLine = new ConnectingLine(colorTint, barHeight, w, h, thumbRadius);

    }

    void print(String str) {
        Log.i("biky", str);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }
        float x = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                connectingLine.press(x);
                connectingLine.update(x, 20);
                break;
            case MotionEvent.ACTION_MOVE:
                connectingLine.update(x, 20);
                break;
            case MotionEvent.ACTION_UP:
                connectingLine.release();
                break;
            default:
                return super.onTouchEvent(event);
        }

        invalidate();
        return true;
    }
}
