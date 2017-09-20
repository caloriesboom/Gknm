package gdg.com.gknm.activity.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.QuestionAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.QuestionListBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.weight.CustomActionBar;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceNormalQuestionActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.my_service_centre_question_lv)
    SHListView myServiceCentreQuestionLv;
    @Bind(R.id.refresh_layout)
    SHSwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_normal_question);
        ButterKnife.bind(this);
        initActionBar();
        initSwipeRefreshLayout();
        initData(true);
    }

    private int totalRecord = 0;
    private int startRecord = 0;
    private int pageSize = 20;
    private List<QuestionListBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private QuestionAdapter taskAdapter;

    private void initData(final boolean isRefresh) {
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getQuestionList("", "1", 0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<QuestionListBean>(this) {
                    @Override
                    protected void onSucceed(QuestionListBean result) {
                        totalRecord = result.getResultEntity().getTotalRecord();
                        if (isRefresh) {
                            mList = result.getResultEntity().getData();
                        } else {
                            mList.addAll(result.getResultEntity().getData());
                        }

                        taskAdapter = new QuestionAdapter(ServiceNormalQuestionActivity.this, mList, R.layout.item_normal_question_layout);
                        myServiceCentreQuestionLv.setAdapter(taskAdapter);
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        } else {
                            refreshLayout.finishLoadmore();
                            Toast.makeText(ServiceNormalQuestionActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                    }
                });
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }


    private void initSwipeRefreshLayout() {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.refresh_view, null);
        final TextView textView = (TextView) view.findViewById(R.id.title);
        refreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startRecord = 0;
                        initData(true);

                    }
                }, 1000);
            }

            @Override
            public void onLoading() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startRecord = startRecord + pageSize;
                        if (totalRecord <= startRecord) {
                            refreshLayout.finishLoadmore();
                            ToastUtils.showShortToast(ServiceNormalQuestionActivity.this, "没有更多了");
                        } else {
                            initData(false);
                        }
                    }
                }, 1000);
            }

            /**
             * 监听下拉刷新过程中的状态改变
             * @param percent 当前下拉距离的百分比（0-1）
             * @param state 分三种状态{NOT_OVER_TRIGGER_POINT：还未到触发下拉刷新的距离；OVER_TRIGGER_POINT：已经到触发下拉刷新的距离；START：正在下拉刷新}
             */
            @Override
            public void onRefreshPulStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        refreshLayout.setRefreshViewText("下拉刷新");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        refreshLayout.setRefreshViewText("松开刷新");
                        break;
                    case SHSwipeRefreshLayout.START:
                        refreshLayout.setRefreshViewText("正在刷新");
                        break;
                }
            }

            @Override
            public void onLoadmorePullStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        textView.setText("上拉加载");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        textView.setText("松开加载");
                        break;
                    case SHSwipeRefreshLayout.START:
                        textView.setText("正在加载...");
                        break;
                }
            }
        });
    }
}
