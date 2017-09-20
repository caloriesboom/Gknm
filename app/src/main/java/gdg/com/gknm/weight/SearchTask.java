package gdg.com.gknm.weight;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.dao.EnterpriseManagerDao;

/**
 * Created by Administrator on 2015/11/10.
 */
public class SearchTask extends Dialog {

    @Bind(R.id.sp_poll_name)
    Spinner spPollName;
    @Bind(R.id.btn_ok)
    public Button btnOk;
    @Bind(R.id.btn_cancel)
    public Button btnCancel;
    private Context context;
    private ArrayAdapter<String> pollAdapter;
    private List<String> pollNameList = new ArrayList<>();
    private EnterpriseManagerDao managerDao;
    private List<AttentionPollBean.ResultEntityBean> listFactory = new ArrayList<>();

    public SearchTask(Context context) {
        super(context);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_search_sign);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        pollNameList.add(0,"全部");
        managerDao = EnterpriseManagerDao.getInstance(context);
        listFactory = managerDao.getAllFactory();
        if (listFactory.size() > 0) {
            for (int i = 0; i < listFactory.size(); i++) {
                pollNameList.add(managerDao.getAllFactory().get(i).getPollSourceName());
            }
        }
    }

    private void initView() {
        pollAdapter = new ArrayAdapter<String>(context, R.layout.spinner_poll_type, pollNameList);
        spPollName.setAdapter(pollAdapter);
    }
    @OnClick({  R.id.btn_ok, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }


    // 获取企业ID
    public String getPollId() {
        if (spPollName.getSelectedItemPosition() < 0) {
            return "";
        }
        return listFactory.get(spPollName.getSelectedItemPosition()-1).getPollSourceId();
    }

}
