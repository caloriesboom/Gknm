package gdg.com.gknm.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import gdg.com.gknm.utils.ContextUtil;

/**
 * Created by GUO.DG on 2017-8-23.
 *
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static Context appContext;
    public static MyApplication myApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        appContext = this;
        ContextUtil.init(getApplicationContext());
    }
    public synchronized static MyApplication getInstance() {
        return myApp;
    }
    public static Context getAppContext() {
        return appContext;
    }
    public String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }
}
