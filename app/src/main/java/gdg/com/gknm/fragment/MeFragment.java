package gdg.com.gknm.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.my.LoginActivity;
import gdg.com.gknm.activity.my.MyAttentionActivity;
import gdg.com.gknm.activity.my.MyMessageActivity;
import gdg.com.gknm.activity.my.MyPushSettingsActivity;
import gdg.com.gknm.activity.my.MyServiceCentreActivity;
import gdg.com.gknm.activity.my.MySystemSettingsActivity;
import gdg.com.gknm.activity.my.UserInfoActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.base.baseFragment;
import gdg.com.gknm.bean.UserBean;
import gdg.com.gknm.utils.ContextUtil;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.weight.CircleImageView;
import gdg.com.gknm.weight.CustomItemLayout;

import static gdg.com.gknm.R.mipmap.me_unlogin;
import static gdg.com.gknm.constant.ConstantIndex.USERINFO_OBJECT;

/**
 * Created by GUO.DG on 2017-9-5.
 * 首页fragment
 */

public class MeFragment extends baseFragment {
    @Bind(R.id.me_head_icon)
    CircleImageView meHeadIcon;
    @Bind(R.id.me_nickname)
    TextView meNickname;
    @Bind(R.id.my_message)
    CustomItemLayout myMessage;
    @Bind(R.id.my_attention)
    CustomItemLayout myAttention;
    @Bind(R.id.my_push_settings)
    CustomItemLayout myPushSettings;
    @Bind(R.id.my_service_centre)
    CustomItemLayout myServiceCentre;
    @Bind(R.id.my_system_settings)
    CustomItemLayout mySystemSettings;

    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {

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

    @OnClick({R.id.me_head_icon, R.id.my_message, R.id.my_attention, R.id.my_push_settings, R.id.my_service_centre, R.id.my_system_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_head_icon:
                gotoUserInfo();
                break;
            case R.id.my_message:
                gotoMessage();
                break;
            case R.id.my_attention:
                gotoAttention();
                break;
            case R.id.my_push_settings:
                gotoPushSetting();
                break;
            case R.id.my_service_centre:
                gotoService();
                break;
            case R.id.my_system_settings:
                gotoSetting();
                break;
        }
    }

    private void gotoService() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, MyServiceCentreActivity.class);
        } else {
            Toast.makeText(mActivity, "您暂未获得该权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    private void gotoSetting() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, MySystemSettingsActivity.class);
        } else {
            Toast.makeText(mActivity, "您暂未获得该权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    private void gotoPushSetting() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, MyPushSettingsActivity.class);
        } else {
            Toast.makeText(mActivity, "您暂未获得该权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    private void gotoAttention() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, MyAttentionActivity.class);
        } else {
            Toast.makeText(mActivity, "您暂未获得该权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    private void gotoMessage() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, MyMessageActivity.class);
        } else {
            Toast.makeText(mActivity, "您暂未获得该权限", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    private void gotoUserInfo() {
        Intent intent = new Intent();
        if (MyApplication.mIsLogin) {
            intent.setClass(mActivity, UserInfoActivity.class);
        } else {
            intent.setClass(mActivity, LoginActivity.class);//登陆
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(MyApplication.mIsLogin){


            if(!TextUtils.isEmpty( SharedPreferenceUtil.get("iconPath",""))){
                Bitmap bitmapFactory = BitmapFactory.decodeFile( SharedPreferenceUtil.get("iconPath",""));
                meHeadIcon.setImageBitmap(bitmapFactory);
            }
        }else{
            meHeadIcon.setImageDrawable(getResources().getDrawable(me_unlogin));
        }
        UserBean user = ((UserBean) SharedPreferenceUtil.getObject(USERINFO_OBJECT, ContextUtil.getContext()));
        if (user!=null){
            meNickname.setText(MyApplication.mUser.getResultEntity().getUserName());
        }else {
            meNickname.setText("请登录");
        }
    }
}
