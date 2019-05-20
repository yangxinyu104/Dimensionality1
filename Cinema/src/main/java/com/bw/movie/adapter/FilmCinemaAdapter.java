package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.FilmCinemaActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.FilmCinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.13 9:01
 * @Description：YangXinYu
 */
public class FilmCinemaAdapter extends RecyclerView.Adapter<FilmCinemaAdapter.holder> {
    List<FilmCinemaBean.ResultBean> list;
    Context context;
    FilmCinemaActivity filmCinemaActivity;



    public FilmCinemaAdapter(List<FilmCinemaBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
        filmCinemaActivity = (FilmCinemaActivity) context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.film_cinema, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.filmCinemaSimpleDraweeViews.setImageURI(list.get(position).getLogo());
        holder.filmCinemaNames.setText(list.get(position).getName());
        holder.filmCinemaAddres.setText(list.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
        Log.e("tag",list.get(position).getFollowCinema()+list.get(position).getName());
        if (list.get(position).getFollowCinema() == 0) {
            holder.filmCinemaXin.setImageResource(R.mipmap.collection);
        } else if (list.get(position).getFollowCinema() == 1) {
            holder.filmCinemaXin.setImageResource(R.mipmap.collection_selected);
        }

        holder.filmCinemaXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getFollowCinema() == 0) {
                    holder.filmCinemaXin.setImageResource(R.mipmap.collection_selected);
                    filmCinemaActivity.iPresenter.followCinema(list.get(position).getId());
                    list.get(position).setFollowCinema(1);
                    MyApplication.filmFlag =1;
                    notifyDataSetChanged();
                } else if (list.get(position).getFollowCinema() == 1) {
                    holder.filmCinemaXin.setImageResource(R.mipmap.collection);
                    filmCinemaActivity.iPresenter.noFollowCinema(list.get(position).getId());
                    list.get(position).setFollowCinema(0);
                    notifyDataSetChanged();
                    MyApplication.filmFlag =2;
                }
            }
        });


    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        @BindView(R.id.film_cinema_SimpleDraweeViews)
        SimpleDraweeView filmCinemaSimpleDraweeViews;
        @BindView(R.id.film_cinema_names)
        TextView filmCinemaNames;
        @BindView(R.id.film_cinema_addres)
        TextView filmCinemaAddres;
        @BindView(R.id.film_cinema_xin)
        ImageView filmCinemaXin;
        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
