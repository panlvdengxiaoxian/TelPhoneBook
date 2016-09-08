package cn.lidongdong.weChatTelBook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.bean.ContactBean;

public class ContactAddActivity extends AppCompatActivity {
    private EditText nameEt,numEt;
    private ContactBean contactBean;
    private String telNum="";
    private String telName="";
    private TextView titleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contect);
        nameEt = (EditText) findViewById(R.id.contact_aty_name_et);
        numEt = (EditText) findViewById(R.id.contact_aty_tel_et);
        titleTv = (TextView) findViewById(R.id.title_tv);
        //去掉顶部的通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDatas();

    }

    private void initDatas() {
        titleTv.setText("添加联系人详情");
        Intent intent =getIntent();
        if (intent!=null) {
           telName=intent.getDataString();
            telNum=intent.getDataString();
            nameEt.setText(telName);
            numEt.setText(telNum);
        }
    }


}
