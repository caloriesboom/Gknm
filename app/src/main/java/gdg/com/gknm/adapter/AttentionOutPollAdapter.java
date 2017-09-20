package gdg.com.gknm.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.dao.EnterpriseManagerDao;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class AttentionOutPollAdapter extends CommonAdapter<AttentionPollBean.ResultEntityBean> {
    private EnterpriseManagerDao mEnterpriseManagerDao;
    public AttentionOutPollAdapter(Context context, List<AttentionPollBean.ResultEntityBean> data, int layoutId) {
        super(context, data, layoutId);
        mEnterpriseManagerDao = EnterpriseManagerDao.getInstance(context);
    }

    @Override
    public void setConverView(int position, final CommonViewHolder myViewHolder, final AttentionPollBean.ResultEntityBean dataBean) {
        ((TextView) myViewHolder.getView(R.id.enterprise_name)).setText(dataBean.getPollSourceName());
    }

}
