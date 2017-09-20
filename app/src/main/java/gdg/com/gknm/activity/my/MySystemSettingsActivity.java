package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomItemLayout;

public class MySystemSettingsActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.system_check_new_version)
    CustomItemLayout systemCheckNewVersion;
    @Bind(R.id.system_about_us)
    CustomItemLayout systemAboutUs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_system_settings);
        ButterKnife.bind(this);
        initActionBar();
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }

    @OnClick({R.id.system_check_new_version, R.id.system_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.system_check_new_version:
                ToastUtils.showShortToast(this,"您的版本已经是最新了");
                break;
            case R.id.system_about_us:
                Intent intent =new Intent();
                intent.setClass(MySystemSettingsActivity.this, MySystemSettingAboutUsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
