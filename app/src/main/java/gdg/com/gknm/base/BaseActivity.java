package gdg.com.gknm.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import gdg.com.gknm.R;
import gdg.com.gknm.entity.ActivityManager;
import gdg.com.gknm.even.ExitLoginEvent;
import gdg.com.gknm.utils.RxBus;
import gdg.com.gknm.utils.SystemBarTintManager;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by GUO.DG on 2017-8-23.
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Subscription mSubscription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        ActivityManager.getInstance().pushActivity(this);
        // 设置通知栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryStyles);
        }

        //退出应用
        mSubscription = RxBus.getInstance().toObservable(ExitLoginEvent.class)
                .subscribe(new Action1<ExitLoginEvent>() {
                    @Override
                    public void call(ExitLoginEvent exitLoginEvent) {

                        ActivityManager.getInstance().popActivity(BaseActivity.this);
                        BaseActivity.this.finish();
                        //杀死该应用进程

                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });

    }

    /**
     * 自定义状态栏
     */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelSubscription(mSubscription);
        // 结束Activity&从堆栈中移除

    }
    public static void cancelSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
