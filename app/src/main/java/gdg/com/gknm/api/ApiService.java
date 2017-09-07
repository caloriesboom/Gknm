package gdg.com.gknm.api;

import gdg.com.gknm.bean.PollInfoBean;
import gdg.com.gknm.bean.TaskListBean;
import gdg.com.gknm.bean.TestBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;



/**
 * Created by GUO.DG on 2017-9-6.
 *  @Path - 替换参数
 *  @Query - 添加查询参数
 *  @Body请求体（RequestBody），一般用于POST方法传递请求参数。
 */

public interface ApiService {
    //监督检查——任务列表：
    // http://192.168.3.223:8080/edds/phoneHandleController/findTaskManagementPage.do?start=0&limit=10&userId=10044
    @GET("/edds/phoneHandleController/findTaskManagementPage.do")
    Observable<TaskListBean> getTaskList(
            @Query("userId") String userId,
            @Query("start") String start,
            @Query("limit") String limit
    );

    //监督检查——企业信息：
    // http://192.168.3.223:8080/edds/phoneHandleController/findPollsource.do?pollSourceId=150500000010
    @GET("/edds/phoneHandleController/findPollsource.do")
    Observable<PollInfoBean> getPollInfo(
            @Query("pollSourceId") String pollSourceId
    );

    //测试：
    // http://60.31.186.26:8082/tlgk/appLoginAction!appLogin.action?userName=mg&passWord=123
    @GET("tlgk/appLoginAction!appLogin.action")
    Observable<TestBean> testurl(
            @Query("userName") String userName,
            @Query("passWord") String passWord
    );
}
