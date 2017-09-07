package gdg.com.gknm.activity.work;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.PollInfoBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomEditText;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PollInfoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.custom_action_bar)
    CustomActionBar customActionBar;
    @Bind(R.id.poll_name)
    CustomEditText pollName;
    @Bind(R.id.sp_poll_category)
    Spinner spPollCategory;
    @Bind(R.id.sp_industry_category)
    Spinner spIndustryCategory;
    @Bind(R.id.corporate_name)
    CustomEditText corporateName;
    @Bind(R.id.environment_person)
    CustomEditText environmentPerson;
    @Bind(R.id.tel_number)
    CustomEditText telNumber;
    @Bind(R.id.address)
    CustomEditText address;
    @Bind(R.id.poll_info)
    CustomEditText pollInfo;
    @Bind(R.id.pollution_control)
    CustomEditText pollutionControl;
    @Bind(R.id.activity_monitor_alarm)
    LinearLayout activityMonitorAlarm;
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.toLocal)
    Button toLocal;
    private Subscription mSubscription;
    private List<PollInfoBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private String TAG = "PollInfoActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_info);
        ButterKnife.bind(this);
        initActionBar();
        initView();
    }

    private void initActionBar() {
        customActionBar.setLeftImageClickListener(this);
        customActionBar.getRightImageView().setVisibility(View.VISIBLE);
    }

    protected void initView() {

        initData();
        //   RefreshUtils.init(refreshLayout, this);

    }

    private void initData() {
        //   refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollInfo("150500000010")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollInfoBean>(this) {
                    @Override
                    protected void onSucceed(PollInfoBean result) {
                        mList = result.getResultEntity().getData();
                        if (mList.size() > 0) {
                            setPollInfo(mList);
                        } else {
                            LogUtil.d(TAG, "no data");
                        }

                        //    refreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        //    refreshLayout.setRefreshing(false);
                    }
                });
    }

    private void setPollInfo(List<PollInfoBean.ResultEntityBean.DataBean> mList) {
        PollInfoBean.ResultEntityBean.DataBean pollBean = mList.get(0);
        pollName.setText(pollBean.getPollSourceName());

    }

    @Override
    public void onRefresh() {
        initData();
    }

    @OnClick(R.id.toLocal)
    public void onClick() {
        StartActivityUtils.startActivityNone(PollInfoActivity.this,ResultCheckActivity.class);
    }
}
