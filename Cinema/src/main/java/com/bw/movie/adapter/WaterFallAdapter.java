package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.bean.PersonCard;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.12 20:45
 * @Description：YangXinYu
 */
public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {
    public boolean isAmplification=false;
    List<PersonCard> posterList;
    Context context;
    DetailsActivity detailsActivity;
    public WaterFallAdapter(List<PersonCard> posterList, Context context) {
        this.context = context;
        this.posterList = posterList;
        detailsActivity = (DetailsActivity) context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.water_photo, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Uri uri = Uri.parse(posterList.get(position).avatarUrl);
        holder.photoSimpleDraweeView.setImageURI(uri);
        holder.photoSimpleDraweeView.getLayoutParams().height = posterList.get(position).imgHeight; //从数据源中获取图片高度，动态设置到控件上
        holder.photoSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View  view_jz = View.inflate(context, R.layout.image, null);
                final PopupWindow  popupWindow2 = new PopupWindow(view_jz,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                SimpleDraweeView simpleDraweeView = view_jz.findViewById(R.id.iamge);
                simpleDraweeView.setImageURI(uri);
                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow2.dismiss();
                    }
                });
                popupWindow2.setBackgroundDrawable(new BitmapDrawable());
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow2.showAtLocation(view_jz,Gravity.CENTER_HORIZONTAL,0,100);


            }
        });
    }


    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo_SimpleDraweeView)
        SimpleDraweeView photoSimpleDraweeView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

