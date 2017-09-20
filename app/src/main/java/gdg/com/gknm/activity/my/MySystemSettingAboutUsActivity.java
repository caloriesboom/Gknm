package gdg.com.gknm.activity.my;

import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.weight.CustomActionBar;

public class MySystemSettingAboutUsActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_system_setting_about_us);
        ButterKnife.bind(this);
        initActionBar();
    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }
}
