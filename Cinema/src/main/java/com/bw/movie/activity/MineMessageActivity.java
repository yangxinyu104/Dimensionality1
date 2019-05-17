package com.bw.movie.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.HeadBean;
import com.bw.movie.bean.PwdBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.util.EncryptUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineMessageActivity extends AppCompatActivity implements ContractInterFace.IMineMessage {

    @BindView(R.id.info_head)
    SimpleDraweeView infoHead;
    @BindView(R.id.info_name)
    TextView infoName;
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.info_birthday)
    TextView infoBirthday;
    @BindView(R.id.info_phone)
    TextView infoPhone;
    @BindView(R.id.info_email)
    TextView infoEmail;
    @BindView(R.id.use_ch)
    RelativeLayout useCh;
    @BindView(R.id.userinfo_back1)
    ImageView userinfoBack1;
    @BindView(R.id.info_RelativeLayout)
    RelativeLayout infoRelativeLayout;
    @BindView(R.id.info_LinearLayout)
    LinearLayout infoLinearLayout;
    private AlertDialog.Builder builders;
    private AlertDialog.Builder builder;
    private ContractInterFace.IPresenter iPresenter;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        ButterKnife.bind(this);
        applypermission();
        infoHead.setImageURI(MyApplication.HeadPic);
        infoName.setText(MyApplication.UserName);
        if (MyApplication.Sex == 1) {
            infoSex.setText("男");
        } else {
            infoSex.setText("女");
        }
        String time = time(MyApplication.birthday);
        infoBirthday.setText(time);
        infoPhone.setText(MyApplication.phones);

        String times = time(MyApplication.lastLoginTime);
        infoEmail.setText(times);
        userinfoBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iPresenter = new MyPresenter<>(this);

        useCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View PwdView = View.inflate(MineMessageActivity.this, R.layout.pwd, null);
                final EditText OldName = PwdView.findViewById(R.id.pwd_old);
                final EditText NewName = PwdView.findViewById(R.id.pwd_new);
                final EditText NewOldName = PwdView.findViewById(R.id.pwd_newold);
                builder = new AlertDialog.Builder(MineMessageActivity.this);
                builder.setView(PwdView);

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldName = EncryptUtil.encrypt(OldName.getText().toString());
                        String newName = EncryptUtil.encrypt(NewName.getText().toString());
                        String newOldName = EncryptUtil.encrypt(NewOldName.getText().toString());
                        iPresenter.pwd(oldName, newName, newOldName);
                    }
                });
                Window window = getWindow();
                window.setWindowAnimations(R.style.mystyle);

                builder.show();
            }
        });
        infoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineMessageActivity.this);
                builder.setTitle("添加图片");
                String[] items = {"选择本地照片", "拍照"};
                builder.setNegativeButton("取消", null);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case CHOOSE_PICTURE: // 选择本地照片
                                Intent openAlbumIntent = new Intent(
                                        Intent.ACTION_PICK);
                                openAlbumIntent.setType("image/*");
                                //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
                                startActivityForResult(openAlbumIntent, 2);
                                break;
                            case TAKE_PICTURE: // 拍照
                                Intent openCameraIntent = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(openCameraIntent, 1);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
        infoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View PwdViewa = View.inflate(MineMessageActivity.this, R.layout.user, null);
                final EditText pwd_name = PwdViewa.findViewById(R.id.pwd_name);
                final EditText pwd_sex = PwdViewa.findViewById(R.id.pwd_sex);
                final EditText pwd_emil = PwdViewa.findViewById(R.id.pwd_emil);
                builders = new AlertDialog.Builder(MineMessageActivity.this);
                builders.setView(PwdViewa);

                builders.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builders.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("tag","确定");
                        //MyApplication.Sex =  pwd_sex.getText().toString();
                        if (pwd_sex.getText().toString().equals("男")){
                            iPresenter.user(pwd_name.getText().toString(), 1, pwd_emil.getText().toString());
                        }else if (pwd_sex.getText().toString().equals("女")){
                            iPresenter.user(pwd_name.getText().toString(), 2, pwd_emil.getText().toString());
                        }


                    }
                });
                Window window = getWindow();
                window.setWindowAnimations(R.style.mystyle);

                builders.show();

            }
        });


    }

    //第二步就是返回的时候获取相册的路径
    File file1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 1:
                Toast.makeText(this, "服务暂无开启", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Uri uri = data.getData();
                crop(uri);

                break;
            case 3:
                //从相册中取出照片
                Bitmap bitmap = data.getParcelableExtra("data");
                String path = Environment.getExternalStorageDirectory() + "/yxy";
                File file = new File(path);//将要保存图片的路径
                if (!file.exists()) {
                    file.mkdir();
                }
                file1 = new File(file, "123456.png");
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    bos.flush();
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iPresenter.head(file1);
                break;
        }

    }

    public void crop(Uri uri) {
// 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 3);
    }

    public String time(long date) {
        long time = Long.parseLong(String.valueOf(date));
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aa = formatter.format(currentTime);
        return aa;

    }

    @Override
    public void pwd(PwdBean wechatLoginBean) {
        Toast.makeText(this, wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (wechatLoginBean.getMessage().equals("密码修改成功")) {
            Intent intent = new Intent(MineMessageActivity.this, LoginActivity.class);
            MyApplication.UserId = 0;
            MyApplication.SessionId = null;
            startActivity(intent);
            finish();
        }
    }

    public void applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否已经给了权限
            int checkpermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            int checkpermissions = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkpermission != PackageManager.PERMISSION_GRANTED) {//没有给权限
                if (checkpermissions != PackageManager.PERMISSION_GRANTED) {
                    Log.e("permission", "动态申请");
                    //参数分别是当前活动，权限字符串数组，requestcode
                    ActivityCompat.requestPermissions(MineMessageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        }
    }


    @Override
    public void head(HeadBean wechatLoginBean) {
        Toast.makeText(this, wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        MyApplication.HeadPic = wechatLoginBean.getHeadPath();
        infoHead.setImageURI(wechatLoginBean.getHeadPath());
    }

    @Override
    public void user(UserBean wechatLoginBean) {
        Log.e("tag","ssssssssdasdasdasdasdasdasdasdass");
        Toast.makeText(this, wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        infoName.setText(wechatLoginBean.getResult().getNickName());
       if (wechatLoginBean.getResult().getSex()==1){
           infoSex.setText("男");

       }else{
           infoSex.setText("女");
       }
        MyApplication.Sex =wechatLoginBean.getResult().getSex();
        MyApplication.UserName = wechatLoginBean.getResult().getNickName();

    }
}
