package com.bikcrum.widget.Util;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by LENOVO on 1/17/2018.
 */

public class Thumb {
    private Canvas canvas;
    private int radius;
    private ColorStateList colorStateList;
    private Point point;

    public Thumb(Canvas canvas, int radius, ColorStateList colorStateList, Point point) {
        this.canvas = canvas;
        this.radius = radius;
        this.colorStateList = colorStateList;
        this.point = point;
    }
}