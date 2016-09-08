package cn.lidongdong.weChatTelBook.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.activity.ContactActivity;
import cn.lidongdong.weChatTelBook.activity.ContactAddActivity;
import cn.lidongdong.weChatTelBook.activity.YellowActivity;
import cn.lidongdong.weChatTelBook.adapter.ContactAdapter;
import cn.lidongdong.weChatTelBook.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 16/8/13.
 * 联系人界面
 */
public class ContactFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private ContactAdapter contactAdapter;
    private Context context;
    private  List<ContactBean> beans = new ArrayList<>();
    private TextView titleTV;
    private ImageView addIv,searchIv,settingIv,contectIv,writesmsIv;
    private ContactBean contactBean;

    private AlertDialog alertDialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化,设置监听都行
        listView = (ListView) view.findViewById(R.id.contact_lv);
        titleTV= (TextView) view.findViewById(R.id.title_tv);
        addIv= (ImageView) view.findViewById(R.id.topbar_add_iv);
        searchIv= (ImageView) view.findViewById(R.id.topbar_search_iv);
        settingIv= (ImageView) view.findViewById(R.id.topbar_setting_iv);
        writesmsIv= (ImageView) view.findViewById(R.id.topbar_writesms_iv);
        contectIv = (ImageView) view.findViewById(R.id.topbar_addcontect_iv);
        contectIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactAdapter = new ContactAdapter(context);
        listView.setAdapter(contactAdapter);
//        SQLTool sqlTool=new SQLTool(context);
        //标题栏设置地
        titleTV.setText("联系人");
        settingIv.setVisibility(View.GONE);
        writesmsIv.setVisibility(View.GONE);
        addIv.setVisibility(View.GONE);
        //获取手机联系人
        daContactData();
        //把内容提供者提供的数据存入数据库
//        sqlTool.insert(beans);
        //  beans =sqlTool.queryAll();
        //设置数据
        contactAdapter.setDatas(beans);

        //listView的行点击事件
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
        //长按删除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                //设置标题和图标
                builder.setTitle("狗蛋儿,确定要删除你女票的电话!!!");
                builder.setIcon(R.mipmap.nvjing);
                builder.setMessage("亲爱的,别走,好吗?");
                builder.setPositiveButton("狗蛋儿,删吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "敢长按我,小兔崽子,别让老娘再看见你", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("狗蛋儿,别删", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "别了,狗蛋儿", Toast.LENGTH_SHORT).show();
                        contactAdapter.deleteData(beans.get(position));
                    }
                });

                builder.create().show();


                return true;
            }
        });

    }

    //手机联系人
    private void daContactData() {
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                beans.add(new ContactBean(name, num));
            }
            cursor.close();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topbar_addcontect_iv:
                Intent intent =new Intent();
                intent.setClass(context, ContactAddActivity.class);
                context.startActivity(intent);
                break;
            case R.id.topbar_search_iv:
                Intent intent1=new Intent();
                intent1.setClass(context, YellowActivity.class);
                context.startActivity(intent1);
                break;

        }
    }
}