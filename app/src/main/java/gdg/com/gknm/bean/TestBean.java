package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-7.
 */

public class TestBean extends BaseResult implements Serializable {

    /**
     * resultCode : true
     * resultEntity : [{"appPolls":[{"enterIn":"","pollSourceId":"1","pollSourceName":"霍煤鸿骏铝电有限公司","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"2","pollSourceName":"内蒙古锦联铝材有限公司","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"3","pollSourceName":"通辽发电总厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"5","pollSourceName":"通辽第二发电厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"6","pollSourceName":"霍林河坑口发电厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"7","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司电力分公司","pollSourceTypeId":"","zoneId":"130"}],"description":"管理员","isDeleted":0,"isSystem":0,"orgId":"","password":"123","roleId":"","roleLevel":"8","roleName":"","unitIn":"1,2,3,4,7,8,5,6,9,10,12,16,17,18,15,13,14,19,20,21,22,24,25,26,27,28,29","userFullname":"管理员","userId":"1","userLevel":"8","userName":"mg","userNo":""}]
     */

    private String resultCode;
    private List<ResultEntityBean> resultEntity;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
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
         * appPolls : [{"enterIn":"","pollSourceId":"1","pollSourceName":"霍煤鸿骏铝电有限公司","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"2","pollSourceName":"内蒙古锦联铝材有限公司","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"3","pollSourceName":"通辽发电总厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"5","pollSourceName":"通辽第二发电厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"6","pollSourceName":"霍林河坑口发电厂","pollSourceTypeId":"","zoneId":"130"},{"enterIn":"","pollSourceId":"7","pollSourceName":"内蒙古霍煤鸿骏铝电有限责任公司电力分公司","pollSourceTypeId":"","zoneId":"130"}]
         * description : 管理员
         * isDeleted : 0
         * isSystem : 0
         * orgId :
         * password : 123
         * roleId :
         * roleLevel : 8
         * roleName :
         * unitIn : 1,2,3,4,7,8,5,6,9,10,12,16,17,18,15,13,14,19,20,21,22,24,25,26,27,28,29
         * userFullname : 管理员
         * userId : 1
         * userLevel : 8
         * userName : mg
         * userNo :
         */

        private String description;
        private int isDeleted;
        private int isSystem;
        private String orgId;
        private String password;
        private String roleId;
        private String roleLevel;
        private String roleName;
        private String unitIn;
        private String userFullname;
        private String userId;
        private String userLevel;
        private String userName;
        private String userNo;
        private List<AppPollsBean> appPolls;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public int getIsSystem() {
            return isSystem;
        }

        public void setIsSystem(int isSystem) {
            this.isSystem = isSystem;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleLevel() {
            return roleLevel;
        }

        public void setRoleLevel(String roleLevel) {
            this.roleLevel = roleLevel;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getUnitIn() {
            return unitIn;
        }

        public void setUnitIn(String unitIn) {
            this.unitIn = unitIn;
        }

        public String getUserFullname() {
            return userFullname;
        }

        public void setUserFullname(String userFullname) {
            this.userFullname = userFullname;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public List<AppPollsBean> getAppPolls() {
            return appPolls;
        }

        public void setAppPolls(List<AppPollsBean> appPolls) {
            this.appPolls = appPolls;
        }

        public static class AppPollsBean {
            /**
             * enterIn :
             * pollSourceId : 1
             * pollSourceName : 霍煤鸿骏铝电有限公司
             * pollSourceTypeId :
             * zoneId : 130
             */

            private String enterIn;
            private String pollSourceId;
            private String pollSourceName;
            private String pollSourceTypeId;
            private String zoneId;

            public String getEnterIn() {
                return enterIn;
            }

            public void setEnterIn(String enterIn) {
                this.enterIn = enterIn;
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

            public String getPollSourceTypeId() {
                return pollSourceTypeId;
            }

            public void setPollSourceTypeId(String pollSourceTypeId) {
                this.pollSourceTypeId = pollSourceTypeId;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }
        }
    }
}
