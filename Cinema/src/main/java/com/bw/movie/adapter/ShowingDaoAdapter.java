package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.bean.ShowingDao;
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
public class ShowingDaoAdapter extends RecyclerView.Adapter<ShowingDaoAdapter.holder> {
    List<ShowingDao> list;
    Context context;

    public ShowingDaoAdapter(List<ShowingDao> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.showing, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(holder holder, final int position) {
        holder.showingSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.showingTextView.setText(list.get(position).getName());
        holder.showingTextView.getBackground().setAlpha(98);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(position);
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
        @BindView(R.id.showing_SimpleDraweeView)
        SimpleDraweeView showingSimpleDraweeView;
        @BindView(R.id.showing_TextView)
        TextView showingTextView;

        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
