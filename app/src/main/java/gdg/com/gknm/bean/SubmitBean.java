package gdg.com.gknm.bean;

import java.io.Serializable;

/**
 * Created by GUO.DG on 2017-9-18.
 * 提交类接口接收实体类
 */

public class SubmitBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity :
     */

    private String message;
    private boolean resultCode;
    private String resultEntity;

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

    public String getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(String resultEntity) {
        this.resultEntity = resultEntity;
    }
}
