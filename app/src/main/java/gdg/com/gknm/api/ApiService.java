package gdg.com.gknm.api;

import java.util.Map;

import gdg.com.gknm.bean.AlarmTypeBean;
import gdg.com.gknm.bean.AttentionOutFlowBean;
import gdg.com.gknm.bean.AttentionPollBean;
import gdg.com.gknm.bean.IndustryTypeBean;
import gdg.com.gknm.bean.MonitorAlarmBean;
import gdg.com.gknm.bean.PollInfoBean;
import gdg.com.gknm.bean.PollTypeBean;
import gdg.com.gknm.bean.PollutionControlBean;
import gdg.com.gknm.bean.QuestionBean;
import gdg.com.gknm.bean.QuestionListBean;
import gdg.com.gknm.bean.SignAlarmBean;
import gdg.com.gknm.bean.SubmitBean;
import gdg.com.gknm.bean.TaskListBean;
import gdg.com.gknm.bean.TaskNoticeBean;
import gdg.com.gknm.bean.TestBean;
import gdg.com.gknm.bean.UpLoadBean;
import gdg.com.gknm.bean.UserBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;



/**
 * Created by GUO.DG on 2017-9-6.
 *  @Path - 替换参数
 *  @Query - 添加查询参数
 *  @Body请求体（RequestBody），一般用于POST方法传递请求参数。
 */

public interface ApiService {
    //登录：
    // http://192.168.2.205:8100/edds/app/login.do?userName=mg&passWord=1
    @FormUrlEncoded
    @POST("edds/app/login.do")
    Observable<UserBean> login(
            @Field("userName") String userName,
            @Field("passWord") String passWord
    );
    //我关注的企业：
    // 192.168.2.205:8100/edds/app/getPollSourceSelectCheck.do?userId=10017
    @GET("edds/app/getPollSourceSelectCheck.do")
    Observable<AttentionPollBean> getAttentionPoll(
            @Query("userId") String userId
    );
    //修改我关注的企业：
    // http://192.168.2.205:8100/edds/app/updatePollSourceSelectCheck.do?userId=10017&checkedIds=150500000010
    @GET("edds/app/updatePollSourceSelectCheck.do")
    Observable<SubmitBean> submitAttentionPoll(
            @Query("userId") String userId,
            @Query("checkedIds") String checkedIds
    );
    //我关注的排口：
    // http://192.168.2.205:8100/edds/app/getPollOutSelectCheck.do?userId=10017&pollSourceId=150500000010
    @GET("edds/app/getPollOutSelectCheck.do")
    Observable<AttentionOutFlowBean> getAttentionOut(
            @Query("userId") String userId,
            @Query("pollSourceId") String pollSourceId
    );
    //修改我关注的排口：
    // http://192.168.2.205:8100/edds/app/updatePollOutSelectCheck.do?userId=10017&pollSourceId=150500000010&checkedIds=3,210
    @GET("edds/app/updatePollOutSelectCheck.do")
    Observable<SubmitBean> submitAttentionOut(
            @Query("userId") String userId,
            @Query("pollSourceId") String pollSourceId,
            @Query("checkedIds") String checkedIds
    );
    //监督检查——任务列表：
    // http://192.168.3.223:8080/edds/phoneHandleController/findTaskManagementPage.do?start=0&limit=10&userId=10044
    @GET("/edds/phoneHandleController/findTaskManagementPage.do")
    Observable<TaskListBean> getTaskList(
            @Query("userId") String userId,
            @Query("start") String start,
            @Query("limit") String limit
    );
    //首页——任务提醒：
    // http://192.168.2.205:8100/edds//phoneHandleController/taskReminder.do?userId=10044&startTime=2017-09-18&endTime=2017-09-18
    @GET("edds/phoneHandleController/taskReminder.do")
    Observable<TaskNoticeBean> getNociteTask(
            @Query("userId") String userId,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime
    );
    //标记报警：
    // http://192.168.2.205:8100/edds/appAlarmDetail/getAlarmDetailsOfSign.do?userId=10017&beginDate=2017-07-01&endDate=2017-09-08&pollSourceId=150500000024&startRecord=0&pageSize=10
    @GET("edds/appAlarmDetail/getAlarmDetailsOfSign.do")
    Observable<SignAlarmBean> getSignList(
            @Query("userId") String userId,
            @Query("pollSourceId") String pollSourceId,
            @Query("startRecord") int startRecord,
            @Query("pageSize") int pageSize,
            @Query("beginDate") String beginDate,
            @Query("endDate") String endDate
    );
    //取消标记报警：
    // http://192.168.2.205:8100/edds/appAlarmDetail/updateAlarmOfSign.do?flagAlarmId=29
    @GET("edds/appAlarmDetail/updateAlarmOfSign.do")
    Observable<SubmitBean> cancelSign(
            @Query("flagAlarmId") String flagAlarmId
    );
    //监督检查——企业信息：
    // http://192.168.3.223:8080/edds/phoneHandleController/findPollsource.do?pollSourceId=150500000010
    @GET("/edds/phoneHandleController/findPollsource.do")
    Observable<PollInfoBean> getPollInfo(
            @Query("pollSourceId") String pollSourceId
    );
    //监督检查——验证：
    // http://192.168.3.223:8080/edds/phoneHandleController/generateDoc.do?taskProgressId=&pollSourceId=&userName=
    @GET("edds/phoneHandleController/generateDoc.do")
    Observable<UpLoadBean> getVerifyInfo(
            @Query("taskProgressId") String taskProgressId,
            @Query("pollSourceId") String pollSourceId,
            @Query("userName") String userName
    );
    //监控报警：
    // http://192.168.2.205:8100/edds/appAlarmDetail/getAlarmDetails.do?alarmSource=3&beginDate=2017-07-01&endDate=2017-09-08&pollSourceId=150500000022&alarmTypeCode=1000&startRecord=0&pageSize=10
    @GET("edds/appAlarmDetail/getAlarmDetails.do")
    Observable<MonitorAlarmBean> getMonitorAlarmList(
            @Query("pollSourceId") String pollSourceId,
            @Query("beginDate") String beginDate,
            @Query("endDate") String endDate,
            @Query("alarmSource") String alarmSource,
            @Query("alarmTypeCode") String alarmTypeCode,
            @Query("pageSize") int pageSize,
            @Query("startRecord") int startRecord
    );
    //异常类型：
    // http://192.168.2.205:8100/edds/appAlarmDetail/alarmTypeByParentCode.do?alarmSource=3
    @GET("edds/appAlarmDetail/alarmTypeByParentCode.do")
    Observable<AlarmTypeBean> getAlarmType(
            @Query("alarmSource") String alarmSource
    );

