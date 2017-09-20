package gdg.com.gknm.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

import gdg.com.gknm.bean.UserBean;
import gdg.com.gknm.service.MessagePushService;
import gdg.com.gknm.utils.ContextUtil;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.SharedPreferenceUtil;

import static gdg.com.gknm.constant.ConstantIndex.USERINFO_OBJECT;

/**
 * Created by GUO.DG on 2017-8-23.
 *
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static Context appContext;
    public static MyApplication myApp = null;
    //用户信息
    public static UserBean mUser ;
    public static String  mUserName = "";
    public static String mUserId = "";
    public static String mCheckUnit = "";
    public static boolean mIsLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        appContext = this;
        ContextUtil.init(getApplicationContext());
        initUser();
        initGlobal();
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

    /**
     * 初始化用户信息
     *
     * @param user
     */
    public static void initUserInfo(UserBean user) {
        mIsLogin = true;//登录成功后为true
        mUser = user;
        mUserId = user.getResultEntity().getUserId();
        mUserName = user.getResultEntity().getUserName();
        LogUtil.d(mUserId);
    }
    private void initUser() {

        UserBean user = (UserBean) SharedPreferenceUtil.getObject(USERINFO_OBJECT, this);
        if (user != null) {
            initUserInfo(user);
            LogUtil.d(TAG,"user 初始化成功");
        }else{
            LogUtil.d(TAG,"user 初始化失败");
        }
    }

    /**
     * 初始化请求队列
     */
    private void initGlobal() {
        Log.e(TAG, "init");
        ActivityManager activityManager = (ActivityManager)
                this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        String classNames = "";
        for (int i = 0; i < serviceList.size(); i++) {
            classNames = classNames + serviceList.get(i).service.getClassName() + ",";
        }
        if (!classNames.contains(MessagePushService.class.getName())) {
            // this.startService(new Intent(this, MessagePushService.class));//开启服务
            Log.e(TAG, "start-service" + classNames);
        }
    }
}
