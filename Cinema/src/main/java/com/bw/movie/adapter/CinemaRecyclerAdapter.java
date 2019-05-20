package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaBannerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/16 14:36
 * Author: 高海波
 */
public class CinemaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<CinemaBannerBean.ResultBean> list;

    public CinemaRecyclerAdapter(Context mContext, List<CinemaBannerBean.ResultBean> lists) {
        this.mContext = mContext;
            this.list = lists;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.filmrecyclers, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.filmrecyclerSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder1.filmrecyclerTextView.setText(list.get(position).getName());
        holder1.filmrecyclerTextView.getBackground().setAlpha(98);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.filmrecycler_SimpleDraweeViews)
        SimpleDraweeView filmrecyclerSimpleDraweeView;
        @BindView(R.id.filmrecycler_TextViews)
        TextView filmrecyclerTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
