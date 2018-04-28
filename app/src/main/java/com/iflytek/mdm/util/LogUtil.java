package com.ssdj.elearn.utils;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * @author zeminwang
 * @version V1.0
 * @Company: 深圳市三三得玖通信有限公司
 * @Title: 教视通
 * @Description:
 * @date 16/11/21 14:50
 */
public class LogUtil {

    public static final int LOGLEVEL_VERBOSE = 2;
    public static final int LOGLEVEL_DEBUG = 3;
    public static final int LOGLEVEL_INFO = 4;
    public static final int LOGLEVEL_WARN = 5;
    public static final int LOGLEVEL_ERROR = 6;

    /* (Lifted from virgo47's stackoverflow answer) */
    private static final int CLIENT_CODE_STACK_INDEX;

    private static boolean OTHER_LOG = true;

    static {
        // Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
        int i = 0;
        for (StackTraceElement ste: Thread.currentThread().getStackTrace())
        {
            i++;
            if (ste.getClassName().equals(LogUtil.class.getName()))
            {
                break;
            }
        }
        CLIENT_CODE_STACK_INDEX = i;
    }

    private LogUtil() {
        throw new UnsupportedOperationException(
                "Cannot initialize " + getClass().getCanonicalName() + " class");
    }

    private static String addCallerInformation() {
        int i, lio; //lio = lastIndexOf
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for (i = 0; !stack[i].getClassName().equals(LogUtil.class.getName()); i++) {
        }
        for (; stack[i].getClassName().equals(LogUtil.class.getName()); i++) {
        }
        lio = stack[i].getFileName().lastIndexOf('.');
        if (lio == -1) {
            return " (" + stack[i].getFileName() + ":" + stack[i].getLineNumber() + ")";
        } else {
            return " ("
                    + stack[i].getFileName().substring(0, lio)
                    + ":"
                    + stack[i].getLineNumber()
                    + ")";
        }
    }

    public static void init(boolean toggle,String tag) {
        Logger.init(tag).logLevel(toggle ? LogLevel.FULL : LogLevel.NONE)
        .methodCount(1);
        setLogEnabled(toggle);
    }

    private static String getCurrentFileName(int offset) {
        String filename = Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getFileName();
        int lineNumber = Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getLineNumber();
        return filename + ":" + lineNumber;
    }

    private static String getCurrentMethodName(int offset) {
        return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getMethodName();
    }

    private static String getCallerInformation() {
        String info = "[" + Thread.currentThread().getName() + "]" + "[" + getCurrentFileName(2) + "]" + "[" + getCurrentMethodName(2) + "]";
        return info;
    }

    private static boolean sLogEnabled = true;
    public static void setLogEnabled(boolean bEnabled)
    {
        sLogEnabled = bEnabled;
    }

    private static int sLogLevel = LOGLEVEL_VERBOSE;
    public static void setLoglevel(int level)
    {
        sLogLevel = level;
    }

    public static void v(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_VERBOSE) {
            if (!OTHER_LOG) Logger.v((null != message ? message.toString().trim() : "null"));
            else Log.v(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void d(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_DEBUG) {
            if (!OTHER_LOG) Logger.d((null != message ? message.toString().trim() : "null"));
            else Log.d(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void i(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_INFO) {
            if (!OTHER_LOG) Logger.i((null != message ? message.toString().trim() : "null"));
            else Log.i(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void w(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_WARN) {
            if (!OTHER_LOG) Logger.w((null != message ? message.toString().trim() : "null"));
            else Log.w(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void e(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_ERROR) {
            if (!OTHER_LOG) Logger.e((null != message ? message.toString().trim() : "null"));
            else Log.e(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void wtf(Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_ERROR) {
            if (!OTHER_LOG) Logger.wtf((null != message ? message.toString().trim() : "null"));
            else Log.wtf(getCallerInformation(),(null != message ? message.toString().trim() : "null"));
        }
    }

    public static void i(Object obj, Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_INFO) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).i(msg);
                else Log.i(obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation());
            }
        }
    }

    public static void e(Object obj, Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_ERROR) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).e(msg);
                else Log.e(obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation());
            }
        }
    }

    public static void d(Object obj, Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_DEBUG) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).d(msg);
                else Log.d(obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation());
            }
        }
    }

    public static void v(Object obj, Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_VERBOSE) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).v(msg);
                else Log.v(obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation());
            }
        }
    }

    public static void w(Object obj, Object message) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_WARN) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).w(msg);

                else Log.w(obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation());
            }
        }
    }

    public static void wtf(Object obj, Object message, Throwable t) {
        if (sLogEnabled && sLogLevel <= LOGLEVEL_ERROR) {
            if (obj != null && message != null) {
                String tag = obj instanceof CharSequence ? obj.toString() : obj.getClass().getSimpleName();
                String msg = message.toString().trim() + addCallerInformation();
                if (!OTHER_LOG) Logger.t(tag).wtf(msg);
                else Log.wtf(obj instanceof CharSequence ? obj.toString()
                                : obj.getClass().getSimpleName(),
                        message.toString().trim() + addCallerInformation(), t);
            }
        }
    }
}
