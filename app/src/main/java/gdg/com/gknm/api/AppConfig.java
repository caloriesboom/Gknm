package gdg.com.gknm.api;


/**
 * @author zjh
 * @date 2016/9/6
 */
public class AppConfig {
    public static final boolean DEBUG = true;

//    public static final String HOST_BASE_IP_DEBUG = "192.168.3.223";
//    public static final String HOST_BASE_PORT_DEBUG = ":8080";

//    public static final String HOST_BASE_IP_DEBUG = "192.168.2.205";
//    public static final String HOST_BASE_PORT_DEBUG = ":8100";

//    public static final String HOST_BASE_IP_DEBUG = "10.15.96.205";
//    public static final String HOST_BASE_PORT_DEBUG = ":9080";

//    public static String HOST_BASE_IP_DEBUG = SharedPreferenceUtil.get(ConstantIndex.SP_URL_IP,"192.168.2.205");
//    public static String HOST_BASE_PORT_DEBUG = SharedPreferenceUtil.get(ConstantIndex.SP_URL_PORT,":8180");

    public static final String HOST_BASE_IP_DEBUG = "192.168.3.220";
    public static final String HOST_BASE_PORT_DEBUG = ":8080";

//    public static final String HOST_BASE_IP_DEBUG = "192.168.3.148";
//    public static final String HOST_BASE_PORT_DEBUG = ":8080";

    public static String HOST_BASE_URL = "http://" + HOST_BASE_IP_DEBUG;
    public static String API_BASE_URL = HOST_BASE_URL + HOST_BASE_PORT_DEBUG;
   // public static String API_BASE_URL = "http://"+SharedPreferenceUtil.get("base_url","");;


}
