package gdg.com.gknm.api;


/**
 * @author zjh
 * @date 2016/9/6
 */
public class AppConfig {
    public static final boolean DEBUG = true;

    public static final String HOST_BASE_IP_DEBUG = "192.168.3.223";
    public static final String HOST_BASE_PORT_DEBUG = ":8080";


    public static String HOST_BASE_URL = "http://" + HOST_BASE_IP_DEBUG;
    public static String API_BASE_URL = HOST_BASE_URL + HOST_BASE_PORT_DEBUG;


}
