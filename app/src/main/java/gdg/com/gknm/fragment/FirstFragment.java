package gdg.com.gknm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.home.SignActivity;
import gdg.com.gknm.base.baseFragment;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class FirstFragment extends baseFragment {
    @Bind(R.id.lv_task)
    ListView lvTask;
    @Bind(R.id.ll_sign)
    LinearLayout llSign;

    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        initActionBar();
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
}
