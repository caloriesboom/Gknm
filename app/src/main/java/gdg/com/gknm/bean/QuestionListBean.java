package gdg.com.gknm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUO.DG on 2017-9-19.
 * 问题集合实体类
 */

public class QuestionListBean extends BaseResult implements Serializable {

    /**
     * message : success
     * resultCode : true
     * resultEntity : {"pageSize":10,"currentPage":1,"startRecord":0,"totalPage":1,"totalRecord":2,"data":[{"questionId":"12","questionSort":"WTLX_JYFK","questionText":"gggggggmmgmgmppww","comunication":"641564994","questionSolution":"我大概知道是怎么回事了","questionStatus":"1","sortName":"建议反馈"},{"questionId":"14","questionSort":"WTLX_JYFK","questionText":"gggggggmmgmgmppwwmmggmgm","comunication":"6415649941t","questionSolution":"的顶顶顶顶顶顶顶顶顶顶大顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶","questionStatus":"1","sortName":"建议反馈"}]}
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

    public static class ResultEntityBean {
        /**
         * pageSize : 10
         * currentPage : 1
         * startRecord : 0
         * totalPage : 1
         * totalRecord : 2
         * data : [{"questionId":"12","questionSort":"WTLX_JYFK","questionText":"gggggggmmgmgmppww","comunication":"641564994","questionSolution":"我大概知道是怎么回事了","questionStatus":"1","sortName":"建议反馈"},{"questionId":"14","questionSort":"WTLX_JYFK","questionText":"gggggggmmgmgmppwwmmggmgm","comunication":"6415649941t","questionSolution":"的顶顶顶顶顶顶顶顶顶顶大顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶","questionStatus":"1","sortName":"建议反馈"}]
         */

        private int pageSize;
        private int currentPage;
        private int startRecord;
        private int totalPage;
        private int totalRecord;
        private List<DataBean> data;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getStartRecord() {
            return startRecord;
        }

        public void setStartRecord(int startRecord) {
            this.startRecord = startRecord;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * questionId : 12
             * questionSort : WTLX_JYFK
             * questionText : gggggggmmgmgmppww
             * comunication : 641564994
             * questionSolution : 我大概知道是怎么回事了
             * questionStatus : 1
             * sortName : 建议反馈
             */

            private String questionId;
            private String questionSort;
            private String questionText;
            private String comunication;
            private String questionSolution;
            private String questionStatus;
            private String sortName;

            public String getQuestionId() {
                return questionId;
            }

            public void setQuestionId(String questionId) {
                this.questionId = questionId;
            }

            public String getQuestionSort() {
                return questionSort;
            }

            public void setQuestionSort(String questionSort) {
                this.questionSort = questionSort;
            }

            public String getQuestionText() {
                return questionText;
            }

            public void setQuestionText(String questionText) {
                this.questionText = questionText;
            }

            public String getComunication() {
                return comunication;
            }

            public void setComunication(String comunication) {
                this.comunication = comunication;
            }

            public String getQuestionSolution() {
                return questionSolution;
            }

            public void setQuestionSolution(String questionSolution) {
                this.questionSolution = questionSolution;
            }

            public String getQuestionStatus() {
                return questionStatus;
            }

            public void setQuestionStatus(String questionStatus) {
                this.questionStatus = questionStatus;
            }

            public String getSortName() {
                return sortName;
            }

            public void setSortName(String sortName) {
                this.sortName = sortName;
            }
        }
    }
}
