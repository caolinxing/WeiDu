package com.bw.movie.bean;

public class ResultBean_Can {
    private int userId;
    private int commentId;
    private int movieId;
    private String sessionId;
    private String replyContent;
    private String commentContent;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public ResultBean_Can(int userId, int commentId, int movieId, String sessionId, String replyContent, String commentContent) {
        this.userId = userId;
        this.commentId = commentId;
        this.movieId = movieId;
        this.sessionId = sessionId;
        this.replyContent = replyContent;
        this.commentContent = commentContent;
    }
}
