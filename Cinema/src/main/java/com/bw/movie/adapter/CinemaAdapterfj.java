package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemafjBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/13 20:36
 * Author: 高海波
 */
public class CinemaAdapterfj extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CinemafjBean.ResultBean> arr = new ArrayList<>();

    public CinemaAdapterfj(Context context) {
        this.context = context;
    }

    public void setArr(List<CinemafjBean.ResultBean> list) {
        if (list != null) {
            arr.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fjcinema, parent, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        myViewHolder holder = (myViewHolder) viewHolder;
        holder.imageViewfj.setImageURI(arr.get(position).getLogo());
        holder.textViewfj.setText(arr.get(position).getName());
        holder.textAddressfj.setText(arr.get(position).getAddress());
        holder.textDistancefj.setText(arr.get(position).getDistance()+"km");
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_viewfj)
        SimpleDraweeView imageViewfj;
        @BindView(R.id.text_viewfj)
        TextView textViewfj;
        @BindView(R.id.text_addressfj)
        TextView textAddressfj;
        @BindView(R.id.text_distancefj)
        TextView textDistancefj;

        public myViewHolder(View itemView) {
            super(itemView);

            imageViewfj = itemView.findViewById(R.id.image_viewfj);
            textViewfj = itemView.findViewById(R.id.text_viewfj);
            textAddressfj = itemView.findViewById(R.id.text_addressfj);
            textDistancefj = itemView.findViewById(R.id.text_distancefj);

        }
    }

}
