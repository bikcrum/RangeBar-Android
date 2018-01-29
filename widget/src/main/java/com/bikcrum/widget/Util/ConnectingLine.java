package com.bikcrum.widget.Util;

import android.graphics.Canvas;

/**
 * Created by LENEVO on 1/29/2018.
 */

public class ConnectingLine extends Bar {
    private final float THUMB_RELEASE_SCALE = 0.8f;
    private float startThumbRadius;
    private float endThumbRadius;

    public ConnectingLine(int color, int width, int windowWidth, int windowHeight, int hPadding) {
        super(color, width, windowWidth, windowHeight, hPadding);
        startThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
        endThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
    }

    @Override
    public void show(Canvas canvas) {
        super.show(canvas);
        canvas.drawCircle(getStart().x, getStart().y, startThumbRadius, getPaint());
        canvas.drawCircle(getEnd().x, getEnd().y, endThumbRadius, getPaint());
    }

    @Override
    public void update(float x, int max) {
        super.update(x, max);

    }

    @Override
    public void press(float x) {
        super.press(x);
        if (getAction() == Action.PRESS_START_THUMB) {
            startThumbRadius = gethPadding();
            endThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
        } else if (getAction() == Action.PRESS_END_THUMB) {
            startThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
            endThumbRadius = gethPadding();
        }
    }

    @Override
    public void release() {
        super.release();
        startThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
        endThumbRadius = gethPadding() * THUMB_RELEASE_SCALE;
    }
}
