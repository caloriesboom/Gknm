package gdg.com.gknm.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * 提示工具类
 * Created by pau on 15/7/8.
 */
public class UIUtil {
    public static void toast(final Context context, final String msg) {

        new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 进度条dialog
     *
     * @param context
     * @param tips
     * @return
     */
    public static ProgressDialog initDialog(Context context, String tips) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(tips);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    /**
     * 进度条dialog
     *
     * @param context
     * @return
     */
    public static ProgressDialog initDialog2(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("正在加载中");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public static void cancleDialog(ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void cancleDialogToast(Context context,ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        ToastUtils.showShortToast(context, "请求失败");
    }
}