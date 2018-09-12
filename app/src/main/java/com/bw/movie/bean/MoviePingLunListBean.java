package com.bw.movie.bean;

import java.util.List;

public class MoviePingLunListBean {

    /**
     * result : [{"commentContent":"11","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-02/20180902185703.unknown","commentId":319,"commentTime":1536238260000,"commentUserId":336,"commentUserName":"dd","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"11","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-02/20180902185703.unknown","commentId":318,"commentTime":1536238254000,"commentUserId":336,"commentUserName":"dd","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"萨达所大所多大所撒多撒23所大2","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-02/20180902185703.unknown","commentId":317,"commentTime":1536238119000,"commentUserId":336,"commentUserName":"dd","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"1602A赵学冲","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-31/20180831093828.unknown","commentId":309,"commentTime":1536221855000,"commentUserId":324,"commentUserName":"李开发","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":1},{"commentContent":"1111","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-31/20180831093828.unknown","commentId":306,"commentTime":1536220690000,"commentUserId":324,"commentUserName":"李开发","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0}]
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
         * commentContent : 11
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-09-02/20180902185703.unknown
         * commentId : 319
         * commentTime : 1536238260000
         * commentUserId : 336
         * commentUserName : dd
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "commentContent='" + commentContent + '\'' +
                    ", commentHeadPic='" + commentHeadPic + '\'' +
                    ", commentId=" + commentId +
                    ", commentTime=" + commentTime +
                    ", commentUserId=" + commentUserId +
                    ", commentUserName='" + commentUserName + '\'' +
                    ", greatNum=" + greatNum +
                    ", hotComment=" + hotComment +
                    ", isGreat=" + isGreat +
                    ", replyNum=" + replyNum +
                    '}';
        }
    }
}
