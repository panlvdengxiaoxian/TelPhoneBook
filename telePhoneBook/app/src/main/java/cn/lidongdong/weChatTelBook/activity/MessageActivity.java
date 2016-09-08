package cn.lidongdong.weChatTelBook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import cn.lidongdong.weChatTelBook.R;

import java.util.List;

import cn.lidongdong.weChatTelBook.adapter.MessageAtyAdapter;
import cn.lidongdong.weChatTelBook.bean.sms.SmsBean;
import cn.lidongdong.weChatTelBook.bean.sms.SmsGroupBean;

public class MessageActivity extends AbsBaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private MessageAtyAdapter messageAtyAdapter;
    private String phoneNum = "";
    private EditText msgAtyEt;
    private Button sendBtn;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView titleTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.mes_aty_rv);
        msgAtyEt = (EditText) findViewById(R.id.mes_aty_et);
        sendBtn = (Button) findViewById(R.id.mes_send_aty_btn);
        titleTv = (TextView) findViewById(R.id.title_tv);
        sendBtn.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        titleTv.setText("短信详情");
        msgAtyEt.setFocusable(true);
        //初始化内存存储的相关对象
        sp = getSharedPreferences("message", MODE_PRIVATE);
        editor = sp.edit();

        Intent intent = getIntent();
        if (intent != null) {
            SmsGroupBean groupBean = (SmsGroupBean) intent.getSerializableExtra("bean");
            phoneNum = groupBean.getNum();
            List<SmsBean> datas = groupBean.getSmsBeanList();
            messageAtyAdapter = new MessageAtyAdapter(datas, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(messageAtyAdapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (phoneNum.isEmpty() != true) {
            SharedPreferences getSp = getSharedPreferences("message", MODE_PRIVATE);
            String note = getSp.getString(phoneNum, "默认");

            if (note.equals("默认")) {

                msgAtyEt.setText("");
                Log.d("xxx", "msgAtyEt.getText():" + msgAtyEt.getText().toString());

            } else {
                msgAtyEt.setText(note);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        String inputStr = msgAtyEt.getText().toString();
        if (!inputStr.isEmpty()) {
            editor.putString(phoneNum, inputStr);
            editor.commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mes_send_aty_btn:
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNum, null, msgAtyEt.getText().toString(), null, null);
                SharedPreferences removeSp = getSharedPreferences("message", MODE_PRIVATE);
                SharedPreferences.Editor editor = removeSp.edit();
                editor.remove(phoneNum);
                editor.commit();
                msgAtyEt.setText("");
                break;
        }
    }
}
