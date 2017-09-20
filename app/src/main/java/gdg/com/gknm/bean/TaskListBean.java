package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-6.
 * 监督检查——待办任务实体类
 */

public class TaskListBean extends BaseResult implements Serializable {


    /**
     * message : ok
     * resultCode : true
     * resultEntity : {"startRecord":"0","pageSize":10,"data":[{"taskProgressId":6,"taskManagement":{"taskManagementId":41,"taskName":"777","taskSource":"2","taskIssuedTime":"2017-09-07","startTime":null,"endTime":null,"taskEndTime":"2017-09-05","createUser":"mg","priority":"1","taskContents":"urutyu","enterpriseName":"150500000053","enterpriseNameValue":"内蒙古霍煤鸿骏铝电有限责任公司","taskCycle":null,"customTime":null,"regionName":"10007","taskIssuedUnit":"通辽市","taskAcceptUnit":"10001","isSiteInspection":"1","taskAssignment":"10044","isSelfSiteInspection":"1","distributionStatus":"1","taskStatus":"0","acceptStatus":"1","isDelete":0},"taskManagementId":41,"checkTime":null,"isEnterpriseRectification":null,"waterOrGas":null,"siteInspectionResult":null,"moveStatus":null,"rectificationAdviseId":null,"rectificationAdvise":{"rectificationAdviseId":null,"taskManagementId":41,"rectificationReason":null,"rectificationContents":null,"thirdoperation":null,"cc":null,"conpanyFeedbackId":null,"thirdoperationFeedbackId":null,"rectificationAdviseStatus":null,"isDelete":0,"taskManagement":null,"feedback":null},"siteInspectionWaterId":1,"siteInspectionGasId":null,"rectificationNotificationId":null,"rectificationNotification":{"rectificationNotificationId":null,"onSiteVerificationMonth":null,"companyName":null,"qixianEPA":null,"endTime":null,"contacts":null,"contactNumber":null,"issuedTime":null,"issuanceTime":null,"problemDescription":null,"rectificationNotificationStatus":null,"isDelete":0},"rectificationReportId":null,"qixianEPAAuditResult":null,"qixianEPAAuditor":null,"progressStatus":1,"enterpriseType":null,"isDelete":0}],"totalRecord":1,"totalPage":1}
     */

    private String message;
    private String resultCode;
    private ResultEntityBean resultEntity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultEntityBean getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(ResultEntityBean resultEntity) {
        this.resultEntity = resultEntity;
    }

    public static class ResultEntityBean {
        /**
         * startRecord : 0
         * pageSize : 10
         * data : [{"taskProgressId":6,"taskManagement":{"taskManagementId":41,"taskName":"777","taskSource":"2","taskIssuedTime":"2017-09-07","startTime":null,"endTime":null,"taskEndTime":"2017-09-05","createUser":"mg","priority":"1","taskContents":"urutyu","enterpriseName":"150500000053","enterpriseNameValue":"内蒙古霍煤鸿骏铝电有限责任公司","taskCycle":null,"customTime":null,"regionName":"10007","taskIssuedUnit":"通辽市","taskAcceptUnit":"10001","isSiteInspection":"1","taskAssignment":"10044","isSelfSiteInspection":"1","distributionStatus":"1","taskStatus":"0","acceptStatus":"1","isDelete":0},"taskManagementId":41,"checkTime":null,"isEnterpriseRectification":null,"waterOrGas":null,"siteInspectionResult":null,"moveStatus":null,"rectificationAdviseId":null,"rectificationAdvise":{"rectificationAdviseId":null,"taskManagementId":41,"rectificationReason":null,"rectificationContents":null,"thirdoperation":null,"cc":null,"conpanyFeedbackId":null,"thirdoperationFeedbackId":null,"rectificationAdviseStatus":null,"isDelete":0,"taskManagement":null,"feedback":null},"siteInspectionWaterId":1,"siteInspectionGasId":null,"rectificationNotificationId":null,"rectificationNotification":{"rectificationNotificationId":null,"onSiteVerificationMonth":null,"companyName":null,"qixianEPA":null,"endTime":null,"contacts":null,"contactNumber":null,"issuedTime":null,"issuanceTime":null,"problemDescription":null,"rectificationNotificationStatus":null,"isDelete":0},"rectificationReportId":null,"qixianEPAAuditResult":null,"qixianEPAAuditor":null,"progressStatus":1,"enterpriseType":null,"isDelete":0}]
         * totalRecord : 1
         * totalPage : 1
         */

