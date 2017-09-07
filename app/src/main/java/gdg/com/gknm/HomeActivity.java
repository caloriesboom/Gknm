package gdg.com.gknm;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import gdg.com.gknm.fragment.FirstFragment;
import gdg.com.gknm.fragment.MeFragment;
import gdg.com.gknm.fragment.WorkFragment;
import gdg.com.gknm.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    public static final String HOME = "HOME";
    public static final String WORK = "WORK";
    public static final String ME = "ME";
    private LayoutInflater mLayoutInflater;
    private FragmentManager mFragmentManager;
    private MeFragment mMeFragment;
    private FirstFragment mFirstFragment;
    private WorkFragment mWorkFragment;
    private TabHost mTabHost;
    private View mRootView;

    private ArrayList<View> mTabViewList = new ArrayList<View>();
    //默认第一个
    private int mLastTabIndex = 0;
    //当前tab array
    public static final String[] mTabTags = {HOME, WORK, ME};
    private String mCurrentTag;
    //退出提示
    private long mLastPressed;
    private final static int EXIT_TIME = 1400;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutInflater = LayoutInflater.from(this);
        mRootView = mLayoutInflater.inflate(R.layout.activity_home, null);
        setContentView(mRootView);
        mFragmentManager = getSupportFragmentManager();
        mTabHost = (TabHost) mRootView.findViewById(android.R.id.tabhost);
        mTabHost.setup();
        initTabHost(savedInstanceState);
        initFragment();
    }



    private void initFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mFirstFragment = new FirstFragment();
        transaction.replace(R.id.tab_content, mFirstFragment, HOME);
        transaction.commitAllowingStateLoss();

    }

    /**
     * 初始化选项卡
     *
     * @param savedInstanceState
     */
    private void initTabHost(Bundle savedInstanceState) {
        //添加选项卡导航页
        mTabHost.addTab(mTabHost
                .newTabSpec(HOME)
                .setContent(mNullContentFactory)
                .setIndicator(createTabIndicator(
                        R.drawable.navigation_indicator_background_home,
                        R.string.navigation_home, false)));

        mTabHost.addTab(mTabHost
                .newTabSpec(WORK)
                .setContent(mNullContentFactory)
                .setIndicator(createTabIndicator(
                        R.drawable.navigation_indicator_background_work,
                        R.string.navigation_work, false)));

        mTabHost.addTab(mTabHost
                .newTabSpec(ME)
                .setContent(mNullContentFactory)
                .setIndicator(createTabIndicator(
                        R.drawable.navigation_indicator_background_me,
                        R.string.navigation_me, false)));

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                final FragmentTransaction transaction = mFragmentManager.beginTransaction();

                if (mFirstFragment != null) {
                    transaction.hide(mFirstFragment);
                }
                if (mWorkFragment != null) {
                    transaction.hide(mWorkFragment);
                }
                if (mMeFragment != null) {
                    transaction.hide(mMeFragment);
                }

                if (tabId.equals(HOME)) {
                    if (mFirstFragment == null) {
                        mFirstFragment = new FirstFragment();
                        transaction.add(R.id.tab_content, mFirstFragment, HOME);
                    } else {
                        transaction.show(mFirstFragment);
                    }
                    mCurrentTag = HOME;
                } else if (tabId.equals(WORK)) {
                    if (mWorkFragment == null) {
                        mWorkFragment = new WorkFragment();
                        transaction.add(R.id.tab_content, mWorkFragment, WORK);
                    } else {
                        transaction.show(mWorkFragment);
                    }
                } else if (tabId.equals(ME)) {
                    if (mMeFragment == null) {
                        mMeFragment = new MeFragment();
                        transaction.add(R.id.tab_content, mMeFragment, ME);

                    } else {
                        transaction.show(mMeFragment);
                    }
                }
                transaction.commit();
            }
        });

        mTabHost.setCurrentTab(mLastTabIndex);
        mCurrentTag = mTabTags[mLastTabIndex];
    }

    private TabHost.TabContentFactory mNullContentFactory =
            new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new TextView(HomeActivity.this);
                }
            };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 设置选项卡相关属性
     *
     * @param imageResId
     * @param stringResId
     * @return
     */
    private LinearLayout createTabIndicator(int imageResId, int stringResId, boolean flag) {
        LinearLayout indicator = (LinearLayout) mLayoutInflater.inflate(
                R.layout.tab_navigation_indicator, null);
        //选项卡的布局
        FrameLayout frame = (FrameLayout) indicator.getChildAt(0);

        ((ImageView) frame.getChildAt(0)).setImageDrawable(getResources()
                .getDrawable(imageResId));

        if (flag) {
            frame.getChildAt(1).setVisibility(View.VISIBLE);
        }

        ((TextView) indicator.getChildAt(1)).setTextColor(getResources()
                .getColorStateList(R.color.select_blue_default_white));
        ((TextView) indicator.getChildAt(1)).setText(stringResId);

        mTabViewList.add(indicator);
        return indicator;
    }

    @Override
    public void onBackPressed() {
        Long now = System.currentTimeMillis();
        if (Math.abs(now - mLastPressed) <= EXIT_TIME) {
            super.onBackPressed();
           // RxBus.getInstance().post(new ExitLoginEvent());
        } else {
            mLastPressed = now;
         //   Toast.makeText(this, getResources().getString(R.string.exit_info), Toast.LENGTH_SHORT).show();
        }
    }


}
