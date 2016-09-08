package cn.lidongdong.weChatTelBook.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

public class ContactActivity extends AppCompatActivity {
   private TextView titleTv,contactAtyNameTv,contactAtyNumTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        titleTv = (TextView) findViewById(R.id.title_tv);
        contactAtyNameTv = (TextView) findViewById(R.id.contact_aty_name_tv);
        contactAtyNumTv = (TextView) findViewById(R.id.contact_aty_num_tv);
        //一些操作
        initView();
    }

    private void initView() {
        //设置标题栏
        titleTv.setText("联系人详情页");
        //去掉手机状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接受Intent跳转传值
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String num=intent.getStringExtra("num");
        contactAtyNameTv.setText(name);
        contactAtyNumTv.setText(num);
        //打电话
        contactAtyNumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNum=contactAtyNumTv.getText().toString();
                Intent intentNum=new Intent("android.intent.action.CALL",Uri.parse("tel:"+strNum));
                startActivity(intentNum);
            }
        });
    }
}
