package gdg.com.gknm.activity.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.SignAlarmAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.bean.SignAlarmBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.SearchSign;
import gdg.com.gknm.weight.SignAlarmPopWindow;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.list_view)
    SHListView listView;
    @Bind(R.id.refresh_layout)
    SHSwipeRefreshLayout swipeRefreshLayout;
    private SignAlarmAdapter signAdapter;
    private List<SignAlarmBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private String TAG = "SignActivity";
    private String userId = "";
    private String pollSourceId = "";
    private String beginDate = "2017-07-01";
    private String endDate = "2017-09-01";
    private int startRecord = 0;
    private int pageSize = 20;
    private int totalRecord = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        intView();
        initActionBar();
        initSwipeRefreshLayout();
        initData();
    }

    private void intView() {

 listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
     @Override
     public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
         SignAlarmBean.ResultEntityBean.DataBean alarmBean = mList.get(i);
         SignAlarmPopWindow PopWindow = new SignAlarmPopWindow(SignActivity.this, alarmBean);
         PopWindow.showPopupWindow(mActionBar);
         PopWindow.setOutsideTouchable(true);
         PopWindow.setFocusable(true);
         return false;
     }
 });
    }

    public void initData() {
        userId = MyApplication.mUserId;
        if (!TextUtils.isEmpty(userId)) {
            sendRequest(true);
        } else {
            ToastUtils.showShortToast(this, "用户为空！");
        }
    }

    private void sendRequest(final boolean isRefresh) {
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getSignList(userId, pollSourceId, startRecord, pageSize, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SignAlarmBean>(this) {
                    @Override
                    protected void onSucceed(SignAlarmBean result) {
                        totalRecord = result.getResultEntity().getTotalRecord();
                        if(isRefresh){
                            mList = result.getResultEntity().getData();
                        }else{
                            mList.addAll(result.getResultEntity().getData());
                        }

                        signAdapter = new SignAlarmAdapter(SignActivity.this,SignActivity.this, mList, R.layout.item_sign_alarm_list);
                        listView.setAdapter(signAdapter);
                        if (isRefresh) {
                            swipeRefreshLayout.finishRefresh();
                        } else {
                            swipeRefreshLayout.finishLoadmore();
                            Toast.makeText(SignActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        swipeRefreshLayout.finishRefresh();
                    }
                });
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
        mActionBar.getRightImageView().setVisibility(View.VISIBLE);
        mActionBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SearchSign dialog = new SearchSign(SignActivity.this);
                dialog.show();
                dialog.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pollSourceId = dialog.getPollId();
                        beginDate = dialog.getStartTime();
                        endDate = dialog.getStopTime();
                        startRecord = 0;
                        if(beginDate.compareTo(endDate) <= 0){
                            dialog.dismiss();
                            sendRequest(true);
                        }else{
                            Toast.makeText(SignActivity.this, "开始时间不能大于结束时间!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

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
                        if (!TextUtils.isEmpty(userId)) {
                            startRecord = 0;
                            sendRequest(true);
                        } else {
                            return;
                        }

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
                            ToastUtils.showShortToast(SignActivity.this, "没有更多了");
                        } else {
                            sendRequest(false);
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
