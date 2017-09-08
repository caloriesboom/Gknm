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
import gdg.com.gknm.utils.StartActivityUtils;

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
        ((TextView)myViewHolder.getView(R.id.task_name)).setText(dataBean.getTaskManagement().getTaskName());
        ((TextView)myViewHolder.getView(R.id.poll_name)).setText( dataBean.getTaskManagement().getEnterpriseNameValue());
        ((TextView)myViewHolder.getView(R.id.last_time)).setText(dataBean.getTaskManagement().getTaskEndTime());
        ((TextView)myViewHolder.getView(R.id.handle)).setText("处理");
        ((TextView)myViewHolder.getView(R.id.handle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityUtils.startActivityOne(context,PollInfoActivity.class,dataBean.getTaskManagement().getEnterpriseName());
            }
        });


    }

}
