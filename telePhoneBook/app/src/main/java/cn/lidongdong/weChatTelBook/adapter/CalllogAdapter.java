package cn.lidongdong.weChatTelBook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

import java.util.List;

import cn.lidongdong.weChatTelBook.bean.CalllogBean;

/**
 * Created by Yu on 16/8/13.
 */
public class CalllogAdapter extends BaseAdapter {
    private Context context;
    private List<CalllogBean> datas;

    public CalllogAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CalllogBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas!=null&&datas.size()>0?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas!=null&&datas.size()>0?datas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalllogViewHolder holder=null;
        if (convertView == null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.item_calllog,parent,false);
            holder=new CalllogViewHolder(convertView);
            convertView.setTag(holder);
        }else {
           holder= (CalllogViewHolder) convertView.getTag();
        }
        CalllogBean calllogBean=new CalllogBean();
        holder.time.setText(datas.get(position).getTime());
        holder.num.setText(datas.get(position).getNum());
        return convertView;
    }
    class CalllogViewHolder{
        TextView time,num;
        public CalllogViewHolder(View view){
            time = (TextView) view.findViewById(R.id.callLog_time_tv);
            num = (TextView) view.findViewById(R.id.callLog_num_tv);
        }
    }
}
