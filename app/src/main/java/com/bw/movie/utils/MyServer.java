package com.bw.movie.utils;


import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.ChooseCinemaBean;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MemberAttentionCinema;
import com.bw.movie.bean.MovieInfoBean;
import com.bw.movie.bean.MovieScheduleBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.bean.MemberAttentionMovies;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.Result_pinglunBean;
import com.bw.movie.bean.SignUpBean;
import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.bean.ZhuiPingListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MyServer {
    /**
     * 热门
     *
     * @修改人和其它信息:
     */
    @GET(Api.HOTMOVIE)
    Observable<MovieBean> loadHotMovie(@Query("page") String page,
                                       @Query("count") String count);

    /**
     * 正在上映
     *
     * @修改人和其它信息:
     */
    @GET(Api.ZHENGZAIMOVIE)
    Observable<MovieBean> rx_zhengzaimovie(@Query("page") String page,
                                           @Query("count") String count);

    /**
     * 即将上映
     *
     * @修改人和其它信息:
     */
    @GET(Api.JIJIANGMOVIE)
    Observable<MovieBean> rx_jijiangmovie(@Query("page") String page,
                                          @Query("count") String count);

    /**
     * 影片详情
     *
     * @修改人和其它信息:
     */
    @GET(Api.XINGQING)
    Observable<MovieInfoBean> rx_moviewInfo(@Query("movieId") String id);

    /**
     * 评论列表
     *
     * @修改人和其它信息:
     */
    @GET(Api.MOVIE_PING_LUN_LIST)
    Observable<MoviePingLunListBean> rx_pinglunList(@Query("movieId") int cinemaId, @Query("page") int page, @Query("count") int count);

    /**
     * 追评列表
     *
     * @修改人和其它信息:
     */
    @GET(Api.MOVIE_ZHUIPINGLIST)
    Observable<ZhuiPingListBean> rx_zhuipingList(@Query("commentId") int cinemaId, @Query("page") int page, @Query("count") int count);

    /**
     * 附近影院
     *
     * @修改人和其它信息:
     */
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<NearCinemaBean> loadFJCineam(@Header("userId") String userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("longitude") String longitude,
                                            @Query("latitude") String latitude,
                                            @Query("page") String page,
                                            @Query("count") String count);

    /**
     * 全部影院
     *
     * @修改人和其它信息:
     */
    @GET("movieApi/cinema/v1/findAllCinemas")
    Observable<AllCinemaBean> loadAllCineam(@Header("userId") String userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("page") String page,
                                            @Query("count") String count);

    /**
     * 查询用户关注的电影列表
     *
     * @修改人和其它信息:
     */
    @GET("movieApi/movie/v1/verify/findMoviePageList")
    Observable<MemberAttentionMovies> getAttentionMovies(@Header("userId") int userId,
                                                         @Header("sessionId") String sessionId,
                                                         @Query("page") String page,
                                                         @Query("count") String count);

    //查询
    @GET("movieApi/cinema/v1/verify/findCinemaPageList")
    Observable<MemberAttentionCinema> getAttentionCinema(@Header("userId") String userId,
                                                         @Header("sessionId") String sessionId,
                                                         @Query("page") String page,
                                                         @Query("count") String count
    );

    @GET("movieApi/cinema/v1/findAllCinemaComment")
    Observable<CinemaCommentBean> loadCinemaComment(@Header("userId") String userId,
                                                    @Header("sessionId") String sessionId,
                                                    @Query("cinemaId") String cinemaId,
                                                    @Query("page") String page,
                                                    @Query("count") String count);

    @GET("movieApi/cinema/v1/findCinemaInfo")
    Observable<CinemaInfoBean> loadCinemaInfo(@Header("userId") String userId,
                                              @Header("sessionId") String sessionId,
                                              @Query("cinemaId") String cinemaId);

    @GET("movieApi/movie/v1/findMovieListByCinemaId")
    Observable<CinemaToMovieBean> loadMovieToCinema(@Header("userId") String userId,
                                                    @Header("sessionId") String sessionId,
                                                    @Query("cinemaId") String cinemaId);

    @GET("movieApi/movie/v1/findMovieScheduleList")
    Observable<MovieScheduleBean> loadMovieTimeAndAddress(@Header("userId") String userId,
                                                          @Header("sessionId") String sessionId,
                                                          @Query("cinemasId") String cinemasId,
                                                          @Query("movieId") String movieId);

    //关注影院
    @GET("movieApi/cinema/v1/verify/followCinema")
    Observable<CinemaCommentBean> followCinema(@Header("userId") String userId,
                                               @Header("sessionId") String sessionId,
                                               @Query("cinemaId") String cinemaId);

    //取消关注影院
    @GET("movieApi/cinema/v1/verify/cancelFollowCinema")
    Observable<CinemaCommentBean> cancleFollowCinema(@Header("userId") String userId,
                                                     @Header("sessionId") String sessionId,
                                                     @Query("cinemaId") String cinemaId);

    //影院评论点赞
    @POST("movieApi/cinema/v1/verify/cinemaCommentGreat")
    Observable<CinemaCommentBean> cinemaCommentGreat(@Header("userId") String userId,
                                                     @Header("sessionId") String sessionId,
                                                     @Query("commentId") String commentId);

    //影院评论点赞
    @POST("movieApi/cinema/v1/verify/cinemaComment")
    @FormUrlEncoded
    Observable<CinemaCommentBean> pingLun(@Header("userId") String userId,
                                          @Header("sessionId") String sessionId,
                                          @Field("cinemaId")String cinemaId,
                                          @Field("commentContent")String content);
    //注册
    @POST("movieApi/user/v1/registerUser")
    @FormUrlEncoded   //使用网页标准的表单提交
    Observable<SignUpBean> signUp(@Field("nickName") String nickName,
                                  @Field("phone") String phone,
                                  @Field("pwd") String pwd,
                                  @Field("pwd2") String pwd2,
                                  @Field("sex") int sex,
                                  @Field("birthday") String birthday,
                                  @Field("imei") String imei,
                                  @Field("ua") String ua,
                                  @Field("screenSize") String screenSize,
                                  @Field("os") String os,
                                  @Field("email") String ema
    );

    //登录
    @POST("movieApi/user/v1/login")
    @FormUrlEncoded   //使用网页标准的表单提交
    Observable<LoginBean> login(@Field("phone") String phone,
                                @Field("pwd") String pwd
    );

    //查询用户的购票相关信息
    @GET("movieApi/user/v1/verify/findUserBuyTicketRecordList")
    Observable<TicketRecordBean> getTicketRecord(@Header("userId") int userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Query("page") String page,
                                                 @Query("count") String count);

    /**
     * 9/8 16：22
     */

    /**
     *添加用户对影片的评论
     */
    @POST(Api.SEND_MOVIE_PING)
    @FormUrlEncoded
    Observable<Result_pinglunBean> sendMoviePing(@Header("userId") int userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Field("movieId") int movieId,
                                                 @Field("commentContent") String commentContent);
    /**
     *添加用户对评论的回复
     */
    @POST(Api.SEND_ZHUI_PING)
    @FormUrlEncoded
    Observable<Result_pinglunBean> sendZhuiPing(@Header("userId") int userId,
                                                @Header("sessionId") String sessionId,
                                                @Field("commentId") int commentId,
                                                @Field("replyContent") String replyContent);
    /**
     *点赞
     */
    @POST(Api.DIAN_ZAN)
    @FormUrlEncoded
    Observable<Result_pinglunBean> sendZan(@Header("userId") int userId,
                                           @Header("sessionId") String sessionId,
                                           @Field("commentId") int commentId);
    /**
     * 9.10
     * 关注
     */
    @GET(Api.GUAN_ZHU)
    Observable<Result_pinglunBean> rx_guanzhu(@Header("userId") int userId,
                                            @Header("sessionId") String sessionId,
                                              @Query("movieId") int movieId);

    /**
     * 选择影院
     */
    @GET(Api.CHOOSE_CINEMA)
    Observable<ChooseCinemaBean> rx_chooseCinema(@Query("movieId") String movieId);

}
