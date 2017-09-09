package gdg.com.gknm.activity.work;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
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
import gdg.com.gknm.bean.IndustryTypeBean;
import gdg.com.gknm.bean.PollInfoBean;
import gdg.com.gknm.bean.PollTypeBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.utils.UIUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomEditText;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PollInfoActivity extends BaseActivity {

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
    @Bind(R.id.toLocal)
    Button toLocal;
    private Subscription mSubscription;
    private List<PollInfoBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private String TAG = "PollInfoActivity";
    private ProgressDialog mProgressDialog;
    private ArrayAdapter<String> pollTypeAdapter;
    private ArrayAdapter<String> industryTypeAdapter;
    private List<String> pollTypeNameList = new ArrayList<>();
    private List<String> industryTypeNameList = new ArrayList<>();
    private List<PollTypeBean.ResultEntityBean.DataBean> pollTypeList = new ArrayList<>();
    private List<IndustryTypeBean.ResultEntityBean.DataBean> industryTypeList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_info);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        LogUtil.d(TAG, "enterpriseID = " + getIntent().getStringExtra("tag1"));
    }

    private void initActionBar() {
        customActionBar.setLeftImageClickListener(this);
    }

    protected void initView() {
        initEditText();
        mProgressDialog = UIUtil.initDialog2(this);
        String pollId = getIntent().getStringExtra("tag1");
        if (!TextUtils.isEmpty(pollId)) {
            LogUtil.e(TAG, "企业ID = " + pollId);
            initData(pollId);
        } else {
            LogUtil.e(TAG, "企业ID为空！！！！");
        }
    }

    private void initEditText() {
        pollName.setClearIconVisible(false);
    }

    private void initData(String pollId) {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollInfo(pollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollInfoBean>(this) {
                    @Override
                    protected void onSucceed(PollInfoBean result) {
                        mList = result.getResultEntity().getData();
                        if (mList.size() > 0) {
                            setPollInfo(mList);
                        } else {
                            setPollInfo(mList);
                            LogUtil.d(TAG, "no data");
                        }
                        UIUtil.cancleDialog(mProgressDialog);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollTpye()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollTypeBean>(this) {
                    @Override
                    protected void onSucceed(PollTypeBean result) {
                        pollTypeList = result.getResultEntity().getData();
                        for (int i = 0; i < pollTypeList.size(); i++) {
                            pollTypeNameList.add(pollTypeList.get(i).getDicName());
                        }
                        initPollSpinnerData(pollTypeNameList);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getIndustryType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<IndustryTypeBean>(this) {
                    @Override
                    protected void onSucceed(IndustryTypeBean result) {
                        industryTypeList = result.getResultEntity().getData();
                        for (int i = 0; i < industryTypeList.size(); i++) {
                            industryTypeNameList.add(industryTypeList.get(i).getDicName());
                        }
                        initIndusSpinnerData(industryTypeNameList);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });
    }

    private void setPollInfo(List<PollInfoBean.ResultEntityBean.DataBean> mList) {
        PollInfoBean.ResultEntityBean.DataBean pollBean = mList.get(0);
        pollName.setText(pollBean.getPollSourceName());
        corporateName.setText(pollBean.getCorpName());
        environmentPerson.setText(pollBean.getEnvironLinkMan());
        telNumber.setText(pollBean.getTelephone());
        address.setText(pollBean.getPollSourceAddress());
        pollInfo.setText(pollBean.getProdScale());

    }


    private void initPollSpinnerData(final List<String> pollType) {
        if (!pollType.isEmpty()) {
            pollType.add(0, "请选择");
            pollTypeAdapter = new ArrayAdapter<String>(PollInfoActivity.this, R.layout.spinner_poll_type, pollType);
            pollTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPollCategory.setAdapter(pollTypeAdapter);
        }

    }

    private void initIndusSpinnerData(final List<String> industryType) {
        if (!industryType.isEmpty()) {
            industryType.add(0, "请选择");
            industryTypeAdapter = new ArrayAdapter<String>(PollInfoActivity.this, R.layout.spinner_poll_type, industryType);
            industryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spIndustryCategory.setAdapter(industryTypeAdapter);
        }

    }


    @OnClick({R.id.pollution_control, R.id.toLocal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pollution_control:
                StartActivityUtils.startActivityNone(PollInfoActivity.this, PollutionControlActivity.class);
                break;
            case R.id.toLocal:
                StartActivityUtils.startActivityNone(PollInfoActivity.this, ResultCheckActivity.class);
                break;
        }
    }
}
