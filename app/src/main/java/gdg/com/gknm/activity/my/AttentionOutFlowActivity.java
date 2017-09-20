package gdg.com.gknm.activity.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.AttentionOutAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.bean.AttentionOutFlowBean;
import gdg.com.gknm.bean.SubmitBean;
import gdg.com.gknm.dao.OutFlowsManagerDao;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttentionOutFlowActivity extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.out_list)
    CustomListView outList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private String userId = "";
    private String pollId = "";
    private OutFlowsManagerDao mOutManagerDao;
    private AttentionOutAdapter adapter;
    private List<AttentionOutFlowBean.ResultEntityBean> mList = new ArrayList<>();
    private String TAG = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_out_flow);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        initData();
    }


    private void initData() {
        userId = MyApplication.mUserId;
        pollId = getIntent().getStringExtra("pollId");
        mOutManagerDao = OutFlowsManagerDao.getInstance(this);
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
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getAttentionOut(userId,pollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AttentionOutFlowBean>(this) {
                    @Override
                    protected void onSucceed(AttentionOutFlowBean result) {
                        mList = result.getResultEntity();
                        adapter = new AttentionOutAdapter(AttentionOutFlowActivity.this, mList, R.layout.item_pollution_enterprise_layout);
                        outList.setAdapter(adapter);
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
        String outIds = mOutManagerDao.queryStroutFlowIds();
        refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).submitAttentionOut(userId,pollId,outIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SubmitBean>(this) {
                    @Override
                    protected void onSucceed(SubmitBean result) {
                        if(result.isResultCode()){
                            LogUtil.d(TAG,"关注的排口修改成功");
                        }else{
                            LogUtil.d(TAG,"关注的排口修改失败");
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
