package gdg.com.gknm.activity.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.MessageAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.MonitorAlarmBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.DateUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.weight.CustomActionBar;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyMessageActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.list_view)
    SHListView listView;
    @Bind(R.id.refresh_layout)
    SHSwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        ButterKnife.bind(this);
        initActionBar();
        initSwipeRefreshLayout();
        initData();
    }

    private void initData() {
        sendQuest(true);
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }
    private int totalRecord=0;
    private int startRecord=0;
    private int pageSize=20;
    private List<MonitorAlarmBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private MessageAdapter taskAdapter;
    private void sendQuest(final boolean isRefresh){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTime = df.format(new Date());
        String day = currentTime.split(" ")[0];
        String time = currentTime.split(" ")[1];
        String beginDate = DateUtil.getCurrentDay()+"%00:00:00";
        String endDate = day+"%"+time;
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getMonitorAlarmList("", beginDate, endDate,"3","",20,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MonitorAlarmBean>(this) {
                    @Override
                    protected void onSucceed(MonitorAlarmBean result) {
                        totalRecord = result.getResultEntity().getTotalRecord();
                        if(isRefresh){
                            mList = result.getResultEntity().getData();

                        }else{
                            mList.addAll(result.getResultEntity().getData());
                        }

                        taskAdapter = new MessageAdapter(MyMessageActivity.this, mList, R.layout.item_my_message_layout);
                        listView.setAdapter(taskAdapter);
                        if (isRefresh) {
                            swipeRefreshLayout.finishRefresh();
                        } else {
                            swipeRefreshLayout.finishLoadmore();
                            Toast.makeText(MyMessageActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                    }
                });
    }

    private void initSwipeRefreshLayout() {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.refresh_view, null);
        final TextView textView = (TextView) view.findViewById(R.id.title);
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startRecord = 0;
                        sendQuest(true);

                    }
                }, 1000);
            }

            @Override
            public void onLoading() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startRecord = startRecord + pageSize;
                        if (totalRecord <= startRecord) {
                            swipeRefreshLayout.finishLoadmore();
                            ToastUtils.showShortToast(MyMessageActivity.this, "没有更多了");
                        } else {
                            sendQuest(false);
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
                        swipeRefreshLayout.setRefreshViewText("下拉刷新");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText("松开刷新");
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setRefreshViewText("正在刷新");
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
