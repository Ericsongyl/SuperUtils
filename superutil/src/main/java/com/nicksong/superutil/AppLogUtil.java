package com.nicksong.superutil;

import android.util.Log;

/**
 * Created by Song Yuanlin on 2015/9/14.
 */
public class AppLogUtil {

    public static boolean DEBUG_FLAG = true; //是否打开日志打印

    public static void e(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.v(tag, msg);
        }
    }

    /**
     * 封装异常类打印方法，便于统一控制打开/关闭
     * @param e
     */
    public static void printStackTrace(Exception e) {
        if (DEBUG_FLAG) {
            e.printStackTrace();
        }
    }
}
