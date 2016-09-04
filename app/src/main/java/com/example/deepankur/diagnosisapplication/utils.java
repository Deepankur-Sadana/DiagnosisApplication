package com.example.deepankur.diagnosisapplication;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by deepankur on 9/4/16.
 */

public class Utils {

    public static int convertDpToPixels(Context context, int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
    }
}
