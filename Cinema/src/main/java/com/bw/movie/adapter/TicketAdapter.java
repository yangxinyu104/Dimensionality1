package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.TicketActivity;
import com.bw.movie.bean.ScheduleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.13 9:01
 * @Description：YangXinYu
 */
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.holder> {
    List<ScheduleBean.ResultBean> list;
    Context context;


    public TicketAdapter(List<ScheduleBean.ResultBean> list, TicketActivity context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.ticket_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(final holder holder, final int position) {
        holder.ticketItemName.setText(list.get(position).getScreeningHall());
        holder.ticketItemStartTime.setText(list.get(position).getBeginTime());
        holder.ticketItemEndTime.setText(list.get(position).getEndTime());
        SpannableString spannableString = changTVsize(list.get(position).getPrice() + "");
        holder.ticketItemPrice.setText(spannableString);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });

    }

    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.5f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
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
        @BindView(R.id.ticket_item_name)
        TextView ticketItemName;
        @BindView(R.id.ticket_item_startTime)
        TextView ticketItemStartTime;
        @BindView(R.id.ticket_item_endTime)
        TextView ticketItemEndTime;
        @BindView(R.id.ticket_item_price)
        TextView ticketItemPrice;

        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
