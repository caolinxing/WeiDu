package com.bw.movie.mvp.contract;

import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.MovieScheduleBean;

public interface CinemaAndMovieContract {
    interface Model {
        void followCinema(String userId,String sessionId,String cinemaId,Presenter presenter);
        void cancleFollowCinema(String userId,String sessionId,String cinemaId,Presenter presenter);
        void loadCinemaInfo(String userId,String sessionId,String cinemaId,Presenter presenter);
        void loadCinemaMovie(String userId,String sessionId,String cinemaId,Presenter presenter);
        void loadMovieSchedule(String userId,String sessionId,String cinemasId,String movieId,Presenter presenter);
    }

    interface View {
        String getCinemaId();
        String getCinemasId();
        String getMovieId();
        String getUserId();
        String getSessionId();
        void setCinemaInfo(CinemaInfoBean cinemaInfoBean);
        void setCinemaMovie(CinemaToMovieBean cinemaToMovieBean);
        void setMovieSchedule(MovieScheduleBean movieTimeAndAddress);
        void setFollowCinema(CinemaCommentBean cinemaInfoBean);
        void setCancleFollowCinema(CinemaCommentBean cinemaInfoBean);
        void setFollowStar();
        void setCancleFollowStar();
    }

    interface Presenter {
        void followCinema();
        void cancleFollowCinema();
        void loadCinemaInfo();
        void loadCinemaMovie();
        void loadMovieSchedule();
        void onSuccessToCancleFollowCinema(CinemaCommentBean cinemaInfoBean);
        void onSuccessToFollowCinema(CinemaCommentBean cinemaInfoBean);
        void onSuccessToCinemaInfo(CinemaInfoBean cinemaInfoBean);
        void onSuccessToCinemaToMovie(CinemaToMovieBean cinemaToMovieBean);
        void onSuccessToMovieTimeAndAddress(MovieScheduleBean movieTimeAndAddress);
    }
}
