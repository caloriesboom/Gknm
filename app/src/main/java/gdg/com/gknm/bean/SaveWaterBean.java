package gdg.com.gknm.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 47129 on 2017/9/11.
 *
 */

public class SaveWaterBean extends BaseResult implements Serializable {
    //企业类型
    private String pollType;
    //行业类别
    private String industryType;
    //废气或废水
    private String gasOrWater;
    //安装总台数
    private String installTotalNum;
    //进水口监控点
    private String inWaterPoint;
    //进水口监控点联网情况

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getGasOrWater() {
        return gasOrWater;
    }

    public void setGasOrWater(String gasOrWater) {
        this.gasOrWater = gasOrWater;
    }

    public String getInstallTotalNum() {
        return installTotalNum;
    }

    public void setInstallTotalNum(String installTotalNum) {
        this.installTotalNum = installTotalNum;
    }

    public String getInWaterPoint() {
        return inWaterPoint;
    }

    public void setInWaterPoint(String inWaterPoint) {
        this.inWaterPoint = inWaterPoint;
    }

    public String getIsInWaterNet() {
        return isInWaterNet;
    }

    public void setIsInWaterNet(String isInWaterNet) {
        this.isInWaterNet = isInWaterNet;
    }

    public String getOutWaterPoint() {
        return outWaterPoint;
    }

    public void setOutWaterPoint(String outWaterPoint) {
        this.outWaterPoint = outWaterPoint;
    }

    public String getIsoutWaterNet() {
        return isoutWaterNet;
    }

    public void setIsoutWaterNet(String isoutWaterNet) {
        this.isoutWaterNet = isoutWaterNet;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getFacInstalTime() {
        return facInstalTime;
    }

    public void setFacInstalTime(String facInstalTime) {
        this.facInstalTime = facInstalTime;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getOperatUnit() {
        return operatUnit;
    }

    public void setOperatUnit(String operatUnit) {
        this.operatUnit = operatUnit;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public byte[] getPollSign() {
        return pollSign;
    }

    public void setPollSign(byte[] pollSign) {
        this.pollSign = pollSign;
    }

    public byte[] getThirdSign() {
        return thirdSign;
    }

    public void setThirdSign(byte[] thirdSign) {
        this.thirdSign = thirdSign;
    }

    public byte[] getUnitSign() {
        return unitSign;
    }

    public void setUnitSign(byte[] unitSign) {
        this.unitSign = unitSign;
    }

    public List<questionBean> getData() {
        return data;
    }

    public void setData(List<questionBean> data) {
        this.data = data;
    }

    private String isInWaterNet;
    //出水口监控点
    private String outWaterPoint;
    //出水口监控点联网情况
    private String isoutWaterNet;
    //监测点名称
    private String pointName;
    //设备安装时间
    private String facInstalTime;
    //设备品牌&型号
    private String facType;
    //验收时间
    private String acceptTime;
    //运维单位
    private String operatUnit;
    //最后一次有效性审核时间
    private String lastTime;
    //检查时间
    private String checkTime;
    //检查单位
    private String checkUnit;
    //检查人
    private String checkPerson;
    //企业现场负责人签字bitmap字节
    private byte[] pollSign;
    //第三方运维负责人签字bitmap字节
    private byte[] thirdSign;
    //检查单位现场负责人签字bitmap字节
    private byte[] unitSign;
    //问题集合
    private List<questionBean> data;
    //问题类
    public static class questionBean{
        //问题id
        private String questionCode;
        //问题描述
        private String questionContent;
    //问题附件
        private File questionFile;
    }
}
