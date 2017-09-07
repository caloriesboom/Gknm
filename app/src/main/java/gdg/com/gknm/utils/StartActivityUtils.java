package gdg.com.gknm.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ${Tom} on 2017/6/14.
 *
 */

public class StartActivityUtils {

    public static void startActivityNone(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        packageContext.startActivity(intent);
    }

    public static void startActivityOne(Context packageContext, Class<?> cls, String tag1) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("tag1", tag1);
        packageContext.startActivity(intent);
    }

    public static void startActivityTwo(Context packageContext, Class<?> cls, String tag1, String tag2) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("tag1", tag1);
        intent.putExtra("tag2", tag2);
        packageContext.startActivity(intent);
    }

    public static void startActivityThree(Context packageContext, Class<?> cls, String tag1, String tag2, String tag3) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("tag1", tag1);
        intent.putExtra("tag2", tag2);
        intent.putExtra("tag3", tag3);
        packageContext.startActivity(intent);
    }

}
