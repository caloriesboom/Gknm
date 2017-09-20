package gdg.com.gknm.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.activity.work.SignConfirmActivity;
import gdg.com.gknm.base.baseFragment;
import gdg.com.gknm.bean.QuestBean;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.SerializableHashMap;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.utils.TaskUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.YjViewUtil;
import gdg.com.gknm.weight.CustomEditText;

/**
 * Created by 47129 on 2017/9/7.
 * 废气
 */

public class GasFragment extends baseFragment {
    @Bind(R.id.ll_gas)
    LinearLayout llGas;
    @Bind(R.id.btn_add_gas_question)
    Button btnAddGasQuestion;
    @Bind(R.id.btn_gas_sign)
    Button btnGasSign;
    @Bind(R.id.total_num_install)
    CustomEditText totalNumInstall;
    @Bind(R.id.original_gas_point)
    CustomEditText originalGasPoint;
    @Bind(R.id.cb_original_gas_on)
    CheckBox cbOriginalGasOn;
    @Bind(R.id.cb_original_gas_off)
    CheckBox cbOriginalGasOff;
    @Bind(R.id.clean_gas_point)
    CustomEditText cleanGasPoint;
    @Bind(R.id.cb_clean_gas_on)
    CheckBox cbCleanGasOn;
    @Bind(R.id.cb_clean_gas_off)
    CheckBox cbCleanGasOff;
    @Bind(R.id.denitrate_point)
    CustomEditText denitratePoint;
    @Bind(R.id.cb_denitrate_point_on)
    CheckBox cbDenitratePointOn;
    @Bind(R.id.cb_denitrate_point_off)
    CheckBox cbDenitratePointOff;
    @Bind(R.id.other_point)
    CustomEditText otherPoint;
    @Bind(R.id.cb_other_point_on)
    CheckBox cbOtherPointOn;
    @Bind(R.id.cb_other_point_off)
    CheckBox cbOtherPointOff;
    @Bind(R.id.et_point_name)
    CustomEditText etPointName;
    @Bind(R.id.et_install_time)
    CustomEditText etInstallTime;
    @Bind(R.id.et_facility_type)
    CustomEditText etFacilityType;
    @Bind(R.id.et_accept)
    CustomEditText etAccept;
    @Bind(R.id.et_unit)
    CustomEditText etUnit;
    @Bind(R.id.et_last_time)
    CustomEditText etLastTime;
    @Bind(R.id.et_1_1)
    CustomEditText et11;
    @Bind(R.id.et_1_2)
    CustomEditText et12;
    @Bind(R.id.et_2_1)
    CustomEditText et21;
    @Bind(R.id.et_2_2)
    CustomEditText et22;
    @Bind(R.id.et_3_1)
    CustomEditText et31;
    @Bind(R.id.et_3_2)
    CustomEditText et32;
    @Bind(R.id.et_4_1)
    CustomEditText et41;
    @Bind(R.id.et_4_2)
    CustomEditText et42;
    @Bind(R.id.et_5_1)
    CustomEditText et51;
    @Bind(R.id.et_5_2)
    CustomEditText et52;
    private String TAG = "GasFragment";

    private List<LinearLayout> linearLayList = new ArrayList<>();

    @Override
    protected int getFragmentContentId() {
        return R.layout.fragment_gas;
    }

