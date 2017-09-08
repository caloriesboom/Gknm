package gdg.com.gknm.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.PollutionControlBean;

/**
 * Created by GUO.DG on 2017-9-8.
 * 治污工艺adapter
 */

public class pollutionContrAdapter extends CommonAdapter<PollutionControlBean.ResultEntityBean.DataBean> {

    public pollutionContrAdapter(Context context, List<PollutionControlBean.ResultEntityBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void setConverView(int position, CommonViewHolder myViewHolder, final PollutionControlBean.ResultEntityBean.DataBean dataBean) {
        if (position % 2 == 1) {
           myViewHolder.getView(R.id.tableLayout1).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_gray));
        } else {
           myViewHolder.getView(R.id.tableLayout1).setBackgroundColor(context.getResources().getColor(R.color.me_message_background_white));
        }


        ((TextView)myViewHolder.getView(R.id.facility_name)).setText(dataBean.getFacilityName());
       // ((TextView)myViewHolder.getView(R.id.creat_time)).setText( dataBean.get);
      //  ((TextView)myViewHolder.getView(R.id.opr_time)).setText(dataBean.getTaskManagement().getTaskEndTime());
      //  ((TextView)myViewHolder.getView(R.id.handle)).setText("处理");
        ((TextView)myViewHolder.getView(R.id.design)).setText(dataBean.getDealAbility());
       // ((TextView)myViewHolder.getView(R.id.run)).setText(dataBean);



    }
}
