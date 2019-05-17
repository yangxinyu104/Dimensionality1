package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.BuyTicketActivity;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.bean.CinematjBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/13 20:36
 * Author: 高海波
 */
public class CinemaAdaptertj extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnItemClickListener mOnItemClickListener;
    private Context context;
    private List<CinematjBean.ResultBean> arr = new ArrayList<>();

    public CinemaAdaptertj(Context context) {
        this.context = context;
    }

    public void setArr(List<CinematjBean.ResultBean> list) {
        if (list != null) {
            arr.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        mOnItemClickListener    = clickListener ;
    }
    public interface OnItemClickListener {
        void onItemClick(String id, String image, String name, String address);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tjcinema, null);
        //View  = LayoutInflater.from(context).inflate(R.layout.item_tjcinema, parent, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final myViewHolder holder = (myViewHolder) viewHolder;
        holder.imageView.setImageURI(arr.get(position).getLogo());
        holder.textView.setText(arr.get(position).getName());
        holder.textAddress.setText(arr.get(position).getAddress());
        holder.textDistance.setText(arr.get(position).getDistance()+"km");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BuyTicketActivity.class);
                intent.putExtra("id",arr.get(position).getId());
                intent.putExtra("name",arr.get(position).getName());
                intent.putExtra("logo",arr.get(position).getLogo());
                intent.putExtra("address",arr.get(position).getAddress());
                context.startActivity(intent);
            }
        });
       /* holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(arr.get(position).getId()+"",,,);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }



     class myViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;
        TextView textView;
        TextView textAddress;
        TextView textDistance;
         LinearLayout linearLayout;

        public myViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
            textAddress = itemView.findViewById(R.id.text_address);
            textDistance = itemView.findViewById(R.id.text_distance);
            linearLayout = itemView.findViewById(R.id.linear_layout);

        }
    }

}
