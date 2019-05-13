package com.bw.movie.contract;

import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.model.MyModel;

/**
 * Project name：WeiDuMovie
 * Time: 2019/5/10 18:22
 * Author: 高海波
 */
public interface ContractInterFace {

    //p层
    public interface  IPresenter{
        void login(String phone, String pwd);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2);
        void popularMovie();
        void showing();
        void beon();
        void attention(int movieId);
        void noattention(int movieId);
        void details(int movieId);
        void particulars(int movieId);
    }
    //p层
    public interface  IModel{
        void login(String phone, String pwd,  MyModel.SetLogin setLogin);
        void register(String nickName, int sex, String birthday, String phone, String emil, String pwd, String pwd2, final MyModel.SetRegister setRegister);
        void popularMovie(MyModel.SetPopularMovie setPopularMovie);
        void showing(MyModel.SetShowing setShowing);
        void beon(MyModel.SetBeon setBeon);
        void attention(int movieId,MyModel.SetAttention setAttention);
        void noattention(int movieId,MyModel.SetNoAttention setNoAttention);
        void details(int movieId,  MyModel.SetDetails setDetails);
        void particulars(int movieId,  MyModel.SetParticulars setParticulars);
    }
    public  interface  ILogin{
        void login(LoginBean loginBean);
    }
    public  interface  IRegister{
        void register(String message);
    }
    public interface  IFilmHome{
        void popularMovie(PopularMovieBean popularMovieBean);
        void showing(ShowingBean showingBean);
        void beon(BeonBean beonBean);
    }
    public interface ISearchFilm{
        void attention(AttentionBean attentionBean);
        void noattention(AttentionBean attentionBean);
    }
    public interface IDetailsFilm{
        void details(DetailsBean detailsBean);
        void particulars(ParticularsBean particularsBean);
    }


}
