package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.bean.AlipayBean;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.AttentionCinemaBean;
import com.bw.movie.bean.AttentionFilmBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.BuyBean;
import com.bw.movie.bean.ChangerBean;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FilmCinemaBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.bean.HeadBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.PwdBean;
import com.bw.movie.bean.RecordBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShapeBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.SignBean;
import com.bw.movie.bean.SoundBean;
import com.bw.movie.bean.SumBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.VersionBean;
import com.bw.movie.bean.WechatBean;
import com.bw.movie.bean.WechatLoginBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.model.MyModel;

import java.io.File;

import static com.bw.movie.app.MyApplication.orderId;

/**
 * Project name：WeiDuMovie
 * Time: 2019/5/10 18:37
 * Author: 高海波
 */
public class MyPresenter<T> implements ContractInterFace.IPresenter {

    T t;
    private final ContractInterFace.IModel iModel;

    public MyPresenter(T t) {
        this.t = t;
        iModel = new MyModel();
    }

    @Override
    public void login(String phone, String pwd) {

        iModel.login(phone, pwd, new MyModel.SetLogin() {
            @Override
            public void Succeed(LoginBean loginBean) {
                ContractInterFace.ILogin iLogin = (ContractInterFace.ILogin) t;
                Log.e("tag", "MyPresenter");
                iLogin.login(loginBean);
            }
        });
    }

