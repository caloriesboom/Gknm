package gdg.com.gknm.api;


import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import gdg.com.gknm.bean.BaseResult;
import gdg.com.gknm.utils.LogUtil;
import rx.Observer;

/**
 * 网络请求返回需要的模型
 * Created by ice on 3/3/16.
 */
public abstract class BaseObserver<T extends BaseResult> implements Observer<T> {
    private Context mContext;

    public BaseObserver(@NonNull Context context) {
        this.mContext = context;
    }

    protected abstract void onSucceed(T result);

    protected void onFailed(String msg) {
        if (!TextUtils.isEmpty(msg))
            LogUtil.d(msg);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        LogUtil.d("异常抛出：" + e.toString());
        if (e instanceof UnknownHostException || e instanceof ConnectException || (e.toString().contains("404"))) {
            onFailed("网络无法连接");
        } else if (e instanceof SocketTimeoutException) {
            onFailed("网络访问超时");
        } else if (e instanceof JsonSyntaxException) {
            onFailed("响应数据格式错误");
        } else if (e.toString().contains("Attempt")) {
            onFailed("解析文件出错");
        } else {
            onFailed("未知错误：" + e.getLocalizedMessage());
        }
    }

    @Override
    public void onNext(T result) {
        if (result != null)
            onSucceed(result);
        else
            onFailed(null);
    }

}
