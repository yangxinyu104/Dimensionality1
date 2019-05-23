package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.BeonDao;
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
public class BeonDaoAdapter extends RecyclerView.Adapter<BeonDaoAdapter.holder> {
    List<BeonDao> list;
    Context context;

    public BeonDaoAdapter(List<BeonDao> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.beon, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(holder holder, final int position) {
        holder.beonSimpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.beonTextView.setText(list.get(position).getName());
        holder.beonTextView.getBackground().setAlpha(98);
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
        @BindView(R.id.beon_SimpleDraweeView)
        SimpleDraweeView beonSimpleDraweeView;
        @BindView(R.id.beon_TextView)
        TextView beonTextView;

        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
