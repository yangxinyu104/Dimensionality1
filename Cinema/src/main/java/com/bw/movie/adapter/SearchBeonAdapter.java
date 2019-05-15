package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.BeonBean;
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
public class SearchBeonAdapter extends RecyclerView.Adapter<SearchBeonAdapter.holder> {
    List<BeonBean.ResultBean> list;
    Context context;
    SearchActivity searchActivity;

    public SearchBeonAdapter(List<BeonBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
        searchActivity = (SearchActivity) context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_beon, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.searchBeonSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.searchBeonName.setText(list.get(position).getName());
        holder.searchBeonXq.setText(list.get(position).getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
        if (list.get(position).getFollowMovie()==1){
            holder.searchBeonXin.setImageResource(R.mipmap.collection_selected);
        }else if(list.get(position).getFollowMovie()==2){
            holder.searchBeonXin.setImageResource(R.mipmap.collection);
        }

        holder.searchBeonXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getFollowMovie()==1){
                    holder.searchBeonXin.setImageResource(R.mipmap.collection);
                    searchActivity.iPresenter.noattention(list.get(position).getId());
                    MyApplication.Zanflag = 3;
                }else if(list.get(position).getFollowMovie()==2){
                    holder.searchBeonXin.setImageResource(R.mipmap.collection_selected);
                    searchActivity.iPresenter.attention(list.get(position).getId());
                    MyApplication.Zanflag = 3;
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
        @BindView(R.id.search_beon_SimpleDraweeView)
        SimpleDraweeView searchBeonSimpleDraweeView;
        @BindView(R.id.search_beon_name)
        TextView searchBeonName;
        @BindView(R.id.search_beon_xin)
        ImageView searchBeonXin;
        @BindView(R.id.search_beon_xq)
        TextView searchBeonXq;

        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
