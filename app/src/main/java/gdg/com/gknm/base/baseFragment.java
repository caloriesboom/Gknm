package gdg.com.gknm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by GUO.DG on 2017-8-23.
 * fragment基类
 */

public abstract class baseFragment extends Fragment {
    protected Activity mActivity;
    protected LayoutInflater mLayoutInflater;
    private View mRootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getFragmentContentId(), null);
            ButterKnife.bind(this, mRootView);
            initActionBar();
            initView();
            initData();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    protected void initActionBar() {

    }

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    //布局中Fragment的ID
    protected abstract void initView();

    protected void initData(){}

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    public void lazyLoad() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
     //   cancelSubscription(mSubscription);
    }

//    public static void cancelSubscription(Subscription subscription) {
//        if (subscription != null && !subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//            LogUtil.d("网络请求取消");
//        }
//    }
}
