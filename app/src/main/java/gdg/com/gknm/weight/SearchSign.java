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
public class SearchSign extends Dialog {

    @Bind(R.id.sp_poll_name)
    Spinner spPollName;
    @Bind(R.id.et_start_time)
    EditText etStartTime;
    @Bind(R.id.et_end_time)
    EditText etEndTime;
    @Bind(R.id.btn_ok)
    public Button btnOk;
    @Bind(R.id.btn_cancel)
    public Button btnCancel;
    private Context context;
    private ArrayAdapter<String> pollAdapter;
    private List<String> pollNameList = new ArrayList<>();
    private EnterpriseManagerDao managerDao;
    private List<AttentionPollBean.ResultEntityBean> listFactory = new ArrayList<>();

    public SearchSign(Context context) {
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


    private void showDialog1() {
        Calendar d = Calendar.getInstance(Locale.CHINA);
        Date myDate = new Date();
        //创建一个Date实例
        d.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH);
        int day = d.get(Calendar.DAY_OF_MONTH);
        //获得日历中的 year month day
        DatePickerDialog dlg = new DatePickerDialog(context, DatePickerListener1, year, month, day);
        //新建一个DatePickerDialog 构造方法中
        //     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
        dlg.show();
    }
    private void showDialog2() {
        Calendar d = Calendar.getInstance(Locale.CHINA);
        Date myDate = new Date();
        //创建一个Date实例
        d.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH);
        int day = d.get(Calendar.DAY_OF_MONTH);
        //获得日历中的 year month day
        DatePickerDialog dlg = new DatePickerDialog(context, DatePickerListener2, year, month, day);
        //新建一个DatePickerDialog 构造方法中
        //     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
        dlg.show();
    }
    private DatePickerDialog.OnDateSetListener DatePickerListener1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear = monthOfYear + 1;
            String month = String.valueOf(monthOfYear);
            String day = String.valueOf(dayOfMonth);
            if (monthOfYear < 10) {
                month = "0" + monthOfYear;
            }
            if (dayOfMonth < 10) {
                day = "0" + day;
            }

            etStartTime.setText(year + "-" + month + "-" + day);


        }
    };
    private DatePickerDialog.OnDateSetListener DatePickerListener2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear = monthOfYear + 1;
            String month = String.valueOf(monthOfYear);
            String day = String.valueOf(dayOfMonth);
            if (monthOfYear < 10) {
                month = "0" + monthOfYear;
            }
            if (dayOfMonth < 10) {
                day = "0" + day;
            }
            etEndTime.setText(year + "-" + month + "-" + day);


        }
    };


    //获取开始时间
    public String getStartTime() {
        if (etStartTime.getText().toString().equals("")) {
            return "";
        }
        return etStartTime.getText().toString();
    }

    //获取结束时间
    public String getStopTime() {
        if (etEndTime.getText().toString().equals("")) {
            return "";
        }
        return etEndTime.getText().toString();
    }

    @OnClick({R.id.et_start_time, R.id.et_end_time, R.id.btn_ok, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_start_time:
                showDialog1();
                break;
            case R.id.et_end_time:
                showDialog2();
                break;
            case R.id.btn_ok:
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }


    // 获取企业ID
    public String getPollId() {
        if (spPollName.getSelectedItemPosition() <= 0) {
            return "";
        }
        return listFactory.get(spPollName.getSelectedItemPosition()-1).getPollSourceId();
    }

}
