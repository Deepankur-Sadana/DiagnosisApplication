package database;

import android.content.Context;

/**
 * Created by deepankur on 9/4/16.
 */

public class FireBaseHelper {

    private static FireBaseHelper sFireBaseHelper;

    public static FireBaseHelper getInstance(Context context) {
        if (sFireBaseHelper == null) sFireBaseHelper = new FireBaseHelper(context);
        return sFireBaseHelper;
    }

    private FireBaseHelper(Context context) {
    }
}
