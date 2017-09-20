package gdg.com.gknm.activity.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.AttentionPollAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.bean.SubmitBean;
import gdg.com.gknm.dao.EnterpriseManagerDao;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttentionPollutionEnterpriseActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.source_city_name)
    TextView sourceCityName;
    @Bind(R.id.enterprice_list)
    CustomListView mListView;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<AttentionPollBean.ResultEntityBean> mList = new ArrayList<>();
    private AttentionPollAdapter adapter;
    private String TAG = "AttentionPollution";
    private String userId="";
    private EnterpriseManagerDao mEnterpriseManagerDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_pollution_enterprise);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        initData();
    }

    private void initData() {
        userId = MyApplication.mUserId;
        mEnterpriseManagerDao = EnterpriseManagerDao.getInstance(this);
        sendRequest();
    }

    private void initView() {
        RefreshUtils.init(refreshLayout, this);

    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }

    private void sendRequest(){
        refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getAttentionPoll(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AttentionPollBean>(this) {
                    @Override
                    protected void onSucceed(AttentionPollBean result) {
                        mList = result.getResultEntity();
                        adapter = new AttentionPollAdapter(AttentionPollutionEnterpriseActivity.this, mList, R.layout.item_pollution_enterprise_layout);
                        mListView.setAdapter(adapter);
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        refreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onRefresh() {
        sendRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        submitPollInfo();
    }

    private void submitPollInfo() {
        String enterpriseIds = mEnterpriseManagerDao.queryStrEnterpriseIds();
        refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).submitAttentionPoll(userId,enterpriseIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SubmitBean>(this) {
                    @Override
                    protected void onSucceed(SubmitBean result) {
                      if(result.isResultCode()){
                        LogUtil.d(TAG,"关注的企业修改成功");
                      }else{
                          LogUtil.d(TAG,"关注的企业修改失败");
                      }
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        refreshLayout.setRefreshing(false);
                    }
                });
    }
}
