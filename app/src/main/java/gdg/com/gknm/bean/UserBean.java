package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-16.
 * 用户实体类
 */

public class UserBean extends BaseResult implements Serializable {

    /**
     * message : 登录成功
     * resultCode : true
     * resultEntity : {"userId":"10017","userName":"mg","password":null,"roleId":null,"roleName":null,"description":null,"isSystem":null,"isDeleted":null,"areaCode":null,"areaName":null,"appPolls":[{"pollSourceId":"150500000010","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"TLRD","pollSourceName":"通辽热电有限责任公司","shortName":"","longitude":"122.296972","latitude":"43.618833","pollSourceOrder":"5","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"通辽市新工一路六号","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-8295040","communicateAddress":"","postalCode":"","email":null,"webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000016","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"HJLD","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司电力分公司","shortName":"","longitude":"119.653056","latitude":"45.219167","pollSourceOrder":"2","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"内蒙古霍林郭勒市滨河路","dutyPerson":null,"linkMan":"李春雨","telephone":"","fax":"0475-7957009","communicateAddress":"","postalCode":"029200","email":"wll136@163.com","webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000024","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"1","monitorErc":"1","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"TLFDZC","pollSourceName":"通辽发电总厂","shortName":"","longitude":"122.161667","latitude":"43.674167","pollSourceOrder":"4","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"通辽市经济技术开发区","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-8292501","communicateAddress":"","postalCode":"028011","email":"rgjd_ldw@126.com","webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000053","cityId":"10000","industryType":"QYHYLB_C3316","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"0","monitorManager":"0","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"HJLY","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司","shortName":"","longitude":"119.661389","latitude":"45.451111","pollSourceOrder":"1","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"霍林郭勒市工业园区","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-2353066","communicateAddress":"","postalCode":"","email":null,"webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘,氟化物","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000054","cityId":"10000","industryType":null,"pollType":null,"regulationType":"4","monitorOnline":"1","monitorErc":"1","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":null,"pollSourceName":"霍林郭勒市金源口电业有限责任公司","shortName":null,"longitude":"119.655556","latitude":"45.230278","pollSourceOrder":null,"corpCode":null,"corpName":null,"createTime":null,"pollSourceAddress":"霍林郭勒市源源工业园区","dutyPerson":null,"linkMan":"王海涛","telephone":null,"fax":"0475-6371166","communicateAddress":null,"postalCode":"029200","email":"","webSite":null,"environJobNo":null,"environLinkMan":null,"environTel":null,"declareCode":null,"outPermitCode":null,"prodScale":null,"ipAddress":null,"mainPollutant":null,"pollArea":null,"pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null}],"unitIn":"'1','2','3','4','5','6','7','8','9','10','12','13','14','15','16','17','18','19','20','21','22'"}
     */

    private String message;
    private boolean resultCode;
    private ResultEntityBean resultEntity;

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

    public ResultEntityBean getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(ResultEntityBean resultEntity) {
        this.resultEntity = resultEntity;
    }

    public static class ResultEntityBean implements Serializable{
        /**
         * userId : 10017
         * userName : mg
         * password : null
         * roleId : null
         * roleName : null
         * description : null
         * isSystem : null
         * isDeleted : null
         * areaCode : null
         * areaName : null
         * appPolls : [{"pollSourceId":"150500000010","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"TLRD","pollSourceName":"通辽热电有限责任公司","shortName":"","longitude":"122.296972","latitude":"43.618833","pollSourceOrder":"5","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"通辽市新工一路六号","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-8295040","communicateAddress":"","postalCode":"","email":null,"webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000016","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"HJLD","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司电力分公司","shortName":"","longitude":"119.653056","latitude":"45.219167","pollSourceOrder":"2","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"内蒙古霍林郭勒市滨河路","dutyPerson":null,"linkMan":"李春雨","telephone":"","fax":"0475-7957009","communicateAddress":"","postalCode":"029200","email":"wll136@163.com","webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000024","cityId":"10000","industryType":"QYHYLB_D4411","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"1","monitorErc":"1","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"TLFDZC","pollSourceName":"通辽发电总厂","shortName":"","longitude":"122.161667","latitude":"43.674167","pollSourceOrder":"4","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"通辽市经济技术开发区","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-8292501","communicateAddress":"","postalCode":"028011","email":"rgjd_ldw@126.com","webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000053","cityId":"10000","industryType":"QYHYLB_C3316","pollType":"QYLX_COMMON","regulationType":"1","monitorOnline":"0","monitorErc":"0","monitorVedio":"0","monitorManager":"0","monitorDirect":"1","monitorJygk":"1","pollSourceCode":"HJLY","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司","shortName":"","longitude":"119.661389","latitude":"45.451111","pollSourceOrder":"1","corpCode":null,"corpName":"","createTime":null,"pollSourceAddress":"霍林郭勒市工业园区","dutyPerson":null,"linkMan":"","telephone":"","fax":"0475-2353066","communicateAddress":"","postalCode":"","email":null,"webSite":"","environJobNo":null,"environLinkMan":"","environTel":"","declareCode":null,"outPermitCode":"","prodScale":"","ipAddress":"","mainPollutant":"二氧化硫,氮氧化物,烟尘,氟化物","pollArea":"0","pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null},{"pollSourceId":"150500000054","cityId":"10000","industryType":null,"pollType":null,"regulationType":"4","monitorOnline":"1","monitorErc":"1","monitorVedio":"1","monitorManager":"1","monitorDirect":"1","monitorJygk":"1","pollSourceCode":null,"pollSourceName":"霍林郭勒市金源口电业有限责任公司","shortName":null,"longitude":"119.655556","latitude":"45.230278","pollSourceOrder":null,"corpCode":null,"corpName":null,"createTime":null,"pollSourceAddress":"霍林郭勒市源源工业园区","dutyPerson":null,"linkMan":"王海涛","telephone":null,"fax":"0475-6371166","communicateAddress":null,"postalCode":"029200","email":"","webSite":null,"environJobNo":null,"environLinkMan":null,"environTel":null,"declareCode":null,"outPermitCode":null,"prodScale":null,"ipAddress":null,"mainPollutant":null,"pollArea":null,"pollTypeName":null,"cityName":null,"industryTypeName":null,"regulationTypeName":null,"pollIn":null,"pollsourceList":null}]
         * unitIn : '1','2','3','4','5','6','7','8','9','10','12','13','14','15','16','17','18','19','20','21','22'
         */

        private String userId;
        private String userName;
        private Object password;
        private Object roleId;
        private Object roleName;
        private Object description;
        private Object isSystem;
        private Object isDeleted;
        private String areaCode;
        private String areaName;
        private String unitIn;
        private List<AppPollsBean> appPolls;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getIsSystem() {
            return isSystem;
        }

        public void setIsSystem(Object isSystem) {
            this.isSystem = isSystem;
        }

        public Object getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Object isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getUnitIn() {
            return unitIn;
        }

        public void setUnitIn(String unitIn) {
            this.unitIn = unitIn;
        }

        public List<AppPollsBean> getAppPolls() {
            return appPolls;
        }

        public void setAppPolls(List<AppPollsBean> appPolls) {
            this.appPolls = appPolls;
        }

        public static class AppPollsBean implements Serializable{
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
