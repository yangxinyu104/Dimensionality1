package com.bwei.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 14:55
 * @Description：YangXinYu
 */
public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {


    }
}
