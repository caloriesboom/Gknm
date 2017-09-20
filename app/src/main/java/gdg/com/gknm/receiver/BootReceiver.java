package gdg.com.gknm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import gdg.com.gknm.service.MessagePushService;
import gdg.com.gknm.utils.SharedPreferenceUtil;


/**
 * 开机启动服务广播
 * Created by ${YeJun} on 2016/4/14.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("广播开启了");
        Intent intent1= new Intent(context, MessagePushService.class);
        intent.putExtra("time", SharedPreferenceUtil.get("Time", "60"));
        context.startService(intent1);//开启服务
        System.out.println("开启了服务gb");

    }
}
