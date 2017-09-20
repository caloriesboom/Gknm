package gdg.com.gknm.activity.work;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.IndustryTypeBean;
import gdg.com.gknm.bean.PollInfoBean;
import gdg.com.gknm.bean.PollTypeBean;
import gdg.com.gknm.bean.SaveCurrentBean;
import gdg.com.gknm.bean.WaterOrGasBean;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.utils.TaskUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.UIUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomEditText;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PollInfoActivity extends BaseActivity {

    @Bind(R.id.custom_action_bar)
    CustomActionBar customActionBar;
    @Bind(R.id.et_check_time)
    CustomEditText pollName;
    @Bind(R.id.sp_poll_category)
    Spinner spPollCategory;
    @Bind(R.id.sp_industry_category)
    Spinner spIndustryCategory;
    @Bind(R.id.corporate_name)
    CustomEditText corporateName;
    @Bind(R.id.environment_person)
    CustomEditText environmentPerson;
    @Bind(R.id.tel_number)
    CustomEditText telNumber;
    @Bind(R.id.address)
    CustomEditText address;
    @Bind(R.id.poll_info)
    CustomEditText pollInfo;
    @Bind(R.id.pollution_control)
    CustomEditText pollutionControl;
    @Bind(R.id.activity_monitor_alarm)
    LinearLayout activityMonitorAlarm;
    @Bind(R.id.toLocal)
    Button toLocal;
    @Bind(R.id.sp_category)
    Spinner spCategory;
    private Subscription mSubscription;
    private List<PollInfoBean.ResultEntityBean.DataBean> mList = new ArrayList<>();
    private String TAG = "PollInfoActivity";
    private ProgressDialog mProgressDialog;
    private ArrayAdapter<String> pollTypeAdapter;
    private ArrayAdapter<String> industryTypeAdapter;
    private ArrayAdapter<String> waterOrGasAdapter;
    private List<String> pollTypeNameList = new ArrayList<>();
    private List<String> pollTypeCodeList = new ArrayList<>();
    private List<String> industryTypeNameList = new ArrayList<>();
    private List<String> industryTypeCodeList = new ArrayList<>();
    private List<String> waterOrGasNameList = new ArrayList<>();
    private List<String> waterOrGasCodeList = new ArrayList<>();
    private List<PollTypeBean.ResultEntityBean.DataBean> pollTypeList = new ArrayList<>();
    private List<IndustryTypeBean.ResultEntityBean.DataBean> industryTypeList = new ArrayList<>();
    private List<WaterOrGasBean> waterOrGasList = new ArrayList<>();

    private String pollTypeCode = "";
    private String industryTypeCode = "";
    private String waterOrGasCode = "";
    private String taskProgressId = "";
    private String checkPerson = "";
    private String[] waterOrGasNames = new String[]{"废水","废气","废气、废水"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_info);
        ButterKnife.bind(this);
        initActionBar();
        initView();
    }

    private void initActionBar() {
        customActionBar.setLeftImageClickListener(this);
    }

    protected void initView() {
        mProgressDialog = UIUtil.initDialog2(this);
        String pollId = getIntent().getStringExtra("tag1");
        taskProgressId = getIntent().getStringExtra("tag3");
        checkPerson = getIntent().getStringExtra("tag4");

        if (!TextUtils.isEmpty(pollId)) {
            LogUtil.e(TAG, "企业ID = " + pollId);
            initData(pollId);
        } else {
            LogUtil.e(TAG, "企业ID为空！！！！");
        }
        spPollCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    LogUtil.d(TAG, "请选择");
                    pollTypeCode = "";
                } else {
                    pollTypeCode = pollTypeList.get(i - 1).getDicCode();
                    LogUtil.d(TAG, pollTypeCode);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spIndustryCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    LogUtil.d(TAG, "请选择");
                    industryTypeCode = "";
                } else {
                    industryTypeCode = industryTypeList.get(i - 1).getDicCode();
                    LogUtil.d(TAG, industryTypeCode);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    LogUtil.d(TAG, "请选择");
                    waterOrGasCode = "";
                } else {
                    waterOrGasCode = waterOrGasList.get(position - 1).getBeanCode();
                    LogUtil.d(TAG, waterOrGasCode);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for(int i=0;i<waterOrGasNames.length;i++){
            WaterOrGasBean bean = new WaterOrGasBean();
            bean.setBeanCode(String.valueOf(i+1));
            bean.setBeanName(waterOrGasNames[i]);
            waterOrGasNameList.add(waterOrGasNames[i]);
            waterOrGasList.add(bean);
            waterOrGasCodeList.add(String.valueOf(i+1));
        }
        initWaterOrGasSpinnerData(waterOrGasNameList,waterOrGasCodeList);
    }


    private void initData(String pollId) {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollInfo(pollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollInfoBean>(this) {
                    @Override
                    protected void onSucceed(PollInfoBean result) {
                        mList = result.getResultEntity().getData();
                            setPollInfo(mList);
                        UIUtil.cancleDialog(mProgressDialog);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getPollTpye()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PollTypeBean>(this) {
                    @Override
                    protected void onSucceed(PollTypeBean result) {
                        pollTypeList = result.getResultEntity().getData();
                        for (int i = 0; i < pollTypeList.size(); i++) {
                            pollTypeNameList.add(pollTypeList.get(i).getDicName());
                            pollTypeCodeList.add(pollTypeList.get(i).getDicCode());
                        }
                        initPollSpinnerData(pollTypeNameList,pollTypeCodeList);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getIndustryType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<IndustryTypeBean>(this) {
                    @Override
                    protected void onSucceed(IndustryTypeBean result) {
                        industryTypeList = result.getResultEntity().getData();
                        for (int i = 0; i < industryTypeList.size(); i++) {
                            industryTypeNameList.add(industryTypeList.get(i).getDicName());
                            industryTypeCodeList.add(industryTypeList.get(i).getDicCode());
                        }
                        initIndusSpinnerData(industryTypeNameList,industryTypeCodeList);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });
    }

    private void setPollInfo(List<PollInfoBean.ResultEntityBean.DataBean> mList) {
        PollInfoBean.ResultEntityBean.DataBean pollBean = mList.get(0);
        pollName.setText(pollBean.getPollSourceName());
        corporateName.setText(pollBean.getCorpName());
        environmentPerson.setText(pollBean.getEnvironLinkMan());
        telNumber.setText(pollBean.getTelephone());
        address.setText(pollBean.getPollSourceAddress());
        pollInfo.setText(pollBean.getProdScale());

    }


    private void initPollSpinnerData(final List<String> pollType,final List<String> pollTypeCode) {
        if (!pollType.isEmpty()) {
            pollType.add(0, "请选择");
            pollTypeAdapter = new ArrayAdapter<String>(PollInfoActivity.this, R.layout.spinner_poll_type, pollType);
            pollTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPollCategory.setAdapter(pollTypeAdapter);
            if(ConstantIndex.SP_IS_SAVE){
               setPollTypeSaveInfo(pollTypeCode);
                if(ConstantIndex.SP_IS_SIGN){
                    spPollCategory.setEnabled(false);
                }else{
                    spPollCategory.setEnabled(true);
                }
            }
        }

    }

    private void initIndusSpinnerData(final List<String> industryType,final List<String> industryTypeCode) {
        if (!industryType.isEmpty()) {
            industryType.add(0, "请选择");
            industryTypeAdapter = new ArrayAdapter<String>(PollInfoActivity.this, R.layout.spinner_poll_type, industryType);
            industryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spIndustryCategory.setAdapter(industryTypeAdapter);
            if(ConstantIndex.SP_IS_SAVE){
                setIndusSaveInfo(industryTypeCode);
                if(ConstantIndex.SP_IS_SIGN){
                    spIndustryCategory.setEnabled(false);
                }else{
                    spIndustryCategory.setEnabled(true);
                }
            }
        }

    }


    private void initWaterOrGasSpinnerData(final List<String> waterOrGas,final List<String> waterOrGasCode) {
        if (!waterOrGas.isEmpty()) {
            waterOrGas.add(0, "请选择");
            waterOrGasAdapter = new ArrayAdapter<String>(PollInfoActivity.this, R.layout.spinner_poll_type, waterOrGas);
            waterOrGasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCategory.setAdapter(waterOrGasAdapter);
            if(ConstantIndex.SP_IS_SAVE){
                setWaterOrGasSaveInfo(waterOrGasCode);
                LogUtil.d(TAG,"当前任务有暂存");
                if(ConstantIndex.SP_IS_SIGN){
                    LogUtil.d(TAG,"当前任务已签字");
                    spCategory.setEnabled(false);
                }else{
                    spCategory.setEnabled(true);
                    LogUtil.d(TAG,"当前任务无签字");
                }
            }else{
                LogUtil.d(TAG,"当前任务无暂存");
            }

        }

    }

    @OnClick({R.id.pollution_control, R.id.toLocal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pollution_control:
                StartActivityUtils.startActivityNone(PollInfoActivity.this, PollutionControlActivity.class);
                break;
            case R.id.toLocal:
                if (TextUtils.isEmpty(pollTypeCode)) {
                    ToastUtils.showShortToast(this, "请选择企业类别！");
                    return;
                }
                if (TextUtils.isEmpty(industryTypeCode)) {
                    ToastUtils.showShortToast(this, "请选择行业类型！");
                    return;
                }
                if (TextUtils.isEmpty(waterOrGasCode)) {
                ToastUtils.showShortToast(this, "请选择废气废水类型！");
                return;
                 }
                gotoCheck();
                break;
        }
    }

    private void gotoCheck() {
        String pollId = getIntent().getStringExtra("tag1");
        String isEnterpriseRectification = getIntent().getStringExtra("tag4");
        if (TextUtils.isEmpty(taskProgressId)) {
            ToastUtils.showShortToast(this, "任务不存在！");
            return;
        }
        if (TextUtils.isEmpty(pollId)) {
            ToastUtils.showShortToast(this, "企业不存在！");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("pollId", pollId);
        intent.putExtra("pollTypeCode", pollTypeCode);
        intent.putExtra("industryTypeCode", industryTypeCode);
        intent.putExtra("WaterOrGas", waterOrGasCode);
        LogUtil.d(TAG,"saveWaterOrGas=="+waterOrGasCode);
        intent.putExtra("taskProgressId", taskProgressId);
        intent.putExtra("checkPerson", checkPerson);
        intent.setClass(PollInfoActivity.this, WaterOrGasActivity.class);
        SharedPreferenceUtil.save(ConstantIndex.WATER_OR_GAS_CURRENT,waterOrGasCode);
        SharedPreferenceUtil.save(ConstantIndex.TASK_ID_CURRENT,taskProgressId);
        SharedPreferenceUtil.save(ConstantIndex.ENTERPRISE_TYPE_CURRENT,pollTypeCode);
        SharedPreferenceUtil.save(ConstantIndex.INDUSTRY_TYPE_CURRENT,industryTypeCode);
        SharedPreferenceUtil.save(ConstantIndex.ENTERPRISE_ID_CURRENT,pollId);
        if(!TextUtils.equals(ConstantIndex.WATER_AND_GAS_VALUE,saveWaterOrGas)){
            if(!TextUtils.equals(saveWaterOrGas,waterOrGasCode)){
                ConstantIndex.SP_IS_SAVE = false;
            }
        }
        startActivity(intent);
    }
    private void setIndusSaveInfo(List<String> industryCodeList) {
        SaveCurrentBean bean = TaskUtil.getSaveBeanByTaskId(taskProgressId,PollInfoActivity.this);
        String industryName = bean.getIndustryType();
        for(int i=0;i<industryCodeList.size();i++){
            if(TextUtils.equals(industryName,industryCodeList.get(i))){
                spIndustryCategory.setSelection(i+1);
            }
        }
    }
    private String saveWaterOrGas = "";
    private void setWaterOrGasSaveInfo(List<String> waterOrGasCodeList) {
        SaveCurrentBean bean = TaskUtil.getSaveBeanByTaskId(taskProgressId,PollInfoActivity.this);
         saveWaterOrGas = bean.getWaterOrGas();
        LogUtil.d(TAG,"waterOrGas=="+saveWaterOrGas);
        for(int i=0;i<waterOrGasCodeList.size();i++){
            LogUtil.d(TAG,"waterOrGasList=="+waterOrGasCodeList.get(i));
            if(TextUtils.equals(saveWaterOrGas,waterOrGasCodeList.get(i))){
                spCategory.setSelection(i+1);
            }
        }
    }
    private void setPollTypeSaveInfo(List<String> pollTypeCodeList) {
        SaveCurrentBean bean = TaskUtil.getSaveBeanByTaskId(taskProgressId,PollInfoActivity.this);
        String pollTypeName = bean.getEnterpriseType();
        for(int i=0;i<pollTypeCodeList.size();i++){
            if(TextUtils.equals(pollTypeName,pollTypeCodeList.get(i))){
                spPollCategory.setSelection(i+1);
            }
        }
    }
}
