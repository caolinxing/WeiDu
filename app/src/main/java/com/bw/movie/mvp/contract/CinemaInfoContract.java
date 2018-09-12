package com.bw.movie.mvp.contract;

import com.bw.movie.bean.CinemaCommentBean;

public interface CinemaInfoContract {
    interface Model {
        void loadComment(String userId,String sessionId,String cinemaId,String page,String count,Presenter presenter);
        void pingLun(String userId,String sessionId,String cinemaId,String content,Presenter presenter);
    }

    interface View {
        String getCinemaId();
        String getPage();
        String getCount();
        String getUserId();
        String getSessionId();
        String getContent();
        void setComment(CinemaCommentBean cinemaCommentBean);
        void setPingLun(String content);
    }

    interface Presenter {
        void loadComment();
        void onSuccessToComment(CinemaCommentBean cinemaCommentBean);
        void pingLun();
        void onSuccessToPingLun(CinemaCommentBean cinemaCommentBean);
    }
}
