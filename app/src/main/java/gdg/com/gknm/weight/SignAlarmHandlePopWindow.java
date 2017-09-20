package gdg.com.gknm.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.work.TaskInfoActivity;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.bean.SubmitBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.ContextUtil;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.utils.ToastUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 右上角的Pop
 * Created by ${YeJun} on 2016/5/30.
 */
public class SignAlarmHandlePopWindow extends PopupWindow {

    @Bind(R.id.btn_check)
    Button btnSave;
    @Bind(R.id.btn_sign)
    Button btnClean;
    @Bind(R.id.ll_pop)
    LinearLayout llPop;
    private View conentView;
    private Context mContext;
    protected Subscription mSubscription;
    private String flagAlarmId;
    public SignAlarmHandlePopWindow(Context context,String flagAlarmId) {
        this.flagAlarmId = flagAlarmId;
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialo_sign_alarm_handle, null);
        ButterKnife.bind(this, conentView);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setClippingEnabled(false);//可以填充状态栏
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x4b000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.take_photo_anim);

    }


    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
        }
    }

    @OnClick({R.id.btn_check, R.id.btn_sign, R.id.ll_pop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                StartActivityUtils.startActivityNone(mContext, TaskInfoActivity.class);
                dismiss();
                break;
            case R.id.btn_sign:
                cancelAlarm();
                break;
            case R.id.ll_pop:
                dismiss();
                break;
        }
    }

    private void cancelAlarm() {
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).cancelSign(flagAlarmId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SubmitBean>(ContextUtil.getContext()) {
                    @Override
                    protected void onSucceed(SubmitBean result) {
                        if(result.isResultCode()){
                            ToastUtils.showShortToast(ContextUtil.getContext(),"取消成功");
                        }else{
                            ToastUtils.showShortToast(ContextUtil.getContext(),result.getMessage());
                        }
                        dismiss();
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(msg);
                        dismiss();
                    }
                });
    }

}





