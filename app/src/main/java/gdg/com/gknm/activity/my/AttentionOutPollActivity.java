package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.adapter.AttentionOutPollAdapter;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.dao.EnterpriseManagerDao;
import gdg.com.gknm.utils.RefreshUtils;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomListView;

public class AttentionOutPollActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.source_city_name)
    TextView sourceCityName;
    @Bind(R.id.enterprice_list)
    CustomListView mListView;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<AttentionPollBean.ResultEntityBean> mList = new ArrayList<>();
    private AttentionOutPollAdapter adapter;
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
        mList = mEnterpriseManagerDao.getAllFactory();
        adapter = new AttentionOutPollAdapter(AttentionOutPollActivity.this, mList, R.layout.item_pollution_out_enterprise_layout);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pollId = mList.get(i).getPollSourceId();
                Intent intent = new Intent();
                intent.putExtra("pollId",pollId);
                intent.setClass(AttentionOutPollActivity.this,AttentionOutFlowActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        RefreshUtils.init(refreshLayout, this);

    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
    }
}