    @Override
    protected void initView() {
        if(TextUtils.equals(getArguments().getString("WaterOrGas"), ConstantIndex.WATER_AND_GAS_VALUE)){
            btnGasSign.setVisibility(View.GONE);
        }
        if(ConstantIndex.SP_IS_SAVE){
            initSaveData();
        }else{
            addQuestionView("",null);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private int questionCode = 1;
    @IdRes
    int imageId = 1;

    /**
     * 动态添加问题布局
     */
    private void addQuestionView(String question,String fileCode) {
        LinearLayout ll = new LinearLayout(mActivity, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setWeightSum(7);
        ll.setLayoutParams(params);

        TextView tv = new TextView(mActivity, null);
        LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(0, YjViewUtil.dip2px2(40));
        tv_params.weight = 1;
        tv.setLayoutParams(tv_params);
        tv.setText("问题" + questionCode + ":");
        tv.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        tv.setTextSize(14);
        tv.setTextColor(Color.BLACK);

        CustomEditText et = new CustomEditText(mActivity, null);
        LinearLayout.LayoutParams et_params = new LinearLayout.LayoutParams(0, YjViewUtil.dip2px2(40));
        et_params.setMargins(0, YjViewUtil.dip2px2(5), YjViewUtil.dip2px2(10), YjViewUtil.dip2px2(5));
        et.setPadding(YjViewUtil.dip2px2(5), 0, 0, YjViewUtil.dip2px2(5));
        et.setBackgroundResource(R.drawable.dark_primary_color_angle22);
        et.setGravity(Gravity.CENTER_VERTICAL);
        et_params.weight = 5;
        et.setLayoutParams(et_params);
        et.setTextSize(14);
        et.setText(question);

        ImageView iv = new ImageView(mActivity, null);
        LinearLayout.LayoutParams iv_params = new LinearLayout.LayoutParams(0, YjViewUtil.dip2px2(35));
        iv.setBackgroundResource(R.mipmap.add_file);
        iv.setPadding(YjViewUtil.dip2px2(5), YjViewUtil.dip2px2(5), YjViewUtil.dip2px2(5), YjViewUtil.dip2px2(5));
        iv_params.gravity = Gravity.CENTER;
        iv_params.weight = (float) 0.7;
        iv.setLayoutParams(iv_params);

        if(!TextUtils.isEmpty(fileCode)){
            iv.setId(Integer.valueOf(fileCode));
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setId((int)(Math.random()*50000));
                LogUtil.d(TAG, "v.getId()==" + v.getId() + "");
                choseFile(v.getId());
            }
        });
        ll.addView(tv);
        ll.addView(et);
        ll.addView(iv);
        llGas.addView(ll);
        linearLayList.add(ll);
        questionCode++;
        imageId++;
    }

    private void choseFile(int code) {
        // TODO: 2016/7/12 遍历文件
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, code);
    }

