package com.example.deepankur.diagnosisapplication.database;

import android.content.Context;

/**
 * Created by deepankur on 9/4/16.
 *
 * Singleton for all com.example.deepankur.diagnosisapplication.database operations
 */

public class FireBaseHelper implements FireBaseKeyIDS{

    private static FireBaseHelper sFireBaseHelper;

    public static FireBaseHelper getInstance(Context context) {
        if (sFireBaseHelper == null) sFireBaseHelper = new FireBaseHelper(context);
        return sFireBaseHelper;
    }

    private FireBaseHelper(Context context) {
    }
}
