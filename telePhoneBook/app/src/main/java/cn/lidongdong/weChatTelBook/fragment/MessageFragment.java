package cn.lidongdong.weChatTelBook.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

import cn.lidongdong.weChatTelBook.activity.InputMsgActivity;
import cn.lidongdong.weChatTelBook.activity.MessageActivity;
import cn.lidongdong.weChatTelBook.activity.SettingsActivity;
import cn.lidongdong.weChatTelBook.activity.YellowActivity;
import cn.lidongdong.weChatTelBook.adapter.MessageAdapter;
import cn.lidongdong.weChatTelBook.bean.sms.SmsBeanManager;
import cn.lidongdong.weChatTelBook.bean.sms.SmsGroupBean;
import cn.lidongdong.weChatTelBook.viewClick.OnRvitemClicklistener;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yu on 16/8/13.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {
    private TextView titleTV;
    private ImageView addIv, searchIv, settingIv, contectIv, writesmsIv;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private Context context;
    //短信数据管理类
    private SmsBeanManager smsBeanManager;
    private MsgReceiver msgReceiver;
    private EditText mesAtyEt;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTV = (TextView) view.findViewById(R.id.title_tv);
        addIv = (ImageView) view.findViewById(R.id.topbar_add_iv);
        searchIv = (ImageView) view.findViewById(R.id.topbar_search_iv);
        settingIv = (ImageView) view.findViewById(R.id.topbar_setting_iv);
        writesmsIv = (ImageView) view.findViewById(R.id.topbar_writesms_iv);
        contectIv = (ImageView) view.findViewById(R.id.topbar_addcontect_iv);
        recyclerView = (RecyclerView) view.findViewById(R.id.mes_rv);
        mesAtyEt = (EditText) view.findViewById(R.id.mes_aty_et);
        searchIv.setOnClickListener(this);
        writesmsIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册广播接受者
        msgReceiver=new MsgReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("xyz");
        context.registerReceiver(msgReceiver,filter);

        //设置标题栏
        titleTV.setText("短信");
        addIv.setVisibility(View.GONE);
        contectIv.setVisibility(View.GONE);
        //初始化短信数据管理类
        smsBeanManager=new SmsBeanManager();
        //获取短信加入管理类
        getSmsData();
        //设置RecycleView
        messageAdapter=new MessageAdapter(context,smsBeanManager);
        //设置布局管理器
        LinearLayoutManager llm=new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        //绑定适配器
        recyclerView.setAdapter(messageAdapter);

        messageAdapter.setListener(new OnRvitemClicklistener<SmsGroupBean>() {
            @Override
            public void onRvItemClick(int position, SmsGroupBean smsGroupBean) {
             Intent intent=new Intent();
                intent.setClass(context, MessageActivity.class);
                intent.putExtra("bean",smsGroupBean);
                context.startActivity(intent);

            }
        });

    }
    private void getSmsData() {
        ContentResolver contentResolver=context.getContentResolver();
        Cursor cursor=contentResolver.query(Telephony.Sms.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            //短信内容
            String body=cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            //未整理格式的时间
            String currentDate=cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));
            //未整理格式的号码
            String currentNum=cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            //整理时间格式
            SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
            long time=Long.valueOf(currentDate);
            Date date=new Date(time);
            //获取格式化后最终的日期
            String finalDate=sdf.format(date);
            //整理号码:(031) 030-123
            String finalNum = currentNum.replace("-","");
            finalNum =finalNum.replace("(","");
            finalNum =finalNum.replace(")","");
            finalNum =finalNum.replace(" ","");
            //将处理完成之后的短信数据,存储到短信管理类中
            smsBeanManager.addData(finalNum,body,finalDate);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topbar_search_iv:
                Intent intent=new Intent();
                intent.setClass(context, YellowActivity.class);
                context.startActivity(intent);
                break;
            case R.id.topbar_writesms_iv:
                Intent intentMsgAdd=new Intent();
                intentMsgAdd.setClass(context, InputMsgActivity.class);
                context.startActivity(intentMsgAdd);
                break;
            case R.id.topbar_setting_iv:
                Intent intentSetting=new Intent();
                intentSetting.setClass(context, SettingsActivity.class);
                context.startActivity(intentSetting);
                break;

        }
    }

    class MsgReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String str=intent.getStringExtra("broadMsg");
          mesAtyEt.setText(str);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(msgReceiver);
    }
}