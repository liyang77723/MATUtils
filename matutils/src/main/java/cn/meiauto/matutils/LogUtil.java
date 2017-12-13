package cn.meiauto.matutils;

import android.util.Log;

/**
 * 日志打印
 */
@SuppressWarnings("unused")
public class LogUtil {
    //控制是否调试程序，在上线时应该关闭
    private static final boolean DEBUG = true;
    private static final String TAG = "-log-";

    private static String mTag = TAG;

    public static void init(String tag) {
        mTag = tag;
    }

    public static void debug(Object msg) {
        if (DEBUG) {
            showLogCat(msg, Log.DEBUG);
        }
    }

    public static void info(Object msg) {
        if (DEBUG) {
            showLogCat(msg, Log.INFO);
        }
    }

    public static void warn(Object msg) {
        if (DEBUG) {
            showLogCat(msg, Log.WARN);
        }
    }

    public static void error(Object msg) {
        if (DEBUG) {
            showLogCat(msg, Log.ERROR);
        }
    }

    public static void error(Throwable tr) {
        if (DEBUG) {
            showLogCat(Log.getStackTraceString(tr), Log.ERROR);
        }
    }

    public static void error(Object msg, Throwable tr) {
        if (DEBUG) {
            showLogCat(msg.toString().concat("\n").concat(Log.getStackTraceString(tr)), Log.ERROR);
        }
    }


    /**
     * 获取调用此方法的类名
     */
    private static String getCurrentClassTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    private static void showLogCat(Object msg, int level) {
        String tag = mTag.concat(":").concat(getCurrentClassTag());
        String content = position().concat(":\n").concat(msg.toString());
        log(level, tag, content);
    }


    /**
     * 超链接，显示调用打印方法的位置
     */
    private static String position() {
        StackTraceElement[] stackTraceElement = Thread.currentThread()
                .getStackTrace();
        int currentIndex = -1;
        for (int i = 0; i < stackTraceElement.length; i++) {
            if (stackTraceElement[i].toString().contains(getCurrentClassTag())) {
                currentIndex = i + 3;
                break;
            }
        }
        String fullClassName = stackTraceElement[currentIndex].getClassName();
        String className = fullClassName.substring(fullClassName
                .lastIndexOf(".") + 1);
        String methodName = stackTraceElement[currentIndex].getMethodName();
        String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
        return "at " + className + "." + methodName + "(" + className + ".java:" + lineNumber + ")";
    }

    private static final int MAX_LOG_LENGTH = 3000;

    private static void log(int level, String tag, String content) {
        int length = content.length();
        if (length <= MAX_LOG_LENGTH) {
            switch (level) {
                case Log.DEBUG:
                    Log.d(tag, content);
                    break;
                case Log.INFO:
                    Log.i(tag, content);
                    break;
                case Log.WARN:
                    Log.w(tag, content);
                    break;
                case Log.ERROR:
                    Log.e(tag, content);
                    break;
            }
        } else {
            int index = 0;
            String logContent;
            while (index < length) {
                int end = index + MAX_LOG_LENGTH;
                if (end > length) {
                    logContent = content.substring(index);
                } else {
                    logContent = content.substring(index, end);
                }
                switch (level) {
                    case Log.DEBUG:
                        Log.d(tag, logContent);
                        break;
                    case Log.INFO:
                        Log.i(tag, logContent);
                        break;
                    case Log.WARN:
                        Log.w(tag, logContent);
                        break;
                    case Log.ERROR:
                        Log.e(tag, logContent);
                        break;
                }
                index += MAX_LOG_LENGTH;
            }
        }
    }
}
