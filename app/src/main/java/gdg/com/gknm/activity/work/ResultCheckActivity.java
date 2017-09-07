package gdg.com.gknm.activity.work;

import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.weight.CustomActionBar;

public class ResultCheckActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_check);
        ButterKnife.bind(this);
        initActionBar();
    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }
}
