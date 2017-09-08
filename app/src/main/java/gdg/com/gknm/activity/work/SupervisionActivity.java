package gdg.com.gknm.activity.work;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.TaskAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.TaskListBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomListView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SupervisionActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.list_view)
    CustomListView listView;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.test)
    TextView test;
    private Subscription mSubscription;
    private String TAG = "SupervisionActivity";
    private TaskAdapter taskAdapter;
    private List<TaskListBean.ResultEntityBean.DataBean> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision);
        ButterKnife.bind(this);
        initActionBar();
        initView();
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
        mActionBar.getRightImageView().setVisibility(View.VISIBLE);
    }

    protected void initView() {

        initTaskData();
        RefreshUtils.init(refreshLayout, this);
        listView.setOnItemClickListener(onItemClickListener);

    }

    @Override
    public void onRefresh() {
        initTaskData();
    }

    private void initTaskData() {
        refreshLayout.setRefreshing(true);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getTaskList("10044", "0", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TaskListBean>(this) {
                    @Override
                    protected void onSucceed(TaskListBean result) {
                        mList = result.getResultEntity().getData();
                        taskAdapter = new TaskAdapter(SupervisionActivity.this, mList, R.layout.item_task_list);
                        listView.setAdapter(taskAdapter);
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

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // TODO: 2017-9-8 弹出任务详情dialog
            //StartActivityUtils.startActivityNone(SupervisionActivity.this, PollInfoActivity.class);
        }
    };

    @OnClick(R.id.test)
    public void onViewClicked() {
        StartActivityUtils.startActivityNone(SupervisionActivity.this, PollInfoActivity.class);
    }
}
