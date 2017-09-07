package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-6.
 *
 */

public class PollInfoBean extends BaseResult implements Serializable {

    /**
     * message : ok
     * resultCode : true
     * resultEntity : {"data":[{"pollSourceId":"150500000010","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"TLRD","pollSourceName":"通辽热电有限责任公司","shortName":"","longitude":"122.296972","latitude":"43.618833","pollSourceOrder":"5","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"通辽市新工一路六号","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-8295040","communicateAddress":"","postalCode":"","email":null,"webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null}]}
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
             * pollSourceId : 150500000010
             * cityId : 10000
             * industryType : QYHYLB_D4411
             * pollType : QYLX_COMMON
             * regulationType : 1
             * monitorOnline : 0
             * monitorErc : 0
             * monitorVedio : 1
             * monitorManager : 1
             * monitorDirect : 1
             * monitorJygk : 1
             * pollSourceCode : TLRD
             * pollSourceName : 通辽热电有限责任公司
             * shortName :
             * longitude : 122.296972
             * latitude : 43.618833
             * pollSourceOrder : 5
             * corpCode : null
             * corpName :
             * createTime : null
             * pollSourceAddress : 通辽市新工一路六号
             * dutyPerson : null
             * linkMan :
             * telephone :
             * fax : 0475-8295040
             * communicateAddress :
             * postalCode :
             * email : null
             * webSite :
             * environJobNo : null
             * environLinkMan :
             * environTel :
             * declareCode : null
             * outPermitCode :
             * prodScale :
             * ipAddress :
             * mainPollutant :
             * pollArea : 0
             * pollTypeName : null
             * cityName : null
             * industryTypeName : null
             * regulationTypeName : null
             * pollIn : null
             * pollsourceList : null
             */

            private String pollSourceId;
            private String cityId;
            private String industryType;
            private String pollType;
            private String regulationType;
            private String monitorOnline;
            private String monitorErc;
            private String monitorVedio;
            private String monitorManager;
            private String monitorDirect;
            private String monitorJygk;
            private String pollSourceCode;
            private String pollSourceName;
            private String shortName;
            private String longitude;
            private String latitude;
            private String pollSourceOrder;
            private Object corpCode;
            private String corpName;
            private Object createTime;
            private String pollSourceAddress;
            private Object dutyPerson;
            private String linkMan;
            private String telephone;
            private String fax;
            private String communicateAddress;
            private String postalCode;
            private Object email;
            private String webSite;
            private Object environJobNo;
            private String environLinkMan;
            private String environTel;
            private Object declareCode;
            private String outPermitCode;
            private String prodScale;
            private String ipAddress;
            private String mainPollutant;
            private String pollArea;
            private Object pollTypeName;
            private Object cityName;
            private Object industryTypeName;
            private Object regulationTypeName;
            private Object pollIn;
            private Object pollsourceList;

            public String getPollSourceId() {
                return pollSourceId;
            }

