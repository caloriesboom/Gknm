package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-18.
 */

public class AlarmTypeBean extends BaseResult implements Serializable{

    /**
     * message : success
     * resultCode : true
     * resultEntity : [{"alarmTypeId":"1","alarmTypeParentCode":"1","alarmTypeParentName":"在线数据缺失","alarmTypeCode":"100","alarmTypeName":"分析仪故障","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"2","alarmTypeParentCode":"1","alarmTypeParentName":"在线数据缺失","alarmTypeCode":"101","alarmTypeName":"工控机/数采仪故障","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"3","alarmTypeParentCode":"1","alarmTypeParentName":"在线数据缺失","alarmTypeCode":"102","alarmTypeName":"在线数据缺失","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"4","alarmTypeParentCode":"2","alarmTypeParentName":"在线数据零值","alarmTypeCode":"200","alarmTypeName":"停运引起零值","alarmSource":"1","isFeedback":"2","lastFeedbackTime":"3"},{"alarmTypeId":"5","alarmTypeParentCode":"2","alarmTypeParentName":"在线数据零值","alarmTypeCode":"201","alarmTypeName":"在线数据零值","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"6","alarmTypeParentCode":"3","alarmTypeParentName":"在线数据超标","alarmTypeCode":"300","alarmTypeName":"在线数据超标","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"7","alarmTypeParentCode":"4","alarmTypeParentName":"在线数据异常","alarmTypeCode":"400","alarmTypeName":"在线数据异常","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"8","alarmTypeParentCode":"4","alarmTypeParentName":"在线数据异常","alarmTypeCode":"401","alarmTypeName":"在线数据超限","alarmSource":"1","isFeedback":"2","lastFeedbackTime":"3"},{"alarmTypeId":"9","alarmTypeParentCode":"4","alarmTypeParentName":"在线数据异常","alarmTypeCode":"402","alarmTypeName":"在线数据波动异常","alarmSource":"1","isFeedback":"2","lastFeedbackTime":"3"},{"alarmTypeId":"10","alarmTypeParentCode":"4","alarmTypeParentName":"在线数据异常","alarmTypeCode":"403","alarmTypeName":"在线数据恒值","alarmSource":"1","isFeedback":"1","lastFeedbackTime":"3"},{"alarmTypeId":"11","alarmTypeParentCode":"5","alarmTypeParentName":"在线监控设备人工干预","alarmTypeCode":"500","alarmTypeName":"工控机修改参数","alarmSource":"2","isFeedback":"1","lastFeedbackTime":"-1"},{"alarmTypeId":"12","alarmTypeParentCode":"5","alarmTypeParentName":"在线监控设备人工干预","alarmTypeCode":"501","alarmTypeName":"PLC修改参数","alarmSource":"2","isFeedback":"2","lastFeedbackTime":"-1"},{"alarmTypeId":"13","alarmTypeParentCode":"6","alarmTypeParentName":"工况数据缺失","alarmTypeCode":"600","alarmTypeName":"全部数据缺失","alarmSource":"3","isFeedback":"1","lastFeedbackTime":"14"},{"alarmTypeId":"14","alarmTypeParentCode":"6","alarmTypeParentName":"工况数据缺失","alarmTypeCode":"601","alarmTypeName":"全部关键数据缺失","alarmSource":"3","isFeedback":"1","lastFeedbackTime":"14"},{"alarmTypeId":"15","alarmTypeParentCode":"6","alarmTypeParentName":"工况数据缺失","alarmTypeCode":"602","alarmTypeName":"关键数据缺失","alarmSource":"3","isFeedback":"1","lastFeedbackTime":"14"},{"alarmTypeId":"16","alarmTypeParentCode":"7","alarmTypeParentName":"工况与在线数据不一致","alarmTypeCode":"700","alarmTypeName":"数据不一致","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"17","alarmTypeParentCode":"7","alarmTypeParentName":"工况与在线数据不一致","alarmTypeCode":"701","alarmTypeName":"修改公式不一致","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"18","alarmTypeParentCode":"7","alarmTypeParentName":"工况与在线数据不一致","alarmTypeCode":"702","alarmTypeName":"时钟不一致","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"19","alarmTypeParentCode":"7","alarmTypeParentName":"工况与在线数据不一致","alarmTypeCode":"703","alarmTypeName":"数据不足无法判断","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"20","alarmTypeParentCode":"8","alarmTypeParentName":"生产停运","alarmTypeCode":"800","alarmTypeName":"生产停运","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"21","alarmTypeParentCode":"9","alarmTypeParentName":"治污设施停运","alarmTypeCode":"900","alarmTypeName":"治污设施停运","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"22","alarmTypeParentCode":"10","alarmTypeParentName":"治污设施异常","alarmTypeCode":"1000","alarmTypeName":"治污设施异常","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"23","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1100","alarmTypeName":"停运后异常","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"24","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1101","alarmTypeName":"周期性变化","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"25","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1102","alarmTypeName":"陡升陡降","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"26","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1103","alarmTypeName":"设限值","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"27","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1104","alarmTypeName":"波动异常","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"28","alarmTypeParentCode":"11","alarmTypeParentName":"排口数据表征异常","alarmTypeCode":"1105","alarmTypeName":"关联度异常","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"29","alarmTypeParentCode":"12","alarmTypeParentName":"工况数据表征异常","alarmTypeCode":"1200","alarmTypeName":"恒值","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"},{"alarmTypeId":"30","alarmTypeParentCode":"12","alarmTypeParentName":"工况数据表征异常","alarmTypeCode":"1201","alarmTypeName":"满屏跳","alarmSource":"3","isFeedback":"2","lastFeedbackTime":"14"}]
     */

