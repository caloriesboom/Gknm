package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-18.
 * 关注的排口实体类
 */

public class AttentionOutFlowBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity : [{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"3","pollOutName":"10号脱硫净烟","checked":true},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"210","pollOutName":"6号脱硝入口","checked":true},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"211","pollOutName":"6号脱硝出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"212","pollOutName":"7号脱硝入口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"213","pollOutName":"7号脱硝出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"214","pollOutName":"8号脱硝入口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"215","pollOutName":"8号脱硝出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"216","pollOutName":"9号脱硝入口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"217","pollOutName":"9号脱硝出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"218","pollOutName":"6号7号脱硫出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"219","pollOutName":"8号9号脱硫出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"230","pollOutName":"10号脱硝入口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"231","pollOutName":"10号脱硝出口","checked":false},{"userId":null,"pollSourceId":"150500000010","pollSourceName":"通辽热电有限责任公司","pollOutId":"232","pollOutName":"10号脱硫原烟","checked":false}]
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
         * pollOutId : 3
         * pollOutName : 10号脱硫净烟
         * checked : true
         */

        private Object userId;
        private String pollSourceId;
        private String pollSourceName;
        private String pollOutId;
        private String pollOutName;
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

        public String getPollOutId() {
            return pollOutId;
        }

        public void setPollOutId(String pollOutId) {
            this.pollOutId = pollOutId;
        }

        public String getPollOutName() {
            return pollOutName;
        }

        public void setPollOutName(String pollOutName) {
            this.pollOutName = pollOutName;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
