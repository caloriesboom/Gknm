package gdg.com.gknm.activity.work;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.fragment.GkFragment;
import gdg.com.gknm.fragment.OnlineFragment;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.IndicatorFragmentActivity;
import gdg.com.gknm.weight.TitleIndicator;
import gdg.com.gknm.weight.ViewPagerCompat;

public class OnlineOrWorkActivity extends IndicatorFragmentActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.prev1)
    Button prev1;
    @Bind(R.id.prev4)
    Button prev4;
    @Bind(R.id.pagerindicator)
    TitleIndicator pagerindicator;
    @Bind(R.id.pager)
    ViewPagerCompat pager;
    public static final int FRAGMENT_ONLINE = 0;
    public static final int FRAGMENT_GK = 3;
    private String TAG = "OnlineOrWorkActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initActionBar();
        initView();

    }

    private void initView() {
            prev1.setBackgroundResource(R.drawable.switch_button_left);
            prev1.setTextColor(getResources().getColor(R.color.colorPrimaryStyles));
            prev4.setBackgroundResource(R.drawable.switch_button_right_checked);
            prev4.setTextColor(getResources().getColor(R.color.white));
    }

    private void initData() {

    }

    @Override
    protected int getMainViewResId() {
        return R.layout.activity_online_gk;
    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {

            tabs.add(new TabInfo(FRAGMENT_ONLINE, "在线报警", OnlineFragment.class));
            tabs.add(new TabInfo(FRAGMENT_GK, "工况报警", GkFragment.class));
            return FRAGMENT_ONLINE;

    }
    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 0) {
            prev1.setBackgroundResource(R.drawable.switch_button_left_checked);
            prev1.setTextColor(getResources().getColor(R.color.white));
            prev4.setBackgroundResource(R.drawable.switch_button_right);
            prev4.setTextColor(getResources().getColor(R.color.colorPrimaryStyles));
        } else if (position == 1) {
            prev1.setBackgroundResource(R.drawable.switch_button_left);
            prev1.setTextColor(getResources().getColor(R.color.colorPrimaryStyles));
            prev4.setBackgroundResource(R.drawable.switch_button_right_checked);
            prev4.setTextColor(getResources().getColor(R.color.white));
            LogUtil.d(TAG,"selectPosition=="+position);
         //   GasFragment gasfragment = (GasFragment) getFragmentById(FRAGMENT_GAS).createFragment(0);
        }
    }

    @OnClick({R.id.prev1, R.id.prev4})
    public void onSelected(View view) {
        switch (view.getId()) {
            case R.id.prev1:
                    onPageSelected(0);
                break;
            case R.id.prev4:
                    onPageSelected(1);
                break;
        }

    }

    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            listener.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }


    public interface MyOnTouchListener {
        public boolean dispatchTouchEvent(MotionEvent ev);
    }
}
