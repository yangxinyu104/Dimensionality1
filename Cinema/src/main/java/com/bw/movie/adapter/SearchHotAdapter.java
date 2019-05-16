package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.PopularMovieBean;
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
public class SearchHotAdapter extends RecyclerView.Adapter<SearchHotAdapter.holder> {
    List<PopularMovieBean.ResultBean> list;
    Context context;
    SearchActivity searchActivity;


    public SearchHotAdapter(List<PopularMovieBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
        searchActivity = (SearchActivity) context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_hot, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.searchHotSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.searchHotName.setText(list.get(position).getName());
        holder.searchHotXq.setText(list.get(position).getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
        if (list.get(position).getFollowMovie()==1){
            holder.searchHotXin.setImageResource(R.mipmap.collection_selected);
        }else if(list.get(position).getFollowMovie()==2){
            holder.searchHotXin.setImageResource(R.mipmap.collection);
        }

        holder.searchHotXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getFollowMovie()==1){
                    holder.searchHotXin.setImageResource(R.mipmap.collection);
                    searchActivity.iPresenter.noattention(list.get(position).getId());
                    MyApplication.Zanflag = 1;
                }else if(list.get(position).getFollowMovie()==2){
                    holder.searchHotXin.setImageResource(R.mipmap.collection_selected);
                    searchActivity.iPresenter.attention(list.get(position).getId());
                    MyApplication.Zanflag =1;
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
        @BindView(R.id.search_hot_SimpleDraweeView)
        SimpleDraweeView searchHotSimpleDraweeView;
        @BindView(R.id.search_hot_name)
        TextView searchHotName;
        @BindView(R.id.search_hot_xq)
        TextView searchHotXq;
        @BindView(R.id.search_hot_xin)
        ImageView searchHotXin;
        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
