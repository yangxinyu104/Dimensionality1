package com.bw.movie.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;


/**
 * Created by &{USER} on &{DATE}.
 */
public class MyViews extends LinearLayout implements View.OnClickListener{

    public View view;
    public ImageView search_image;
    public EditText search_edname;
    public TextView search_textName;
    public TextView locations;
    public RelativeLayout searchLinear;
    public ImageView dingwei;
    private AutoTransition transition;
    Context context;
    public MyViews(Context context) {
        super(context);

    }

    public MyViews(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.iteam_view,this);

        search_image = view.findViewById(R.id.search_image);
        search_edname = view.findViewById(R.id.search_edname);
        locations = view.findViewById(R.id.locations);
        search_textName = view.findViewById(R.id.search_textName);
        searchLinear = view.findViewById(R.id.search_linear);
        dingwei = view.findViewById(R.id.dingwei);
        search_image.setOnClickListener(this);
        search_textName.setOnClickListener(this);
        dingwei.setOnClickListener(this);

    }

    public MyViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_image:
                //点击搜索 伸展
                initExpand();
                break;
            case R.id.search_textName:
                //点击text收缩
                Toast.makeText(context, search_edname.getText().toString(), Toast.LENGTH_SHORT).show();
                initReduce();

                break;
        }

    }

    /*设置伸展状态时的布局*/
    public void initExpand() {
        search_edname.setHint("CGV影城");
        search_edname.requestFocus();
        search_edname.setHintTextColor(Color.WHITE);
        search_textName.setText("搜索");
        search_textName.setPadding(0,8,0,0);
        search_textName.setVisibility(View.VISIBLE);
        search_edname.setVisibility(View.VISIBLE);
        LayoutParams LayoutParams = (LinearLayout.LayoutParams) searchLinear.getLayoutParams();
        LayoutParams.width = dip2px(230);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        searchLinear.setLayoutParams(LayoutParams);
        search_edname.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                search_edname.setFocusable(true);
                search_edname.setFocusableInTouchMode(true);

                return false;
            }
        });
        //开始动画
        beginDelayedTransition(searchLinear);

    }

    /*设置收缩状态时的布局*/
    private void initReduce() {
        search_edname.setCursorVisible(false);
        search_edname.setVisibility(View.GONE);
        search_textName.setVisibility(View.GONE);
        LayoutParams LayoutParams = (LinearLayout.LayoutParams)  searchLinear.getLayoutParams();
        LayoutParams.width = dip2px(40);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        searchLinear.setLayoutParams(LayoutParams);

        //隐藏键盘
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity)context).getWindow().getDecorView().getWindowToken(), 0);
        search_edname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edname.setCursorVisible(true);


            }
        });
        //开始动画
        beginDelayedTransition( searchLinear);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void beginDelayedTransition(ViewGroup view) {
        transition = new AutoTransition();
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(view, transition);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }
}
