package upraxis.com.task.utils;

import android.util.Log;

import upraxis.com.task.BuildConfig;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class LogHelper {

    public static void log(final String key, final String message) {
        if (BuildConfig.DEBUG) {
            Log.d(key, message);
        }
    }
}
