package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaPingAdapter;
import com.bw.movie.adapter.CinemaRecyclerAdapter;
import com.bw.movie.adapter.TicketAdapters;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.ShapeBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.util.EncryptUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recycler.coverflow.RecyclerCoverFlow;

import static com.bw.movie.activity.BuyTicketActivity.SHARE_TYPE.Type_WXSceneSession;
import static com.bw.movie.activity.BuyTicketActivity.SHARE_TYPE.Type_WXSceneTimeline;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/15 15:38
 * Author: 高海波
 */

public class BuyTicketActivity extends BaseActivity implements ContractInterFace.IBuyTicket, ContractInterFace.ITicket {

    @BindView(R.id.cinema_cinemalogo)
    SimpleDraweeView cinemaCinemalogo;
    @BindView(R.id.cinema_cinemaname)
    TextView cinemaCinemaname;
    @BindView(R.id.cinema_cinemaaddress)
    TextView cinemaCinemaaddress;
    @BindView(R.id.xin_id)
    LinearLayout xinId;
    @BindView(R.id.Layout_of_cinema)
    LinearLayout LayoutOfCinema;
    @BindView(R.id.cinema_addres)
    ImageView cinemaAddres;
    @BindView(R.id.cinema_movie_coverflow)
    RecyclerCoverFlow cinemaMovieCoverflow;
    @BindView(R.id.proBars)
    SeekBar proBars;
    @BindView(R.id.ssc_id)
    LinearLayout sscId;
    @BindView(R.id.recy_hot_movie)
    RecyclerView recyHotMovie;
    @BindView(R.id.movieByCinema_finish)
    ImageView movieByCinemaFinish;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;

    private ContractInterFace.IPresenter cinemaPresenter;
    private CinemaRecyclerAdapter mAdapter;
    private TextView details_name;
    private TextView details_phone;
    private TextView details_metro;
    private int id;
    private PopupWindow popupWindow1;
    private RelativeLayout details_relativeLayout;
    private RelativeLayout details_details;
    private RecyclerView evaluate_recycler1;
    private TicketAdapters ticketAdapters;
    private String stringExtra;
    private String address;
    List<CinemaBannerBean.ResultBean> beanList = new ArrayList<>();
    private String APP_ID = "wxb3852e6a6b7d9516";

    private IWXAPI iwxapi;


    enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline}

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema__banner_ticket);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        stringExtra = getIntent().getStringExtra("name");
        cinemaCinemalogo.setImageURI(getIntent().getStringExtra("logo"));
        cinemaCinemaname.setText(stringExtra);
        address = getIntent().getStringExtra("address");
        cinemaCinemaaddress.setText(address);
        cinemaPresenter = new MyPresenter<>(this);
        cinemaPresenter.banners(id);
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.registerApp(APP_ID);
        if (beanList == null) {
            Toast.makeText(this, "该影院没有电影", Toast.LENGTH_LONG).show();
        } else {
            mAdapter = new CinemaRecyclerAdapter(this, beanList);
            cinemaMovieCoverflow.setAdapter(mAdapter);
            //让轮播图显示中间的图片
            cinemaMovieCoverflow.smoothScrollToPosition(1);
            mAdapter.setOnItemClick(new CinemaRecyclerAdapter.OnItemClick() {
                @Override
                public void onItemClick(int position) {
                    cinemaMovieCoverflow.smoothScrollToPosition(position);
                    cinemaPresenter.infos(beanList.get(position).getId());
                    cinemaPresenter.schedule(id, beanList.get(position).getId());
                    MyApplication.FilmNames = beanList.get(position).getName();

                }
            });
        }
        movieByCinemaFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public String getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis();
        //将毫秒值转换为String类型数据
        String time_stamp = String.valueOf(time);
        //返回出去
        return time_stamp;
    }

    @Override
    public void banners(final CinemaBannerBean wechatLoginBean) {
        List<CinemaBannerBean.ResultBean> result = wechatLoginBean.getResult();
        beanList.addAll(result);
        mAdapter.notifyDataSetChanged();
        cinemaPresenter.schedule(id, beanList.get(1).getId());
        MyApplication.FilmNames = wechatLoginBean.getResult().get(1).getName();
    }

    @Override
    public void infos(CinemaxqBean wechatLoginBean) {
        View view_xsq = View.inflate(this, R.layout.cinema_details_popupwindow, null);
        popupWindow1 = new PopupWindow(view_xsq, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        details_name = view_xsq.findViewById(R.id.details_name);
        details_phone = view_xsq.findViewById(R.id.details_phone);
        ImageView request = view_xsq.findViewById(R.id.request);
        details_metro = view_xsq.findViewById(R.id.details_metro);
        details_relativeLayout = view_xsq.findViewById(R.id.details_RelativeLayout);
        //布局
       // details_details = view_xsq.findViewById(R.id.details_details);
        evaluate_recycler1 = view_xsq.findViewById(R.id.evaluate_recycler);
        TextView evaluatess = view_xsq.findViewById(R.id.evaluatess);
        TextView detailsss = view_xsq.findViewById(R.id.detailsss);
        detailsss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_relativeLayout.setVisibility(View.VISIBLE);
                evaluate_recycler1.setVisibility(View.GONE);
            }
        });
        evaluatess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cinemaPresenter.ping(id, 1, 10);
                details_relativeLayout.setVisibility(View.GONE);
                evaluate_recycler1.setVisibility(View.VISIBLE);


            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        details_metro.setText(wechatLoginBean.getResult().getVehicleRoute());
        details_name.setText(wechatLoginBean.getResult().getName());
        details_phone.setText(wechatLoginBean.getResult().getPhone());
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow1.showAtLocation(view_xsq, Gravity.CENTER_HORIZONTAL, 0, 100);

    }

    @Override
    public void ping(CinemaplBean wechatLoginBean) {
        List<CinemaplBean.ResultBean> result = wechatLoginBean.getResult();
        CinemaPingAdapter cinemaPing = new CinemaPingAdapter(result, BuyTicketActivity.this);
        evaluate_recycler1.setLayoutManager(new LinearLayoutManager(this));
        evaluate_recycler1.setAdapter(cinemaPing);
    }

    @Override
    public void shape(ShapeBean wechatLoginBean) {
        // Toast.makeText(this, wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        String appSecret = wechatLoginBean.getAppSecret();

        String decrypt = EncryptUtil.decrypt(appSecret);
        Log.e("tag", "appId   " + wechatLoginBean.getAppId() + "  appSecret :   " + wechatLoginBean.getAppSecret());

        Log.e("tag", "appIdss   " + wechatLoginBean.getAppId() + "  appSecretss :   " + decrypt);


    }


    @Override
    public void schedule(final ScheduleBean scheduleBean) {
        ;
        recyHotMovie.setLayoutManager(new LinearLayoutManager(this));
        ticketAdapters = new TicketAdapters(scheduleBean.getResult(), this);
        recyHotMovie.setAdapter(ticketAdapters);
        ticketAdapters.setOnItemClick(new TicketAdapters.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent1 = new Intent(BuyTicketActivity.this, SeatActivity.class);
                intent1.putExtra("cinemaName", stringExtra);
                intent1.putExtra("cinemaAddress", address);
                intent1.putExtra("filmName", MyApplication.FilmNames);
                intent1.putExtra("startTime", scheduleBean.getResult().get(position).getBeginTime());
                intent1.putExtra("endTime", scheduleBean.getResult().get(position).getEndTime());
                intent1.putExtra("TicketName", scheduleBean.getResult().get(position).getScreeningHall());
                intent1.putExtra("seatsTotal", scheduleBean.getResult().get(position).getSeatsTotal());
                MyApplication.price = scheduleBean.getResult().get(position).getPrice();
                MyApplication.scheduleId = scheduleBean.getResult().get(position).getStatus();
                startActivity(intent1);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cinemaPresenter.Desetory();
        cinemaPresenter = null;
    }

    @OnClick(R.id.cinema_addres)
    public void onViewClicked() {
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        String timeStame = getTimeStame();
        String md5Decode = MyApplication.md5Decode(timeStame + "wxShare" + "movie");
        cinemaPresenter.shape(timeStame, md5Decode);
        AlertDialog.Builder builder = new AlertDialog.Builder(BuyTicketActivity.this);
        builder.setTitle("分享");
        String[] items = {"分享朋友圈", "分享微信好友"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE:
                        share(Type_WXSceneTimeline);
                        break;
                    case TAKE_PICTURE:
                        share(Type_WXSceneSession);
                        break;
                }
            }
        });
        builder.show();
    }

    private void share(SHARE_TYPE type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = "http://www.sohu.com/a/225460183_100054693";
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "Hi,Friend";
        msg.description = "Here is a project done by Yang Xin Yu";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.four);
        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("Req");
        req.message = msg;
        switch (type) {
            case Type_WXSceneSession:
                req.scene = WXSceneSession;
                break;
            case Type_WXSceneTimeline:
                req.scene = WXSceneTimeline;
                break;
        }
        iwxapi.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
