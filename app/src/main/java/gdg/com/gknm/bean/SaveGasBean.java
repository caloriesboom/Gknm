package gdg.com.gknm.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 47129 on 2017/9/11.
 *
 */

public class SaveGasBean extends BaseResult implements Serializable {
    //企业类型
    private String pollType;
    //行业类别
    private String industryType;
    //废气或废水
    private String gasOrWater;
    //安装总套数
    private String installTotalNum;
    //原烟气监控点
    private String OriGasPoint;
    //原烟气监控点联网情况
    private String isOriGasNet;
    //净烟气监控点
    private String cleanGasPoint;
    //净烟气监控点联网情况
    private String isCleanGasNet;
    //脱销监测点
    private String derPoint;
    //脱销检测点联网情况
    private String isDerNet;
    //其他检测点
    private String otherPoint;
    //其他监测点联网情况
    private String isOtherNet;
    //监测点名称
    private String pointName;
    //设备安装时间
    private String facInstalTime;
    //设备品牌&型号
    private String facType;
    //验收时间
    private String acceptTime;
    //运维单位e
    private String operatUnit;
    //最后一次有效性审核时间
    private String lastTime;

    public String getPollType() {
        return pollType;
    }

    public void setPollType(String pollType) {
        this.pollType = pollType;
    }

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

    public String getOriGasPoint() {
        return OriGasPoint;
    }

    public void setOriGasPoint(String oriGasPoint) {
        OriGasPoint = oriGasPoint;
    }

    public String getIsOriGasNet() {
        return isOriGasNet;
    }

    public void setIsOriGasNet(String isOriGasNet) {
        this.isOriGasNet = isOriGasNet;
    }

    public String getCleanGasPoint() {
        return cleanGasPoint;
    }

    public void setCleanGasPoint(String cleanGasPoint) {
        this.cleanGasPoint = cleanGasPoint;
    }

    public String getIsCleanGasNet() {
        return isCleanGasNet;
    }

    public void setIsCleanGasNet(String isCleanGasNet) {
        this.isCleanGasNet = isCleanGasNet;
    }

    public String getDerPoint() {
        return derPoint;
    }

    public void setDerPoint(String derPoint) {
        this.derPoint = derPoint;
    }

    public String getIsDerNet() {
        return isDerNet;
    }

    public void setIsDerNet(String isDerNet) {
        this.isDerNet = isDerNet;
    }

    public String getOtherPoint() {
        return otherPoint;
    }

    public void setOtherPoint(String otherPoint) {
        this.otherPoint = otherPoint;
    }

    public String getIsOtherNet() {
        return isOtherNet;
    }

    public void setIsOtherNet(String isOtherNet) {
        this.isOtherNet = isOtherNet;
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

    public String getPollutionRegist() {
        return pollutionRegist;
    }

    public void setPollutionRegist(String pollutionRegist) {
        this.pollutionRegist = pollutionRegist;
    }

    public String getPollutionCheck() {
        return pollutionCheck;
    }

    public void setPollutionCheck(String pollutionCheck) {
        this.pollutionCheck = pollutionCheck;
    }

    public String getGasRegist() {
        return gasRegist;
    }

    public void setGasRegist(String gasRegist) {
        this.gasRegist = gasRegist;
    }

    public String getGasCheck() {
        return gasCheck;
    }

    public void setGasCheck(String gasCheck) {
        this.gasCheck = gasCheck;
    }

    public String getAdjustRegist() {
        return adjustRegist;
    }

    public void setAdjustRegist(String adjustRegist) {
        this.adjustRegist = adjustRegist;
    }

    public String getSpeedRegist() {
        return speedRegist;
    }

    public void setSpeedRegist(String speedRegist) {
        this.speedRegist = speedRegist;
    }

    public String getSpeedcheck() {
        return speedcheck;
    }

    public void setSpeedcheck(String speedcheck) {
        this.speedcheck = speedcheck;
    }

    public String getPtRegist() {
        return ptRegist;
    }

    public void setPtRegist(String ptRegist) {
        this.ptRegist = ptRegist;
    }

    public String getPtcheck() {
        return ptcheck;
    }

    public void setPtcheck(String ptcheck) {
        this.ptcheck = ptcheck;
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

    //排污——申报登记值
    private String pollutionRegist;
    //排污——现场核查值
    private String pollutionCheck;
    //过剩空气系数——申报登记值
    private String gasRegist;
    //过剩空气系数——现场核查值
    private String gasCheck;
    //校准系数——申报登记值
    private String adjustRegist;
    //速度场系数——申报登记值
    private String speedRegist;
    //速度场系数——现场核查值
    private String speedcheck;
    //皮托管系数——申报登记值
    private String ptRegist;
    //皮托管系数——现场核查值
    private String ptcheck;
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
