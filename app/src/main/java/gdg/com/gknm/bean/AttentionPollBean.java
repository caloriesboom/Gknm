package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-18.
 * 关注的企业实体类
 */

public class AttentionPollBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity : [{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","checked":true},{"userId":null,"pollSourceId":"150500000016","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司电力分公司","checked":false},{"userId":null,"pollSourceId":"150500000024","pollSourceName":"通辽发电总厂","checked":false},{"userId":null,"pollSourceId":"150500000053","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司","checked":false},{"userId":null,"pollSourceId":"150500000054","pollSourceName":"霍林郭勒市金源口电业有限责任公司","checked":false}]
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
         * userId : null
         * pollSourceId : 150500000010
         * pollSourceName : 通辽热电有限责任公司
         * checked : true
         */

        private Object userId;
        private String pollSourceId;
        private String pollSourceName;
        private boolean checked;

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getPollSourceId() {
            return pollSourceId;
        }

        public void setPollSourceId(String pollSourceId) {
            this.pollSourceId = pollSourceId;
        }

        public String getPollSourceName() {
            return pollSourceName;
        }

        public void setPollSourceName(String pollSourceName) {
            this.pollSourceName = pollSourceName;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
