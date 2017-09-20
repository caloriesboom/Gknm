package gdg.com.gknm.weight;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;


/**
 * 右上角的Pop
 * Created by ${YeJun} on 2016/5/30.
 */
public class SignAlarmSearchPopWindow extends PopupWindow {

    @Bind(R.id.sp_poll_name)
    Spinner spPollName;
    @Bind(R.id.et_start_time)
    CustomEditText etStartTime;
    @Bind(R.id.et_end_time)
    CustomEditText etEndTime;
    @Bind(R.id.btn_ok)
    public Button btnOk;
    @Bind(R.id.btn_cancel)
    public Button btnCancel;
    private View conentView;
    private Activity mContext;


    public SignAlarmSearchPopWindow(Activity context) {
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialo_sign_alarm_search, null);
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

        initData();
    }

    private void initData() {

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
        }
    }


    @OnClick({R.id.et_start_time, R.id.et_end_time, R.id.btn_ok, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_start_time:
                break;
            case R.id.et_end_time:
                break;
            case R.id.btn_ok:
                break;
            case R.id.btn_cancel:
                break;
        }
    }
    public String getPollId(){
        return null;
    }

    public String getStartTime(){
        return null;
    }
    public String getEndTime(){
        return null;
    }
}




