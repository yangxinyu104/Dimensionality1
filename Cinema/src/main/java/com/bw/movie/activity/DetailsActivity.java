package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.adapter.ReviewAdapter;
import com.bw.movie.adapter.WaterFallAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PersonCard;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements ContractInterFace.IDetailsFilm {


    @BindView(R.id.details_CheckBox)
    ImageView detailsCheckBox;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_SimpleDraweeView)
    SimpleDraweeView detailsSimpleDraweeView;
    @BindView(R.id.detailss_xq)
    TextView detailssXq;
    @BindView(R.id.detailss_yg)
    TextView detailssYg;
    @BindView(R.id.detailss_jz)
    TextView detailssJz;
    @BindView(R.id.detailss_yp)
    TextView detailssYp;
    @BindView(R.id.linear_id)
    LinearLayout linearId;
    @BindView(R.id.detailss_finish)
    ImageView detailssFinish;
    @BindView(R.id.detailss_gm)
    Button detailssGm;
    @BindView(R.id.details_RelativeLayout)
    RelativeLayout detailsRelativeLayout;
    @BindView(R.id.details_SimpleDraweeView_alpha)
    SimpleDraweeView detailsSimpleDraweeViewAlpha;
    @BindView(R.id.details_popwName)
    TextView detailsPopwName;
    public ContractInterFace.IPresenter iPresenter;
    private int id;
    private SimpleDraweeView simpleDraweeView;
    private TextView pop_name;
    private TextView pop_tvDaoyan;
    private TextView Pop_TvTime;
    private TextView pop_tvAddress;
    private TextView pop_tvLeixing;
    private TextView pop_jianjie;
    private TextView yan_1;
    private TextView yan_2;
    private TextView yan_3;
    private TextView yan_4;
    private TextView jue_1;
    private TextView jue_2;
    private TextView jue_3;
    private TextView jue_4;
    private PopupWindow popupWindow;
    private PopupWindow popupWindow1;
    private VideoView popw_foreshow_video1;
    private VideoView popw_foreshow_video2;
    private VideoView popw_foreshow_video3;
    private PopupWindow popupWindow2;
    private PopupWindow popupWindow3;
    public ReviewAdapter adapter;
    private PopupWindow popupWindow4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Log.e("tag", "flag : " + MyApplication.flag);
        if (MyApplication.flag==1) {
            detailsCheckBox.setImageResource(R.mipmap.collection_selected);
        } else if (MyApplication.flag==2) {
            detailsCheckBox.setImageResource(R.mipmap.collection);
        }

        Intent intent = getIntent();
        id = intent.getIntExtra("aid", 0);
        MyApplication.movieId = id;
        iPresenter = new MyPresenter<>(this);
        iPresenter.details(id);
        iPresenter.particulars(id);
        iPresenter.review(id,10);
    }

    @OnClick({R.id.detailss_xq, R.id.detailss_yg, R.id.detailss_jz, R.id.detailss_yp, R.id.detailss_finish, R.id.detailss_gm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detailss_xq:
                detailsPopwName.setText(MyApplication.resultBean.getName());
                View  view_xq = View.inflate(this, R.layout.popw_particulars, null);
                popupWindow = new PopupWindow(view_xq,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                PopwParticulars(view_xq);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(view_xq,Gravity.CENTER_HORIZONTAL,0,100);
                break;
            case R.id.detailss_yg:
                detailsPopwName.setText(MyApplication.resultBean.getName());
                View  view_yg = View.inflate(this, R.layout.popw_foreshow, null);
                popupWindow1 = new PopupWindow(view_yg,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                popw_foreshow_video1 = view_yg.findViewById(R.id.popw_foreshow_video1);
                popw_foreshow_video2 = view_yg.findViewById(R.id.popw_foreshow_video2);
                popw_foreshow_video3 = view_yg.findViewById(R.id.popw_foreshow_video3);
                popw_foreshow_video1.setVideoURI(Uri.parse(MyApplication.resultBean.getShortFilmList().get(0).getVideoUrl()));
                popw_foreshow_video1.requestFocus();
                if (popw_foreshow_video1.isPlaying()){
                    popw_foreshow_video1.pause();
                }
                popw_foreshow_video1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popw_foreshow_video1.start();
                        popw_foreshow_video1.setBackgroundResource(0);
                        return false;
                    }
                });

                popw_foreshow_video2.setVideoURI(Uri.parse(MyApplication.resultBean.getShortFilmList().get(1).getVideoUrl()));
                popw_foreshow_video2.requestFocus();
                if (popw_foreshow_video2.isPlaying()){
                    popw_foreshow_video2.pause();
                }
                popw_foreshow_video2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popw_foreshow_video2.start();
                        popw_foreshow_video2.setBackgroundResource(0);
                        return false;
                    }
                });

                popw_foreshow_video3.setVideoURI(Uri.parse(MyApplication.resultBean.getShortFilmList().get(2).getVideoUrl()));
                popw_foreshow_video3.requestFocus();
                if (popw_foreshow_video3.isPlaying()){
                    popw_foreshow_video3.pause();
                }
                popw_foreshow_video3.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popw_foreshow_video3.start();
                        popw_foreshow_video3.setBackgroundResource(0);
                        return false;
                    }
                });


                ImageView popw_foreshow_finish = view_yg.findViewById(R.id.popw_foreshow_finish);
                popw_foreshow_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow1.dismiss();
                        detailsPopwName.setText("电影详情");
                        popw_foreshow_video3.pause();
                        popw_foreshow_video3.stopPlayback();
                    }
                });
                popupWindow1.setBackgroundDrawable(new BitmapDrawable());
                popupWindow1.setOutsideTouchable(true);
                popupWindow1.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow1.showAtLocation(view_yg,Gravity.CENTER_HORIZONTAL,0,100);
                break;
            case R.id.detailss_jz:
                detailsPopwName.setText(MyApplication.resultBean.getName());
                 View  view_jz = View.inflate(this, R.layout.popw_photo, null);
                popupWindow2 = new PopupWindow(view_jz,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
               ImageView popw_photo_finish =  view_jz.findViewById(R.id.popw_photo_finish);
                popw_photo_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow2.dismiss();
                        detailsPopwName.setText("电影详情");
                    }
                });
               RecyclerView  popw_photo_RecyclerView = view_jz.findViewById(R.id.popw_photo_RecyclerView);
                StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                List<PersonCard> personCards = buildData(MyApplication.resultBean);
                WaterFallAdapter waterFallAdapter = new WaterFallAdapter(personCards,this);
                popw_photo_RecyclerView.setLayoutManager(mLayoutManager);
                popw_photo_RecyclerView.setAdapter(waterFallAdapter);
                popupWindow2.setBackgroundDrawable(new BitmapDrawable());
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow2.showAtLocation(view_jz,Gravity.CENTER_HORIZONTAL,0,100);
                break;
            case R.id.detailss_yp:
                detailsPopwName.setText(MyApplication.resultBean.getName());
                View  view_yp = View.inflate(this, R.layout.popw_review, null);
                popupWindow3 = new PopupWindow(view_yp,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ImageView popw_review_finish =  view_yp.findViewById(R.id.popw_review_finish);
                XRecyclerView popw_review_RecyclerView  = view_yp.findViewById(R.id.popw_review_RecyclerView);
                ImageView popw_review_finishs =  view_yp.findViewById(R.id.popw_review_finishs);
                ImageView  popw_review_pl = view_yp.findViewById(R.id.popw_review_pl);
                popw_review_pl.setOnClickListener(new View.OnClickListener() {

                    private EditText popw_edit_et;

                    @Override
                    public void onClick(View v) {
                        View  view_yps = View.inflate(DetailsActivity.this, R.layout.popw_edit, null);
                        popupWindow4 = new PopupWindow(view_yps,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                        popw_edit_et = view_yps.findViewById(R.id.popw_edit_et);
                        Button popw_edit_send = view_yps.findViewById(R.id.popw_edit_send);
                        popw_edit_send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                iPresenter.filmReview(id,popw_edit_et.getText().toString());
                                popupWindow4.dismiss();
                            }
                        });
                        popupWindow4.setFocusable(true);
                        popupWindow4.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow4.setOutsideTouchable(true);
                        popupWindow4.setAnimationStyle(R.style.popwin_anim_style);
                        popupWindow4.showAtLocation(view_yps,Gravity.CENTER_HORIZONTAL,0,100);

                    }
                });
                popw_review_finishs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow3.dismiss();
                        detailsPopwName.setText("电影详情");
                    }
                });
                popw_review_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new ReviewAdapter(MyApplication.reviewBean,this);
                popw_review_RecyclerView.setAdapter(adapter);

                popw_review_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow3.dismiss();
                        detailsPopwName.setText("电影详情");
                    }
                });
                popupWindow3.setBackgroundDrawable(new BitmapDrawable());
                popupWindow3.setOutsideTouchable(true);
                popupWindow3.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow3.showAtLocation(view_yp,Gravity.CENTER_HORIZONTAL,0,100);

                break;
            case R.id.detailss_finish:
                finish();
                break;
            case R.id.detailss_gm:
                Intent intent = new Intent(this,FilmCinemaActivity.class);
                startActivity(intent);
                break;
        }
    }

    private List<PersonCard> buildData(ParticularsBean.ResultBean resultBean) {
        List<PersonCard> list = new ArrayList<>();
        for(int i=0;i<6;i++) {
            PersonCard p = new PersonCard();
            p.avatarUrl = resultBean.getPosterList().get(i);
            p.imgHeight = (i % 2)*100 + 400; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            list.add(p);
        }
        return  list;
    }

    private void PopwParticulars(View view_xq) {
        simpleDraweeView = view_xq.findViewById(R.id.popw_particulars_SimpleDraweeView);
        simpleDraweeView.bringToFront();
        pop_name = view_xq.findViewById(R.id.Pop_name);
        pop_tvDaoyan = view_xq.findViewById(R.id.Pop_TvDaoyan);
        Pop_TvTime = view_xq.findViewById(R.id.Pop_TvTime);
        pop_tvAddress = view_xq.findViewById(R.id.Pop_TvAddress);
        pop_tvLeixing = view_xq.findViewById(R.id.Pop_TvLeixing);
        pop_jianjie = view_xq.findViewById(R.id.Pop_Jianjie);
        yan_1 = view_xq.findViewById(R.id.Yan_1);
        yan_2 = view_xq.findViewById(R.id.Yan_2);
        yan_3 = view_xq.findViewById(R.id.Yan_3);
        yan_4 = view_xq.findViewById(R.id.Yan_4);
        jue_1 = view_xq.findViewById(R.id.Jue_1);
        jue_2 = view_xq.findViewById(R.id.Jue_2);
        jue_3 = view_xq.findViewById(R.id.Jue_3);
        jue_4 = view_xq.findViewById(R.id.Jue_4);

        ImageView Pop_down = view_xq.findViewById(R.id.Pop_down);
        Pop_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                detailsPopwName.setText("电影详情");
            }
        });

        simpleDraweeView.setImageURI(MyApplication.resultBean.getImageUrl());
        pop_name.setText(MyApplication.resultBean.getName());
        pop_tvDaoyan.setText(MyApplication.resultBean.getMovieTypes());
        Pop_TvTime.setText(MyApplication.resultBean.getDirector());
        pop_tvAddress.setText(MyApplication.resultBean.getDuration());
        pop_tvLeixing.setText(MyApplication.resultBean.getPlaceOrigin());
        pop_jianjie.setText(MyApplication.resultBean.getSummary());
        String[] sourceStrArray = MyApplication.resultBean.getStarring().split(",");
        yan_1.setText(sourceStrArray[0]);
        yan_2.setText(sourceStrArray[1]);
        yan_3.setText(sourceStrArray[2]);
        yan_4.setText(sourceStrArray[3]);
    }

    @Override
    public void details(DetailsBean detailsBean) {
        detailsSimpleDraweeViewAlpha.setImageURI(detailsBean.getResult().getImageUrl());
        detailsName.setText(detailsBean.getResult().getName());
        MyApplication.FilmName = detailsBean.getResult().getName();
        detailsSimpleDraweeView.setImageURI(detailsBean.getResult().getImageUrl());
    }

    @Override
    public void particulars(ParticularsBean particularsBean) {
        ParticularsBean.ResultBean result = particularsBean.getResult();
        MyApplication.resultBean = result;


    }

    @Override
    public void review(ReviewBean reviewBean) {
        List<ReviewBean.ResultBean> result = reviewBean.getResult();
        MyApplication.reviewBean = result;
    }

    @Override
    public void great(GreatBean greatBean) {
        Toast.makeText(this, greatBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void filmReview(GreatBean greatBean) {
        Toast.makeText(this, greatBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