    @Override
    public void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2) {
        iModel.register(nickName, sex, birthday, phone, emil, pwd, pwd2, new MyModel.SetRegister() {
            @Override
            public void Succeed(RegisterBean registerBean) {
                ContractInterFace.IRegister iRegister = (ContractInterFace.IRegister) t;
                iRegister.register(registerBean.getMessage());
            }
        });
    }

    @Override
    public void popularMovie() {
        iModel.popularMovie(new MyModel.SetPopularMovie() {
            @Override
            public void Succeed(PopularMovieBean popularMovieBean) {
                ContractInterFace.IFilmHome iFilmHome = (ContractInterFace.IFilmHome) t;
                iFilmHome.popularMovie(popularMovieBean);
            }
        });
    }

    @Override
    public void showing() {
        iModel.showing(new MyModel.SetShowing() {
            @Override
            public void Succeed(ShowingBean showingBean) {
                ContractInterFace.IFilmHome iFilmHome = (ContractInterFace.IFilmHome) t;
                iFilmHome.showing(showingBean);
            }
        });
    }

    @Override
    public void beon() {
        iModel.beon(new MyModel.SetBeon() {
            @Override
            public void Succeed(BeonBean beonBean) {
                ContractInterFace.IFilmHome iFilmHome = (ContractInterFace.IFilmHome) t;
                iFilmHome.beon(beonBean);
            }
        });
    }

    @Override
    public void attention(int movieId) {
        iModel.attention(movieId, new MyModel.SetAttention() {
            @Override
            public void Succeed(AttentionBean attentionBean) {
                ContractInterFace.ISearchFilm iSearchFilm = (ContractInterFace.ISearchFilm) t;
                iSearchFilm.attention(attentionBean);
            }
        });
    }

    @Override
    public void noattention(int movieId) {
        iModel.noattention(movieId, new MyModel.SetNoAttention() {
            @Override
            public void Succeed(AttentionBean attentionBean) {
                ContractInterFace.ISearchFilm iSearchFilm = (ContractInterFace.ISearchFilm) t;
                iSearchFilm.noattention(attentionBean);
            }
        });
    }

    @Override
    public void details(int movieId) {
        iModel.details(movieId, new MyModel.SetDetails() {
            @Override
            public void Succeed(DetailsBean detailsBean) {
                ContractInterFace.IDetailsFilm iDetailsFilm = (ContractInterFace.IDetailsFilm) t;
                iDetailsFilm.details(detailsBean);
            }
        });
    }

    @Override
    public void particulars(int movieId) {
        iModel.particulars(movieId, new MyModel.SetParticulars() {
            @Override
            public void Succeed(ParticularsBean particularsBean) {
                ContractInterFace.IDetailsFilm iDetailsFilm = (ContractInterFace.IDetailsFilm) t;
                iDetailsFilm.particulars(particularsBean);
            }
        });
    }

    @Override
    public void review(int movieId, int count) {
        iModel.review(movieId, count, new MyModel.SetReview() {
            @Override
            public void Succeed(ReviewBean reviewBean) {
                ContractInterFace.IDetailsFilm iDetailsFilm = (ContractInterFace.IDetailsFilm) t;
                iDetailsFilm.review(reviewBean);
            }
        });
    }

    @Override
    public void great(int commentId) {
        iModel.great(commentId, new MyModel.SetGreat() {
            @Override
            public void Succeed(GreatBean greatBean) {
                ContractInterFace.IDetailsFilm iDetailsFilm = (ContractInterFace.IDetailsFilm) t;
                iDetailsFilm.great(greatBean);
            }
        });
    }

    @Override
    public void filmReview(int movieId, String commentContent) {
        iModel.filmReview(movieId, commentContent, new MyModel.SetFilmReview() {
            @Override
            public void Succeed(GreatBean greatBean) {
                ContractInterFace.IDetailsFilm iDetailsFilm = (ContractInterFace.IDetailsFilm) t;
                iDetailsFilm.filmReview(greatBean);
            }
        });
    }

    @Override
    public void filmcinema(int movieId) {
        iModel.filmcinema(movieId, new MyModel.SetFilmCinema() {
            @Override
            public void Succeed(FilmCinemaBean filmCinemaBean) {
                ContractInterFace.IFilmCinema iFilmCinema = (ContractInterFace.IFilmCinema) t;
                iFilmCinema.filmcinema(filmCinemaBean);
            }
        });
    }

    @Override
    public void followCinema(int cinemaId) {
        iModel.followCinema(cinemaId, new MyModel.SetFollowCinema() {
            @Override
            public void Succeed(GreatBean greatBean) {
                ContractInterFace.IFilmCinema iFilmCinema = (ContractInterFace.IFilmCinema) t;
                iFilmCinema.followCinema(greatBean);
            }
        });
    }

    @Override
    public void noFollowCinema(int cinemaId) {
        iModel.noFollowCinema(cinemaId, new MyModel.SetNoFollowCinema() {
            @Override
            public void Succeed(GreatBean greatBean) {
                ContractInterFace.IFilmCinema iFilmCinema = (ContractInterFace.IFilmCinema) t;
                iFilmCinema.noFollowCinema(greatBean);
            }
        });
    }

    @Override
    public void schedule(int cinemasId, int movieId) {
        iModel.schedule(cinemasId, movieId, new MyModel.SetSchedule() {
            @Override
            public void Succeed(ScheduleBean scheduleBean) {
                ContractInterFace.ITicket iTicket = (ContractInterFace.ITicket) t;
                iTicket.schedule(scheduleBean);
            }
        });
    }

    @Override
    public void buy(int scheduleId, int amount, String sign) {
        iModel.buy(scheduleId, amount, sign, new MyModel.SetBuy() {
            @Override
            public void Succeed(BuyBean buyBean) {
                ContractInterFace.IBuy iBuy = (ContractInterFace.IBuy) t;
                iBuy.buy(buyBean);
            }
        });

    }

    @Override
    public void message(int cinemaId) {
        iModel.message(cinemaId, new MyModel.SetMessage() {
            @Override
            public void Succeed(CinemaxqBean cinemaxqBean) {
                ContractInterFace.IMessage iMessage = (ContractInterFace.IMessage) t;
                iMessage.message(cinemaxqBean);
            }
        });
    }

    @Override
    public void recommend(int page, int count) {
        iModel.recommend(page, count, new MyModel.SetRecommend() {
            @Override
            public void Succeed(CinemafjBean wechatLoginBean) {
                ContractInterFace.IFilm iFilm = (ContractInterFace.IFilm) t;
                iFilm.recommend(wechatLoginBean);
            }
        });
    }

    @Override
    public void nearby(int page, int count) {
        iModel.nearby(page, count, new MyModel.SetNearby() {
            @Override
            public void Succeed(CinemafjBean wechatLoginBean) {
                ContractInterFace.IFilm iFilm = (ContractInterFace.IFilm) t;
                iFilm.nearby(wechatLoginBean);
            }
        });

    }

    @Override
    public void banners(int cinemaId) {
        iModel.banners(cinemaId, new MyModel.SetBanner() {
            @Override
            public void Succeed(CinemaBannerBean wechatLoginBean) {
                ContractInterFace.IBuyTicket iBuyTicket = (ContractInterFace.IBuyTicket) t;
                iBuyTicket.banners(wechatLoginBean);

            }
        });
    }

    @Override
    public void infos(int cinemaId) {
        iModel.infos(cinemaId, new MyModel.SetInfos() {
            @Override
            public void Succeed(CinemaxqBean wechatLoginBean) {
                ContractInterFace.IBuyTicket  iBuyTicket = (ContractInterFace.IBuyTicket) t;
                iBuyTicket.infos(wechatLoginBean);
            }
        });
    }

    @Override
    public void ping(int cinemaId, int page, int count) {
        iModel.ping(cinemaId, page, count, new MyModel.SetPing() {
            @Override
            public void Succeed(CinemaplBean wechatLoginBean) {
                ContractInterFace.IBuyTicket iBuyTicket = (ContractInterFace.IBuyTicket) t;
                iBuyTicket.ping(wechatLoginBean);
            }
        });
    }

    @Override
    public void version(String ak) {
        iModel.version(ak, new MyModel.SetVersion() {
            @Override
            public void Succeed(VersionBean wechatLoginBean) {
                ContractInterFace.IMy iMy = (ContractInterFace.IMy) t;
                iMy.version(wechatLoginBean);
            }
        });
    }

    @Override
    public void opinion(String content) {
        iModel.opinion(content, new MyModel.SetOpinion() {
            @Override
            public void Succeed(OpinionBean wechatLoginBean) {
                ContractInterFace.IOpinion iMy = (ContractInterFace.IOpinion) t;
                iMy.opinion(wechatLoginBean);
            }
        });
    }

    @Override
    public void attentionFilm(int page, int count) {
        iModel.attentionFilm(page, count, new MyModel.SetAttentionFilm() {
            @Override
            public void Succeed(AttentionFilmBean wechatLoginBean) {
                ContractInterFace.IAttentionAll iAttentionAll = (ContractInterFace.IAttentionAll) t;
                iAttentionAll.attentionFilm(wechatLoginBean);
            }
        });
    }

    @Override
    public void attentionCinema(int page, int count) {
        iModel.attentionCinema(page, count, new MyModel.SetAttentionCinema() {
            @Override
            public void Succeed(AttentionCinemaBean wechatLoginBean) {
                ContractInterFace.IAttentionAll iAttentionAll = (ContractInterFace.IAttentionAll) t;
                iAttentionAll.attentionCinema(wechatLoginBean);
            }
        });

    }

    @Override
    public void record(int page, int count, int status) {
        iModel.record(page, count, status, new MyModel.SetRecord() {
            @Override
            public void Succeed(RecordBean wechatLoginBean) {
                ContractInterFace.IRecord iRecord = (ContractInterFace.IRecord) t;
                iRecord.record(wechatLoginBean);
            }
        });
    }

    @Override
    public void sound(int page, int count) {
        iModel.sound(page, count, new MyModel.SetSound() {
            @Override
            public void Succeed(SoundBean wechatLoginBean) {
                ContractInterFace.ISound iSound = (ContractInterFace.ISound) t;
                iSound.sound(wechatLoginBean);
            }
        });
    }

    @Override
    public void changer(int id) {
        iModel.changer(id, new MyModel.SetChanger() {
            @Override
            public void Succeed(ChangerBean wechatLoginBean) {
                ContractInterFace.ISound iSound = (ContractInterFace.ISound) t;
                iSound.changer(wechatLoginBean);
            }
        });
    }

    @Override
    public void sum() {
        iModel.sum(new MyModel.SetSum() {
            @Override
            public void Succeed(SumBean wechatLoginBean) {
                ContractInterFace.ISound iSound = (ContractInterFace.ISound) t;
                iSound.sum(wechatLoginBean);
            }
        });
    }

    @Override
    public void Desetory() {
        if (t!=null){
            t=null;
        }
    }

    @Override
    public void shape(String time, String sign) {
        iModel.shape(time, sign, new MyModel.SetShape() {
            @Override
            public void Succeed(ShapeBean wechatLoginBean) {
                ContractInterFace.IBuyTicket iBuyTicket = (ContractInterFace.IBuyTicket) t;
                iBuyTicket.shape(wechatLoginBean);
            }
        });
    }

    @Override
    public void wechat(int payType, String orderId) {
        iModel.wechat(payType, orderId, new MyModel.SetWechat() {
            @Override
            public void Succeed(WechatBean wechatBean) {
                ContractInterFace.IBuy iBuy = (ContractInterFace.IBuy) t;
                iBuy.wechat(wechatBean);
            }
        });
    }

    @Override
    public void alipay(int payType, String orderId) {
        iModel.alipay(payType, orderId, new MyModel.SetAlipay() {
            @Override
            public void Succeed(AlipayBean alipayBean) {
                ContractInterFace.IBuy iBuy = (ContractInterFace.IBuy) t;
                iBuy.alipay(alipayBean);
            }
        });
    }

    @Override
    public void wechatlogin(String code) {
        iModel.wechatlogin(code, new MyModel.SetWechatLogin() {
            @Override
            public void Succeed(LoginBean wechatLoginBean) {
                ContractInterFace.IWechatLogin iWechatLogin = (ContractInterFace.IWechatLogin) t;
                iWechatLogin.wechatlogin(wechatLoginBean);
            }
        });
    }

    @Override
    public void pwd(String oldPwd, String newPwd, String newPwd2) {
        iModel.pwd(oldPwd, newPwd, newPwd2, new MyModel.SetPwd() {
            @Override
            public void Succeed(PwdBean wechatLoginBean) {
                ContractInterFace.IMineMessage iMineMessage = (ContractInterFace.IMineMessage) t;
                iMineMessage.pwd(wechatLoginBean);
            }
        });
    }

    @Override
    public void head(File image) {
        iModel.head(image, new MyModel.SetHead() {
            @Override
            public void Succeed(HeadBean wechatLoginBean) {
                ContractInterFace.IMineMessage iMineMessage = (ContractInterFace.IMineMessage) t;
                iMineMessage.head(wechatLoginBean);
            }
        });
    }

    @Override
    public void user(String nickName, int sex, String email) {
        iModel.user(nickName, sex, email, new MyModel.SetUser() {
            @Override
            public void Succeed(UserBean wechatLoginBean) {
                ContractInterFace.IMineMessage iMineMessage = (ContractInterFace.IMineMessage) t;
                iMineMessage.user(wechatLoginBean);
            }
        });
    }

    @Override
    public void signin() {
        iModel.signin(new MyModel.SetSignin() {
            @Override
            public void Succeed(SignBean wechatLoginBean) {
                ContractInterFace.IMy iMy = (ContractInterFace.IMy) t;
                iMy.signin(wechatLoginBean);
            }
        });
    }
}



