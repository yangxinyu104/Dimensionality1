package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.RecordBean;

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
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.holder> {
    List<RecordBean.ResultBean> list;
    Context context;


    public RecordAdapter(List<RecordBean.ResultBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.record_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {

        long time = Long.parseLong(String.valueOf(list.get(position).getCreateTime()));
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        holder.recordTime.setText(dateString);
        holder.recordName.setText(list.get(position).getMovieName());
        holder.recordDanhao.setText(list.get(position).getOrderId());
        //holder.recordYuan.setText(list.get(position).getCinemaName());
        holder.recordCinemting.setText(list.get(position).getScreeningHall());
        holder.recordStarttimes.setText(list.get(position).getBeginTime());
        holder.recordEndtimes.setText(list.get(position).getEndTime());
        holder.recordNum.setText(list.get(position).getAmount() + "张");
        holder.recordMoney.setText(list.get(position).getPrice() + "元");
        if (list.get(position).getStatus() == 1) {
            holder.recordAaa.setVisibility(View.VISIBLE);
            holder.recordBbb.setVisibility(View.GONE);
        } else {
            holder.recordAaa.setVisibility(View.GONE);
            holder.recordBbb.setVisibility(View.VISIBLE);
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
        @BindView(R.id.record_aaa)
        Button recordAaa;
        @BindView(R.id.record_time)
        RadioButton recordTime;
        @BindView(R.id.record_name)
        TextView recordName;
        @BindView(R.id.record_danhao)
        TextView recordDanhao;
        /*@BindView(R.id.record_yuan)
        TextView recordYuan;*/
        @BindView(R.id.record_cinemting)
        TextView recordCinemting;
        @BindView(R.id.record_starttimes)
        TextView recordStarttimes;
        @BindView(R.id.record_endtimes)
        TextView recordEndtimes;
        @BindView(R.id.record_num)
        TextView recordNum;
        @BindView(R.id.record_money)
        TextView recordMoney;
        @BindView(R.id.record_bbb)
        Button recordBbb;
        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
