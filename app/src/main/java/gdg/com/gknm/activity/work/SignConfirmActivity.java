package gdg.com.gknm.activity.work;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.AppConfig;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.QuestBean;
import gdg.com.gknm.bean.SaveCurrentBean;
import gdg.com.gknm.bean.UpLoadBean;
import gdg.com.gknm.bean.UserBean;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.MultiSpinner;
import gdg.com.gknm.utils.SerializableHashMap;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.utils.StartActivityUtils;
import gdg.com.gknm.utils.TaskUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.UIUtil;
import gdg.com.gknm.utils.YjViewUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomEditText;
import gdg.com.gknm.weight.SignLeader2PopWindow;
import gdg.com.gknm.weight.SignLeaderPopWindow;
import gdg.com.gknm.weight.SignPopWindow;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static gdg.com.gknm.constant.ConstantIndex.USERINFO_OBJECT;

public class SignConfirmActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;

    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.ll_poll_sign)
    LinearLayout llPollSign;
    @Bind(R.id.ll_third_sign)
    LinearLayout llThirdSign;
    @Bind(R.id.ll_check_unit_sign)
    LinearLayout llCheckUnitSign;
    @Bind(R.id.activity_monitor_alarm)
    LinearLayout activityMonitorAlarm;
    @Bind(R.id.sp_check_unit)
    MultiSpinner spCheckUnit;
    @Bind(R.id.sp_check_person)
    MultiSpinner spCheckPerson;
    @Bind(R.id.iv_poll_sign)
    ImageView ivPollSign;
    @Bind(R.id.iv_third_sign)
    ImageView ivThirdSign;
    @Bind(R.id.iv_unit_sign)
    ImageView ivUnitSign;
    @Bind(R.id.et_check_time)
    CustomEditText etCheckTime;
    private String TAG = "SignConfirmActivity";
    private ProgressDialog mProgressDialog;
    Map<String, String> gasMap = new HashMap<>();
    Map<String, String> waterMap = new HashMap<>();
    private Bitmap bitmapPoll;
    private Bitmap bitmapThird;
    private Bitmap bitmapUnit;
    private List<File> signFileList = new ArrayList<>();
    private String taskProgressId = "";
    private String WaterOrGas = "";
    private String enterpriseType = "";
    private String industryType = "";
    private String pollId = "";
    private String checkTime = "";
    private String checkUnit = "";
    private String checkPerson = "";
    private String isZG = "0";
    private String taskAssignment = "";
    private boolean isLeader = false;
    private List<SaveCurrentBean> saveCurrentBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_confirm);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        initData();
    }

    private void initData() {
        if(SharedPreferenceUtil.getObject(USERINFO_OBJECT,this)!=null){
            UserBean user = (UserBean)SharedPreferenceUtil.getObject(USERINFO_OBJECT,this);
            checkUnit = user.getResultEntity().getAreaName();
            LogUtil.d(TAG, "checkUnit=="+checkUnit);
            if(TextUtils.equals(user.getResultEntity().getAreaCode(),"RYLB_JK")){
                isLeader = true;
            }
        }else{
            LogUtil.d(TAG, "UserBean=="+null);
        }
        ;
        //初始化sp
        taskProgressId = SharedPreferenceUtil.get(ConstantIndex.TASK_ID_CURRENT, "");
        if (!TextUtils.isEmpty(taskProgressId)) {
            LogUtil.d(TAG, "taskProgressId==初始化成功");
            LogUtil.d(TAG, "taskProgressId==" + taskProgressId);
        } else {
            LogUtil.d(TAG, "taskProgressId==初始化失败");
        }
        WaterOrGas = SharedPreferenceUtil.get(ConstantIndex.WATER_OR_GAS_CURRENT, "");
        if (!TextUtils.isEmpty(WaterOrGas)) {
            LogUtil.d(TAG, "WaterOrGas==初始化成功");
            LogUtil.d(TAG, "WaterOrGas==" + WaterOrGas);
        } else {
            LogUtil.d(TAG, "WaterOrGas==初始化失败");
        }
        enterpriseType = SharedPreferenceUtil.get(ConstantIndex.ENTERPRISE_TYPE_CURRENT, "");
        if (!TextUtils.isEmpty(enterpriseType)) {
            LogUtil.d(TAG, "enterpriseType==初始化成功");
            LogUtil.d(TAG, "enterpriseType==" + enterpriseType);
        } else {
            LogUtil.d(TAG, "enterpriseType==初始化失败");
        }
        industryType = SharedPreferenceUtil.get(ConstantIndex.INDUSTRY_TYPE_CURRENT, "");
        if (!TextUtils.isEmpty(industryType)) {
            LogUtil.d(TAG, "industryType==初始化成功");
            LogUtil.d(TAG, "industryType==" + industryType);
        } else {
            LogUtil.d(TAG, "industryType==初始化失败");
        }
        pollId = SharedPreferenceUtil.get(ConstantIndex.ENTERPRISE_ID_CURRENT, "");
        if (!TextUtils.isEmpty(pollId)) {
            LogUtil.d(TAG, "pollId==初始化成功");
            LogUtil.d(TAG, "pollId==" + pollId);
        } else {
            LogUtil.d(TAG, "pollId==初始化失败");
        }

        //初始化map
        if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_VALUE)) {
            if (getIntent().getSerializableExtra("waterMap") != null) {
                SerializableHashMap myWaterMap = (SerializableHashMap) getIntent().getSerializableExtra("waterMap");
                waterMap = myWaterMap.getMap();
                questWaterBeanList = (List<QuestBean>) getIntent().getSerializableExtra("waterQuestionList");
                LogUtil.d(TAG, "废水提交==waterMap初始化成功");
            } else {
                LogUtil.d(TAG, "废水提交==waterMap初始化失败");
            }
        } else if (TextUtils.equals(WaterOrGas, ConstantIndex.GAS_VALUE)) {
            if (getIntent().getSerializableExtra("gasMap") != null) {
                SerializableHashMap myGasMap = (SerializableHashMap) getIntent().getSerializableExtra("gasMap");
                gasMap = myGasMap.getMap();
                questGasBeanList = (List<QuestBean>) getIntent().getSerializableExtra("gasQuestionList");

                LogUtil.d(TAG, "废气提交==gasMap初始化成功");

            } else {
                LogUtil.d(TAG, "废气提交==gasMap初始化失败");
            }
        } else if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_AND_GAS_VALUE)) {
            if (getIntent().getSerializableExtra("waterMap") != null && SharedPreferenceUtil.getObject(ConstantIndex.SP_GAS_MAP, this) != null) {
                SerializableHashMap myWaterMap = (SerializableHashMap) getIntent().getSerializableExtra("waterMap");
                waterMap = myWaterMap.getMap();

                SerializableHashMap myGasMap = (SerializableHashMap) SharedPreferenceUtil.getObject(ConstantIndex.SP_GAS_MAP, this);
                gasMap = myGasMap.getMap();

                questWaterBeanList = (List<QuestBean>) getIntent().getSerializableExtra("waterQuestionList");
                questGasBeanList = (List<QuestBean>) SharedPreferenceUtil.getObject(ConstantIndex.SP_GAS_QUESTION, this);

                LogUtil.d(TAG, "废气废水提交==gasMap初始化成功");
            } else {
                LogUtil.d(TAG, "废气废水提交==gasMap初始化失败");
            }
        }
        //初始化签字
        if (ConstantIndex.SP_IS_SAVE) {
            if (TaskUtil.getSignListByTaskId(taskProgressId, this) != null) {
                signFileList = TaskUtil.getSignListByTaskId(taskProgressId, this);
                for (int i = 0; i < signFileList.size(); i++) {
                    File file = signFileList.get(i);
                    String fileName = signFileList.get(i).getName();
                    Bitmap bm = BitmapFactory.decodeFile(file.getPath());
                    LogUtil.d("fileName==" + fileName);
                    // 将图片显示到ImageView中
                    if (TextUtils.indexOf(fileName, ConstantIndex.SP_SIGN_POLL_PAG_NAME) != -1) {
                        if (bm != null) {
                            ivPollSign.setImageBitmap(bm);
                            llPollSign.setClickable(false);
                            llPollSign.setFocusable(false);
                            llPollSign.setBackgroundColor(Color.GRAY);
                        }
                    } else if (TextUtils.indexOf(fileName, ConstantIndex.SP_SIGN_THIRD_PAG_NAME) != -1) {
                        if (bm != null) {
                            ivThirdSign.setImageBitmap(bm);
                            llThirdSign.setClickable(false);
                            llThirdSign.setFocusable(false);
                            llThirdSign.setBackgroundColor(Color.GRAY);
                        }
                    } else if (TextUtils.indexOf(fileName, ConstantIndex.SP_SIGN_UNIT_PAG_NAME) != -1) {
                        if (bm != null) {
                            ivUnitSign.setImageBitmap(bm);
                            llCheckUnitSign.setClickable(false);
                            llCheckUnitSign.setFocusable(false);
                            llCheckUnitSign.setBackgroundColor(Color.GRAY);
                        }
                    }
                }
                LogUtil.d(TAG, "signFileList==初始化成功");
                LogUtil.d(TAG, "signFileList.size()==" + signFileList.size());
            } else {
                LogUtil.d(TAG, "signFileList==初始化失败");
            }

        }
        //初始化检查单位，检查人,检查时间
        if (getIntent().getStringExtra("checkPerson") != null) {
            checkPerson = getIntent().getStringExtra("checkPerson");
            LogUtil.d(TAG, "checkPerson==初始化成功");
            LogUtil.d(TAG, "checkPerson==" + checkPerson);
        }else{
            LogUtil.d(TAG, "checkPerson==初始化失败");
        }
