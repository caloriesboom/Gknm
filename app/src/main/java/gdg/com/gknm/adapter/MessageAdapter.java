package gdg.com.gknm.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.MonitorAlarmBean;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class MessageAdapter extends CommonAdapter<MonitorAlarmBean.ResultEntityBean.DataBean> {
    public MessageAdapter(Context context, List<MonitorAlarmBean.ResultEntityBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void setConverView(int position, CommonViewHolder myViewHolder, final MonitorAlarmBean.ResultEntityBean.DataBean dataBean) {
        if (position % 2 == 1) {
            myViewHolder.getView(R.id.item_my_message_layout).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_gray));
        } else {
            myViewHolder.getView(R.id.item_my_message_layout).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_white));
        }
        ((TextView) myViewHolder.getView(R.id.my_message_title)).setText(dataBean.getPollSourceName());
        ((TextView) myViewHolder.getView(R.id.my_message_content)).setText(dataBean.getAlarmTypeName());

    }

}
