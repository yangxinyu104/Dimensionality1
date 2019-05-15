package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
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

    List<PersonCard> posterList;
    Context context;

    public WaterFallAdapter(List<PersonCard> posterList, Context context) {
        this.context = context;
        this.posterList = posterList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.water_photo, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri uri = Uri.parse(posterList.get(position).avatarUrl);
        holder.photoSimpleDraweeView.setImageURI(uri);
        holder.photoSimpleDraweeView.getLayoutParams().height = posterList.get(position).imgHeight; //从数据源中获取图片高度，动态设置到控件上

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

