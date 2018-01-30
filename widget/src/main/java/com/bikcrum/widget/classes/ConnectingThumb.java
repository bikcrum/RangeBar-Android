package com.bikcrum.widget.classes;

import android.graphics.Canvas;

/**
 * Created by LENEVO on 1/29/2018.
 */

public class ConnectingThumb extends Bar {

    private final float THUMB_RELEASE_SCALE = 0.8f;
    private float startThumbRadius;
    private float endThumbRadius;

    private float stepGap;
    private int progressStart;
    private int progressEnd;

    private boolean isRange;

    protected enum Action {
        PRESS_START_THUMB,
        PRESS_END_THUMB,
        RELEASE
    }

    private Action action;


    public ConnectingThumb(int color, float height, int windowWidth, int windowHeight, float thumbRadius, int max) {
        super(color, height, windowWidth, windowHeight, thumbRadius);

        startThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
        endThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;

        stepGap = (getWindowWidth() - getThumbRadius() * 2f) / max;
    }

    public void setRangeEnabled(boolean isRange) {
        this.isRange = isRange;
    }

    @Override
    public void show(Canvas canvas) {
        getStart().x = getThumbRadius() + progressStart * stepGap;
        getEnd().x = getThumbRadius() + progressEnd * stepGap;

        super.show(canvas);

        if (isRange) {
            canvas.drawCircle(getStart().x, getStart().y, startThumbRadius, getPaint());
        }
        canvas.drawCircle(getEnd().x, getEnd().y, endThumbRadius, getPaint());
    }

    public void update(float x) {
        if (x < getThumbRadius()) {
            x = getThumbRadius();
        } else if (x > getWindowWidth() - getThumbRadius()) {
            x = getWindowWidth() - getThumbRadius();
        }

        int progress = Math.round((x - getThumbRadius()) / stepGap);

        if (action == Action.PRESS_START_THUMB) {

            progressStart = progress;

            startThumbRadius = getThumbRadius();
            endThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;

        } else if (action == Action.PRESS_END_THUMB) {

            progressEnd = progress;

            startThumbRadius = getThumbRadius() * THUMB_RELEASE_SCALE;
            endThumbRadius = getThumbRadius();
        }
    }

    public int getProgressStart() {
        return progressStart;
    }

    public void setProgressStart(int startIndex) {
        this.progressStart = startIndex;
    }

    public int getProgressEnd() {
        return progressEnd;
    }

    public void setProgressEnd(int progressEnd) {
        this.progressEnd = progressEnd;
    }

    public void setMax(int max) {
        stepGap = (getWindowWidth() - getThumbRadius() * 2f) / max;
    }

    public void press(float x) {
        if (Math.abs(getStart().x - x) < Math.abs(getEnd().x - x) && isRange) {
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

    public void setRangeColor(int color) {
        super.setColor(color);
    }
}
