package cn.lidongdong.weChatTelBook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

import java.util.List;

import cn.lidongdong.weChatTelBook.bean.sms.SmsBean;

/**
 * Created by dllo on 16/8/27.
 * 短信详情适配器
 */
public class MessageAtyAdapter extends RecyclerView.Adapter {
    //短信集合
    private List<SmsBean> datas;
    private Context context;

    public MessageAtyAdapter(List<SmsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_msg_aty,parent,false);
        MsgViewHolder msgViewHolder=new MsgViewHolder(view);

        return msgViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         MsgViewHolder msgViewHolder= (MsgViewHolder) holder;
        msgViewHolder.bodyTv.setText(datas.get(position).getBody());
        msgViewHolder.dateTv.setText(datas.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }
    class MsgViewHolder extends RecyclerView.ViewHolder{
        TextView bodyTv,dateTv;

        public MsgViewHolder(View itemView) {
            super(itemView);
            bodyTv = (TextView) itemView.findViewById(R.id.item_aty_msg_body_tv);
            dateTv = (TextView) itemView.findViewById(R.id.item_aty_msg_date_tv);
        }
    }
}
