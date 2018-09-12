package com.bw.movie.mvp.presenter;

import com.bw.movie.R;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.MovieScheduleBean;
import com.bw.movie.mvp.contract.CinemaAndMovieContract;
import com.bw.movie.mvp.model.CinemaAndMovieModel;

import java.lang.ref.WeakReference;

public class CinemaAndMoviePresenter extends BasePresenter implements CinemaAndMovieContract.Presenter {

    private CinemaAndMovieContract.View view;
    private CinemaAndMovieContract.Model model;

    public CinemaAndMoviePresenter(Object view) {
        super(view);
        this.view = (CinemaAndMovieContract.View) view;
        model = new CinemaAndMovieModel();
    }

    @Override
    public void loadCinemaInfo() {
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        String cinemaId = view.getCinemaId();
        model.loadCinemaInfo(userId, sessionId, cinemaId, this);
    }

    @Override
    public void loadCinemaMovie() {
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        String cinemaId = view.getCinemaId();
        model.loadCinemaMovie(userId, sessionId, cinemaId, this);
    }

    @Override
    public void loadMovieSchedule() {
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        String cinemasId = view.getCinemasId();
        String movieId = view.getMovieId();
        model.loadMovieSchedule(userId, sessionId, cinemasId, movieId, this);
    }

    @Override
    public void onSuccessToCancleFollowCinema(CinemaCommentBean cinemaInfoBean) {
        view.setCancleFollowCinema(cinemaInfoBean);
    }

    @Override
    public void onSuccessToFollowCinema(CinemaCommentBean cinemaInfoBean) {
        view.setFollowCinema(cinemaInfoBean);
    }

    @Override
    public void followCinema() {
        String cinemaId = view.getCinemaId();
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        model.followCinema(userId, sessionId, cinemaId, this);
    }

    @Override
    public void cancleFollowCinema() {
        String cinemaId = view.getCinemaId();
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        model.cancleFollowCinema(userId, sessionId, cinemaId, this);
    }

    @Override
    public void onSuccessToCinemaInfo(CinemaInfoBean cinemaInfoBean) {
        if(cinemaInfoBean.getResult().isFollowCinema()){
            view.setCancleFollowStar();
        }else {
            view.setFollowStar();
        }
        view.setCinemaInfo(cinemaInfoBean);
    }

    @Override
    public void onSuccessToCinemaToMovie(CinemaToMovieBean cinemaToMovieBean) {
        view.setCinemaMovie(cinemaToMovieBean);
    }

    @Override
    public void onSuccessToMovieTimeAndAddress(MovieScheduleBean movieTimeAndAddress) {
        view.setMovieSchedule(movieTimeAndAddress);
    }

    @Override
    protected WeakReference getWeak() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return (BaseModel) model;
    }
}
