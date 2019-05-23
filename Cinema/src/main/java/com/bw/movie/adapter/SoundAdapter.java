package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.SoundBean;
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
public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.holder> {
    List<SoundBean.ResultBean> list;
    Context context;

    public SoundAdapter(List<SoundBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sound_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.soundItemName.setText(list.get(position).getTitle());

        long createTime = list.get(position).getPushTime();
        long time = Long.parseLong(String.valueOf(createTime));
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);


        holder.soundItemTime.setText(dateString);
        holder.soundItemMessage.setText(list.get(position).getContent());

       if (list.get(position).getStatus()==0){
           holder.soundItemNum.setVisibility(View.VISIBLE);
       }else{
           holder.soundItemNum.setVisibility(View.GONE);
       }


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
        @BindView(R.id.sound_item_name)
        TextView soundItemName;
        @BindView(R.id.sound_item_time)
        TextView soundItemTime;
        @BindView(R.id.sound_item_message)
        TextView soundItemMessage;
        @BindView(R.id.sound_item_num)
        TextView soundItemNum;


        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
