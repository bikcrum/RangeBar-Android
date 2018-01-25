package com.bikcrum.widget;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.bikcrum.widget.Util.Thumb;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class RangeBar extends View {

    //attrs
    private int max;
    private int startIndex;
    private int endIndex;
    private ColorStateList colorTint;
    private float barWidth;
    private float thumbWidth;
    private ColorStateList thumbDrawable;

    //local vars
    private PointF startThumbPoint;
    private PointF endThumbPoint;
    private PointF startBarPoint;
    private PointF endBarPoint;
    private Thumb startThumb;
    private Thumb endThumb;
    private float barLength;
    private float stepGap;
    private Paint paint = new Paint();

    public RangeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private int getColor(int colorAttr,int defaultColor) throws Exception {
        TypedValue typedValue = new TypedValue();
        TypedArray a = getContext().obtainStyledAttributes(typedValue.data, new int[] { colorAttr });
        int color = a.getColor(0, defaultColor);
        a.recycle();
        return color;
    }

    //examples accessing colors


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeBar, defStyleAttr, 0);

        max = a.getInteger(R.styleable.RangeBar_max, 100);
        startIndex = a.getInteger(R.styleable.RangeBar_startIndex, 0);
        endIndex = a.getInteger(R.styleable.RangeBar_endIndex, max - 1);
        colorTint = a.getColorStateList(R.styleable.RangeBar_colorTint);
        barWidth = a.getDimension(R.styleable.RangeBar_barWidth, 10);
        thumbWidth = a.getDimension(R.styleable.RangeBar_thumbWidth, 10);
        thumbDrawable = a.getColorStateList(R.styleable.RangeBar_thumbDrawable);

        if (thumbDrawable == null) {
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_enabled}, // enabled
                    new int[] {-android.R.attr.state_enabled}, // disabled
                    new int[] {-android.R.attr.state_checked}, // unchecked
                    new int[] { android.R.attr.state_pressed}  // pressed
            };
/*
            int colorAccent;
            try{
            colorPressed = getColor(R.attr.colorAccent,Color.RED);
            color = getColor(R.attr.colorPrimary,Color.parseColor("#FF4081"));


            int[] colors = new int[] {
                    getColor(R.attr.colorAccent),
                    Color.RED,
                    Color.GREEN,
                    getColor(R.attr.colorPrimary)
            };
*/
        //    thumbDrawable = new ColorStateList(states, colors);
        }

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

        if (Math.max(thumbWidth, barWidth) > desiredHeight) {
            desiredHeight = (int) Math.max(thumbWidth, barWidth);
        }

        if (Math.max(thumbWidth, barWidth) > desiredWidth) {
            desiredWidth = (int) Math.max(thumbWidth, barWidth);
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
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

        //Measure Height
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

        paint.reset();

        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(barWidth);
        paint.setDither(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawLine(startBarPoint.x, startBarPoint.y, endBarPoint.x, endBarPoint.y, paint);

        paint.reset();

        paint.setColor(thumbDrawable.getColorForState(new int[]{android.R.attr.state_enabled}, Color.RED));
        paint.setStrokeWidth(barWidth);
        paint.setDither(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawLine(startThumbPoint.x, startThumbPoint.y, endThumbPoint.x, endThumbPoint.y, paint);

        canvas.drawCircle(startThumbPoint.x, startThumbPoint.y, thumbWidth / 2, paint);

        canvas.drawCircle(endThumbPoint.x, endThumbPoint.y, thumbWidth / 2, paint);


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

        startBarPoint = new PointF(thumbWidth / 2, h / 2);
        endBarPoint = new PointF(w - thumbWidth / 2, h / 2);

        startThumbPoint = new PointF(thumbWidth / 2, h / 2);
        endThumbPoint = new PointF(w - thumbWidth / 2, h / 2);

        barLength = w - thumbWidth;

        stepGap = barLength / max;

        print("steps gap = " + stepGap);
    }

    void print(String str) {
        Log.i("biky", str);
    }

    boolean nearStartThumb = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                nearStartThumb = nearStartThumb(new PointF(x, y));

                break;
            case MotionEvent.ACTION_MOVE:
                if (nearStartThumb) {

                    if (x >= startBarPoint.x && x <= endBarPoint.x) {

                        startIndex = (int) Math.round((x - thumbWidth / 2f) / stepGap);
                        print("start index = " + startIndex);

                        startThumbPoint.x = startIndex * stepGap + startBarPoint.x;
                    }
                } else {
                    if (x >= startBarPoint.x && x <= endBarPoint.x) {

                        endIndex = (int) Math.round((x - thumbWidth / 2f) / stepGap);
                        print("end index = " + endIndex);

                        endThumbPoint.x = endIndex * stepGap + startBarPoint.x;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return super.onTouchEvent(event);
        }

        //  Log.i("biky", "x = " + x + " y = " + y);
        invalidate();
        return true;
    }

    boolean nearStartThumb(PointF pointF) {
        return Math.abs(startThumbPoint.x - pointF.x) < Math.abs(endThumbPoint.x - pointF.x);
    }
}
