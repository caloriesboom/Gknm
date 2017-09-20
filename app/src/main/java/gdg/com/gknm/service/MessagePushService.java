package gdg.com.gknm.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gdg.com.gknm.R;
import gdg.com.gknm.activity.my.MyMessageActivity;
import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.api.BaseObserver;
import gdg.com.gknm.bean.MonitorAlarmBean;
import gdg.com.gknm.network.RetorfitManager;
import gdg.com.gknm.utils.DateUtil;
import gdg.com.gknm.utils.LogUtil;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pau on 15/7/7.
 */
public class MessagePushService extends Service {
    private static final String TAG = "MessagePushService";
    //获取消息线程
    private MessageThread messageThread = null;
    public boolean isRunning = true;
    private String time = "1";
    //点击查看
    private Intent messageIntent = null;
    private PendingIntent messagePendingIntent = null;
    //通知栏消息
    private int messageNotificationID = 1000;
    private Notification messageNotification = null;
    private NotificationManager messageNotificationManager = null;
    private ScheduledExecutorService scheduledExecutorService;
    private String message = "";
    private String companyId = "";//企业id
    private String alarmTypeId = "";//异常类型id
    private String stateId = "";//处理状态id
    private String startTime = "";//开始时间
    private String stopTime = "";//结束时间
    boolean flag ;  //是否推送消息 true是推送 false是不推送

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "开启服务");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //服务开启式就调用此方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Log.d(TAG, "intent为空");
            time = SharedPreferenceUtil.get("Time", "10");
        } else {
            flag=intent.getBooleanExtra("flag", true);
            time = intent.getStringExtra("time");
        }
        Log.d(TAG, "onStartCommand" + time);
        Log.d(TAG, "是否推送消息：" + flag);
        //开启线程
        scheduled();




        return START_STICKY;
    }

    /**
     * 从服务端获取消息
     */
    class MessageThread implements Runnable {
        @Override
        public void run() {
            if (isRunning) {
                try {
                    //设置消息内容和标题
                    sendRequest();
                    Log.d(TAG, "十分钟一次");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
private int totalRecord=0;
    private void sendRequest() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTime = df.format(new Date());
        String day = currentTime.split(" ")[0];
        String time = currentTime.split(" ")[1];
        Log.d(TAG, "day==" + day + ";time==" + time);
        String beginDate = DateUtil.getCurrentDay()+"%00:00:00";
        String endDate = day+"%"+time;
        Log.d(TAG, "发送推送.....");
        RetorfitManager.getInstance().createReq(ApiService.class).getMonitorAlarmList("", beginDate, endDate,"3","",20,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MonitorAlarmBean>(this) {
                    @Override
                    protected void onSucceed(MonitorAlarmBean result) {
                        totalRecord = result.getResultEntity().getTotalRecord();
                        if(totalRecord>0){


                        LogUtil.d(TAG,"totalRecord=="+totalRecord);
                        List<MonitorAlarmBean.ResultEntityBean.DataBean> mList = result.getResultEntity().getData();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            messageIntent = new Intent(MessagePushService.this, MyMessageActivity.class);//点击跳进实时报警
                            messagePendingIntent = PendingIntent.getActivity(MessagePushService.this, 0, messageIntent, 0);
                            messageNotification = new Notification.Builder(MessagePushService.this)
                                    .setAutoCancel(true)
                                    .setContentTitle("新消息")
                                    .setContentText("您有"+totalRecord+"条新消息")
                                    .setContentIntent(messagePendingIntent)
                                    .setSmallIcon(R.mipmap.logo_message)
                                    .setWhen(System.currentTimeMillis())
                                    .build();
                            Log.d(TAG, "messageNotificationc初始化成功");

                        }
                        messageNotificationManager = (NotificationManager) getSystemService(MessagePushService.NOTIFICATION_SERVICE);
                        messageNotificationManager.notify(messageNotificationID, messageNotification);
                        //避免覆盖消息，采取ID自增
                        messageNotificationID++;
                        }
                    }
                    @Override
                    protected void onFailed(String msg) {
                        super.onFailed(msg);
                        LogUtil.d(TAG, msg);
                    }
                });

    }

    /**
     * 模拟了服务端的消息。实际应用中应该去服务器拿到message
     *
     * @return
     */
    public String getServerMessage() {
        return "yes";
    }

    public void scheduled() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new MessageThread(), 0, Long.parseLong("1"), TimeUnit.MINUTES);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scheduledExecutorService.shutdownNow();
        isRunning = false;
        Log.d(TAG, "停止服务");
    }
}