    private void getContent() {
        Intent intent = setData();
        startActivity(intent);
    }
    /**
     * 初始化暂存信息
     */
    private void initSaveData() {
        String taskId = SharedPreferenceUtil.get(ConstantIndex.TASK_ID_CURRENT,"");
        Map map = TaskUtil.getGasMapByTaskId(taskId,mActivity);
        //获取自动监控设备安装情况
        totalNumInstall.setText((String)map.get("installTotalNumber"));
        originalGasPoint.setText((String)map.get("rawGasPointNumber"));
        if(TextUtils.equals("true",(String)map.get("rawGasPointNetworking"))){
            cbOriginalGasOn.setChecked(true);
            cbOriginalGasOff.setChecked(false);
        }else{
            cbOriginalGasOn.setChecked(false);
            cbOriginalGasOff.setChecked(true);
        }
        cleanGasPoint.setText((String)map.get("netGasPointNumber"));
        if(TextUtils.equals("true",(String)map.get("netGasPointNetworking"))){
            cbCleanGasOn.setChecked(true);
            cbCleanGasOff.setChecked(false);
        }else{
            cbCleanGasOn.setChecked(false);
            cbCleanGasOff.setChecked(true);
        }
        denitratePoint.setText((String)map.get("denitrationPointNumber"));
        if(TextUtils.equals("true",(String)map.get("denitrationPointNetworking"))){
            cbDenitratePointOn.setChecked(true);
            cbDenitratePointOff.setChecked(false);
        }else{
            cbDenitratePointOn.setChecked(false);
            cbDenitratePointOff.setChecked(true);
        }
        otherPoint.setText((String)map.get("otherPointNumber"));
        if(TextUtils.equals("true",(String)map.get("otherPointNetworking"))){
            cbOtherPointOn.setChecked(true);
            cbOtherPointOff.setChecked(false);
        }else{
            cbOtherPointOn.setChecked(false);
            cbOtherPointOff.setChecked(true);
        }
        //获取自动监控设备基本情况
        etPointName.setText((String)map.get("devicePointName"));
        etInstallTime.setText((String)map.get("deviceInstallTime"));
        etFacilityType.setText((String)map.get("deviceBrand"));
        etAccept.setText((String)map.get("deviceAcceptanceTime"));
        etUnit.setText((String)map.get("deviceOperationUnit"));
        etLastTime.setText((String)map.get("deviceLastValidityAuditTime"));
        //获取设备运行参数记录表
        et11.setText((String)map.get("outletSizeDeclareRegisterValue"));
        et12.setText((String)map.get("outletSizeSceneCheckValue"));
        et21.setText((String)map.get("excessAirFactorDeclareRegisterValue"));
        et22.setText((String)map.get("excessAirFactorSceneCheckValue"));
        et31.setText((String)map.get("correctDeclareRegisterValue"));
        et32.setText((String)map.get("correctCheckValue"));
        et41.setText((String)map.get("velocityFieldDeclareRegisterValue"));
        et42.setText((String)map.get("velocityFieldCheckValue"));
        et51.setText((String)map.get("pitotDeclareRegisterValue"));
        et52.setText((String)map.get("pitotCheckValue"));
        //获取问题总汇
        List<QuestBean> questBeanList = TaskUtil.getGasQuestByTaskId(taskId,mActivity);
        for(int i=0;i<questBeanList.size();i++){
            String question = questBeanList.get(i).getQuestionContent();
            File file = questBeanList.get(i).getQuestionFile();
            String fileCode = questBeanList.get(i).getQuestionCode();
            SharedPreferenceUtil.saveObject(String.valueOf(fileCode), mActivity, file);
            LogUtil.d(TAG,"question=="+question);
            if(file!=null){
                LogUtil.d(TAG,"file=="+file.toString());
            }
            addQuestionView(question,fileCode);
        }

        if(ConstantIndex.SP_IS_SIGN){
            //遍历所有子控件，设置为不可编辑状态
            for(int i = 0;i<llGas.getChildCount();i++) {
                if (llGas.getChildAt(i) instanceof LinearLayout) {
                    LinearLayout ll = (LinearLayout) llGas.getChildAt(i);
                    for(int j = 0;j<ll.getChildCount();j++){
                        ll.getChildAt(j).setClickable(false);
                        ll.getChildAt(j).setFocusable(false);
                        if(ll.getChildAt(j) instanceof TableLayout){
                            TableLayout tableLayout = (TableLayout) ll.getChildAt(j);
                            for(int m = 0;m<tableLayout.getChildCount();m++){
                                if(tableLayout.getChildAt(m) instanceof TableRow){
                                    TableRow tableRow = (TableRow) tableLayout.getChildAt(m);
                                    for(int n = 0;n<tableRow.getChildCount();n++){
                                        tableRow.getChildAt(n).setClickable(false);
                                        tableRow.getChildAt(n).setFocusable(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public SerializableHashMap getGasInfoMap() {
        if(isContentOk()){
            SerializableHashMap myMap = new SerializableHashMap();
            HashMap map = new HashMap();
            //获取自动监控设备安装情况
            map.put("installTotalNumber", totalNumInstall.getText().toString());
            map.put("rawGasPointNumber", originalGasPoint.getText().toString());
            map.put("rawGasPointNetworking", String.valueOf(cbOriginalGasOn.isChecked()));
            map.put("netGasPointNumber", cleanGasPoint.getText().toString());
            map.put("netGasPointNetworking", String.valueOf(cbCleanGasOn.isChecked()));
            map.put("denitrationPointNumber", denitratePoint.getText().toString());
            map.put("denitrationPointNetworking", String.valueOf(cbDenitratePointOn.isChecked()));
            map.put("otherPointNumber", otherPoint.getText().toString());
            map.put("otherPointNetworking", String.valueOf(cbOtherPointOn.isChecked()));
            //获取自动监控设备基本情况
            map.put("devicePointName", etPointName.getText().toString());
            map.put("deviceInstallTime", etInstallTime.getText().toString());
            map.put("deviceBrand", etFacilityType.getText().toString());
            map.put("deviceAcceptanceTime", etAccept.getText().toString());
            map.put("deviceOperationUnit", etUnit.getText().toString());
            map.put("deviceLastValidityAuditTime", etLastTime.getText().toString());
            //获取设备运行参数记录表
            map.put("outletSizeDeclareRegisterValue", et11.getText().toString());
            map.put("outletSizeSceneCheckValue", et12.getText().toString());
            map.put("excessAirFactorDeclareRegisterValue", et21.getText().toString());
            map.put("excessAirFactorSceneCheckValue", et22.getText().toString());
            map.put("correctDeclareRegisterValue", et31.getText().toString());
            map.put("correctCheckValue", et32.getText().toString());
            map.put("velocityFieldDeclareRegisterValue", et41.getText().toString());
            map.put("velocityFieldCheckValue", et42.getText().toString());
            map.put("pitotDeclareRegisterValue", et51.getText().toString());
            map.put("pitotCheckValue", et52.getText().toString());

            map.put("taskProgressId", getArguments().getString("taskProgressId"));
            map.put("pollSourceId", getArguments().getString("pollId"));
            map.put("pollTypeCode", getArguments().getString("pollTypeCode"));
            map.put("industryTypeCode", getArguments().getString("industryTypeCode"));
            map.put("WaterOrGas", getArguments().getString("WaterOrGas"));
            map.put("checkPerson", getArguments().getString("checkPerson"));
            myMap.setMap(map);
            return myMap;
        }else{
           // ToastUtils.showShortToast(mActivity, "联网情况填写错误！");
            return null;
        }

    }
    public List<QuestBean> getGasQues() {
        if(isContentOk()){
            //获取问题总汇
            List<QuestBean> questBeanList = new ArrayList<>();
            for (int i = 0; i < linearLayList.size(); i++) {
                QuestBean questBean = new QuestBean();
                LinearLayout lin = linearLayList.get(i);
                CustomEditText cet = (CustomEditText) lin.getChildAt(1);
                LogUtil.d(TAG, "问题" + (i + 1) + ":" + cet.getText().toString());
                ImageView iv = (ImageView) lin.getChildAt(2);
                questBean.setQuestionCode(String.valueOf(iv.getId()));
                questBean.setQuestionContent(cet.getText().toString());

                File file = (File) SharedPreferenceUtil.getObject(String.valueOf(iv.getId()), mActivity);
                if (file != null) {
                    LogUtil.d(TAG, "文件" + (i + 1) + ":" + file.toString());
                    questBean.setQuestionFile(file);
                } else {
                    LogUtil.d(TAG, "文件" + (i + 1) + ":null");
                }
                questBeanList.add(questBean);
            }
            return questBeanList;
        }else{
            return null;
        }
    }

    @NonNull
    private Intent setData() {
        Intent intent = new Intent();
        intent.setClass(mActivity, SignConfirmActivity.class);
        HashMap map = new HashMap();
        SerializableHashMap myMap = new SerializableHashMap();
        //获取自动监控设备安装情况
        map.put("installTotalNumber", totalNumInstall.getText().toString());
        map.put("rawGasPointNumber", originalGasPoint.getText().toString());
        map.put("rawGasPointNetworking", String.valueOf(cbOriginalGasOn.isChecked()));
        map.put("netGasPointNumber", cleanGasPoint.getText().toString());
        map.put("netGasPointNetworking", String.valueOf(cbCleanGasOn.isChecked()));
        map.put("denitrationPointNumber", denitratePoint.getText().toString());
        map.put("denitrationPointNetworking", String.valueOf(cbDenitratePointOn.isChecked()));
        map.put("otherPointNumber", otherPoint.getText().toString());
        map.put("otherPointNetworking", String.valueOf(cbOtherPointOn.isChecked()));
        //获取自动监控设备基本情况
        map.put("devicePointName", etPointName.getText().toString());
        map.put("deviceInstallTime", etInstallTime.getText().toString());
        map.put("deviceBrand", etFacilityType.getText().toString());
        map.put("deviceAcceptanceTime", etAccept.getText().toString());
        map.put("deviceOperationUnit", etUnit.getText().toString());
        map.put("deviceLastValidityAuditTime", etLastTime.getText().toString());
        //获取设备运行参数记录表
        map.put("outletSizeDeclareRegisterValue", et11.getText().toString());
        map.put("outletSizeSceneCheckValue", et12.getText().toString());
        map.put("excessAirFactorDeclareRegisterValue", et21.getText().toString());
        map.put("excessAirFactorSceneCheckValue", et22.getText().toString());
        map.put("correctDeclareRegisterValue", et31.getText().toString());
        map.put("correctCheckValue", et32.getText().toString());
        map.put("velocityFieldDeclareRegisterValue", et41.getText().toString());
        map.put("velocityFieldCheckValue", et42.getText().toString());
        map.put("pitotDeclareRegisterValue", et51.getText().toString());
        map.put("pitotCheckValue", et52.getText().toString());

        map.put("taskProgressId", getArguments().getString("taskProgressId"));
        map.put("pollSourceId", getArguments().getString("pollId"));
        map.put("pollTypeCode", getArguments().getString("pollTypeCode"));
        map.put("industryTypeCode", getArguments().getString("industryTypeCode"));
        map.put("WaterOrGas", getArguments().getString("WaterOrGas"));
        map.put("checkPerson", getArguments().getString("checkPerson"));
        myMap.setMap(map);
        intent.putExtra("gasMap", myMap);
        intent.putExtra("checkPerson",getArguments().getString("checkPerson"));
        //获取问题总汇
        List<QuestBean> questBeanList = new ArrayList<>();
        for (int i = 0; i < linearLayList.size(); i++) {
            QuestBean questBean = new QuestBean();
            LinearLayout lin = linearLayList.get(i);
            CustomEditText cet = (CustomEditText) lin.getChildAt(1);
            LogUtil.d(TAG, "问题" + (i + 1) + ":" + cet.getText().toString());
            questBean.setQuestionCode(String.valueOf(i));
            questBean.setQuestionContent(cet.getText().toString());
            ImageView iv = (ImageView) lin.getChildAt(2);
            File file = (File) SharedPreferenceUtil.getObject(String.valueOf(iv.getId()), mActivity);
            if (file != null) {
                LogUtil.d(TAG, "文件" + (i + 1) + ":" + file.toString());
                questBean.setQuestionFile(file);
            } else {
                LogUtil.d(TAG, "文件" + (i + 1) + ":null");
            }
            questBeanList.add(questBean);
        }
        intent.putExtra("gasQuestionList", (Serializable) questBeanList);
        return intent;
    }


    @OnClick({R.id.btn_add_gas_question, R.id.btn_gas_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_gas_question:
                if (imageId < 10) {
                    addQuestionView("",null);
                } else {
                    ToastUtils.showShortToast(mActivity, "超过最大问题数！！");
                }

                break;
            case R.id.btn_gas_sign:
                if (isContentOk()) {
                    getContent();
                } else {
                    ToastUtils.showShortToast(mActivity, "联网情况填写错误！");
                }
                break;
        }
    }

    private boolean isContentOk() {
        if (cbOriginalGasOn.isChecked() == cbOriginalGasOff.isChecked()) {
            return false;
        }
        if (cbCleanGasOn.isChecked() == cbCleanGasOff.isChecked()) {
            return false;
        }
        if (cbDenitratePointOn.isChecked() == cbDenitratePointOff.isChecked()) {
            return false;
        }
        if (cbOtherPointOn.isChecked() == cbOtherPointOff.isChecked()) {
            return false;
        }
        return true;
    }

    /**
     * 文件选择器
     */
    private Uri uri;
    private File file;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            file = null;
            if (uri != null) {
                if (uri.getScheme().toString().compareTo("content") == 0) {
                    String[] proj = {MediaStore.Images.Media.DATA};
                    String path = null;
                    Cursor cursor = mActivity.getContentResolver().query(uri, proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int actual_image_column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        path = cursor.getString(actual_image_column_index);
                    }
                    cursor.close();
                    if (path != null) {
                        file = new File(path);
                    } else {
                        ToastUtils.showShortToast(mActivity, "路径无效！请从文件管理器中选择文件!");
                    }

                } else if (uri.getScheme().compareTo("file") == 0) {
                    String path = uri.getPath();
                    if (path != null) {
                        file = new File(path);
                        SharedPreferenceUtil.saveObject(String.valueOf(requestCode), mActivity, file);
                        LogUtil.d(TAG, "path:" + path);
                        ToastUtils.showShortToast(mActivity, "已添加  " + path.substring(path.lastIndexOf("/") + 1) + "文件。");
                    } else {
                        ToastUtils.showShortToast(mActivity, "路径无效！请从文件管理器中选择文件!");
                    }
                } else {
                    LogUtil.d(TAG, "检测不到文件");
                }

            }

        }

    }

}
