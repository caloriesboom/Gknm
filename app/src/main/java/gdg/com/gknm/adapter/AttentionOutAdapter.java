package gdg.com.gknm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.AttentionOutFlowBean;
import gdg.com.gknm.dao.OutFlowsManagerDao;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class AttentionOutAdapter extends CommonAdapter<AttentionOutFlowBean.ResultEntityBean> {
    private OutFlowsManagerDao mOutFlowsManagerDao;
    public AttentionOutAdapter(Context context, List<AttentionOutFlowBean.ResultEntityBean> data, int layoutId) {
        super(context, data, layoutId);
        mOutFlowsManagerDao = OutFlowsManagerDao.getInstance(context);
    }

    @Override
    public void setConverView(int position, final CommonViewHolder myViewHolder, final AttentionOutFlowBean.ResultEntityBean dataBean) {
        ((TextView) myViewHolder.getView(R.id.enterprise_name)).setText(dataBean.getPollOutName());
        if (dataBean.isChecked()) {
            ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.added_city));
        } else {
            ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.add_city));
        }
      ((ImageView) myViewHolder.getView(R.id.is_checked)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashSet<String> flowIds = mOutFlowsManagerDao.queryoutFlowIds();
                if (flowIds.contains(dataBean.getPollOutId())) {
                    ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.add_city));
                    mOutFlowsManagerDao.deleteoutFlow(dataBean);
                } else {
                    ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.added_city));
                    mOutFlowsManagerDao.insertOutFlow(dataBean);
                }
            }
        });


    }

}
