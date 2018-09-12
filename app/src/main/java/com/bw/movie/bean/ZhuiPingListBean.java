package com.bw.movie.bean;

import java.util.List;

public class ZhuiPingListBean {

    /**
     * result : [{"replyContent":"ok","replyHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","replyTime":1535634909000,"replyUserId":319,"replyUserName":"ww"},{"replyContent":"豪恩","replyHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","replyTime":1535632175000,"replyUserId":307,"replyUserName":"悟空"},{"replyContent":"你是谁","replyHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-30/20180830191312.png","replyTime":1535462411000,"replyUserId":215,"replyUserName":"陈佳是逗比"},{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1534216056000,"replyUserId":6,"replyUserName":"谁的益达"},{"replyContent":"赞赞啊","replyHeadPic":"http://172.17.8.100/images/head_pic/bwjy.jpg","replyTime":1533719015000,"replyUserId":6,"replyUserName":"谁的益达"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * replyContent : ok
         * replyHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * replyTime : 1535634909000
         * replyUserId : 319
         * replyUserName : ww
         */

        private String replyContent;
        private String replyHeadPic;
        private long replyTime;
        private int replyUserId;
        private String replyUserName;

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(String replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }

        public long getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(long replyTime) {
            this.replyTime = replyTime;
        }

        public int getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(int replyUserId) {
            this.replyUserId = replyUserId;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }
    }
}
