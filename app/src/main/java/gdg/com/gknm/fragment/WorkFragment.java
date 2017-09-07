package gdg.com.gknm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.work.HistoryActivity;
import gdg.com.gknm.activity.work.LocaleInspectActivity;
import gdg.com.gknm.activity.work.MonitorAlarmActivity;
import gdg.com.gknm.activity.work.SupervisionActivity;
import gdg.com.gknm.adapter.WorkAdapter;
import gdg.com.gknm.base.baseFragment;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class WorkFragment extends baseFragment {
    @Bind(R.id.gv_work)
    GridView gvWork;
    private WorkAdapter mAdapter;
    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        mAdapter = new WorkAdapter(mActivity);
        gvWork.setAdapter(mAdapter);
        gvWork.setOnItemClickListener(itemClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if (i==0) {
                //监控报警
                startNewActivity(MonitorAlarmActivity.class);

            } else if (i==1) {
                //监督检查
                startNewActivity(SupervisionActivity.class);

            } else if (i==2) {
                //历史问题
                startNewActivity(HistoryActivity.class);
            } else if (i==3) {
                //现场检查
                startNewActivity(LocaleInspectActivity.class);
            }
        }
    };

    private void startNewActivity(Class<?> cls) {

        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
