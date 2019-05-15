package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.FilmCinemaBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.model.MyModel;

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
    public void login(String phone,String pwd) {

        iModel.login(phone, pwd, new MyModel.SetLogin() {
            @Override
            public void Succeed(LoginBean loginBean) {
                ContractInterFace.ILogin iLogin = (ContractInterFace.ILogin) t;
                Log.e("tag","MyPresenter");
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
}
