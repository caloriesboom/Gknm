package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-19.
 * 首页任务提醒实体类
 */

public class TaskNoticeBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity : [{"taskManagementId":"89","taskName":"11111","taskSource":"2","taskIssuedTime":"2017-09-18","taskEndTime":"2017-09-17","taskAssignment":"10044","taskReminderText":"您有一条11111需要在2017年09月17日之前处理，请及时查看！","startTime":null,"endTime":null},{"taskManagementId":"90","taskName":"22222","taskSource":"2","taskIssuedTime":"2017-09-18","taskEndTime":"2017-09-17","taskAssignment":"10044","taskReminderText":"您有一条22222需要在2017年09月17日之前处理，请及时查看！","startTime":null,"endTime":null}]
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
         * taskManagementId : 89
         * taskName : 11111
         * taskSource : 2
         * taskIssuedTime : 2017-09-18
         * taskEndTime : 2017-09-17
         * taskAssignment : 10044
         * taskReminderText : 您有一条11111需要在2017年09月17日之前处理，请及时查看！
         * startTime : null
         * endTime : null
         */

        private String taskManagementId;
        private String taskName;
        private String taskSource;
        private String taskIssuedTime;
        private String taskEndTime;
        private String taskAssignment;
        private String taskReminderText;
        private Object startTime;
        private Object endTime;

        public String getTaskManagementId() {
            return taskManagementId;
        }

        public void setTaskManagementId(String taskManagementId) {
            this.taskManagementId = taskManagementId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskSource() {
            return taskSource;
        }

        public void setTaskSource(String taskSource) {
            this.taskSource = taskSource;
        }

        public String getTaskIssuedTime() {
            return taskIssuedTime;
        }

        public void setTaskIssuedTime(String taskIssuedTime) {
            this.taskIssuedTime = taskIssuedTime;
        }

        public String getTaskEndTime() {
            return taskEndTime;
        }

        public void setTaskEndTime(String taskEndTime) {
            this.taskEndTime = taskEndTime;
        }

        public String getTaskAssignment() {
            return taskAssignment;
        }

        public void setTaskAssignment(String taskAssignment) {
            this.taskAssignment = taskAssignment;
        }

        public String getTaskReminderText() {
            return taskReminderText;
        }

        public void setTaskReminderText(String taskReminderText) {
            this.taskReminderText = taskReminderText;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }
    }
}
