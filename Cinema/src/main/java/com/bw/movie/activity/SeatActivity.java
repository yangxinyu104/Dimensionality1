package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.AlipayBean;
import com.bw.movie.bean.BuyBean;
import com.bw.movie.bean.WechatBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.customview.MoveSeatView;
import com.bw.movie.R;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.util.AuthResult;
import com.bw.movie.util.PayResult;
import com.bw.movie.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;
import com.bw.movie.R;
import com.bw.movie.customview.MoveSeatView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SeatActivity extends BaseActivity implements ContractInterFace.IBuy {

    @BindView(R.id.seat_cinemaName)
    TextView seatCinemaName;
    @BindView(R.id.seat_cinemaAddress)
    TextView seatCinemaAddress;
    @BindView(R.id.seat_filmName)
    TextView seatFilmName;
    @BindView(R.id.seat_startTime)
    TextView seatStartTime;
    @BindView(R.id.seat_endTime)
    TextView seatEndTime;
    @BindView(R.id.seat_TicketName)
    TextView seatTicketName;
    private MoveSeatView moveSeatView;
    int selectSum = 0;
    private PopupWindow popupWindow3;
    private PopupWindow popupWindow4;
    private ContractInterFace.IPresenter iPresenter;
    private ImageView popw_zf_wechatRadioButton;
    private ImageView popw_zf_alipay_radioButton;
    private TextView popw_zf_zf;
    private PopupWindow popupWindow41;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

   /* @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    *//**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     *//*
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(SeatActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SeatActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(SeatActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(SeatActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        ButterKnife.bind(this);
        moveSeatView = findViewById(R.id.seat_MoveSeatView);
        Intent intent = getIntent();
        String cinemaName = intent.getStringExtra("cinemaName");
        String cinemaAddress = intent.getStringExtra("cinemaAddress");
        String filmName = intent.getStringExtra("filmName");
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");
        String TicketName = intent.getStringExtra("TicketName");
        int seatsTotal = intent.getIntExtra("seatsTotal", 0);
        seatCinemaName.setText(cinemaName);
        seatCinemaAddress.setText(cinemaAddress);
        seatFilmName.setText(filmName);
        seatStartTime.setText(startTime);
        seatEndTime.setText(endTime);
        seatTicketName.setText(TicketName);

        iPresenter = new MyPresenter<>(this);

        regToWx();
        moveSeatView.setScreenName(TicketName);//设置屏幕名称
        moveSeatView.setMaxSelected(5);//设置最多选中
        moveSeatView.setSeatChecker(new MoveSeatView.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                int row1 = row + 1;
                int column1 = column + 1;
                selectSum++;
                View view_yp = View.inflate(SeatActivity.this, R.layout.popw_xd, null);
                popupWindow3 = new PopupWindow(view_yp, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView popw_xd_finish = view_yp.findViewById(R.id.popw_xd_finish);
                TextView popw_xd_price = view_yp.findViewById(R.id.popw_xd_price);
                ImageView popw_xd_dui = view_yp.findViewById(R.id.popw_xd_dui);
                popw_xd_dui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = MyApplication.UserId + "" + MyApplication.scheduleId + "" + selectSum + "movie";
                        String decode = MyApplication.md5Decode(s);
                        iPresenter.buy(MyApplication.scheduleId, selectSum, decode);
                        popupWindow3.dismiss();


                        View view_yps = View.inflate(SeatActivity.this, R.layout.popw_zf, null);
                        popupWindow41 = new PopupWindow(view_yps, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        ImageView popw_zf_finish = view_yps.findViewById(R.id.popw_zf_finish);
                        LinearLayout popw_zf_wechat = view_yps.findViewById(R.id.popw_zf_wechat);
                        LinearLayout popw_zf_alipay = view_yps.findViewById(R.id.popw_zf_alipay);
                        popw_zf_wechatRadioButton = view_yps.findViewById(R.id.popw_zf_wechatRadioButton);
                        popw_zf_alipay_radioButton = view_yps.findViewById(R.id.popw_zf_alipay_RadioButton);
                        popw_zf_zf = view_yps.findViewById(R.id.popw_zf_zf);
                        popw_zf_wechat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popw_zf_wechatRadioButton.setBackgroundResource(R.drawable.yuan_shape_select);
                                popw_zf_zf.setVisibility(View.VISIBLE);
                                popw_zf_zf.setText("微信支付" + MyApplication.sum + "元");
                                popw_zf_alipay_radioButton.setBackgroundResource(R.drawable.yuan_shape);
                                MyApplication.type = 1;
                            }
                        });
                        popw_zf_alipay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popw_zf_alipay_radioButton.setBackgroundResource(R.drawable.yuan_shape_select);
                                popw_zf_zf.setVisibility(View.VISIBLE);
                                popw_zf_zf.setText("支付宝支付" + MyApplication.sum + "元");
                                popw_zf_wechatRadioButton.setBackgroundResource(R.drawable.yuan_shape);
                                MyApplication.type = 2;
                            }
                        });
                        popw_zf_finish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow41.dismiss();
                            }
                        });
                        popw_zf_zf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (MyApplication.type == 1) {
                                    iPresenter.wechat(1, MyApplication.orderId);
                                } else if (MyApplication.type == 2) {
                                    iPresenter.alipay(2, MyApplication.orderId);
                                }
                            }
                        });

                        popupWindow41.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow41.setOutsideTouchable(true);
                        popupWindow41.setAnimationStyle(R.style.popwin_anim_style);
                        popupWindow41.showAtLocation(view_yps, Gravity.CENTER_HORIZONTAL, 0, 100);

                    }
                });
                popw_xd_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow3.dismiss();
                    }
                });
                Log.e("tag", MyApplication.price + "   price ");
                Log.e("tag", selectSum + "" + "   selectSum ");
                //Log.e("tag", Double.parseDouble(price) * selectSum+ "   sum ");
                Log.e("tag", MyApplication.price * selectSum + "");
                String format = String.format("%.2f", MyApplication.price * selectSum);
                SpannableString spannableString = changTVsize(format);
                MyApplication.sum = spannableString;
                popw_xd_price.setText(spannableString);


                popupWindow3.setBackgroundDrawable(new BitmapDrawable());
                popupWindow3.setOutsideTouchable(true);
                popupWindow3.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow3.showAtLocation(view_yp, Gravity.CENTER_HORIZONTAL, 0, 100);

            }

            @Override
            public void unCheck(int row, int column) {
                int row1 = row + 1;
                int column1 = column + 1;
                selectSum--;

                View view_yps = View.inflate(SeatActivity.this, R.layout.popw_xd, null);
                popupWindow4 = new PopupWindow(view_yps, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                ImageView popw_xd_finish = view_yps.findViewById(R.id.popw_xd_finish);
                TextView popw_xd_price = view_yps.findViewById(R.id.popw_xd_price);
                ImageView popw_xd_dui = view_yps.findViewById(R.id.popw_xd_dui);
                popw_xd_dui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = MyApplication.UserId + "" + MyApplication.scheduleId + "" + selectSum + "movie";
                        String decode = MyApplication.md5Decode(s);
                        iPresenter.buy(MyApplication.scheduleId, selectSum, decode);
                        popupWindow4.dismiss();
                    }
                });
                popw_xd_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow4.dismiss();
                    }
                });
                String format = String.format("%.2f", MyApplication.price * selectSum);
                SpannableString spannableString = changTVsize(format);
                popw_xd_price.setText(spannableString);
                popupWindow4.setBackgroundDrawable(new BitmapDrawable());
                popupWindow4.setOutsideTouchable(true);
                popupWindow4.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow4.showAtLocation(view_yps, Gravity.CENTER_HORIZONTAL, 0, 100);
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        Log.e("tag", "selectSum " + selectSum);
        int i = seatsTotal / 12;
        moveSeatView.setData(i, 12);

    }

    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        Log.e("tags","sssss");
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

    @Override
    public void buy(BuyBean buyBean) {
        Toast.makeText(this, buyBean.getMessage(), Toast.LENGTH_SHORT).show();
        MyApplication.orderId = buyBean.getOrderId();
    }

    @Override
    public void wechat(WechatBean wechatBean) {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("tag",wechatBean.getAppId()+"      "+wechatBean.getMessage());
        String appId = wechatBean.getAppId();
        String partnerId = wechatBean.getPartnerId();
        String prepayId = wechatBean.getPrepayId();
        String packageValue = wechatBean.getPackageValue();
        String nonceStr = wechatBean.getNonceStr();
        String timeStamp = wechatBean.getTimeStamp();
        String sign = wechatBean.getSign();
        PayReq request = new PayReq();

        request.appId =appId ;
            request.partnerId = partnerId;
            request.prepayId = prepayId;
            request.packageValue = packageValue;
            request.nonceStr = nonceStr;
            request.timeStamp = timeStamp;
            request.sign = sign;
            request.extData = "app data"; //
        //request.checkArgs();
        api.sendReq(request);

        Log.d("jim","check args "+request.checkArgs());

    }
    @Override
    public void alipay(final AlipayBean alipayBean) {
        Toast.makeText(this, alipayBean.message, Toast.LENGTH_SHORT).show();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SeatActivity.this);
                Map<String, String> result = alipay.payV2(alipayBean.result, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
               // mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.Desetory();
        iPresenter =null;
    }
}