        private String startRecord;
        private int pageSize;
        private int totalRecord;
        private int totalPage;
        private List<DataBean> data;

        public String getStartRecord() {
            return startRecord;
        }

        public void setStartRecord(String startRecord) {
            this.startRecord = startRecord;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * taskProgressId : 6
             * taskManagement : {"taskManagementId":41,"taskName":"777","taskSource":"2","taskIssuedTime":"2017-09-07","startTime":null,"endTime":null,"taskEndTime":"2017-09-05","createUser":"mg","priority":"1","taskContents":"urutyu","enterpriseName":"150500000053","enterpriseNameValue":"内蒙古霍煤鸿骏铝电有限责任公司","taskCycle":null,"customTime":null,"regionName":"10007","taskIssuedUnit":"通辽市","taskAcceptUnit":"10001","isSiteInspection":"1","taskAssignment":"10044","isSelfSiteInspection":"1","distributionStatus":"1","taskStatus":"0","acceptStatus":"1","isDelete":0}
             * taskManagementId : 41
             * checkTime : null
             * isEnterpriseRectification : null
             * waterOrGas : null
             * siteInspectionResult : null
             * moveStatus : null
             * rectificationAdviseId : null
             * rectificationAdvise : {"rectificationAdviseId":null,"taskManagementId":41,"rectificationReason":null,"rectificationContents":null,"thirdoperation":null,"cc":null,"conpanyFeedbackId":null,"thirdoperationFeedbackId":null,"rectificationAdviseStatus":null,"isDelete":0,"taskManagement":null,"feedback":null}
             * siteInspectionWaterId : 1
             * siteInspectionGasId : null
             * rectificationNotificationId : null
             * rectificationNotification : {"rectificationNotificationId":null,"onSiteVerificationMonth":null,"companyName":null,"qixianEPA":null,"endTime":null,"contacts":null,"contactNumber":null,"issuedTime":null,"issuanceTime":null,"problemDescription":null,"rectificationNotificationStatus":null,"isDelete":0}
             * rectificationReportId : null
             * qixianEPAAuditResult : null
             * qixianEPAAuditor : null
             * progressStatus : 1
             * enterpriseType : null
             * isDelete : 0
             */

            private int taskProgressId;
            private TaskManagementBean taskManagement;
            private int taskManagementId;
            private Object checkTime;
            private String isEnterpriseRectification;
            private String waterOrGas;
            private Object siteInspectionResult;
            private Object moveStatus;
            private Object rectificationAdviseId;
            private RectificationAdviseBean rectificationAdvise;
            private int siteInspectionWaterId;
            private Object siteInspectionGasId;
            private Object rectificationNotificationId;
            private RectificationNotificationBean rectificationNotification;
            private Object rectificationReportId;
            private Object qixianEPAAuditResult;
            private Object qixianEPAAuditor;
            private int progressStatus;
            private Object enterpriseType;
            private int isDelete;

            public int getTaskProgressId() {
                return taskProgressId;
            }

            public void setTaskProgressId(int taskProgressId) {
                this.taskProgressId = taskProgressId;
            }

            public TaskManagementBean getTaskManagement() {
                return taskManagement;
            }

            public void setTaskManagement(TaskManagementBean taskManagement) {
                this.taskManagement = taskManagement;
            }

            public int getTaskManagementId() {
                return taskManagementId;
            }

            public void setTaskManagementId(int taskManagementId) {
                this.taskManagementId = taskManagementId;
            }

            public Object getCheckTime() {
                return checkTime;
            }

            public void setCheckTime(Object checkTime) {
                this.checkTime = checkTime;
            }

            public String getIsEnterpriseRectification() {
                return isEnterpriseRectification;
            }

            public void setIsEnterpriseRectification(String isEnterpriseRectification) {
                this.isEnterpriseRectification = isEnterpriseRectification;
            }

            public String getWaterOrGas() {
                return waterOrGas;
            }

            public void setWaterOrGas(String waterOrGas) {
                this.waterOrGas = waterOrGas;
            }

            public Object getSiteInspectionResult() {
                return siteInspectionResult;
            }

            public void setSiteInspectionResult(Object siteInspectionResult) {
                this.siteInspectionResult = siteInspectionResult;
            }

            public Object getMoveStatus() {
                return moveStatus;
            }

            public void setMoveStatus(Object moveStatus) {
                this.moveStatus = moveStatus;
            }

            public Object getRectificationAdviseId() {
                return rectificationAdviseId;
            }

            public void setRectificationAdviseId(Object rectificationAdviseId) {
                this.rectificationAdviseId = rectificationAdviseId;
            }

            public RectificationAdviseBean getRectificationAdvise() {
                return rectificationAdvise;
            }

            public void setRectificationAdvise(RectificationAdviseBean rectificationAdvise) {
                this.rectificationAdvise = rectificationAdvise;
            }

            public int getSiteInspectionWaterId() {
                return siteInspectionWaterId;
            }

            public void setSiteInspectionWaterId(int siteInspectionWaterId) {
                this.siteInspectionWaterId = siteInspectionWaterId;
            }

            public Object getSiteInspectionGasId() {
                return siteInspectionGasId;
            }

            public void setSiteInspectionGasId(Object siteInspectionGasId) {
                this.siteInspectionGasId = siteInspectionGasId;
            }

            public Object getRectificationNotificationId() {
                return rectificationNotificationId;
            }

            public void setRectificationNotificationId(Object rectificationNotificationId) {
                this.rectificationNotificationId = rectificationNotificationId;
            }

            public RectificationNotificationBean getRectificationNotification() {
                return rectificationNotification;
            }

            public void setRectificationNotification(RectificationNotificationBean rectificationNotification) {
                this.rectificationNotification = rectificationNotification;
            }

            public Object getRectificationReportId() {
                return rectificationReportId;
            }

            public void setRectificationReportId(Object rectificationReportId) {
                this.rectificationReportId = rectificationReportId;
            }

            public Object getQixianEPAAuditResult() {
                return qixianEPAAuditResult;
            }

            public void setQixianEPAAuditResult(Object qixianEPAAuditResult) {
                this.qixianEPAAuditResult = qixianEPAAuditResult;
            }

            public Object getQixianEPAAuditor() {
                return qixianEPAAuditor;
            }

            public void setQixianEPAAuditor(Object qixianEPAAuditor) {
                this.qixianEPAAuditor = qixianEPAAuditor;
            }

            public int getProgressStatus() {
                return progressStatus;
            }

            public void setProgressStatus(int progressStatus) {
                this.progressStatus = progressStatus;
            }

            public Object getEnterpriseType() {
                return enterpriseType;
            }

            public void setEnterpriseType(Object enterpriseType) {
                this.enterpriseType = enterpriseType;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public static class TaskManagementBean {
                /**
                 * taskManagementId : 41
                 * taskName : 777
                 * taskSource : 2
                 * taskIssuedTime : 2017-09-07
                 * startTime : null
                 * endTime : null
                 * taskEndTime : 2017-09-05
                 * createUser : mg
                 * priority : 1
                 * taskContents : urutyu
                 * enterpriseName : 150500000053
                 * enterpriseNameValue : 内蒙古霍煤鸿骏铝电有限责任公司
                 * taskCycle : null
                 * customTime : null
                 * regionName : 10007
                 * taskIssuedUnit : 通辽市
                 * taskAcceptUnit : 10001
                 * isSiteInspection : 1
                 * taskAssignment : 10044
                 * isSelfSiteInspection : 1
                 * distributionStatus : 1
                 * taskStatus : 0
                 * acceptStatus : 1
                 * isDelete : 0
                 */

                private int taskManagementId;
                private String taskName;
                private String taskSource;
                private String taskIssuedTime;
                private Object startTime;
                private Object endTime;
                private String taskEndTime;
                private String createUser;
                private String priority;
                private String taskContents;
                private String enterpriseName;
                private String enterpriseNameValue;
                private Object taskCycle;
                private Object customTime;
                private String regionName;
                private String taskIssuedUnit;
                private String taskAcceptUnit;
                private String isSiteInspection;
                private String taskAssignment;
                private String isSelfSiteInspection;
                private String distributionStatus;
                private String taskStatus;
                private String acceptStatus;
                private int isDelete;

                public int getTaskManagementId() {
                    return taskManagementId;
                }

                public void setTaskManagementId(int taskManagementId) {
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

                public String getTaskEndTime() {
                    return taskEndTime;
                }

                public void setTaskEndTime(String taskEndTime) {
                    this.taskEndTime = taskEndTime;
                }

                public String getCreateUser() {
                    return createUser;
                }

                public void setCreateUser(String createUser) {
                    this.createUser = createUser;
                }

                public String getPriority() {
                    return priority;
                }

                public void setPriority(String priority) {
                    this.priority = priority;
                }

                public String getTaskContents() {
                    return taskContents;
                }

                public void setTaskContents(String taskContents) {
                    this.taskContents = taskContents;
                }

                public String getEnterpriseName() {
                    return enterpriseName;
                }

                public void setEnterpriseName(String enterpriseName) {
                    this.enterpriseName = enterpriseName;
                }

                public String getEnterpriseNameValue() {
                    return enterpriseNameValue;
                }

                public void setEnterpriseNameValue(String enterpriseNameValue) {
                    this.enterpriseNameValue = enterpriseNameValue;
                }

                public Object getTaskCycle() {
                    return taskCycle;
                }

                public void setTaskCycle(Object taskCycle) {
                    this.taskCycle = taskCycle;
                }

                public Object getCustomTime() {
                    return customTime;
                }

                public void setCustomTime(Object customTime) {
                    this.customTime = customTime;
                }

                public String getRegionName() {
                    return regionName;
                }

                public void setRegionName(String regionName) {
                    this.regionName = regionName;
                }

                public String getTaskIssuedUnit() {
                    return taskIssuedUnit;
                }

                public void setTaskIssuedUnit(String taskIssuedUnit) {
                    this.taskIssuedUnit = taskIssuedUnit;
                }

                public String getTaskAcceptUnit() {
                    return taskAcceptUnit;
                }

                public void setTaskAcceptUnit(String taskAcceptUnit) {
                    this.taskAcceptUnit = taskAcceptUnit;
                }

                public String getIsSiteInspection() {
                    return isSiteInspection;
                }

                public void setIsSiteInspection(String isSiteInspection) {
                    this.isSiteInspection = isSiteInspection;
                }

                public String getTaskAssignment() {
                    return taskAssignment;
                }

                public void setTaskAssignment(String taskAssignment) {
                    this.taskAssignment = taskAssignment;
                }

                public String getIsSelfSiteInspection() {
                    return isSelfSiteInspection;
                }

                public void setIsSelfSiteInspection(String isSelfSiteInspection) {
                    this.isSelfSiteInspection = isSelfSiteInspection;
                }

                public String getDistributionStatus() {
                    return distributionStatus;
                }

                public void setDistributionStatus(String distributionStatus) {
                    this.distributionStatus = distributionStatus;
                }

                public String getTaskStatus() {
                    return taskStatus;
                }

                public void setTaskStatus(String taskStatus) {
                    this.taskStatus = taskStatus;
                }

                public String getAcceptStatus() {
                    return acceptStatus;
                }

                public void setAcceptStatus(String acceptStatus) {
                    this.acceptStatus = acceptStatus;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
                }
            }

            public static class RectificationAdviseBean {
                /**
                 * rectificationAdviseId : null
                 * taskManagementId : 41
                 * rectificationReason : null
                 * rectificationContents : null
                 * thirdoperation : null
                 * cc : null
                 * conpanyFeedbackId : null
                 * thirdoperationFeedbackId : null
                 * rectificationAdviseStatus : null
                 * isDelete : 0
                 * taskManagement : null
                 * feedback : null
                 */

                private Object rectificationAdviseId;
                private int taskManagementId;
                private Object rectificationReason;
                private Object rectificationContents;
                private Object thirdoperation;
                private Object cc;
                private Object conpanyFeedbackId;
                private Object thirdoperationFeedbackId;
                private Object rectificationAdviseStatus;
                private int isDelete;
                private Object taskManagement;
                private Object feedback;

                public Object getRectificationAdviseId() {
                    return rectificationAdviseId;
                }

                public void setRectificationAdviseId(Object rectificationAdviseId) {
                    this.rectificationAdviseId = rectificationAdviseId;
                }

                public int getTaskManagementId() {
                    return taskManagementId;
                }

                public void setTaskManagementId(int taskManagementId) {
                    this.taskManagementId = taskManagementId;
                }

                public Object getRectificationReason() {
                    return rectificationReason;
                }

                public void setRectificationReason(Object rectificationReason) {
                    this.rectificationReason = rectificationReason;
                }

                public Object getRectificationContents() {
                    return rectificationContents;
                }

                public void setRectificationContents(Object rectificationContents) {
                    this.rectificationContents = rectificationContents;
                }

                public Object getThirdoperation() {
                    return thirdoperation;
                }

                public void setThirdoperation(Object thirdoperation) {
                    this.thirdoperation = thirdoperation;
                }

                public Object getCc() {
                    return cc;
                }

                public void setCc(Object cc) {
                    this.cc = cc;
                }

                public Object getConpanyFeedbackId() {
                    return conpanyFeedbackId;
                }

                public void setConpanyFeedbackId(Object conpanyFeedbackId) {
                    this.conpanyFeedbackId = conpanyFeedbackId;
                }

                public Object getThirdoperationFeedbackId() {
                    return thirdoperationFeedbackId;
                }

                public void setThirdoperationFeedbackId(Object thirdoperationFeedbackId) {
                    this.thirdoperationFeedbackId = thirdoperationFeedbackId;
                }

                public Object getRectificationAdviseStatus() {
                    return rectificationAdviseStatus;
                }

                public void setRectificationAdviseStatus(Object rectificationAdviseStatus) {
                    this.rectificationAdviseStatus = rectificationAdviseStatus;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
                }

                public Object getTaskManagement() {
                    return taskManagement;
                }

                public void setTaskManagement(Object taskManagement) {
                    this.taskManagement = taskManagement;
                }

                public Object getFeedback() {
                    return feedback;
                }

                public void setFeedback(Object feedback) {
                    this.feedback = feedback;
                }
            }

            public static class RectificationNotificationBean {
                /**
                 * rectificationNotificationId : null
                 * onSiteVerificationMonth : null
                 * companyName : null
                 * qixianEPA : null
                 * endTime : null
                 * contacts : null
                 * contactNumber : null
                 * issuedTime : null
                 * issuanceTime : null
                 * problemDescription : null
                 * rectificationNotificationStatus : null
                 * isDelete : 0
                 */

                private Object rectificationNotificationId;
                private Object onSiteVerificationMonth;
                private Object companyName;
                private Object qixianEPA;
                private Object endTime;
                private Object contacts;
                private Object contactNumber;
                private Object issuedTime;
                private Object issuanceTime;
                private Object problemDescription;
                private Object rectificationNotificationStatus;
                private int isDelete;

                public Object getRectificationNotificationId() {
                    return rectificationNotificationId;
                }

                public void setRectificationNotificationId(Object rectificationNotificationId) {
                    this.rectificationNotificationId = rectificationNotificationId;
                }

                public Object getOnSiteVerificationMonth() {
                    return onSiteVerificationMonth;
                }

                public void setOnSiteVerificationMonth(Object onSiteVerificationMonth) {
                    this.onSiteVerificationMonth = onSiteVerificationMonth;
                }

                public Object getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(Object companyName) {
                    this.companyName = companyName;
                }

                public Object getQixianEPA() {
                    return qixianEPA;
                }

                public void setQixianEPA(Object qixianEPA) {
                    this.qixianEPA = qixianEPA;
                }

                public Object getEndTime() {
                    return endTime;
                }

                public void setEndTime(Object endTime) {
                    this.endTime = endTime;
                }

                public Object getContacts() {
                    return contacts;
                }

                public void setContacts(Object contacts) {
                    this.contacts = contacts;
                }

                public Object getContactNumber() {
                    return contactNumber;
                }

                public void setContactNumber(Object contactNumber) {
                    this.contactNumber = contactNumber;
                }

                public Object getIssuedTime() {
                    return issuedTime;
                }

                public void setIssuedTime(Object issuedTime) {
                    this.issuedTime = issuedTime;
                }

                public Object getIssuanceTime() {
                    return issuanceTime;
                }

                public void setIssuanceTime(Object issuanceTime) {
                    this.issuanceTime = issuanceTime;
                }

                public Object getProblemDescription() {
                    return problemDescription;
                }

                public void setProblemDescription(Object problemDescription) {
                    this.problemDescription = problemDescription;
                }

                public Object getRectificationNotificationStatus() {
                    return rectificationNotificationStatus;
                }

                public void setRectificationNotificationStatus(Object rectificationNotificationStatus) {
                    this.rectificationNotificationStatus = rectificationNotificationStatus;
                }

                public int getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(int isDelete) {
                    this.isDelete = isDelete;
                }
            }
        }
    }
}
