package gdg.com.gknm.activity.fragment;

import android.view.View;
import android.widget.GridView;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.WorkAdapter;
import gdg.com.gknm.base.baseFragment;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class WorkFragment extends baseFragment {
    private View mRootView;
    private GridView gv_attnention;//登录的布局gridview
    private WorkAdapter mAdapter;
    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {

    }
}
