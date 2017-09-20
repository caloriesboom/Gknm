package gdg.com.gknm.bean;

import java.io.Serializable;

/**
 * Created by GUO.DG on 2017-9-11.
 */

public class UpLoadBean extends BaseResult implements Serializable {

    /**
     * message : ok
     * resultCode : true
     * resultEntity : success
     */

    private String message;
    private String resultCode;
    private String resultEntity;

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

    public String getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(String resultEntity) {
        this.resultEntity = resultEntity;
    }
}