    //监督检查——企业类别：
    // http://192.168.3.223:8080/edds/phoneHandleController/findPollType.do
    @GET("/edds/phoneHandleController/findPollType.do")
    Observable<PollTypeBean> getPollTpye(

    );
    //问题类型：
    // http://192.168.2.205:8100/edds/appPhoneQuestion/findDicsByParentCode.do?dicCode=WTLX
    @GET("edds/appPhoneQuestion/findDicsByParentCode.do")
    Observable<QuestionBean> getQuestion(
            @Query("dicCode") String dicCode

    );
    //问题提交：
    // http://localhost:8080/edds/appPhoneQuestion/askQuestion.do?questionSort=WTLX_JYFK&questionText=测试&comunication=12321312
    @GET("edds/appPhoneQuestion/askQuestion.do")
    Observable<SubmitBean> subMitQuest(
            @Query("questionSort") String questionSort,
            @Query("questionText") String questionText,
            @Query("comunication") String comunication

    );
    //查询问题集合：
    // http://localhost:8080/edds/appPhoneQuestion/getPhoneQuestionList.do?questionSort=WTLX_JYFK&questionStatus=1&startRecord=0&pageSize=10
    @GET("edds/appPhoneQuestion/getPhoneQuestionList.do")
    Observable<QuestionListBean> getQuestionList(
            @Query("questionSort") String questionSort,
            @Query("questionStatus") String questionStatus,
            @Query("startRecord") int startRecord,
            @Query("pageSize") int pageSize

    );
    //监督检查——行业类型：
    // http://192.168.3.223:8080/edds/phoneHandleController/findIndustryType.do
    @GET("/edds/phoneHandleController/findIndustryType.do")
    Observable<IndustryTypeBean> getIndustryType(
    );

    //监督检查——治污工艺：
    // http://192.168.3.223:8080/edds/phoneHandleController/getFacilityByPollId.do?start=0&limit=10&pollSourceId=150500000053&userId=10044
    @GET("/edds/phoneHandleController/getFacilityByPollId.do")
    Observable<PollutionControlBean> getPollutionControlList(
            @Query("pollSourceId") String pollSourceId
    );
    //监督检查——提交废气信息
    //http://192.168.3.223:8080/edds/phoneHandleController/saveSiteInspectionGas.do?siteInspectionGasId=1&taskProgressId=6
    @FormUrlEncoded
    @POST("edds/phoneHandleController/saveSiteInspectionGas.do")
    Observable<UpLoadBean> saveGasInfo(
            @FieldMap Map<String, String> maps
    );
    //监督检查——提交废水信息
    //http://192.168.3.223:8080/edds/phoneHandleController/saveSiteInspectionWater.do?siteInspectionGasId=1&taskProgressId=6
    @FormUrlEncoded
    @POST("edds/phoneHandleController/saveSiteInspectionWater.do")
    Observable<UpLoadBean> saveWaterInfo(
            @FieldMap Map<String, String> options
    );
    //测试：
    // http://60.31.186.26:8082/tlgk/appLoginAction!appLogin.action?userName=mg&passWord=123
    @GET("tlgk/appLoginAction!appLogin.action")
    Observable<TestBean> testurl(
            @Query("userName") String userName,
            @Query("passWord") String passWord
    );
    //提交任务总信息
    // http://192.168.3.223:8080/edds/phoneHandleController/saveTaskProcess.do?checkUnit=&checkTime=&isEnterpriseRectification=&waterOrGas=&enterpriseType=&industryType=
    @GET("edds/phoneHandleController/saveTaskProcess.do")
    Observable<UpLoadBean> saveTaskProcess(
            @Query("checkUnit") String userName,
            @Query("checkTime") String checkTime,
            @Query("isEnterpriseRectification") String isEnterpriseRectification,
            @Query("moveStatus") String moveStatus,
            @Query("waterOrGas") String waterOrGas,
            @Query("enterpriseType") String enterpriseType,
            @Query("taskProgressId") String taskProgressId,
            @Query("industryType") String industryType
    );
    // 上传图片http://192.168.3.223:8080/edds/phoneHandleController/uploadQuestionFile.do?upload=
    @POST("edds/phoneHandleController/uploadQuestionFile.do")
    Observable<UpLoadBean> upload(
            @Body RequestBody Body
    );
}
