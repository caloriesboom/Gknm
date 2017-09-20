package gdg.com.gknm.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具
 * Created by yangle on 2016/11/29.
 */
public class CheckOutUtils {


    /**
     * 校验手机
     */
    public static boolean checkPhone(Context context, String phone) {

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShortToast(context, "手机号不能为空");
            return false;
        } else if (!checkPhoneFormat(phone)) {
            ToastUtils.showShortToast(context, "手机号格式不正确");
            return false;
        }
        return true;
    }

    /**
     * 校验短信验证码
     */
    public static boolean checkSmsCode(Context context, String code) {

        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(context, "验证码不能为空");
            return false;
        }
       /* if ((!code.equals(SharedPreferenceUtil.get(REGIS_CODE, "")))) {
            ToastUtils.showShortToast(context, "验证码不正确");
            return false;
        }*/
        return true;
    }

    /**
     * 校验密码
     */

    public static boolean checkPassWord(Context context, String password, String passwordAgain) {

        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast(context, "密码不能为空");
            return false;
        }

        if (!checkPasswordFormat(password)) {
            ToastUtils.showShortToast(context, "密码不合法");
            return false;
        }
        if ((passwordAgain.equals("") || !password.equals(passwordAgain))) {
            ToastUtils.showShortToast(context, "两次输入密码不一致");
            return false;

        }
        return true;

    }

    /**
     * 校验用户名
     */

    public static boolean checkUserName(Context context, String userName) {

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShortToast(context, "用户名不能为空");
            return false;
        }
        if (!checkUserNameFormat(userName)) {
            ToastUtils.showShortToast(context, "用户名不合法");
            return false;
        }
        return true;

    }

    /**
     * 用户名校验
     *
     * @param userName
     * @return
     */
    public static boolean checkUserNameFormat(String userName) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[\\u4E00-\\u9FA50-9a-zA-Z]{4,20}$");
        m = p.matcher(userName);
        b = m.matches();
        return b;
    }

    /**
     * 手机号校验
     *
     * @param mobiles
     * @return
     */
    public static boolean checkPhoneFormat(String mobiles) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobiles);
        b = m.matches();
        return b;
    }

    /**
     * 密码校验
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordFormat(String password) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[0-9a-zA-Z]{6,20}$"); // 验证手机号
        m = p.matcher(password);
        b = m.matches();
        return b;
    }

    /**
     * 校验空值
     *
     * @param context
     * @param msg
     */
    public static boolean checkNullInfo(Context context, String info, String msg) {
        if (TextUtils.isEmpty(info)) {
            ToastUtils.showShortToast(context, msg + "不能为空");
            return false;
        }
        return true;
    }

    /**
     * 校验空值
     *
     * @param context
     * @param msg
     */
    public static void checkNullIndex(Context context, String info, String msg) {
        if (TextUtils.isEmpty(info)) {
            ToastUtils.showShortToast(context, msg + "不能为空");
            return ;
        }
    }

    /**
     * 校验集合空值
     *
     * @param context
     * @param msg
     */
    public static boolean checkListInfo(Context context, List<String> list, String msg) {
        if (list.isEmpty() && list.size() <= 0) {
            ToastUtils.showShortToast(context, msg + "不能为空");
            return false;
        }
        return true;
    }
}
