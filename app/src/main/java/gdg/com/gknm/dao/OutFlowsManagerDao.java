package gdg.com.gknm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gdg.com.gknm.bean.AttentionOutFlowBean;
import gdg.com.gknm.dbHelper.DatabaseHelper;

/**
 * Created by GUO.DG on 15/6/8.
 * 
 */
public class OutFlowsManagerDao {
    private static final String TAG = "OutFlowsManagerDao";

    private DatabaseHelper dbHelper;

    private static OutFlowsManagerDao instance;

    public OutFlowsManagerDao(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public static OutFlowsManagerDao getInstance(Context context) {
        if (null == instance) {
            instance = new OutFlowsManagerDao(context);
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
     * 插入一条排口
     *
     * @param outFlow
     */
    public synchronized void insertOutFlow(final AttentionOutFlowBean.ResultEntityBean outFlow) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + " where "
                + DatabaseHelper.COLUMN_OUT_FLOW_ID + "='" + outFlow.getPollOutId() + "';";
        String insertSql = "insert into " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + " values(?,?,?);";
        Cursor cursor = db.rawQuery(querySql, null);
        if (cursor.getCount() != 0) {
        //如果有数据
            Log.d(TAG, "getcount" + cursor.getCount() + "已有数据不需要插入");
            cursor.close();
            db.close();
            return;
        } else {
            db.execSQL(insertSql, new Object[]{outFlow.getPollOutId(), outFlow.getPollOutName(),outFlow.getPollSourceId()});
            Log.d(TAG, "insert" + outFlow.getPollOutName());
            cursor.close();
            db.close();
        }

    }

    /**
     * 删除一条企业信息
     *
     * @param outFlow
     */
    public void deleteoutFlow(final AttentionOutFlowBean.ResultEntityBean outFlow) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + " where "
                + DatabaseHelper.COLUMN_OUT_FLOW_ID + "=" + outFlow.getPollOutId() + ";";
        String deleteSql = "delete from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + " where "
                + DatabaseHelper.COLUMN_OUT_FLOW_ID + "=" + outFlow.getPollOutId() + ";";
        Log.d(TAG, outFlow.getPollOutName());
        db.execSQL(deleteSql);
        db.close();
    }


    /**
     * 已添加的企业列表
     *
     * @return
     */
    public synchronized HashSet<String> queryoutFlowIds() {
        HashSet<String> outFlowIds = new HashSet<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            outFlowIds.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_FLOW_ID)));
        }
        cursor.close();
        db.close();
        return outFlowIds;
    }

    /**
     * 已添加的企业列表id
     *
     * @return
     */
    public synchronized String queryStroutFlowIds() {
        StringBuffer idBuffer = new StringBuffer();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            idBuffer.append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_FLOW_ID)) + ",");
        }
        cursor.close();
        db.close();
        String ids = "";
        if (idBuffer.length() > 0) {
            ids = idBuffer.substring(0, idBuffer.length() - 1);
        }
        Log.d(TAG, "ids==" + ids);
        return ids;
    }


    /**
     * 获得全部排口
     *
     * @return
     */
    public synchronized List<AttentionOutFlowBean.ResultEntityBean> getAllFactory() {
        List<AttentionOutFlowBean.ResultEntityBean> list = new ArrayList<AttentionOutFlowBean.ResultEntityBean>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO;
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            AttentionOutFlowBean.ResultEntityBean outFlow = new AttentionOutFlowBean.ResultEntityBean();
            outFlow.setPollOutName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POLL_NAME)));
            outFlow.setPollOutId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_FLOW_ID)));
            list.add(outFlow);
        }
        cursor.close();
        db.close();
        return list;
    }
    /**
     * 根据pollId获得关注的排口
     *
     * @param pollId
     * @return
     */
    public List<AttentionOutFlowBean.ResultEntityBean> findByPollId(String pollId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select * from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + " where " + DatabaseHelper.COLUMN_OUT_FLOW_POLL_ID + "=" + pollId;
        List<AttentionOutFlowBean.ResultEntityBean> list = new ArrayList<AttentionOutFlowBean.ResultEntityBean>();
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            AttentionOutFlowBean.ResultEntityBean outFlow = new AttentionOutFlowBean.ResultEntityBean();
            outFlow.setPollOutName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_FLOW_NAME)));
            outFlow.setPollOutId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OUT_FLOW_ID)));
            list.add(outFlow);
        }
        return list;
    }

    /**
     * 删除全部排口
     */
    public void deleteAllFlow() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String deleteSql = "delete from " + DatabaseHelper.TABLE_ATTENTION_OUT_FLOW_INFO + ";";
        db.execSQL(deleteSql);
        // db.delete(DatabaseHelper.TABLE_POLLS,null,null);
        db.close();
    }
}
