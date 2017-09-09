package gdg.com.gknm.activity.work;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.pollutionContrAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.PollutionControlBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PollutionControlActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.list_view)
    CustomListView listView;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<PollutionControlBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private pollutionContrAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution_control);
        ButterKnife.bind(this);
        initActionBar();
        initView();
    }
    protected void initView() {

        initData();
        RefreshUtils.init(refreshLayout, this);

    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }

    @Override
    public void onRefresh() {
        initData();
    }
    private void initData() {
        refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollutionControlList("150500000053")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollutionControlBean>(this) {
                    @Override
                    protected void onSucceed(PollutionControlBean result) {
                        mList = result.getResultEntity().getData();
                        adapter = new pollutionContrAdapter(PollutionControlActivity.this, mList, R.layout.item_pollution_control);
                        listView.setAdapter(adapter);
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d("PollutionControlActivity", msg);
                        refreshLayout.setRefreshing(false);
                    }
                });
    }
}
