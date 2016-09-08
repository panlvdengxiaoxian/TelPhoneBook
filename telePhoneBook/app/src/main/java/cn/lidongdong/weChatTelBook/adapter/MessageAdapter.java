package cn.lidongdong.weChatTelBook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.bean.sms.SmsBean;
import cn.lidongdong.weChatTelBook.bean.sms.SmsBeanManager;
import cn.lidongdong.weChatTelBook.bean.sms.SmsGroupBean;


import cn.lidongdong.weChatTelBook.viewClick.OnRvitemClicklistener;

/**
 *
 * 短信的适配器
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MesViewHolder>{
    private Context context;
    //定义短信管理类
    private SmsBeanManager smsBeanManager;
    //定义接口对象
    //传入泛型,分组短信实体类
    private OnRvitemClicklistener<SmsGroupBean> listener;
    //set
    public void setListener(OnRvitemClicklistener<SmsGroupBean> listener) {
        this.listener = listener;
    }

    public MessageAdapter(Context context, SmsBeanManager smsBeanManager) {
        this.context = context;
        this.smsBeanManager = smsBeanManager;
    }

    @Override
    public MesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        MesViewHolder mesViewHolder=new MesViewHolder(view);
        return mesViewHolder;
    }

    @Override
    public void onBindViewHolder(final MesViewHolder holder, final int position) {
         SmsGroupBean smsGroupBean=smsBeanManager.getItemData(position);
        //号码
        holder.nameTv.setText(smsGroupBean.getNum());
        //日期和短信内容
        //显示的是最后一条短信的日期和内容
        final SmsBean smsBean=smsGroupBean.getLastSms();
        holder.contentTv.setText(smsBean.getBody());
        holder.timeTv.setText(smsBean.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    int p=holder.getLayoutPosition();
                    SmsGroupBean groupBean=smsBeanManager.getItemData(p);
                    //接口回调
                    listener.onRvItemClick(p,groupBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return smsBeanManager==null?0:smsBeanManager.getSize();
    }

    class MesViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv,timeTv,contentTv;
        public MesViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.item_message_name_tv);
            timeTv = (TextView) itemView.findViewById(R.id.item_message_time_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_message_content_tv);
        }
    }

}
