package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.base.MyApplication;
import gdg.com.gknm.service.MessagePushService;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.weight.CustomActionBar;
import gdg.com.gknm.weight.CustomSwitchButton;

public class MyPushSettingsActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.push_real_time)
    CustomSwitchButton realTime;
    @Bind(R.id.push_fixed_time)
    CustomSwitchButton fixedTime;
    @Bind(R.id.spinner_push_fixed)
    Spinner spinner;
    @Bind(R.id.push_no)
    CustomSwitchButton noPush;
    @Bind(R.id.check_push_setting)
    CheckBox checkBox;
    private String second = "60";//实时推送时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_push_settings);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        initData();
    }

    private void initData() {
        //使用数组作为数据源
        final Integer arr[] = new Integer[]{2, 12, 24};
        // adpater对象
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(Integer.parseInt(SharedPreferenceUtil.get("persion", "0")), true);

    }

    private void initView() {
        realTime.updateSwitchState(false);

        //true 是推送，false是不推送
        if (SharedPreferenceUtil.get("Time", "60").equals(second)) {
            realTime.updateSwitchState(true);
            fixedTime.updateSwitchState(false);

        } else {
            fixedTime.updateSwitchState(true);
            realTime.updateSwitchState(false);
        }

        if (SharedPreferenceUtil.get("noPush", "true").equals("true")) {
            checkBox.setChecked(true);
            noPush.updateSwitchState(false);
        } else {
            checkBox.setChecked(false);
            noPush.updateSwitchState(true);
        }

        if (MyApplication.mIsLogin) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (fixedTime.getSwitchState()) {//当定时推送打开时
                        SharedPreferenceUtil.save("Time", Integer.parseInt(String.valueOf(spinner.getSelectedItem())) * 60 + "");
                        SharedPreferenceUtil.save("persion", spinner.getSelectedItemPosition() + "");
                        realTime.updateSwitchState(false);
                    } else {
                        SharedPreferenceUtil.save("Time", second);
                        realTime.updateSwitchState(true);
                    }

                    Intent intent = new Intent(MyPushSettingsActivity.this, MessagePushService.class);
                    MyPushSettingsActivity.this.stopService(intent);//关闭服务
                    intent.putExtra("flag", SharedPreferenceUtil.getBoolean("Flag",true));
                    intent.putExtra("time", SharedPreferenceUtil.get("Time", "60"));
                    MyPushSettingsActivity.this.startService(intent);//开启服务
                    System.out.println("开启了服务1");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            fixedTime.setOnUISwitchDelegate(new CustomSwitchButton.OnUISwitchDelegate() {
                @Override
                public void onUISwitchDelegate(boolean isSwitchOn) {
                    if (fixedTime.getSwitchState()) {
                        SharedPreferenceUtil.save("Time", Integer.parseInt(String.valueOf(spinner.getSelectedItem())) * 60 + "");
                        SharedPreferenceUtil.save("persion", spinner.getSelectedItemPosition() + "");
                        realTime.updateSwitchState(false);
                    } else {
                        SharedPreferenceUtil.save("Time", second);
                        realTime.updateSwitchState(true);
                    }
                    Intent intent = new Intent(MyPushSettingsActivity.this, MessagePushService.class);

                    MyPushSettingsActivity.this.stopService(intent);//关闭服务

                    intent.putExtra("time", SharedPreferenceUtil.get("Time", "60"));
                    intent.putExtra("flag", SharedPreferenceUtil.getBoolean("Flag",true));
                    MyPushSettingsActivity.this.startService(intent);//开启服务
                    System.out.println("开启了服务２");
                }
            });

            //实时推送开关的监听
            realTime.setOnUISwitchDelegate(new CustomSwitchButton.OnUISwitchDelegate() {
                @Override
                public void onUISwitchDelegate(boolean isSwitchOn) {
                    if (realTime.getSwitchState()) {
                        SharedPreferenceUtil.save("Time", second);
                        fixedTime.updateSwitchState(false);
                    } else {
                        SharedPreferenceUtil.save("Time", Integer.parseInt(String.valueOf(spinner.getSelectedItem())) * 60 + "");
                        fixedTime.updateSwitchState(true);
                    }

                    Intent intent = new Intent(MyPushSettingsActivity.this, MessagePushService.class);
                    MyPushSettingsActivity.this.stopService(intent);//关闭服务
                    intent.putExtra("time", SharedPreferenceUtil.get("Time", "60"));
                    intent.putExtra("flag", SharedPreferenceUtil.getBoolean("Flag",true));
                    MyPushSettingsActivity.this.startService(intent);//开启服务
                    System.out.println("开启了服务３");
                }
            });

            noPush.setOnUISwitchDelegate(new CustomSwitchButton.OnUISwitchDelegate() {
                @Override
                public void onUISwitchDelegate(boolean isSwitchOn) {
                    if (noPush.getSwitchState()) {

                        SharedPreferenceUtil.saveBoolean("Flag", false);//不推送消息
                        SharedPreferenceUtil.save("noPush", "false");

                    } else {

                        SharedPreferenceUtil.saveBoolean("Flag", true);//推送
                        SharedPreferenceUtil.save("noPush", "true");
                    }
                    System.out.println("设置中不推送消息："+SharedPreferenceUtil.getBoolean("Flag",true));
                }
            });
        }
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);

    }
}
