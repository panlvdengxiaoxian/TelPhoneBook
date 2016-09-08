package cn.lidongdong.weChatTelBook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.bean.CalllogBean;
import cn.lidongdong.weChatTelBook.bean.ContactBean;

import java.util.List;

/**
 * Created by dllo on 16/8/17.
 */
public class ContactAdapter extends BaseAdapter{
    private Context context;
    private List<ContactBean> datas;


    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ContactBean> datas) {
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
        ViewHolder viewHolder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ContactBean contactBean= (ContactBean) getItem(position);
        viewHolder.contactName.setText(contactBean.getName());
        viewHolder.contactNum.setText(contactBean.getTelNum());
        return convertView;
    }
    class ViewHolder{
        TextView contactName,contactNum;

        public ViewHolder(View view) {
           contactName= (TextView) view.findViewById(R.id.item_contact_name_tv);
            contactNum= (TextView) view.findViewById(R.id.item_contact_num_tv);
        }
    }
    public void deleteData(ContactBean contactBean){
        datas.remove(contactBean);
        notifyDataSetChanged();
    }
}
