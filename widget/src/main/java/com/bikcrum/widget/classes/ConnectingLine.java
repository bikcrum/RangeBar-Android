package com.bikcrum.widget.classes;

import android.graphics.Canvas;

/**
 * Created by LENEVO on 1/29/2018.
 */

public class ConnectingLine extends Bar {

    private final float THUMB_RELEASE_SCALE = 0.8f;
    private float startThumbRadius;
    private float endThumbRadius;

    private float stepGap;
    private int startIndex;
    private int endIndex;

    protected enum Action {
        PRESS_START_THUMB,
        PRESS_END_THUMB,
        RELEASE
    }

    private Action action;


    public ConnectingLine(int color, float height, int windowWidth, int windowHeight, float thumbRadius, int max) {
        super(color, height, windowWidth, windowHeight, thumbRadius);


        startThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
        endThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;

        stepGap = (getWindowWidth() - getThumbRadius() * 2f) / (max - 1);
    }

    @Override
    public void show(Canvas canvas) {
        getStart().x = getThumbRadius() + startIndex * stepGap;
        getEnd().x = getThumbRadius() + endIndex * stepGap;

        super.show(canvas);

        canvas.drawCircle(getStart().x, getStart().y, startThumbRadius, getPaint());
        canvas.drawCircle(getEnd().x, getEnd().y, endThumbRadius, getPaint());
    }

    public void update(float x) {
        int index = Math.round((x - getThumbRadius()) / stepGap);

        if (action == Action.PRESS_START_THUMB) {

            startIndex = index;

            startThumbRadius = getThumbRadius();
            endThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;

        } else if (action == Action.PRESS_END_THUMB) {

            endIndex = index;

            startThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
            endThumbRadius = getThumbRadius();
        }
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setMax(int max) {
        stepGap = (getWindowWidth() - getThumbRadius() * 2f) / (max - 1);
    }

    public void press(float x) {
        if (Math.abs(getStart().x - x) < Math.abs(getEnd().x - x)) {
            action = Action.PRESS_START_THUMB;
        } else {
            action = Action.PRESS_END_THUMB;
        }
    }

    public void release() {
        action = Action.RELEASE;
        startThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
        endThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
    }
}
