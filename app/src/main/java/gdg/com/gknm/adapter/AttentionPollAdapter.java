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
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.dao.EnterpriseManagerDao;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class AttentionPollAdapter extends CommonAdapter<AttentionPollBean.ResultEntityBean> {
    private EnterpriseManagerDao mEnterpriseManagerDao;
    public AttentionPollAdapter(Context context, List<AttentionPollBean.ResultEntityBean> data, int layoutId) {
        super(context, data, layoutId);
        mEnterpriseManagerDao = EnterpriseManagerDao.getInstance(context);
    }

    @Override
    public void setConverView(int position, final CommonViewHolder myViewHolder, final AttentionPollBean.ResultEntityBean dataBean) {
        ((TextView) myViewHolder.getView(R.id.enterprise_name)).setText(dataBean.getPollSourceName());
        if (dataBean.isChecked()) {
            ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.added_city));
        } else {
            ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.add_city));
        }
      ((ImageView) myViewHolder.getView(R.id.is_checked)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashSet<String> enterpriseIds = mEnterpriseManagerDao.queryEnterpriseIds();
                if (enterpriseIds.contains(dataBean.getPollSourceId())) {
                    ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.add_city));
                    mEnterpriseManagerDao.deleteEnterprise(dataBean);
                } else {
                    ((ImageView) myViewHolder.getView(R.id.is_checked)).setImageDrawable(context.getResources().getDrawable(R.mipmap.added_city));
                    mEnterpriseManagerDao.insertEnterprise(dataBean);
                }
            }
        });


    }

}
