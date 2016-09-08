package cn.lidongdong.weChatTelBook.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.activity.CallLogActivity;
import cn.lidongdong.weChatTelBook.activity.YellowActivity;
import cn.lidongdong.weChatTelBook.adapter.CalllogAdapter;
import cn.lidongdong.weChatTelBook.bean.CalllogBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class YellowpageCallLogFragment extends Fragment implements View.OnClickListener {
    private List<CalllogBean> datas;
    private ListView listView;
    private CalllogAdapter calllogAdapter;
    private Context context;
    private TextView titleTV;
    CalllogBean calllogBean;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yellowpage_call_log,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.ye_calllog_lv);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        calllogAdapter=new CalllogAdapter(context);
        //绑定适配器
        listView.setAdapter(calllogAdapter);
        //获取手机通话记录
        datas=getColllog();
        //倒置数据
        Collections.reverse(datas);
        //设置数据到适配器
        calllogAdapter.setDatas(datas);
        //行布局点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                calllogBean= (CalllogBean) parent.getItemAtPosition(position);
                String num=calllogBean.getNum();
                String time=calllogBean.getTime();
                Intent intent=new Intent();
                intent.putExtra("num",num);
                intent.putExtra("time",time);
                intent.setClass(context, CallLogActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public List<CalllogBean> getColllog() {
        List<CalllogBean> list=new ArrayList<>();
        Cursor cursor=context.getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String num=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String time=cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
            SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
            long longTime=Long.valueOf(time);
            Date date=new Date(longTime);
            String formatTime=sdf.format(date);
            //将数据存储到集合
            list.add(new CalllogBean(num,formatTime));
        }
        //返回
        return list ;
    }


    public void onClick(View v) {
        switch (v.getId()){
        }
    }

}
