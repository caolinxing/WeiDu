package com.bw.movie.ui.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAndMovieAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.MovieScheduleBean;
import com.bw.movie.mvp.contract.CinemaAndMovieContract;
import com.bw.movie.mvp.presenter.CinemaAndMoviePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class CinemaAndMovieActivity extends BaseActivity implements CinemaAndMovieContract.View {

    @BindView(R.id.rv_cinemaandmovie)
    RecyclerView rvCinemaandmovie;
    @BindView(R.id.iv_back_cinemaandmovie)
    ImageView iv_back_cinemaandmovie;
    @BindView(R.id.iv_star_cinemaandmovie)
    ImageView iv_star_cinemaandmovie;
    private CinemaInfoBean cinemaInfoBean;
    private CinemaToMovieBean cinemaToMovieBean;
    private MovieScheduleBean movieTimeAndAddress;
    private int cinemaId = 18;
    private int cinemasId = 18;
    private int movieId = 14;
    private int i = 0;
    private CinemaAndMovieContract.Presenter presenter;
    private CinemaAndMovieAdapter adapter;
    private int userId;
    private String sessionId;

    @Override
    public int bindLayout() {
        return R.layout.activity_cinema_and_movie;
    }

    @Override
    public void initData() {
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        userId = userinfo.getInt("userId", 0);
        sessionId = userinfo.getString("sessionId", "1536496959836306");
        cinemaInfoBean = new CinemaInfoBean();
        cinemaToMovieBean = new CinemaToMovieBean();
        movieTimeAndAddress = new MovieScheduleBean();
        presenter = new CinemaAndMoviePresenter(this);
        rvCinemaandmovie.setLayoutManager(new LinearLayoutManager(this));
        EventBus.getDefault().register(this);
        presenter.loadCinemaInfo();
        presenter.loadCinemaMovie();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(EventBean eventBean) {
        cinemaId = eventBean.getCinemaId();
        cinemasId = eventBean.getCinemaId();
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }


    @OnClick({R.id.iv_back_cinemaandmovie, R.id.iv_star_cinemaandmovie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_cinemaandmovie:
                finish();
                break;
            case R.id.iv_star_cinemaandmovie:
                if(userId == 0 || sessionId.equals("")){
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                }else if (cinemaInfoBean.getResult().isFollowCinema()) {
                    presenter.followCinema();
                    presenter.loadCinemaInfo();
                } else {
                    presenter.cancleFollowCinema();
                    presenter.loadCinemaInfo();
                }
                break;
        }
    }

    @Override
    public String getCinemaId() {
        return String.valueOf(cinemaId);
    }

    @Override
    public String getCinemasId() {
        return String.valueOf(cinemasId);
    }

    @Override
    public String getMovieId() {
        return String.valueOf(movieId);
    }

    @Override
    public String getUserId() {
        return String.valueOf(userId);
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setCinemaInfo(CinemaInfoBean cinemaInfoBean) {
        this.cinemaInfoBean.setResult(cinemaInfoBean.getResult());

    }

    @Override
    public void setCinemaMovie(CinemaToMovieBean cinemaToMovieBean) {
        this.cinemaToMovieBean.setResult(cinemaToMovieBean.getResult());
        movieId = cinemaToMovieBean.getResult().get(0).getId();
        presenter.loadMovieSchedule();
    }

    @Override
    public void setMovieSchedule(MovieScheduleBean movieTimeAndAddress) {
        this.movieTimeAndAddress.setResult(movieTimeAndAddress.getResult());
        if (i == 0) {
            adapter = new CinemaAndMovieAdapter(this,
                    cinemaInfoBean, cinemaToMovieBean, this.movieTimeAndAddress);
            rvCinemaandmovie.setAdapter(adapter);
            i++;
        } else {
            adapter.notifyItemChanged(2);
        }
        adapter.setCallback(new CinemaAndMovieAdapter.Callback() {
            @Override
            public void onScrollToGallery(String cinema, String movie) {
                cinemasId = Integer.parseInt(cinema);
                movieId = Integer.parseInt(movie);
                presenter.loadMovieSchedule();
            }
        });
    }

    @Override
    public void setFollowCinema(CinemaCommentBean cinemaInfoBean) {
        Toast.makeText(this,cinemaInfoBean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCancleFollowCinema(CinemaCommentBean cinemaInfoBean) {
        Toast.makeText(this,cinemaInfoBean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFollowStar() {
        iv_star_cinemaandmovie.setImageResource(R.drawable.star_yellow);
    }

    @Override
    public void setCancleFollowStar() {
        iv_star_cinemaandmovie.setImageResource(R.drawable.star_white);
    }


}
