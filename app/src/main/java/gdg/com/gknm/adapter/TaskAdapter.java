package gdg.com.gknm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.activity.work.PollInfoActivity;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.TaskListBean;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.utils.TaskUtil;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class TaskAdapter extends CommonAdapter<TaskListBean.ResultEntityBean.DataBean> {
    public TaskAdapter(Context context, List<TaskListBean.ResultEntityBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void setConverView(int position, CommonViewHolder myViewHolder, final TaskListBean.ResultEntityBean.DataBean dataBean) {
        if (position % 2 == 1) {
            myViewHolder.getView(R.id.tableLayout1).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_gray));
        } else {
            myViewHolder.getView(R.id.tableLayout1).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_white));
        }
        ((TextView) myViewHolder.getView(R.id.task_name)).setText(dataBean.getTaskManagement().getTaskName());
        ((TextView) myViewHolder.getView(R.id.et_check_time)).setText(dataBean.getTaskManagement().getEnterpriseNameValue());
        ((TextView) myViewHolder.getView(R.id.last_time)).setText(dataBean.getTaskManagement().getTaskEndTime());
        String taskId = String.valueOf(dataBean.getTaskProgressId());
        if(TaskUtil.isTaskSaved(taskId,context)){
            ((TextView) myViewHolder.getView(R.id.handle)).setText("查看");
        }else{
            ((TextView) myViewHolder.getView(R.id.handle)).setText("处理");
        }
        ((TextView) myViewHolder.getView(R.id.handle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TaskUtil.isTaskSaved( String.valueOf(dataBean.getTaskProgressId()),context)){
                    ConstantIndex.SP_IS_SAVE = true;
                    ConstantIndex.SP_IS_SIGN = TaskUtil.getSaveBeanByTaskId(String.valueOf(dataBean.getTaskProgressId()),context).isSign();
                    LogUtil.d("当前任务签字状态=="+TaskUtil.getSaveBeanByTaskId(String.valueOf(dataBean.getTaskProgressId()),context).isSign());
                }
                StartActivityUtils.startActivityFour(context
                        , PollInfoActivity.class,
                        dataBean.getTaskManagement().getEnterpriseName(),
                        dataBean.getWaterOrGas(),
                        String.valueOf(dataBean.getTaskProgressId()),
                        dataBean.getTaskManagement().getTaskAssignment());
            }
        });


    }

}
