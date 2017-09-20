package gdg.com.gknm.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * 用法
 * DatabaseHelper helper = DatabaseHelper.getInstance(this);
 * SQLiteDatabase db = helper.getWritableDatabase();
 * Created by pau on 15/5/26.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "gknm.db";//数据库名称
    public static final String PATH = android.os.Environment.getExternalStorageDirectory() + "/Environment/";
    private static final int DATABASE_VERSION = 6;

    //关注的企业表
    public static final String TABLE_ATTENTION_ENTERPRISE_INFO = "TABLE_ATTENTION_ENTERPRISE";//关注的企业表
    public static final String COLUMN_POLL_ID = "COLUMN_POLL_ID";//企业id
    public static final String COLUMN_POLL_NAME = "COLUMN_POLL_NAME";//企业名称

    //关注的排口表
    public static final String TABLE_ATTENTION_OUT_FLOW_INFO = "TABLE_ATTENTION_OUT_FLOW";//关注的排口表
    public static final String COLUMN_OUT_FLOW_ID = "COLUMN_OUT_FLOW_ID";//排口id
    public static final String COLUMN_OUT_FLOW_NAME = "COLUMN_OUT_FLOW_NAME";//排口名称
    public static final String COLUMN_OUT_FLOW_POLL_ID = "COLUMN_OUT_FLOW_POLL_ID";//排口企业名称

    //设施表
    public static final String TABLE_FACILITY_INFO = "TABLE_FACILITY_INFO";//设施表
    public static final String COLUMN_FACILITY_ID = "COLUMN_FACILITY_ID";//设施id
    public static final String COLUMN_FACILITY_NAME = "COLUMN_FACILITY_NAME";//设施名称
    public static final String COLUMN_FACILITY_POLL_ID = "COLUMN_FACILITY_POLL_ID";//设施名称

    // 标记的报警
    public static final String TABLE_SIGN_ALARM_INFO = "TABLE_SIGN_ALARM_INFO";//标记报警
    public static final String COLUMN_SIGN_ALARM_ID = "COLUMN_SIGN_ALARM_ID";//标记报警ID
    public static final String COLUMN_SIGN_ALARM_NAME = "COLUMN_SIGN_ALARM_NAME";//标记报警名称
    public static final String COLUMN_SIGN_ALARM_POLL_ID = "COLUMN_SIGN_ALARM_POLL_ID";//企业ID

    //已标记的报警
    public static final String TABLE_ALARM_SIGN_INFO = "TABLE_ALARM_SIGN_INFO";
    public static final String COLUMN_ALARM_SIGN_CODE = "COLUMN_ALARM_CODE"; //报警代码
    public static final String COLUMN_ALARM_SIGN_NAME = "COLUMN_ALARM_TYPE_NAME"; //报警类型名称
    //创建表
    private final String CREATE_TABLE_ATTENTION_ENTERPRISE_INFO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENTION_ENTERPRISE_INFO + " ("
                    + COLUMN_POLL_ID + " VARCHAR(20) NOT NULL,"
                    + COLUMN_POLL_NAME + " VARCHAR(20) NOT NULL);";


    private final String CREATE_TABLE_ATTENTION_OUT_FLOW_INFO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ATTENTION_OUT_FLOW_INFO + " ("
                    + COLUMN_OUT_FLOW_ID + " VARCHAR(20) NOT NULL,"
                    + COLUMN_OUT_FLOW_NAME + " VARCHAR(20) NOT NULL,"
                    + COLUMN_OUT_FLOW_POLL_ID + " VARCHAR(20) NOT NULL);";

    private final String CREATE_TABLE_ALARM_SIGN_INFO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ALARM_SIGN_INFO + " ("
                    + COLUMN_ALARM_SIGN_CODE + " VARCHAR(20) NOT NULL,"
                    + COLUMN_ALARM_SIGN_NAME + " VARCHAR(20) NOT NULL);";

    private final String CREATE_TABLE_FACILITY_INFO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_FACILITY_INFO + " ("
                    + COLUMN_FACILITY_ID + " VARCHAR(20) NOT NULL,"
                    + COLUMN_FACILITY_NAME + " VARCHAR(20) NOT NULL,"
                    + COLUMN_FACILITY_POLL_ID + " VARCHAR(20) NOT NULL);";

    private final String CREATE_TABLE_SIGN_ALARM_INFO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SIGN_ALARM_INFO + " ("
                    + COLUMN_SIGN_ALARM_ID + " VARCHAR(20) NOT NULL,"
                    + COLUMN_SIGN_ALARM_NAME + " VARCHAR(20) NOT NULL,"
                    + COLUMN_SIGN_ALARM_POLL_ID + " VARCHAR(20) NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取数dbhelper
     */
    public static DatabaseHelper getInstance(Context context) {
        if (null == instance) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }
    public static int getVersion() {
        return DATABASE_VERSION;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ATTENTION_ENTERPRISE_INFO);
        db.execSQL(CREATE_TABLE_ATTENTION_OUT_FLOW_INFO);
        db.execSQL(CREATE_TABLE_ALARM_SIGN_INFO);
        db.execSQL(CREATE_TABLE_FACILITY_INFO);
        db.execSQL(CREATE_TABLE_SIGN_ALARM_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENTION_ENTERPRISE_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENTION_OUT_FLOW_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM_SIGN_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACILITY_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGN_ALARM_INFO);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //每次成功打开数据库后首先被执行
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /**
         执行数据库的降级操作
         1、只有新版本比旧版本低的时候才会执行
         2、如果不执行降级操作，会抛出异常
         */
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

}
