package gdg.com.gknm.activity.my;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.base.BaseActivity;
import gdg.com.gknm.bean.QuestionBean;
import gdg.com.gknm.bean.SubmitBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.UIUtil;
import gdg.com.gknm.weight.CustomActionBar;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceLeaveMessageActivity extends BaseActivity {
    @Bind(R.id.custom_action_bar)
    CustomActionBar mActionBar;
    @Bind(R.id.et_type_lv)
    ListView et_type_lv;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_contact)
    EditText etContact;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    private ProgressDialog progressDialog;//进度条
    List<QuestionBean.ResultEntityBean> listType = new ArrayList<QuestionBean.ResultEntityBean>();
    private String questionContent = "";
    private String phone = "";
    private String selectTypeId="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_leave_message);
        ButterKnife.bind(this);
        initActionBar();
        initData();
        sendQuest();
    }

    private void initData() {
        et_type_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectTypeId =listType.get(et_type_lv.getCheckedItemPosition()).getDicCode();
            }
        });
    }

    private void initActionBar() {
        mActionBar.setLeftImageClickListener(this);
        progressDialog = UIUtil.initDialog(ServiceLeaveMessageActivity.this, "正在加载，请稍后...");

    }

    private void sendQuest() {
        mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).getQuestion("WTLX")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<QuestionBean>(this) {
                    @Override
                    protected void onSucceed(QuestionBean result) {
                        listType = result.getResultEntity();
                        List<String> typename = new ArrayList<>();
                        for (int i = 0; i < listType.size(); i++) {
                            typename.add(listType.get(i).getDicName());
                        }
                        et_type_lv.setAdapter(new ArrayAdapter<String>(ServiceLeaveMessageActivity.this, android.R.layout.simple_list_item_single_choice, typename));
                        et_type_lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//如果不使用这个设置，选项中的radiobutton无法响应选中事件
                        et_type_lv.setItemChecked(0, true);
                    }

                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                    }
                });
    }

    @OnClick(R.id.btn_submit)
    public void onClick() {
        questionContent = etContent.getText().toString();
        phone = etContact.getText().toString();
        selectTypeId =listType.get(et_type_lv.getCheckedItemPosition()).getDicCode();

        if(!TextUtils.isEmpty(questionContent)){
            if(!TextUtils.isEmpty(phone)){
                mSubscription = RetorfitManager.getInstance().createReq(ApiService.class).subMitQuest(selectTypeId,questionContent,phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<SubmitBean>(this) {
                            @Override
                            protected void onSucceed(SubmitBean result) {
                                if(result.isResultCode()){
                                    etContent.setText("");
                                    etContact.setText("");
                                    ToastUtils.showShortToast(ServiceLeaveMessageActivity.this,result.getMessage());
                                }

                            }

                            @Override
                            protected void onFailed(String msg) {
                                super.onFailed(msg);
                            }
                        });
            }else{
                ToastUtils.showShortToast(this,"联系方式不能为空");
            }

        }else{
            ToastUtils.showShortToast(this,"内容不能为空");
        }


    }
}
