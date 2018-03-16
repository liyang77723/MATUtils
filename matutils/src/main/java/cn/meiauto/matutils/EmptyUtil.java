package cn.meiauto.matutils;

import android.util.SparseArray;

import java.util.Collection;
import java.util.Map;

public class EmptyUtil {

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNull(CharSequence charSequence) {
        return isEmpty(charSequence) || charSequence.equals("null") || charSequence.equals("NULL");
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isEmpty(SparseArray array) {
        return array == null || array.size() == 0;
    }
}
