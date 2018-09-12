package com.bw.movie.mvp.contract;

import com.bw.movie.bean.CinemaCommentBean;

public interface AllCinemaCommentContract {
    interface Model {

        void loadAllCinemaComment(String userId,String sessionId,String cinemaId,String page,String count,Presenter presenter);

    }

    interface View {
        void setAllCinemaComment(CinemaCommentBean cinemaComment);
        String getCinemaId();
        String getPage();
        String getCount();
    }

    interface Presenter {
        void loadAllCinemaComment();
        void onSuccessToAllCinemaComment(CinemaCommentBean cinemaComment);
    }
}
