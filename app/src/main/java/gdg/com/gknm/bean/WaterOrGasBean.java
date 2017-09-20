package gdg.com.gknm.bean;

import java.io.Serializable;

/**
 * Created by 47129 on 2017/9/13.
 * 废气废水实体类
 */

public class WaterOrGasBean extends BaseResult implements Serializable {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanCode() {
        return beanCode;
    }

    public void setBeanCode(String beanCode) {
        this.beanCode = beanCode;
    }

    private String beanCode;
}
