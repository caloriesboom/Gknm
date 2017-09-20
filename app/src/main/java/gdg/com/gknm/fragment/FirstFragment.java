package gdg.com.gknm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.home.SignActivity;
import gdg.com.gknm.activity.work.TaskInfoActivity;
import gdg.com.gknm.adapter.TaskNoticeAdapter;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.base.baseFragment;
import gdg.com.gknm.bean.TaskNoticeBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.DateUtil;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.weight.CustomListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class FirstFragment extends baseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.lv_task)
    CustomListView lvTask;
    @Bind(R.id.ll_sign)
    LinearLayout llSign;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private String userId = "";
    private String startTime = "";
    private String endTime = "";
    private List<TaskNoticeBean.ResultEntityBean> mList = new ArrayList<>();
    private TaskNoticeAdapter taskAdapter;
    private String TAG = "FirstFragment";

    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        RefreshUtils.init(refreshLayout, this);
        initActionBar();
        userId = MyApplication.mUserId;
        startTime = DateUtil.getSpecifiedDayBefore(DateUtil.getCurrentDay());
        endTime = DateUtil.getSpecifiedDayAfter(DateUtil.getCurrentDay());
        sendRequest();
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StartActivityUtils.startActivityNone(mActivity, TaskInfoActivity.class);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_sign)
    public void onClick() {
        //监控报警
        startNewActivity(SignActivity.class);
    }
    private void startNewActivity(Class<?> cls) {

        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void sendRequest(){
        refreshLayout.setRefreshing(false);
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getNociteTask(userId, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TaskNoticeBean>(mActivity) {
                    @Override
                    protected void onSucceed(TaskNoticeBean result) {
                        LogUtil.d(TAG, result.getResultEntity().size()+"");
                        mList = result.getResultEntity();
                        taskAdapter = new TaskNoticeAdapter(mActivity, mList, R.layout.item_notice_task_list_adapter);
                       lvTask.setAdapter(taskAdapter);
                    }
                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });
    }

    @Override
    public void onRefresh() {

        sendRequest();
    }
}
