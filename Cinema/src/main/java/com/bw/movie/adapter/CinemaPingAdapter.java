package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaplBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/17 17:42
 * Author: 高海波
 */
public class CinemaPingAdapter extends RecyclerView.Adapter<CinemaPingAdapter.MyView> {

    List<CinemaplBean.ResultBean> resultBeans;
    Context context;



    public CinemaPingAdapter(List<CinemaplBean.ResultBean> resultBeans, Context context) {
        this.resultBeans = resultBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_ding_layout, parent, false);
        MyView myView = new MyView(view);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.filmreview_heard2.setImageURI(resultBeans.get(position).getCommentHeadPic());
        holder.filmreview_name2.setText(resultBeans.get(position).getCommentUserName());
        holder.filmreview_pinglun2.setText(resultBeans.get(position).getCommentContent());
        //holder.filmreview_reply2.setText(resultBeans.get(position).getGreatNum()+"");

    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        private final SimpleDraweeView filmreview_heard2;
        private final TextView filmreview_name2;
        private final TextView filmreview_pinglun2;
        //private final TextView filmreview_reply2;
        private final ImageView dianzancheckbox2;
        public MyView(View itemView) {
            super(itemView);

            filmreview_heard2 = itemView.findViewById(R.id.filmreview_heard2);
            filmreview_name2 = itemView.findViewById(R.id.filmreview_name2);
            filmreview_pinglun2 = itemView.findViewById(R.id.filmreview_pinglun2);
            //filmreview_reply2 = itemView.findViewById(R.id.filmreview_reply2);
            dianzancheckbox2 = itemView.findViewById(R.id.dianzancheckbox2);

        }
    }
}
