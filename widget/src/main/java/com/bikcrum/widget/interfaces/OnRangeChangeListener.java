package com.bikcrum.widget.interfaces;

import com.bikcrum.widget.RangeBar;

/**
 * Created by LENEVO on 1/29/2018.
 */

public interface OnRangeChangeListener {
    void onRangeChanged(RangeBar bar, int startIndex,int endIndex, boolean fromUser);
    void onStartTrackingTouch(RangeBar bar);
    void onStopTrackingTouch(RangeBar bar);
}
