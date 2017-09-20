package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomItemLayout;

public class MyAttentionActivity extends BaseActivity {

    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.attention_pollution_source)
    CustomItemLayout attentionPollutionSource;
    @Bind(R.id.attention_unusual_type)
    CustomItemLayout attentionUnusualType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        ButterKnife.bind(this);
        initActionBar();

    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }
    @OnClick({R.id.attention_pollution_source, R.id.attention_unusual_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attention_pollution_source:
                gotoPoll();
                break;
            case R.id.attention_unusual_type:
                gotoOut();
                break;
        }
    }

    private void gotoOut() {
        Intent intent = new Intent();
        intent.setClass(MyAttentionActivity.this, AttentionOutPollActivity.class);
        startActivity(intent);
    }

    private void gotoPoll() {
        Intent intent = new Intent();
        intent.setClass(MyAttentionActivity.this, AttentionPollutionEnterpriseActivity.class);
        startActivity(intent);
    }
}
