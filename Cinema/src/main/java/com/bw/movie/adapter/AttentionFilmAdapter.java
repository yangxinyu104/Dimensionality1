package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.AttentionFilmBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.13 9:01
 * @Description：YangXinYu
 */
public class AttentionFilmAdapter extends RecyclerView.Adapter<AttentionFilmAdapter.holder> {
    List<AttentionFilmBean.ResultBean> list;
    Context context;


    public AttentionFilmAdapter(List<AttentionFilmBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.attention_film, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.attentionFilmImage.setImageURI(list.get(position).getImageUrl());
        holder.attentionFilmName.setText(list.get(position).getName());
        holder.attentionFilmMessage.setText(list.get(position).getSummary());
        long createTime = list.get(position).getReleaseTime();
        long time = Long.parseLong(String.valueOf(createTime));
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        holder.attentionFilmTime.setText(dateString);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
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
        @BindView(R.id.attention_film_image)
        SimpleDraweeView attentionFilmImage;
        @BindView(R.id.attention_film_name)
        TextView attentionFilmName;
        @BindView(R.id.attention_film_message)
        TextView attentionFilmMessage;
        @BindView(R.id.attention_film_time)
        TextView attentionFilmTime;
        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
