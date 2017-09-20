package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.even.ExitLoginEvent;
import gdg.com.gknm.service.MessagePushService;
import gdg.com.gknm.utils.RxBus;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomItemLayout;

import static gdg.com.gknm.constant.ConstantIndex.USERINFO_OBJECT;

public class UserInfoActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.my_username)
    CustomItemLayout myUsername;
    @Bind(R.id.my_real_name)
    CustomItemLayout myRealName;
    @Bind(R.id.my_description)
    CustomItemLayout myDescription;
    @Bind(R.id.logout)
    Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initActionBar();
        initData();
    }

    private void initData() {
        myUsername.getRightText().setText(MyApplication.mUserName);
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }
    public void logout(View v) {
        switch (v.getId()) {

            case R.id.logout:
                logout();
                break;
        }
    }

    private void logout() {//退出时移除权限
        SharedPreferenceUtil.remove(USERINFO_OBJECT);
        SharedPreferenceUtil.remove("islogin");
        MyApplication.mUser = null;
        MyApplication.mIsLogin = false;
        MyApplication.mUserName = null;

        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_POLL);
        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_THIRD);
        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_UNIT);
        ConstantIndex.SP_IS_SAVE = false;
        ConstantIndex.SP_IS_SIGN = false;

        SharedPreferenceUtil.remove(ConstantIndex.WATER_OR_GAS_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.TASK_ID_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.ENTERPRISE_TYPE_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.INDUSTRY_TYPE_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.ENTERPRISE_ID_CURRENT);

        Intent intent = new Intent(UserInfoActivity.this, MessagePushService.class);
        UserInfoActivity.this.stopService(intent);//关闭服务

        Intent intent1 = new Intent(UserInfoActivity.this, LoginActivity.class);
        startActivity(intent1);
        RxBus.getInstance().post(new ExitLoginEvent());

    }
}
