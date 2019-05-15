package com.bw.movie.url;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 22:10
 * @Description：YangXinYu
 */
public class URl {

    //外网地址
    public static  String URL_OUTERNET = "http://mobile.bwstudent.com/movieApi/";

    //内网地址
    public static String URL_INTRANET =  "http://172.17.8.100/movieApi/";

    //登录
    public  static String URL_LOGIN = "user/v1/login";

    //注册
    public  static String URL_REGISTER = "user/v1/registerUser";

    //热门电影http://172.17.8.100/movieApi/
    public  static String URL_POPULARMOVIE = "movie/v1/findHotMovieList";

    //正在热映
    public static String URL_SHOWING = "movie/v1/findReleaseMovieList";

    //即将上映
    public static String URL_BEON = "movie/v1/findComingSoonMovieList";

    //关注
    public static String URL_ATTENTION = "movie/v1/verify/followMovie";

    //取消关注
    public static String URL_NOATTENTION = "movie/v1/verify/cancelFollowMovie";

    //详情
    public static String URL_DETAILS = " movie/v1/findMoviesById";

    //电影详情
    public static String URL_PARTICULARS = "movie/v1/findMoviesDetail";

    //推荐电影
    public static String URL_CINEMATJ="http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas";

    //电影评论
    public static String URL_REVIEW= "movie/v1/findAllMovieComment";

    //点赞
    public static String URL_GREAT="movie/v1/verify/movieCommentGreat";

    //添加用户对影片的评论
    public static String URL_FILEMREVIEW="movie/v1/verify/movieComment";

    //根据电影ID查询当前排片该电影的影院列表
    public static String URL_FILMCINEMA="movie/v1/findCinemasListByMovieId";

    //关注影院
    public static String URL_FOLLOWCINEMA="cinema/v1/verify/followCinema";

    //取消关注影院
    public static String URL_NOFOLLOWCINEMA="cinema/v1/verify/cancelFollowCinema";

    //根据电影ID和影院ID查询电影排期列表
    public static String URL_SCHEDULE="movie/v1/findMovieScheduleList";

}
