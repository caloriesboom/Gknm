package gdg.com.gknm.activity.fragment;

import gdg.com.gknm.R;
import gdg.com.gknm.base.baseFragment;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class FirstFragment extends baseFragment {
    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        initActionBar();
    }
}
