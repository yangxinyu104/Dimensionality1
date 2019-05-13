package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.CinematjBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/13 20:36
 * Author: 高海波
 */
public class CinemaAdaptertj extends RecyclerView.Adapter<CinemaAdaptertj.myViewHolder> {

    private Context context;
    private List<CinematjBean.ResultBean> arr = new ArrayList<>();

    public CinemaAdaptertj(Context context) {
        this.context = context;
    }

    public void setArr(List<CinematjBean.ResultBean> list) {
        if (list != null) {
            arr.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_fragtj, null);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cinema_tj)
        RecyclerView cinemaTj;

        public myViewHolder(View itemView) {
            super(itemView);
            cinemaTj = itemView.findViewById(R.id.cinema_tj);
        }
    }

}
