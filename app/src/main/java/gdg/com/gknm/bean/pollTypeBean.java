package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-8.
 * 企业类型bean
 */

public class PollTypeBean extends BaseResult implements Serializable {

    /**
     * message : ok
     * resultCode : true
     * resultEntity : {"data":[{"dicId":"137","dicName":"国控重点污染源","dicCode":"GK_XC","dicOrder":"1","dicMemo":null,"userId":"mg","parentDicId":"131","dtCreate":1504770208000,"isSys":"0","dics":[]},{"dicId":"138","dicName":"治理工程减排项目","dicCode":"JP_XC","dicOrder":"2","dicMemo":null,"userId":"mg","parentDicId":"131","dtCreate":1504770236000,"isSys":"0","dics":[]},{"dicId":"139","dicName":"省控重点污染源","dicCode":"SK_XC","dicOrder":"3","dicMemo":null,"userId":"mg","parentDicId":"131","dtCreate":1504770257000,"isSys":"0","dics":[]},{"dicId":"140","dicName":"市控重点污染源","dicCode":"SKW_XC","dicOrder":"4","dicMemo":null,"userId":"mg","parentDicId":"131","dtCreate":1504848488000,"isSys":"0","dics":[]}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * dicId : 137
             * dicName : 国控重点污染源
             * dicCode : GK_XC
             * dicOrder : 1
             * dicMemo : null
             * userId : mg
             * parentDicId : 131
             * dtCreate : 1504770208000
             * isSys : 0
             * dics : []
             */

            private String dicId;
            private String dicName;
            private String dicCode;
            private String dicOrder;
            private Object dicMemo;
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

            public Object getDicMemo() {
                return dicMemo;
            }

            public void setDicMemo(Object dicMemo) {
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
}
