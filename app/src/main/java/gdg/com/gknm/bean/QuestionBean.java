package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-18.
 */

public class QuestionBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity : [{"dicId":"128","dicName":"使用帮助","dicCode":"WTLX_SYBZ","dicOrder":"1","dicMemo":"使用帮助","userId":"mg","parentDicId":"127","dtCreate":1504665609000,"isSys":"0","dics":[]},{"dicId":"129","dicName":"建议反馈","dicCode":"WTLX_JYFK","dicOrder":"2","dicMemo":"建议反馈","userId":"mg","parentDicId":"127","dtCreate":1504665653000,"isSys":"0","dics":[]},{"dicId":"130","dicName":"其他帮助","dicCode":"WTLX_QTBZ","dicOrder":"3","dicMemo":"其他帮助","userId":"mg","parentDicId":"127","dtCreate":1504665696000,"isSys":"0","dics":[]}]
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
         * dicId : 128
         * dicName : 使用帮助
         * dicCode : WTLX_SYBZ
         * dicOrder : 1
         * dicMemo : 使用帮助
         * userId : mg
         * parentDicId : 127
         * dtCreate : 1504665609000
         * isSys : 0
         * dics : []
         */

        private String dicId;
        private String dicName;
        private String dicCode;
        private String dicOrder;
        private String dicMemo;
        private String userId;
        private String parentDicId;
        private long dtCreate;
        private String isSys;
        private List<?> dics;

        public String getDicId() {
            return dicId;
        }

        public void setDicId(String dicId) {
            this.dicId = dicId;
        }

        public String getDicName() {
            return dicName;
        }

        public void setDicName(String dicName) {
            this.dicName = dicName;
        }

        public String getDicCode() {
            return dicCode;
        }

        public void setDicCode(String dicCode) {
            this.dicCode = dicCode;
        }

        public String getDicOrder() {
            return dicOrder;
        }

        public void setDicOrder(String dicOrder) {
            this.dicOrder = dicOrder;
        }

        public String getDicMemo() {
            return dicMemo;
        }

        public void setDicMemo(String dicMemo) {
            this.dicMemo = dicMemo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getParentDicId() {
            return parentDicId;
        }

        public void setParentDicId(String parentDicId) {
            this.parentDicId = parentDicId;
        }

        public long getDtCreate() {
            return dtCreate;
        }

        public void setDtCreate(long dtCreate) {
            this.dtCreate = dtCreate;
        }

        public String getIsSys() {
            return isSys;
        }

        public void setIsSys(String isSys) {
            this.isSys = isSys;
        }

        public List<?> getDics() {
            return dics;
        }

        public void setDics(List<?> dics) {
            this.dics = dics;
        }
    }
}