            public void setPollSourceId(String pollSourceId) {
                this.pollSourceId = pollSourceId;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getIndustryType() {
                return industryType;
            }

            public void setIndustryType(String industryType) {
                this.industryType = industryType;
            }

            public String getPollType() {
                return pollType;
            }

            public void setPollType(String pollType) {
                this.pollType = pollType;
            }

            public String getRegulationType() {
                return regulationType;
            }

            public void setRegulationType(String regulationType) {
                this.regulationType = regulationType;
            }

            public String getMonitorOnline() {
                return monitorOnline;
            }

            public void setMonitorOnline(String monitorOnline) {
                this.monitorOnline = monitorOnline;
            }

            public String getMonitorErc() {
                return monitorErc;
            }

            public void setMonitorErc(String monitorErc) {
                this.monitorErc = monitorErc;
            }

            public String getMonitorVedio() {
                return monitorVedio;
            }

            public void setMonitorVedio(String monitorVedio) {
                this.monitorVedio = monitorVedio;
            }

            public String getMonitorManager() {
                return monitorManager;
            }

            public void setMonitorManager(String monitorManager) {
                this.monitorManager = monitorManager;
            }

            public String getMonitorDirect() {
                return monitorDirect;
            }

            public void setMonitorDirect(String monitorDirect) {
                this.monitorDirect = monitorDirect;
            }

            public String getMonitorJygk() {
                return monitorJygk;
            }

            public void setMonitorJygk(String monitorJygk) {
                this.monitorJygk = monitorJygk;
            }

            public String getPollSourceCode() {
                return pollSourceCode;
            }

            public void setPollSourceCode(String pollSourceCode) {
                this.pollSourceCode = pollSourceCode;
            }

            public String getPollSourceName() {
                return pollSourceName;
            }

            public void setPollSourceName(String pollSourceName) {
                this.pollSourceName = pollSourceName;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getPollSourceOrder() {
                return pollSourceOrder;
            }

            public void setPollSourceOrder(String pollSourceOrder) {
                this.pollSourceOrder = pollSourceOrder;
            }

            public Object getCorpCode() {
                return corpCode;
            }

            public void setCorpCode(Object corpCode) {
                this.corpCode = corpCode;
            }

            public String getCorpName() {
                return corpName;
            }

            public void setCorpName(String corpName) {
                this.corpName = corpName;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getPollSourceAddress() {
                return pollSourceAddress;
            }

            public void setPollSourceAddress(String pollSourceAddress) {
                this.pollSourceAddress = pollSourceAddress;
            }

            public Object getDutyPerson() {
                return dutyPerson;
            }

            public void setDutyPerson(Object dutyPerson) {
                this.dutyPerson = dutyPerson;
            }

            public String getLinkMan() {
                return linkMan;
            }

            public void setLinkMan(String linkMan) {
                this.linkMan = linkMan;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getFax() {
                return fax;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public String getCommunicateAddress() {
                return communicateAddress;
            }

            public void setCommunicateAddress(String communicateAddress) {
                this.communicateAddress = communicateAddress;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getWebSite() {
                return webSite;
            }

            public void setWebSite(String webSite) {
                this.webSite = webSite;
            }

            public Object getEnvironJobNo() {
                return environJobNo;
            }

            public void setEnvironJobNo(Object environJobNo) {
                this.environJobNo = environJobNo;
            }

            public String getEnvironLinkMan() {
                return environLinkMan;
            }

            public void setEnvironLinkMan(String environLinkMan) {
                this.environLinkMan = environLinkMan;
            }

            public String getEnvironTel() {
                return environTel;
            }

            public void setEnvironTel(String environTel) {
                this.environTel = environTel;
            }

            public Object getDeclareCode() {
                return declareCode;
            }

            public void setDeclareCode(Object declareCode) {
                this.declareCode = declareCode;
            }

            public String getOutPermitCode() {
                return outPermitCode;
            }

            public void setOutPermitCode(String outPermitCode) {
                this.outPermitCode = outPermitCode;
            }

            public String getProdScale() {
                return prodScale;
            }

            public void setProdScale(String prodScale) {
                this.prodScale = prodScale;
            }

            public String getIpAddress() {
                return ipAddress;
            }

            public void setIpAddress(String ipAddress) {
                this.ipAddress = ipAddress;
            }

            public String getMainPollutant() {
                return mainPollutant;
            }

            public void setMainPollutant(String mainPollutant) {
                this.mainPollutant = mainPollutant;
            }

            public String getPollArea() {
                return pollArea;
            }

            public void setPollArea(String pollArea) {
                this.pollArea = pollArea;
            }

            public Object getPollTypeName() {
                return pollTypeName;
            }

            public void setPollTypeName(Object pollTypeName) {
                this.pollTypeName = pollTypeName;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }

            public Object getIndustryTypeName() {
                return industryTypeName;
            }

            public void setIndustryTypeName(Object industryTypeName) {
                this.industryTypeName = industryTypeName;
            }

            public Object getRegulationTypeName() {
                return regulationTypeName;
            }

            public void setRegulationTypeName(Object regulationTypeName) {
                this.regulationTypeName = regulationTypeName;
            }

            public Object getPollIn() {
                return pollIn;
            }

            public void setPollIn(Object pollIn) {
                this.pollIn = pollIn;
            }

            public Object getPollsourceList() {
                return pollsourceList;
            }

            public void setPollsourceList(Object pollsourceList) {
                this.pollsourceList = pollsourceList;
            }
        }
    }
}
