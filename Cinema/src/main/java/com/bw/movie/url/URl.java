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

    //微信登陆
    public  static  String URL_WECHATLOGIN = "user/v1/weChatBindingLogin";

    //注册
    public  static String URL_REGISTER = "user/v1/registerUser";

    //热门电影
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

    //购票下单
    public static String URL_BUY ="movie/v1/verify/buyMovieTicket";

    //支付
    public static String URL_WECHAT="movie/v1/verify/pay";

    //修改密码
    public static String URL_PWD = "user/v1/verify/modifyUserPwd";

    //修改头像
    public static String URL_HEAD = "user/v1/verify/uploadHeadPic";

    //修改用户信息
    public static String URL_USER = "user/v1/verify/modifyUserInfo";

    //签到
    public static String URL_SIGNIN = "user/v1/verify/userSignIn";

    //查询影院信息明细
    public static String URL_MESSAGE="cinema/v1/findCinemaInfo";

    //查询影院评论信息
    public static String URL_CRITICISM="cinema/v1/findAllCinemaComment";

    //查询推荐影院信息
    public static String URL_RECOMMEND= "cinema/v1/findRecommendCinemas";

    //查询附近影院
    public static String URL_NEARBY= "cinema/v1/findNearbyCinemas";

    //影院轮播
    public static String URL_BANNER=  "movie/v1/findMovieListByCinemaId";

    //影院电影信息明细
    public static String URL_INFO=  "cinema/v1/findCinemaInfo";

    //影院评论
    public static String URL_PING = "cinema/v1/findAllCinemaComment";

    //查询新版本
    public static  String URL_VERSION = "tool/v1/findNewVersion";

    //意见反馈
    public static  String URL_OPINION = "tool/v1/verify/recordFeedBack";

    //查询用户关注的影片列表
    public static  String URL_ATTENTIONFILM ="movie/v1/verify/findMoviePageList";

    //查询用户关注的影院信息
    public static  String URL_ATTENTIONCINEMA ="cinema/v1/verify/findCinemaPageList";

    //用户购票记录查询列表
    public static  String URL_RECORD="user/v1/verify/findUserBuyTicketRecordList";

    // 查询系统消息列表
    public static  String URL_SOUND="tool/v1/verify/findAllSysMsgList";

    //查询用户当前未读消息数量
    public static  String URL_NOREADNUM="tool/v1/verify/findUnreadMessageCount";

    //系统消息读取状态修改
    public static  String URL_CHANGER="tool/v1/verify/changeSysMsgStatus";

    //微信分享前置接口，获取分享所需参数
    public static  String URL_SHAPE = "tool/v1/wxShare";


}
