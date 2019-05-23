package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MovieActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WechatLoginBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：微信回调
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler ,ContractInterFace.IWechatLogin {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    ContractInterFace.IPresenter presenterInterface;

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toweichat);
        presenterInterface = new MyPresenter<>(this);
        regToWx();
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
        api.handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == baseResp.getType())
                    Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) baseResp).code;
                        Log.e("tag","code = " + code);
                        presenterInterface.wechatlogin(code);
                        //finish();
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        Toast.makeText(this, "微信分享成功", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                break;
        }
    }


    @Override
    public void wechatlogin(LoginBean wechatLoginBean) {
        if(wechatLoginBean.getMessage().length() != 0){
            //Log.e("tag", wechatLoginBean.getResult().getSessionId()+"*********");
            MyApplication.UserId = wechatLoginBean.getResult().getUserId();
            MyApplication.SessionId = wechatLoginBean.getResult().getSessionId();
            MyApplication.HeadPic = wechatLoginBean.getResult().getUserInfo().getHeadPic();
            MyApplication.UserName = wechatLoginBean.getResult().getUserInfo().getNickName();
            MyApplication.phones = wechatLoginBean.getResult().getUserInfo().getPhone();
            MyApplication.birthday = wechatLoginBean.getResult().getUserInfo().getBirthday();
            MyApplication.Sex =  wechatLoginBean.getResult().getUserInfo().getSex();
            MyApplication.lastLoginTime =  wechatLoginBean.getResult().getUserInfo().getLastLoginTime();
                    Toast.makeText(WXEntryActivity.this,wechatLoginBean.getMessage(),Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(WXEntryActivity.this,MovieActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Toast.makeText(WXEntryActivity.this,  "  0" + wechatLoginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInterface.Desetory();
        presenterInterface=null;
    }
}
