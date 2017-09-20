package gdg.com.gknm.weight;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.bean.TaskListBean;


/**
 * 右上角的Pop
 * Created by ${YeJun} on 2016/5/30.
 */
public class TaskPopWindow extends PopupWindow {
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.tv_alarm_cause)
    TextView tvAlarmCause;
    @Bind(R.id.ll_pop)
    LinearLayout llPop;
    private View conentView;
    private Activity mContext;
    private TaskListBean.ResultEntityBean.DataBean dataBean;

    public TaskPopWindow(Activity context, TaskListBean.ResultEntityBean.DataBean dataBean) {
        this.mContext = context;
        this.dataBean = dataBean;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialo_task, null);
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
        tvStartTime.setText(dataBean.getTaskManagement().getTaskIssuedTime());
        tvEndTime.setText(dataBean.getTaskManagement().getTaskSource());
        tvAlarmCause.setText(dataBean.getTaskManagement().getTaskContents());

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
        }
    }

    @OnClick(R.id.ll_pop)
    public void onClick() {
        dismiss();
    }
}




