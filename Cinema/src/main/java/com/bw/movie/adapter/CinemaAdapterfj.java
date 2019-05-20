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
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.CinemafjBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/13 20:36
 * Author: 高海波
 */
public class CinemaAdapterfj extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnItemClickListener mOnItemClickListener;

    private Context context;
    List<CinemafjBean.ResultBean> fjlist;

    public CinemaAdapterfj(List<CinemafjBean.ResultBean> fjlist, Context context) {
        this.context = context;
        this.fjlist = fjlist;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int id, String image, String name, String address);
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fjcinema, parent, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {



        final myViewHolder holder = (myViewHolder) viewHolder;
       /* if (MyApplication.filmFlag ==1){
            holder.collectionIteam.setImageResource(R.mipmap.collection_selected);
        }else{
            holder.collectionIteam.setImageResource(R.mipmap.collection);
        }*/
        holder.imageViewfj.setImageURI(fjlist.get(position).getLogo());
        holder.textViewfj.setText(fjlist.get(position).getName());
        holder.textAddressfj.setText(fjlist.get(position).getAddress());
        holder.textDistancefj.setText(fjlist.get(position).getDistance() + "km");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(fjlist.get(position).getId(), fjlist.get(position).getLogo(), fjlist.get(position).getName(), fjlist.get(position).getAddress());
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return fjlist.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageViewfj;
        TextView textViewfj;
        TextView textAddressfj;
        TextView textDistancefj;
        ImageView collectionIteam;

        public myViewHolder(View itemView) {
            super(itemView);
            imageViewfj = itemView.findViewById(R.id.image_viewfj);
            textViewfj = itemView.findViewById(R.id.text_viewfj);
            textAddressfj = itemView.findViewById(R.id.text_addressfj);
            textDistancefj = itemView.findViewById(R.id.text_distancefj);
            collectionIteam = itemView.findViewById(R.id.collection_iteam);

        }
    }
}
