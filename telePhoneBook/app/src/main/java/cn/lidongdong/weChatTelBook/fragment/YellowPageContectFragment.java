package cn.lidongdong.weChatTelBook.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.activity.ContactActivity;
import cn.lidongdong.weChatTelBook.adapter.ContactAdapter;
import cn.lidongdong.weChatTelBook.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/24.
 * 黄页界面的联系人
 */
public class YellowPageContectFragment extends Fragment{
    private ListView listView;
    private ContactAdapter contactAdapter;
    private Context context;
    private List<ContactBean> beans = new ArrayList<>();
    private TextView tabYeTv;
    private ContactBean contactBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yellowpagecontact,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化,设置监听都行
        listView = (ListView) view.findViewById(R.id.ye_lv);
        tabYeTv = (TextView) view.findViewById(R.id.tab_ye_tv);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactAdapter = new ContactAdapter(context);
        listView.setAdapter(contactAdapter);
//        SQLTool sqlTool=new SQLTool(context);
        //获取手机联系人
        daContactData();
        //把内容提供者提供的数据存入数据库
//        sqlTool.insert(beans);
        contactAdapter.setDatas(beans);
        //行布局点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactBean = (ContactBean) parent.getItemAtPosition(position);
                Intent intent=new Intent();
                intent.setClass(context,ContactActivity.class);
                intent.putExtra("name", contactBean.getName());
                intent.putExtra("num", contactBean.getTelNum());
                context.startActivity(intent);
            }
        });

    }
    //手机联系人
    private void daContactData() {
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor!=null) {
         //   Log.d("ContactFragment", "cursor.getCount():"+cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                beans.add(new ContactBean(name, num));
            }
        }
        cursor.close();

    }
}
