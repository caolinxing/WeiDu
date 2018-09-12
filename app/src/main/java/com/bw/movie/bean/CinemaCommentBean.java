package com.bw.movie.bean;

import java.util.List;

public class CinemaCommentBean {

    /**
     * result : [{"commentContent":"(⊙_⊙)?(⊙o⊙)\u2026\\(^o^)/~","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-06/20180906193720.png","commentId":207,"commentTime":1536288906000,"commentUserId":399,"commentUserName":"白无常","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"适配、优化、AsyncTask、Fragment生命周期调用、MVC、MVP、多渠道打包、Handler","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-31/20180831154205.png","commentId":188,"commentTime":1535979680000,"commentUserId":226,"commentUserName":"阎王爷","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"我","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":186,"commentTime":1535979355000,"commentUserId":230,"commentUserName":"小包子","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐我我我我","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":185,"commentTime":1535979307000,"commentUserId":230,"commentUserName":"小包子","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐我","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":184,"commentTime":1535979304000,"commentUserId":230,"commentUserName":"小包子","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":181,"commentTime":1535979286000,"commentUserId":230,"commentUserName":"小包子","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":182,"commentTime":1535979286000,"commentUserId":230,"commentUserName":"小包子","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":183,"commentTime":1535979286000,"commentUserId":230,"commentUserName":"小包子","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":175,"commentTime":1535979285000,"commentUserId":230,"commentUserName":"小包子","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"泡沫罐","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":176,"commentTime":1535979285000,"commentUserId":230,"commentUserName":"小包子","greatNum":1,"hotComment":0,"isGreat":0}]
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
         * commentContent : (⊙_⊙)?(⊙o⊙)…\(^o^)/~
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-09-06/20180906193720.png
         * commentId : 207
         * commentTime : 1536288906000
         * commentUserId : 399
         * commentUserName : 白无常
         * greatNum : 1
         * hotComment : 0
         * isGreat : 0
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
    }
}
