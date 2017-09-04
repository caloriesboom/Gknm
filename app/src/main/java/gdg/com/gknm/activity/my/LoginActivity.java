package gdg.com.gknm.activity.my;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import gdg.com.gknm.R;
import gdg.com.gknm.base.BaseAcivity;

public class LoginActivity extends BaseAcivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Log.d("LoginActivity", "slkdjflksjdlfkj");
    }
}
