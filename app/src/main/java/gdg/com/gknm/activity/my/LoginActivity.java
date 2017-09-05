package gdg.com.gknm.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.HomeActivity;
import gdg.com.gknm.R;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.check_box_remeber)
    CheckBox checkBoxRemeber;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.configuration)
    Button configuration;
    @Bind(R.id.tv_ip)
    TextView tvIp;
    @Bind(R.id.edit_configuration)
    EditText editConfiguration;
    @Bind(R.id.btn_configuration)
    Button btnConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.check_box_remeber, R.id.login, R.id.configuration, R.id.btn_configuration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_box_remeber:
                break;
            case R.id.login:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.configuration:
                break;
            case R.id.btn_configuration:
                break;
        }
    }
}
