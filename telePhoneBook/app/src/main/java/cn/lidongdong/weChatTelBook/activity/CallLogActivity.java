package cn.lidongdong.weChatTelBook.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;

public class CallLogActivity extends AppCompatActivity {
  private TextView titleTv;
    private TextView calllogTelTv,calllogTimeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        titleTv = (TextView) findViewById(R.id.title_tv);
        calllogTelTv = (TextView) findViewById(R.id.callLog_aty_num_tv);
        calllogTimeTv = (TextView) findViewById(R.id.callLog_aty_time_tv);
        intiDatas();
    }

    private void intiDatas() {

        //设置标题
        titleTv.setText("通话详情");
        //去掉顶部的通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接受行布局跳转的数据
        Intent intent=getIntent();
        String num=intent.getStringExtra("num");
        String time=intent.getStringExtra("time");
        calllogTelTv.setText(num);
        calllogTimeTv.setText(time);
        calllogTelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTel=calllogTelTv.getText().toString();
                Intent intentTel=new Intent("android.intent.action.CALL", Uri.parse("tel:"+strTel));
                startActivity(intentTel);
            }
        });
    }
}
