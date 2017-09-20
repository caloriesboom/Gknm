package gdg.com.gknm.activity.my;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.HomeActivity;
import gdg.com.gknm.R;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.bean.UserBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.service.MessagePushService;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.UIUtil;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static gdg.com.gknm.constant.ConstantIndex.USERINFO_OBJECT;
import static gdg.com.gknm.utils.CheckOutUtils.checkNullInfo;

public class LoginActivity extends AppCompatActivity {
    protected Subscription mSubscription;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.check_box_remeber)
    CheckBox checkBoxRemeber;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.configuration)
    Button configuration;
    @Bind(R.id.tv_ip)
    TextView tvIp;
    @Bind(R.id.edit_configuration)
    EditText editConfiguration;
    @Bind(R.id.btn_configuration)
    Button btnConfiguration;
    @Bind(R.id.layout_configuration)
    LinearLayout layoutConfiguration;
    private ProgressDialog mProgressDialog;
    private String TAG = "LoginActivity";
    private String userName = "";
    private String passWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        if (SharedPreferenceUtil.getObject(USERINFO_OBJECT, this) != null) {
            LogUtil.d(TAG, "SharedPreferenceUtil.getObject(USERINFO_OBJECT,this)==不为空");
            initService();
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            LogUtil.d(TAG, "SharedPreferenceUtil.getObject(USERINFO_OBJECT,this)==为空");
        }
    }

    private void initView() {
        mProgressDialog = UIUtil.initDialog(this, "正在登陆...");
    }

    @OnClick({R.id.check_box_remeber, R.id.login, R.id.configuration, R.id.btn_configuration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_box_remeber:
                break;
            case R.id.login:
                String userName = etUsername.getText().toString();
                String passWord = etPassword.getText().toString();

                if (checkNullInfo(this, userName, "用户名") && (checkNullInfo(this, passWord, "密码"))) {
                    login(userName, passWord);
                }
                break;
            case R.id.configuration:
                layoutConfiguration.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_configuration:
//                if (!"".equals(editConfiguration.getText().toString().trim())) {
//                    String url = editConfiguration.getText().toString().trim();
//                    String ip = "";
//                    String port = "";
//                    if(url.contains(":")){
//                        if(url.split(":").length>1){
//                            ip = editConfiguration.getText().toString().trim().split(":")[0];
//                            port = editConfiguration.getText().toString().trim().split(":")[1];
//                            LogUtil.d(TAG,"ip=="+ip);
//                            LogUtil.d(TAG,"port=="+port);
//                            SharedPreferenceUtil.save(ConstantIndex.SP_URL_IP, ip);
//                            SharedPreferenceUtil.save(ConstantIndex.SP_URL_PORT, ":"+port);
//                            SharedPreferenceUtil.save("base_url", "http://"+ip+":"+port);
//                            LogUtil.d(TAG,"ip=="+ip);
//                            LogUtil.d(TAG,"port=="+port);
//                            layoutConfiguration.setVisibility(View.GONE);
//                        }else{
//                            ToastUtils.showShortToast(this,"非法的url");
//                        }
//                    }else{
//                        ToastUtils.showShortToast(this,"非法的url");
//                    }
//
//                }
                break;
        }
    }

    /**
     * 登录
     */
    private void login(String userName, String passWord) {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserBean>(this) {
                    @Override
                    protected void onSucceed(UserBean result) {
                        if (result.isResultCode()) {
                            LogUtil.d(TAG, "登录成功");
                            initUser(result);
                        } else {
                            LogUtil.d(TAG, "登录失败" + result.getMessage());
                            ToastUtils.showShortToast(LoginActivity.this, result.getMessage());
                        }

                        UIUtil.cancleDialog(mProgressDialog);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, "登录失败：：" + msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
    }

    private void initUser(UserBean userBean) {
        MyApplication.initUserInfo(userBean);
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        SharedPreferenceUtil.saveBoolean("islogin", true);
        SharedPreferenceUtil.saveObject(USERINFO_OBJECT, getApplicationContext(), userBean);
        initService();

        this.finish();
    }

    private void initService() {
        /**
         * 判断服务未启动 则启动服务
         */
        ActivityManager activityManager = (ActivityManager)
                LoginActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        String classNames = "";
        for (int i = 0; i < serviceList.size(); i++) {
            classNames = classNames + serviceList.get(i).service.getClassName() + ",";
        }
        if (!classNames.contains(MessagePushService.class.getName()) && MyApplication.mIsLogin) {
            Intent intent2 = new Intent(LoginActivity.this, MessagePushService.class);
            if (SharedPreferenceUtil.get("Time", "10") != null) {
                intent2.putExtra("time", SharedPreferenceUtil.get("Time", "10"));
            } else {
                intent2.putExtra("time", "10");
            }
            LoginActivity.this.startService(intent2);//开启服务
            System.out.println("登陆时开启服务");
        }

    }

    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
