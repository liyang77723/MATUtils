package cn.meiauto.matutils;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtil {

    public static File makeDirsIfNotExist(String dir) {
        File dirFile = new File(dir);
        //noinspection ResultOfMethodCallIgnored
        dirFile.mkdirs();
        return dirFile;
    }

    public static boolean isExist(File file, long wantLength) {
        return file.isFile() && file.length() == wantLength;
    }

    /**
     * 返回byte的数据大小对应的文本
     */
    public static String convertFileLenght(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024f) {
            return size + "bytes";
        } else if (size < 1048576F) {
            float kbsize = size / 1024F;
            return formater.format(kbsize) + "KB";
        } else if (size < 1073741824F) {
            float mbsize = size / 1048576F;
            return formater.format(mbsize) + "MB";
        } else if (size < 1099511627776F) {
            float gbsize = size / 1073741824F;
            return formater.format(gbsize) + "GB";
        } else {
            return "";
        }
    }

}
