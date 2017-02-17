package com.nicksong.superutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;

/**
 * 作者：songyuanlin
 * 创建时间：2017/2/17
 * 功能描述:
 */

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    // 需求是 整个应用程序 只有一个 CsnCrashHandler
    @SuppressLint("StaticFieldLeak")
    private static MyCrashHandler crashHandler;
    private Context context;

    // 1.私有化构造方法
    private MyCrashHandler() {

    }

    public static synchronized MyCrashHandler getInstance() {
        if (crashHandler != null) {
            return crashHandler;
        } else {
            crashHandler = new MyCrashHandler();
            return crashHandler;
        }
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public void uncaughtException(Thread arg0, Throwable arg1) {
        System.out.println("程序挂掉了 ");
        // 1.获取当前程序的版本号. 版本的id
        String versioninfo = getVersionInfo();

        // 2.获取手机的硬件信息.
        String mobileInfo = getMobileInfo();

        String exceptionTime = FormatUtils.dateTimeFormat(String.valueOf(System.currentTimeMillis()));

        // 3.把错误的堆栈信息 获取出来
        String errorinfo = getErrorInfo(arg1);

        // 4.把所有的信息 还有信息对应的时间 提交到服务器或者保存到本地存储
        AppLogUtil.i("bcf", "errorinfo: " + errorinfo);
        //saveFile("应用版本: " + versioninfo + getUserInfo() + "\n时间: " + exceptionTime + "\n设备信息: " + mobileInfo + "\n错误信息:\n" + errorinfo);
    }

    private void saveFile(String str) {
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "csn/" + FormatUtils.dateTimeFormat(String.valueOf(System.currentTimeMillis())) + ".txt";
        } else
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + "csn/CsnCrash.txt";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 干掉当前的程序
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取错误的信息
     *
     * @param arg1
     * @return
     */
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    /**
     * 获取手机的硬件信息
     *
     * @return
     */
    private String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        // 通过反射获取系统的硬件信息
        try {

            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                // 暴力反射 ,获取私有的信息
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                sb.append(name + " = " + value);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取手机的版本信息
     *
     * @return
     */
    private String getVersionInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }
}