//        if (getIntent().getStringExtra("checkUnit") != null) {
//            checkUnit = getIntent().getStringExtra("checkUnit");
//            LogUtil.d(TAG, "checkUnit==初始化成功");
//            LogUtil.d(TAG, "checkUnit==" + checkUnit);
//        }else{
//            LogUtil.d(TAG, "checkUnit==初始化失败");
//        }

        if (ConstantIndex.SP_IS_SAVE) {
            SaveCurrentBean bean = TaskUtil.getSaveBeanByTaskId(taskProgressId, this);
            if (bean != null) {
                checkTime = bean.getCheckTime();
                etCheckTime.setText(bean.getCheckTime());
                LogUtil.d(TAG, "CheckTime==初始化成功");
                LogUtil.d(TAG, "CheckTime==" + bean.getCheckTime());
            } else {
                LogUtil.d(TAG, "CheckTime==初始化失败");
            }
            if (ConstantIndex.SP_IS_SIGN) {
                etCheckTime.setEnabled(false);
                etCheckTime.setTextColor(Color.GRAY);
            }

        }
        spCheckUnit.setHint(checkUnit);
        spCheckPerson.setHint(checkPerson);
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
    }

    private void initView() {
        mProgressDialog = UIUtil.initDialog2(this);

    }

    private void setSignBitMap() {
        signFileList.clear();
        if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_POLL, this) != null) {
            byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_POLL, this);
            bitmapPoll = YjViewUtil.getBitmap(bigmapByte);
            ivPollSign.setImageBitmap(bitmapPoll);
            String jPoll = YjViewUtil.saveMyBitmap("companyImg" + System.currentTimeMillis(), bitmapPoll);
            File file = new File(jPoll);
            signFileList.add(file);
        }
        if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_THIRD, this) != null) {
            byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_THIRD, this);
            bitmapThird = YjViewUtil.getBitmap(bigmapByte);
            ivThirdSign.setImageBitmap(bitmapThird);
            String jThird = YjViewUtil.saveMyBitmap("thirdImg" + System.currentTimeMillis(), bitmapThird);
            File file = new File(jThird);
            signFileList.add(file);
        }
        if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_UNIT, this) != null) {
            byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_UNIT, this);
            bitmapUnit = YjViewUtil.getBitmap(bigmapByte);
            ivUnitSign.setImageBitmap(bitmapUnit);
            String jUnit = YjViewUtil.saveMyBitmap("checkImg" + System.currentTimeMillis(), bitmapUnit);
            File file = new File(jUnit);
            signFileList.add(file);
        }
    }

    @OnClick({R.id.ll_poll_sign, R.id.ll_third_sign, R.id.ll_check_unit_sign, R.id.btn_save, R.id.btn_confirm, R.id.et_check_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_poll_sign:
                if (!TextUtils.isEmpty(etCheckTime.getText().toString())) {
                    sign("企业现场负责人签字");
                } else {
                    ToastUtils.showShortToast(SignConfirmActivity.this, "请选择检查时间");
                }
                break;
            case R.id.ll_third_sign:
                if (!TextUtils.isEmpty(etCheckTime.getText().toString())) {
                    sign("第三方运维现场负责人签字");
                } else {
                    ToastUtils.showShortToast(SignConfirmActivity.this, "请选择检查时间");
                }
                break;
            case R.id.ll_check_unit_sign:
                if (!TextUtils.isEmpty(etCheckTime.getText().toString())) {
                    sign("检查单位现场负责人签字");
                } else {
                    ToastUtils.showShortToast(SignConfirmActivity.this, "请选择检查时间");
                }
                break;
            case R.id.btn_save:
                saveInfo();
                removeSp();
                ToastUtils.showShortToast(this, "暂存成功");
                finish();
                StartActivityUtils.startActivityNone(this, TaskInfoActivity.class);
                break;
            case R.id.btn_confirm:
                if (isContentRight() == 0) {
                    if (isLeader) {
                        showDialog();
                    }else{
                        sendTaskInfo();
                    }
                } else if (isContentRight() == 1) {
                    ToastUtils.showShortToast(SignConfirmActivity.this, "请签字后再提交");
                } else if (isContentRight() == 2) {
                    ToastUtils.showShortToast(SignConfirmActivity.this, "请选择检查时间");
                }
                break;
            case R.id.et_check_time:
                if (!ConstantIndex.SP_IS_SIGN) {
                    getCheckTime();
                }

                break;
        }
    }

    private void showDialog() {
        final SignLeaderPopWindow PopWindow = new SignLeaderPopWindow(SignConfirmActivity.this);
        PopWindow.showPopupWindow(mActionBar);
        PopWindow.setOutsideTouchable(true);
        PopWindow.setFocusable(true);
        PopWindow.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopWindow.dismiss();
                showDialog2();
            }
        });
        PopWindow.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopWindow.dismiss();
                sendTaskInfo();
            }
        });
    }
    private void showDialog2() {
        final SignLeader2PopWindow PopWindow = new SignLeader2PopWindow(SignConfirmActivity.this);
        PopWindow.showPopupWindow(mActionBar);
        PopWindow.setOutsideTouchable(true);
        PopWindow.setFocusable(true);
        PopWindow.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZG = "0";
                PopWindow.dismiss();
                sendTaskInfo();
            }
        });
        PopWindow.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZG = "1";
                PopWindow.dismiss();
                sendTaskInfo();
            }
        });
    }
    private void getCheckTime() {
        Calendar d = Calendar.getInstance(Locale.CHINA);
        Date myDate = new Date();
        //创建一个Date实例
        d.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH);
        int day = d.get(Calendar.DAY_OF_MONTH);
        //获得日历中的 year month day
        DatePickerDialog dlg = new DatePickerDialog(SignConfirmActivity.this, DatePickerListener, year, month, day);
        //新建一个DatePickerDialog 构造方法中
        //     （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）
        dlg.show();
    }

    /**
     * 暂存信息
     */
    private void saveInfo() {
        SaveCurrentBean saveBean = new SaveCurrentBean();
        saveBean.setTaskProgressId(taskProgressId);
        saveBean.setPollId(pollId);
        saveBean.setEnterpriseType(enterpriseType);
        saveBean.setIndustryType(industryType);
        saveBean.setCheckPerson(checkPerson);
        saveBean.setCheckTime(checkTime);
        saveBean.setCheckUnit(checkUnit);
        saveBean.setWaterOrGas(WaterOrGas);
        saveBean.setSignFile(signFileList);
        if (signFileList.size() > 0) {
            saveBean.setSign(true);
        } else {
            saveBean.setSign(false);
        }
        if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_VALUE)) {
            saveBean.setWaterMap(waterMap);
            saveBean.setWaterQuestion(questWaterBeanList);
        } else if (TextUtils.equals(WaterOrGas, ConstantIndex.GAS_VALUE)) {
            saveBean.setGasMap(gasMap);
            saveBean.setGasQuestion(questGasBeanList);
        } else if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_AND_GAS_VALUE)) {
            saveBean.setWaterMap(waterMap);
            saveBean.setWaterQuestion(questWaterBeanList);
            saveBean.setGasMap(gasMap);
            saveBean.setGasQuestion(questGasBeanList);
        }
        if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SAVE_BEAN_LIST, this) == null) {
            saveCurrentBeanList = new ArrayList<>();
        } else {
            if (TaskUtil.isTaskSaved(taskProgressId, this)) {
                LogUtil.d(TAG, "task有暂存，执行替换");
                TaskUtil.deletTaskByTaskId(taskProgressId, this);
            }
            saveCurrentBeanList = (List<SaveCurrentBean>) SharedPreferenceUtil.getObject(ConstantIndex.SP_SAVE_BEAN_LIST, this);
        }
        saveCurrentBeanList.add(saveBean);
        SharedPreferenceUtil.saveObject(ConstantIndex.SP_SAVE_BEAN_LIST, this, saveCurrentBeanList);
    }


    private void sign(String title) {
        SignPopWindow PopWindow = new SignPopWindow(this, title);
        PopWindow.showPopupWindow(mActionBar);
        PopWindow.setOutsideTouchable(true);
        PopWindow.setFocusable(true);
        PopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setSignBitMap();
            }
        });

    }

    private int isContentRight() {
        //签字少于3个不给提交
        if (signFileList.size() != 3) {
            return 1;
        } else if (TextUtils.isEmpty(etCheckTime.getText().toString())) {
            return 2;
        } else {
            return 0;
        }

    }

    /**
     * 提交废气问题
     */
    private List<QuestBean> questGasBeanList = new ArrayList<>();
    private List<QuestBean> questWaterBeanList = new ArrayList<>();

    private void sendGasFileUpload() {

        if (questGasBeanList.size() > 0) {
            for (int i = 0; i < questGasBeanList.size(); i++) {
                File file = questGasBeanList.get(i).getQuestionFile();
                String des = questGasBeanList.get(i).getQuestionContent();
                upLoadFile(file, des, ConstantIndex.GAS_VALUE);
            }
        }
    }

    /**
     * 提交废水问题
     */
    private void sendWaterFileUpload() {

        if (questWaterBeanList.size() > 0) {
            for (int i = 0; i < questWaterBeanList.size(); i++) {
                File file = questWaterBeanList.get(i).getQuestionFile();
                String des = questWaterBeanList.get(i).getQuestionContent();
                upLoadFile(file, des, ConstantIndex.WATER_VALUE);
            }
        }
    }

    /**
     * 提交任务信息
     */
    private void sendTaskInfo() {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).saveTaskProcess(checkUnit, checkTime, isZG, WaterOrGas, enterpriseType, taskProgressId, industryType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpLoadBean>(this) {
                    @Override
                    protected void onSucceed(UpLoadBean result) {
                        LogUtil.d(TAG, "任务总信息上传成功");
                        UIUtil.cancleDialog(mProgressDialog);
                        if (TextUtils.equals(ConstantIndex.WATER_VALUE, WaterOrGas)) {
                            //废水
                            sendWaterUpload();

                        } else if (TextUtils.equals(ConstantIndex.GAS_VALUE, WaterOrGas)) {
                            //废气
                            sendGasUpload();
                        } else if (TextUtils.equals(ConstantIndex.WATER_AND_GAS_VALUE, WaterOrGas)) {
                            //废水废气
                            sendWaterUpload();
                            sendGasUpload();
                        }
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);

                        LogUtil.d(TAG, "任务总信息上传失败：：" + msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
    }

    /**
     * 提交废气信息
     */
    private void sendGasUpload() {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).saveGasInfo(gasMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpLoadBean>(this) {
                    @Override
                    protected void onSucceed(UpLoadBean result) {
                        LogUtil.d(TAG, "废气信息上传成功");
                        UIUtil.cancleDialog(mProgressDialog);
                        sendGasFileUpload();
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, "废气信息上传失败：：" + msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
    }

    /**
     * 提交废水信息
     */
    private void sendWaterUpload() {
        //putWaterMap();
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).saveWaterInfo(waterMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpLoadBean>(this) {
                    @Override
                    protected void onSucceed(UpLoadBean result) {
                        UIUtil.cancleDialog(mProgressDialog);
                        LogUtil.d(TAG, "废水信息上传成功");
                        sendWaterFileUpload();

                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, "废水信息上传失败：：" + msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
    }

    /**
     * 提交问题及附件信息
     *
     * @param file
     * @param des
     */
    private void upLoadFile(File file, String des, String waterOrGas) {
        mProgressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        try {
            params.put("file", file);
            params.put("problemDescription", des);
            params.put("waterOrGas", waterOrGas);
            params.put("taskProgressId", taskProgressId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String requestURL = AppConfig.API_BASE_URL+"/edds/phoneHandleController/uploadQuestionFile.do?";
        Log.d(TAG, requestURL);
        client.post(requestURL, params, asyncHttpResponseHandlerFile);
    }

    /**
     * 提交签字png
     *
     * @param signFile
     */

    private void upLoadSign(File signFile) {
        mProgressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        try {
            params.put("taskProgressId", taskProgressId);
            params.put("file", signFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String requestURL =  AppConfig.API_BASE_URL+"/edds/phoneHandleController/uploadSignatureFile.do?";
        //String requestURL = "http://192.168.3.148:8080/edds/archiveController/saveArchiveMonitor.do?";
        Log.d(TAG, requestURL);
        client.post(requestURL, params, asyncHttpResponseHandlerSign);
    }

    private int gasQuestionCont = 0;
    private int waterQuestionCont = 0;
    private int waterAndGasQuestionCont = 0;
    private int signCont = 0;
    private AsyncHttpResponseHandler asyncHttpResponseHandlerFile = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers,
                              byte[] responseBody) {
            // 上传成功后要做的工作
            if (TextUtils.equals(WaterOrGas, ConstantIndex.GAS_VALUE)) {
                gasQuestionCont++;
                if (gasQuestionCont == questGasBeanList.size()) {
                    Log.d(TAG, "废气问题上传" + gasQuestionCont + "次");
                    Log.d(TAG, "废气问题上传成功");
                    UIUtil.cancleDialog(mProgressDialog);
                    for (int i = 0; i < signFileList.size(); i++) {
                        upLoadSign(signFileList.get(i));
                    }
                } else {
                    Log.d(TAG, "废气问题上传" + gasQuestionCont + "次");
                }
            } else if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_VALUE)) {
                waterQuestionCont++;
                if (waterQuestionCont == questWaterBeanList.size()) {
                    Log.d(TAG, "废水问题上传" + waterQuestionCont + "次");
                    Log.d(TAG, "废水问题上传成功");
                    UIUtil.cancleDialog(mProgressDialog);
                    for (int i = 0; i < signFileList.size(); i++) {
                        upLoadSign(signFileList.get(i));
                    }
                } else {
                    Log.d(TAG, "废水问题上传" + waterQuestionCont + "次");
                }

            } else if (TextUtils.equals(WaterOrGas, ConstantIndex.WATER_AND_GAS_VALUE)) {
                waterAndGasQuestionCont++;
                if (waterAndGasQuestionCont == (questWaterBeanList.size() + questGasBeanList.size())) {
                    Log.d(TAG, "废水废气问题上传" + waterAndGasQuestionCont + "次");
                    Log.d(TAG, "废水废气问题上传成功");
                    UIUtil.cancleDialog(mProgressDialog);
                    for (int i = 0; i < signFileList.size(); i++) {
                        upLoadSign(signFileList.get(i));
                    }
                } else {
                    Log.d(TAG, "废水废气问题上传" + waterAndGasQuestionCont + "次");
                }
            }


        }

        @Override
        public void onFailure(int statusCode, Header[] headers,
                              byte[] responseBody, Throwable error) {
            // 上传失败后要做到工作
            ToastUtils.showShortToast(SignConfirmActivity.this, "问题上传失败");
            cleanCount();
            Log.d(TAG, "问题上传失败：：" + error.toString());
            UIUtil.cancleDialog(mProgressDialog);
        }

    };

    private AsyncHttpResponseHandler asyncHttpResponseHandlerSign = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers,
                              byte[] responseBody) {
            // 上传成功后要做的工作
           // ToastUtils.showShortToast(SignConfirmActivity.this, "上传成功");
            signCont++;
            if (3 == signCont) {
                Log.d(TAG, "签字上传" + signCont + "次");
                Log.d(TAG, "签字上传成功");
                sendVerify();
                UIUtil.cancleDialog(mProgressDialog);
            } else {
                Log.d(TAG, "签字上传" + signCont + "次");
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers,
                              byte[] responseBody, Throwable error) {
            // 上传失败后要做到工作
            //ToastUtils.showShortToast(SignConfirmActivity.this, "签字上传失败");
            cleanCount();
            Log.d(TAG, "签字上传失败：：" + error.toString());
            UIUtil.cancleDialog(mProgressDialog);
        }

    };

    private void sendVerify() {
        mProgressDialog.show();
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getVerifyInfo(taskProgressId, pollId, checkPerson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpLoadBean>(this) {
                    @Override
                    protected void onSucceed(UpLoadBean result) {
                        LogUtil.d(TAG, "验证信息提交成功");
                        ToastUtils.showShortToast(SignConfirmActivity.this, "提交成功！");
                        TaskUtil.deletTaskByTaskId(taskProgressId, SignConfirmActivity.this);
                        UIUtil.cancleDialog(mProgressDialog);
                        removeSp();
                        finish();
                        StartActivityUtils.startActivityNone(SignConfirmActivity.this, TaskInfoActivity.class);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        cleanCount();
                        LogUtil.d(TAG, "验证信息提交失败：：" + msg);
                        ToastUtils.showShortToast(SignConfirmActivity.this, msg);
                        UIUtil.cancleDialog(mProgressDialog);
                    }
                });
    }


    private void removeSp() {
        SharedPreferenceUtil.remove(ConstantIndex.WATER_OR_GAS_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.TASK_ID_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.ENTERPRISE_TYPE_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.INDUSTRY_TYPE_CURRENT);
        SharedPreferenceUtil.remove(ConstantIndex.ENTERPRISE_ID_CURRENT);

        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_POLL);
        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_THIRD);
        SharedPreferenceUtil.remove(ConstantIndex.SP_SIGN_UNIT);

        ConstantIndex.SP_IS_SAVE = false;
        ConstantIndex.SP_IS_SIGN = false;


    }


    private void cleanCount() {
        gasQuestionCont = 0;
        waterQuestionCont = 0;
        waterAndGasQuestionCont = 0;
        signCont = 0;
    }

    private DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener() {

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
            etCheckTime.setText(year + "-" + month + "-" + day);
            checkTime = etCheckTime.getText().toString();
        }
    };
}
