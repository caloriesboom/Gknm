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

public class MyServiceCentreActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.service_leave_message)
    CustomItemLayout serviceLeaveMessage;
    @Bind(R.id.service_normal_question)
    CustomItemLayout serviceNormalQuestion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service_centre);
        ButterKnife.bind(this);
        initActionBar();
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }

    @OnClick({R.id.service_leave_message, R.id.service_normal_question})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.service_leave_message:
                intent.setClass(MyServiceCentreActivity.this,ServiceLeaveMessageActivity.class);
                break;
            case R.id.service_normal_question:
                intent.setClass(MyServiceCentreActivity.this,ServiceNormalQuestionActivity.class);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
