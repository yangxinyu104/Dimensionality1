package com.bw.movie.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.AttentionActivity;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MineMessageActivity;
import com.bw.movie.activity.OpinionActivity;
import com.bw.movie.activity.RecordActivity;
import com.bw.movie.activity.SoundActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.SignBean;
import com.bw.movie.bean.VersionBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.util.DownUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class MyFragment extends Fragment implements ContractInterFace.IMy {
    private DownloadManager mDownloadManager;
    private long mId;
    private Dialog mDialog1;
    private ProgressBar mProgressBar;

    @BindView(R.id.mine_sound)
    ImageView mineSound;
    @BindView(R.id.mine_head)
    SimpleDraweeView mineHead;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.qiandao)
    Button qiandao;
    @BindView(R.id.mine_myinfo)
    ImageView mineMyinfo;
    @BindView(R.id.mine_like)
    ImageView mineLike;
    @BindView(R.id.mine_bypiao)
    ImageView mineBypiao;
    @BindView(R.id.mine_yijian)
    ImageView mineYijian;
    @BindView(R.id.mine_update)
    ImageView mineUpdate;
    @BindView(R.id.back_login)
    ImageView backLogin;
    Unbinder unbinder;
    private ContractInterFace.IPresenter iPresenter;
    private TextView mPrecent;
    private TextView mComplete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mineHead.setImageURI(MyApplication.HeadPic);
        mineName.setText(MyApplication.UserName);
        iPresenter = new MyPresenter<>(this);
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.signin();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.mine_myinfo, R.id.mine_like, R.id.mine_bypiao, R.id.mine_yijian, R.id.mine_update, R.id.back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_myinfo:
                Intent intent = new Intent(getActivity(), MineMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_like:
                Intent intent3 = new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent3);
                break;
            case R.id.mine_bypiao:
                Intent intent1s = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent1s);
                break;
            case R.id.mine_yijian:
                Intent intent1 = new Intent(getActivity(), OpinionActivity.class);
                startActivity(intent1);
                break;
            case R.id.mine_update:
                long appVersionCode = MyApplication.getAppVersionCode(getContext());
                iPresenter.version(appVersionCode + "");
                break;
            case R.id.back_login:
                getActivity().finish();
                MyApplication.UserId = 0;
                MyApplication.SessionId = null;
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mineHead.setImageURI(MyApplication.HeadPic);
        mineName.setText(MyApplication.UserName);
    }

    @Override
    public void signin(SignBean wechatLoginBean) {
        Toast.makeText(getActivity(), wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void version(final VersionBean wechatLoginBean) {
        MyApplication.UpdateAddress = wechatLoginBean.getDownloadUrl();
        //Toast.makeText(getContext(), wechatLoginBean.getFlag() + "", Toast.LENGTH_SHORT).show();
        if (wechatLoginBean.getFlag() == 2) {
            AlertDialog.Builder builders = new AlertDialog.Builder(getContext());
            builders.setTitle("提示:");
            builders.setMessage("当前版本为  ：  " + MyApplication.getAppVersionName(getContext()) + "   已是最新版本");
        } else if (wechatLoginBean.getFlag() == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("提示:");
            builder.setMessage("当前不是最新版本,是否更新当前的版本");
            builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //此处使用DownLoadManager开启下载任务
                    mDownloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);

                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(wechatLoginBean.getDownloadUrl()));
                    // 下载过程和下载完成后通知栏有通知消息。
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setTitle("下载");
                    request.setDescription("apk正在下载");
                    //设置保存目录  /storage/emulated/0/Android/包名/files/Download
                    request.setDestinationInExternalFilesDir(getActivity(),Environment.DIRECTORY_DOWNLOADS,"movie.apk");
                    mId = mDownloadManager.enqueue(request);

                    //注册内容观察者，实时显示进度
                    MyContentObserver downloadChangeObserver = new MyContentObserver(null);
                    getActivity().getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true, downloadChangeObserver);

                    //广播监听下载完成
                    listener(mId);
                    //弹出进度条，先隐藏前一个dialog
                    dialog.dismiss();
                    //显示进度的对话框
                    mDialog1 = new Dialog(getContext(), R.style.Theme_AppCompat_Dialog_Alert);
                    View view = getActivity().getLayoutInflater().inflate(R.layout.progress_dialog, null);
                    mProgressBar = view.findViewById(R.id.pb);
                    mPrecent = view.findViewById(R.id.tv_precent);
                    mDialog1.setContentView(view);
                    mDialog1.show();



                }
            });
            builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }

    }
    private void listener(final long id) {
        //Toast.makeText(MainActivity.this,"XXXX",Toast.LENGTH_SHORT).show();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long longExtra = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == longExtra){
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    File apkFile = getActivity().getExternalFilesDir("DownLoad/movie.apk");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        Uri uriForFile = FileProvider.getUriForFile(context, "com.bw.movie.fileprovider", apkFile);
                        install.setDataAndType(uriForFile,"application/vnd.android.package-archive");
                    }else {
                        install.setDataAndType(Uri.fromFile(apkFile),"application/vnd.android.package-archive");
                    }

                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(install);
                    Toast.makeText(getActivity(),"ZZZZ",Toast.LENGTH_SHORT).show();
                }
            }

        };

        getActivity().registerReceiver(broadcastReceiver,intentFilter);
    }

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }


        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onChange(boolean selfChange) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(mId);
            DownloadManager dManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            final Cursor cursor = dManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                final int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                final int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                float percent = (float) currentSize / (float) totalSize;
                float progress = (float) Math.floor(percent * 100);
                mPrecent.setText(progress+"%");
                mProgressBar.setProgress((int) progress,true);
                if (progress == 100)
                    mDialog1.dismiss();
            }
        }

    }



    @OnClick(R.id.mine_sound)
    public void onViewClicked() {

        Intent intent = new Intent(getActivity(),SoundActivity.class);
        getActivity().startActivity(intent);

    }
    @Override
    public void onDetach() {
        super.onDetach();
        iPresenter.Desetory();
        iPresenter =null;
    }
}
