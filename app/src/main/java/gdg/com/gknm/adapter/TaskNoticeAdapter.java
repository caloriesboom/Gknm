package gdg.com.gknm.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.TaskNoticeBean;
import gdg.com.gknm.utils.LogUtil;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class TaskNoticeAdapter extends CommonAdapter<TaskNoticeBean.ResultEntityBean> {
    public TaskNoticeAdapter(Context context, List<TaskNoticeBean.ResultEntityBean> data, int layoutId) {
        super(context, data, layoutId);
        LogUtil.d("TaskNoticeAdapter==初始化");
    }

    @Override
    public void setConverView(int position, CommonViewHolder myViewHolder,TaskNoticeBean.ResultEntityBean dataBean) {
        LogUtil.d("TaskNoticeAdapter=="+String.valueOf((position+1)+".")+dataBean.getTaskReminderText());
        ((TextView)myViewHolder.getView(R.id.tv_task_num)).setText(String.valueOf((position+1)+"."));
        ((TextView)myViewHolder.getView(R.id.tv_task_content)).setText(dataBean.getTaskReminderText());

    }

}
