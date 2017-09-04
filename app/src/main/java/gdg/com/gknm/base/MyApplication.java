package gdg.com.gknm.base;

import android.app.Application;
import android.content.Context;

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
}
