package cn.meiauto.matutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class IntentUtil {
    public static void installApp(Context context, File apkFile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
