package gdg.com.gknm.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import gdg.com.gknm.R;
import gdg.com.gknm.adapter.base.CommonAdapter;
import gdg.com.gknm.adapter.base.CommonViewHolder;
import gdg.com.gknm.bean.QuestionListBean;
import gdg.com.gknm.utils.LogUtil;

/**
 * Created by ${Tom} on 2017/2/10.
 * 任务列表
 */
public class QuestionAdapter extends CommonAdapter<QuestionListBean.ResultEntityBean.DataBean> {
    public QuestionAdapter(Context context, List<QuestionListBean.ResultEntityBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
        LogUtil.d("TaskNoticeAdapter==初始化");
    }

    @Override
    public void setConverView(int position, CommonViewHolder myViewHolder,QuestionListBean.ResultEntityBean.DataBean dataBean) {
        ((TextView)myViewHolder.getView(R.id.nomal_question_problem)).setText(dataBean.getQuestionText());
        ((TextView)myViewHolder.getView(R.id.nomal_question_answer)).setText(dataBean.getQuestionSolution());

    }

}
