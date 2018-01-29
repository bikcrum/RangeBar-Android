package com.bikcrum.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.bikcrum.widget.classes.Bar;
import com.bikcrum.widget.classes.ConnectingLine;
import com.bikcrum.widget.interfaces.OnRangeChangeListener;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class RangeBar extends View {

    private int max;
    private int startIndex;
    private int endIndex;
    private int colorTint;

    private float thumbRadius;
    private float barHeight;

    private Bar bar;
    private ConnectingLine connectingLine;

    private OnRangeChangeListener onRangeChangeListener;

    public RangeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public void setOnRangeChangeListener(OnRangeChangeListener onRangeChangeListener) {
        this.onRangeChangeListener = onRangeChangeListener;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeBar, defStyleAttr, 0);

        max = a.getInteger(R.styleable.RangeBar_max, 100);
        try {
            colorTint = a.getColor(R.styleable.RangeBar_colorTint, getDefaultColorTint());
        } catch (Exception e) {
            colorTint = getDefaultColorTint();
            e.printStackTrace();
        }

        thumbRadius = a.getDimension(R.styleable.RangeBar_thumbRadius, dpToPx(8));
        barHeight = a.getDimension(R.styleable.RangeBar_barHeight, dpToPx(3));

        if (max < 4) {
            max = 4;
        }

        startIndex = a.getInteger(R.styleable.RangeBar_startIndex, 1);
        endIndex = a.getInteger(R.styleable.RangeBar_endIndex, max - 2);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 80;
        int desiredHeight = 40 + getPaddingTop() + getPaddingBottom();

        if (Math.max(thumbRadius * 2, barHeight) > desiredHeight) {
            desiredHeight = (int) Math.max(thumbRadius * 2f, barHeight);
        }

        if (Math.max(thumbRadius * 2, barHeight) > desiredWidth) {
            desiredWidth = (int) Math.max(thumbRadius * 2f, barHeight);
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

        if (bar != null) bar.show(canvas);
        if (connectingLine != null) connectingLine.show(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bar = new Bar(Color.LTGRAY, barHeight, w, h, thumbRadius);

        connectingLine = new ConnectingLine(colorTint, barHeight, w, h, thumbRadius, max);

        connectingLine.setStartIndex(startIndex);
        connectingLine.setEndIndex(endIndex);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        if (connectingLine != null) connectingLine.setMax(max);
        invalidate();
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
        if (startIndexPrev != startIndex && connectingLine != null) {
            connectingLine.setStartIndex(startIndex);
            if (onRangeChangeListener != null) {
                onRangeChangeListener.onRangeChanged(this, Math.min(startIndex, endIndex), Math.max(startIndex, endIndex), true);
            }
            startIndexPrev = startIndex;
            invalidate();
        }
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
        if (endIndexPrev != endIndex && connectingLine != null) {
            connectingLine.setEndIndex(endIndex);
            if (onRangeChangeListener != null) {
                onRangeChangeListener.onRangeChanged(this, Math.min(startIndex, endIndex), Math.max(startIndex, endIndex), true);
            }
            endIndexPrev = endIndex;
            invalidate();
        }
        invalidate();

    }

    int startIndexPrev = -1;
    int endIndexPrev = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }
        float x = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (connectingLine != null) {
                    connectingLine.press(x);
                    connectingLine.update(x);
                }
                if (onRangeChangeListener != null) {
                    onRangeChangeListener.onStartTrackingTouch(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (connectingLine != null) connectingLine.update(x);
                break;
            case MotionEvent.ACTION_UP:
                if (connectingLine != null) connectingLine.release();
                if (onRangeChangeListener != null) {
                    onRangeChangeListener.onStopTrackingTouch(this);
                }
                break;
            default:
                return super.onTouchEvent(event);
        }

        if (connectingLine != null) {
            startIndex = connectingLine.getStartIndex();
            endIndex = connectingLine.getEndIndex();
        }

        if (startIndex != startIndexPrev || endIndex != endIndexPrev) {
            if (onRangeChangeListener != null) {
                onRangeChangeListener.onRangeChanged(this, Math.min(startIndex, endIndex), Math.max(startIndex, endIndex), true);
            }
        }

        startIndexPrev = startIndex;
        endIndexPrev = endIndex;

        invalidate();
        return true;
    }

    public int getDefaultColorTint() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});

        int color;
        try {
            color = a.getColor(0, Color.parseColor("#FF4081"));
        } catch (Exception e) {
            // pink accent
            color = Color.parseColor("#FF4081");
        }

        a.recycle();

        return color;
    }
}