    private String message;
    private boolean resultCode;
    private List<ResultEntityBean> resultEntity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResultCode() {
        return resultCode;
    }

    public void setResultCode(boolean resultCode) {
        this.resultCode = resultCode;
    }

    public List<ResultEntityBean> getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(List<ResultEntityBean> resultEntity) {
        this.resultEntity = resultEntity;
    }

    public static class ResultEntityBean {
        /**
         * alarmTypeId : 1
         * alarmTypeParentCode : 1
         * alarmTypeParentName : 在线数据缺失
         * alarmTypeCode : 100
         * alarmTypeName : 分析仪故障
         * alarmSource : 1
         * isFeedback : 1
         * lastFeedbackTime : 3
         */

        private String alarmTypeId;
        private String alarmTypeParentCode;
        private String alarmTypeParentName;
        private String alarmTypeCode;
        private String alarmTypeName;
        private String alarmSource;
        private String isFeedback;
        private String lastFeedbackTime;

        public String getAlarmTypeId() {
            return alarmTypeId;
        }

        public void setAlarmTypeId(String alarmTypeId) {
            this.alarmTypeId = alarmTypeId;
        }

        public String getAlarmTypeParentCode() {
            return alarmTypeParentCode;
        }

        public void setAlarmTypeParentCode(String alarmTypeParentCode) {
            this.alarmTypeParentCode = alarmTypeParentCode;
        }

        public String getAlarmTypeParentName() {
            return alarmTypeParentName;
        }

        public void setAlarmTypeParentName(String alarmTypeParentName) {
            this.alarmTypeParentName = alarmTypeParentName;
        }

        public String getAlarmTypeCode() {
            return alarmTypeCode;
        }

        public void setAlarmTypeCode(String alarmTypeCode) {
            this.alarmTypeCode = alarmTypeCode;
        }

        public String getAlarmTypeName() {
            return alarmTypeName;
        }

        public void setAlarmTypeName(String alarmTypeName) {
            this.alarmTypeName = alarmTypeName;
        }

        public String getAlarmSource() {
            return alarmSource;
        }

        public void setAlarmSource(String alarmSource) {
            this.alarmSource = alarmSource;
        }

        public String getIsFeedback() {
            return isFeedback;
        }

        public void setIsFeedback(String isFeedback) {
            this.isFeedback = isFeedback;
        }

        public String getLastFeedbackTime() {
            return lastFeedbackTime;
        }

        public void setLastFeedbackTime(String lastFeedbackTime) {
            this.lastFeedbackTime = lastFeedbackTime;
        }
    }
}
