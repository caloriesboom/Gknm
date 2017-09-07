package gdg.com.gknm.activity.home;

import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.weight.CustomActionBar;

public class SignActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        initActionBar();
    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
        mActionBar.getRightImageView().setVisibility(View.VISIBLE);
    }
}
