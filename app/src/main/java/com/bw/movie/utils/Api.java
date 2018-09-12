package com.bw.movie.utils;

public class Api {
    public static final String BASE_API="http://172.17.8.100/";
    public static final String HOTMOVIE="movieApi/movie/v1/findHotMovieList";
    public static final String ZHENGZAIMOVIE="movieApi/movie/v1/findReleaseMovieList";
    public static final String JIJIANGMOVIE="movieApi/movie/v1/findComingSoonMovieList";
    public static final String XINGQING="movieApi/movie/v1/findMoviesDetail";
    public static final String MOVIE_PING_LUN_LIST="movieApi/movie/v1/findAllMovieComment";
    public static final String MOVIE_ZHUIPINGLIST="movieApi/movie/v1/findCommentReply";
    public static final String SEND_MOVIE_PING="movieApi/movie/v1/verify/movieComment";
    public static final String SEND_ZHUI_PING="movieApi/movie/v1/verify/commentReply";
    public static final String DIAN_ZAN="movieApi/movie/v1/verify/movieCommentGreat";
    public static final String GUAN_ZHU="movieApi/movie/v1/verify/followMovie";
    public static final String CHOOSE_CINEMA="movieApi/movie/v1/findCinemasListByMovieId";
}
