package waisblut.com.mywindnotificator;

import android.util.Log;

public final class Logger {

    //Project Constants
    private final static String TAG = "waisblut";
    private final static boolean IS_DEBUG = BuildConfig.DEBUG;

    public final static String API_KEY = "14b0e6fe8746bbc9c2820b1c8cc87270";
    private final static int  MINIMUM_REQUEST_TIME = 10; //Minutes

    public static void log(char type, String s) {
        if (IS_DEBUG) {
            switch (type) {
                case 'd':
                    Log.d(TAG, s);
                    break;

                case 'e':
                    Log.e(TAG, s);
                    break;

                case 'i':
                    Log.i(TAG, s);
                    break;

                case 'v':
                    Log.v(TAG, s);
                    break;

                case 'w':
                    Log.w(TAG, s);
                    break;

                default:
                    break;
            }
        }
    }
}
