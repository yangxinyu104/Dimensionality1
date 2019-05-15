package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.ReviewBean;
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
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.holder> {
    List<ReviewBean.ResultBean> list;
    Context context;
    DetailsActivity detailsActivity;


    public ReviewAdapter(List<ReviewBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
        detailsActivity = (DetailsActivity) context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.review, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.reviewSimpleDraweeView.setImageURI(list.get(position).getCommentHeadPic());
        holder.reviewTextViewName.setText(list.get(position).getCommentUserName());
        holder.reviewTextViewPl.setText(list.get(position).getCommentContent());

        long createTime = list.get(position).getCommentTime();
        long time = Long.parseLong(String.valueOf(createTime));
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        holder.reviewTextViewData.setText(dateString);
        holder.reviewTextViewZannum.setText(list.get(position).getGreatNum()+"");
        holder.reviewTextViewLiaonum.setText(list.get(position).getReplyNum()+"");
        if (list.get(position).getIsGreat()==0){
            holder.reviewTextViewZan.setBackgroundResource(R.mipmap.praise);
        }else{
            holder.reviewTextViewZan.setBackgroundResource(R.mipmap.praise_selected);
        }
        holder.reviewTextViewZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.reviewTextViewZan.setBackgroundResource(R.mipmap.praise_selected);
                    detailsActivity.iPresenter.great(list.get(position).getCommentId());
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
        @BindView(R.id.review_SimpleDraweeView)
        SimpleDraweeView reviewSimpleDraweeView;
        @BindView(R.id.review_TextView_name)
        TextView reviewTextViewName;
        @BindView(R.id.review_TextView_pl)
        TextView reviewTextViewPl;
        @BindView(R.id.review_TextView_data)
        TextView reviewTextViewData;
        @BindView(R.id.review_TextView_zan)
        CheckBox reviewTextViewZan;
        @BindView(R.id.review_TextView_zannum)
        TextView reviewTextViewZannum;
        @BindView(R.id.review_TextView_liao)
        ImageView reviewTextViewLiao;
        @BindView(R.id.review_TextView_liaonum)
        TextView reviewTextViewLiaonum;
        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
