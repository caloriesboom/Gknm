package gdg.com.gknm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.dbHelper.DatabaseHelper;

/**
 * Created by pau on 15/6/8.
 * 关注的企业dao
 */
public class EnterpriseManagerDao {
    private static final String TAG = "EnterpriseManagerDao";

    private DatabaseHelper dbHelper;

    private static EnterpriseManagerDao instance;

    public EnterpriseManagerDao(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public static EnterpriseManagerDao getInstance(Context context) {
        if (null == instance) {
            instance = new EnterpriseManagerDao(context);
        }
        return instance;
    }

    /**
     * 断开数据库
     */
    public void close() {
        dbHelper.close();
    }


    /**
     * 插入一条企业信息
     *
     * @param enterprise
     */
    public synchronized void insertEnterprise(final AttentionPollBean.ResultEntityBean enterprise) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO + " where "
                + DatabaseHelper.COLUMN_POLL_ID + "='" + enterprise.getPollSourceId() + "';";
        String insertSql = "insert into " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO + " values(?,?);";
        Cursor cursor = db.rawQuery(querySql, null);
        if (cursor.getCount() != 0) {//如果有数据
            Log.e(TAG, "getcount" + cursor.getCount() + "已有数据不需要插入");
            cursor.close();
            db.close();
            return;
        } else {
            db.execSQL(insertSql, new Object[]{enterprise.getPollSourceId(), enterprise.getPollSourceName()});
            Log.e(TAG, "insert" + enterprise.getPollSourceName());
            cursor.close();
            db.close();
        }

    }

    /**
     * 删除一条企业信息
     *
     * @param enterprise
     */
    public void deleteEnterprise(final AttentionPollBean.ResultEntityBean enterprise) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO + " where "
                + DatabaseHelper.COLUMN_POLL_ID + "=" + enterprise.getPollSourceId() + ";";
        String deleteSql = "delete from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO + " where "
                + DatabaseHelper.COLUMN_POLL_ID + "=" + enterprise.getPollSourceId() + ";";
        Log.e(TAG, enterprise.getPollSourceName());
        db.execSQL(deleteSql);
        db.close();
    }

    /**
     * 已添加的企业列表
     *
     * @return
     */
    public synchronized HashSet<String> queryEnterpriseIds() {
        HashSet<String> enterpriseIds = new HashSet<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            enterpriseIds.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POLL_ID)));
        }
        cursor.close();
        db.close();
        return enterpriseIds;
    }




    /**
     * 获得全部企业
     * @return
     */
    public synchronized List<AttentionPollBean.ResultEntityBean> getAllFactory() {
        List<AttentionPollBean.ResultEntityBean> list = new ArrayList<AttentionPollBean.ResultEntityBean>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            AttentionPollBean.ResultEntityBean enterprise = new AttentionPollBean.ResultEntityBean();
            enterprise.setPollSourceName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POLL_NAME)));
            enterprise.setPollSourceId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POLL_ID)));
            list.add(enterprise);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 已添加的企业列表id
     *
     * @return
     */
    public synchronized String queryStrEnterpriseIds() {
        StringBuffer idBuffer = new StringBuffer();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_ENTERPRISE_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            idBuffer.append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POLL_ID)) + ",");
        }
        cursor.close();
        db.close();
        String ids = "";
        if (idBuffer.length() > 0) {
            ids = idBuffer.substring(0, idBuffer.length() - 1);
        }

        return ids;
    }
}
