/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gdg.com.gknm.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import gdg.com.gknm.R;
import gdg.com.gknm.weight.CustomEditText;

import static gdg.com.gknm.utils.ContextUtil.context;


/**
 * 对应继承自AdapterView的Layout来说,每一个Item需要显示的高度是由minHight决定的，默认只是布局高度，
 * 如果要调整item的显示高度则需要调用scale()方法，得到缩放值，然后在getView()中手动设置.
 * 没一个item内部的布局需要调用scaleContentView()设置一次. 名称：YjViewUtil.java 描述：View工具类.
 */
public class YjViewUtil {
    /**
     * 没有连接网络
     */
    public static final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    public static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    public static final int NETWORK_WIFI = 1;

    public static int getNetWorkState(Context context) {

        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }

    /**
     * 将dip转换为像素
     *
     * @param dipValue
     * @return
     */

    public static int dip2px2(float dipValue) {

        return dip2px2(context, dipValue);
    }

    /**
     * 将dip转换为像素
     *
     * @param dipValue
     * @return
     */
    public static int dip2px2(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 获取屏幕尺寸与密度.
     *
     * @param context the context
     * @return mDisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources mResources;
        if (context == null) {
            mResources = Resources.getSystem();

        } else {
            mResources = context.getResources();
        }
        // DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5,
        // xdpi=160.421, ydpi=159.497}
        // DisplayMetrics{density=2.0, width=720, height=1280,
        // scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
        return mDisplayMetrics;
    }

    /**
     * 中文转码
     * utf8
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        if (content != null) {
            try {
                return URLEncoder.encode(content, "utf-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            return "";
        }
        return content;
    }

    /**
     * 格式化文件大小
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }


    public static double formatDouble2(double d) {
        // 旧方法，已经不再推荐使用
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }


    public static void setContact(Activity context, CustomEditText contactName, CustomEditText contactNum, Intent data) {
        ContentResolver reContentResolverol = context.getContentResolver();
        Uri contactData = data.getData();
        @SuppressWarnings("deprecation")
        Cursor cursor = context.managedQuery(contactData, null, null, null, null);
        cursor.moveToFirst();
        String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                null,
                null);
        while (phone.moveToNext()) {
            String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (substring(usernumber, 0, 3).equals("+86")) {
                contactName.setText(username);
                contactNum.setText(substring(usernumber, 3));
            } else {
                contactName.setText(username);
                contactNum.setText(usernumber);
            }

            if (cursor != null) {
                cursor.close();

            }

        }


    }

    /**
     * 截取字符串
     *
     * @param s
     * @param from
     * @return
     */
    protected static String substring(String s, int from) {
        try {
            return s.substring(from);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected static String substring(String s, int from, int len) {
        try {
            return s.substring(from, from + len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 灵活取值
     **/
    public static void workByKeySetEntry(Map<String, String> map) {
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            LogUtil.d("key：" + key + "，value：" + val);
        }
    }


    /**
     * MD5加密字符串
     *
     * @param string
     * @return
     */

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static void hideLayout(final LinearLayout linearView, boolean flag) {
        int startHeight;
        int targetHeight;
        if (!flag) {    //  展开的动画
            startHeight = 0;
            targetHeight = getMeasureHeight(linearView);
            flag = true;
            linearView.getMeasuredHeight();  //  0
        } else {
            flag = false;
            startHeight = getMeasureHeight(linearView);
            targetHeight = 0;
        }
        // 值动画
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, targetHeight);
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearView.getLayoutParams();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  // 监听值的变化
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int value = (Integer) animator.getAnimatedValue();// 运行当前时间点的一个值
                layoutParams.height = value;
                linearView.setLayoutParams(layoutParams);// 刷新界面

            }
        });
        animator.setDuration(300);
        animator.start();
    }

    /**
     * 获取控件实际的高度
     */
    public static int getMeasureHeight(LinearLayout linearView) {
        int width = linearView.getMeasuredWidth();  //  由于宽度不会发生变化  宽度的值取出来
        linearView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;//  让高度包裹内容
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);  //  mode+size
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);// 我的高度 最大是1000
        // 测量规则 宽度是一个精确的值width, 高度最大是1000,以实际为准
        linearView.measure(widthMeasureSpec, heightMeasureSpec); // 通过该方法重新测量控件
        return linearView.getMeasuredHeight();
    }


    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断WIFI网络是否可以ping通
     */
    public static final boolean ping(Context context) {

        if (isWifiConnected(context)) {

            String result = null;
            try {
                String ip = "192.168.128.1";// ping 的地址，可以换成任何一种可靠的外网
                Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
                // 读取ping的内容，可以不加
                InputStream input = p.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                StringBuffer stringBuffer = new StringBuffer();
                String content = "";
                while ((content = in.readLine()) != null) {
                    stringBuffer.append(content);
                }
                Log.d("------ping-----", "result content : " + stringBuffer.toString());
                // ping的状态
                int status = p.waitFor();
                if (status == 0) {
                    result = "success";
                    return true;
                } else {
                    result = "failed";
                }
            } catch (IOException e) {
                result = "IOException";
            } catch (InterruptedException e) {
                result = "InterruptedException";
            } finally {
                Log.d("----result---", "result = " + result);
            }
            return false;

        }
        return false;


    }


    /**
     * 分享信息
     *
     * @param context
     * @param toShare
     */
    public static void shareInfo(Activity context, String toShare) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "选择分享方式");
        intent.putExtra(Intent.EXTRA_TEXT, toShare.trim());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        toActivity(context, intent, -1, true);
    }

    public static void toActivity(final Activity context, final Intent intent, final int requestCode, final boolean showAnimation) {
        if (context == null || intent == null) {
            return;
        }
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (requestCode < 0) {
                    context.startActivity(intent);
                } else {
                    context.startActivityForResult(intent, requestCode);
                }
                if (showAnimation) {
                    context.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                } else {
                    context.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                }
            }
        });
    }

    //Bitmap转换成byte[ ]:
    public static byte[] getBytes(Bitmap bitmap) {
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
    }

    //把byte[ ]转换回来Bitmap:
    public static Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }

    /**
     * 保存bitmap到SD卡
     * @param bitName 保存的名字
     * @param mBitmap 图片对像
     * return 生成压缩图片后的图片路径
     */
    public static String saveMyBitmap(String bitName,Bitmap mBitmap) {
        Bitmap outB = mBitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas=new Canvas(outB);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        String signSavePath = Environment.getExternalStorageDirectory()+"/SignFile/";
        File f1 = new File(signSavePath);
        if (!f1.exists()) {
            f1.mkdir();
        }
        String file_path = f1.getAbsolutePath()+"/"+bitName+".png";
        File f = new File(file_path);
        try {

            f.createNewFile();
        } catch (IOException e) {
            System.out.println("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            outB.compress(Bitmap.CompressFormat.PNG, 100, fOut);
           // mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        } catch (Exception e) {
            return "create_bitmap_error";
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return signSavePath + bitName + ".png";
    }
}
