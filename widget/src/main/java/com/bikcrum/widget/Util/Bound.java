package com.bikcrum.widget.Util;

import android.graphics.PointF;

/**
 * Created by LENEVO on 1/26/2018.
 */

public class Bound {
    public PointF start = new PointF();
    public PointF end = new PointF();

    public float length;

    public Bound() {
    }

    public Bound(PointF start, PointF end) {
        this.start.x = start.x;
        this.start.y = start.y;

        this.end.x = end.x;
        this.end.y = end.y;

        length = end.x - start.x;
    }
}
