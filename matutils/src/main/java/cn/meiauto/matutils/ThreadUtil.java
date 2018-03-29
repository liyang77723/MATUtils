package cn.meiauto.matutils;

/**
 * author : LiYang
 * email  : yang.li@nx-engine.com
 * time   : 2018/3/29
 */
public class ThreadUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
