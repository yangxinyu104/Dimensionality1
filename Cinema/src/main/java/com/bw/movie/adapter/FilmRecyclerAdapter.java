package com.bw.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.fragment.FilmFragment;
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
public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    FilmFragment filmFragment;
    List<BeonBean.ResultBean> list;


    public FilmRecyclerAdapter(FilmFragment filmFragment, List<BeonBean.ResultBean> list) {
        this.filmFragment = filmFragment;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.filmrecycler, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.filmrecyclerSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.filmrecyclerTextView.setText(list.get(position).getName());
        holder.filmrecyclerTextView.getBackground().setAlpha(98);
    }

    @Override
    public void onClick(View view) {
        if (onItemClick != null) {
            onItemClick.onItemClick((int) view.getTag());
        }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.filmrecycler_SimpleDraweeView)
        SimpleDraweeView filmrecyclerSimpleDraweeView;
        @BindView(R.id.filmrecycler_TextView)
        TextView filmrecyclerTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}

