package cn.lidongdong.weChatTelBook.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.activity.YellowActivity;

/**
 * Created by Yu on 16/8/13.
 * 拨号界面
 */
public class CallFragment extends Fragment implements View.OnClickListener {
    ImageView callImgBtn1, callImgBtn2, callImgBtn3, callImgBtn4, callImgBtn5, callImgBtn6, callImgBtn7, callImgBtn8, callImgBtn9, callImgBtn0, callImgBtn10, callImgBtn11;
    EditText callEt;
    TextView callTv, callDelTv;
    ImageView call;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_call, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //失去焦点,防止软键盘弹出
        super.onViewCreated(view, savedInstanceState);
        //初始化
        //在这里设置监听事件也行
        callEt = (EditText) view.findViewById(R.id.call_inputNum_et);
        callImgBtn0 = (ImageView) view.findViewById(R.id.call_btn0);
        callImgBtn1 = (ImageView) view.findViewById(R.id.call_btn1);
        callImgBtn2 = (ImageView) view.findViewById(R.id.call_btn2);
        callImgBtn3 = (ImageView) view.findViewById(R.id.call_btn3);
        callImgBtn4 = (ImageView) view.findViewById(R.id.call_btn4);
        callImgBtn5 = (ImageView) view.findViewById(R.id.call_btn5);
        callImgBtn6 = (ImageView) view.findViewById(R.id.call_btn6);
        callImgBtn7 = (ImageView) view.findViewById(R.id.call_btn7);
        callImgBtn8 = (ImageView) view.findViewById(R.id.call_btn8);
        callImgBtn9 = (ImageView) view.findViewById(R.id.call_btn9);
        callImgBtn10 = (ImageView) view.findViewById(R.id.call_btn10);
        callImgBtn11 = (ImageView) view.findViewById(R.id.call_btn11);
        callDelTv = (TextView) view.findViewById(R.id.call_delete_tv);
        callTv = (TextView) view.findViewById(R.id.call_tv_yel);
        call = (ImageView) view.findViewById(R.id.call);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callEt.setFocusable(false);
        //设置监听
        callImgBtn0.setOnClickListener(this);
        callImgBtn1.setOnClickListener(this);
        callImgBtn2.setOnClickListener(this);
        callImgBtn3.setOnClickListener(this);
        callImgBtn4.setOnClickListener(this);
        callImgBtn5.setOnClickListener(this);
        callImgBtn6.setOnClickListener(this);
        callImgBtn7.setOnClickListener(this);
        callImgBtn8.setOnClickListener(this);
        callImgBtn9.setOnClickListener(this);
        callImgBtn10.setOnClickListener(this);
        callImgBtn11.setOnClickListener(this);
        callDelTv.setOnClickListener(this);
        call.setOnClickListener(this);


        callEt.setOnClickListener(this);

        callTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YellowActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String num = "";
        switch (v.getId()) {
            case R.id.call_btn0:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "0");
                } else {
                    callEt.setText("0");
                }
                break;
            case R.id.call_btn1:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "1");
                } else {
                    callEt.setText("1");
                }
                break;
            case R.id.call_btn2:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "2");
                } else {
                    callEt.setText("2");
                }
                break;
            case R.id.call_btn3:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "3");
                } else {
                    callEt.setText("3");
                }
                break;
            case R.id.call_btn4:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "4");
                } else {
                    callEt.setText("4");
                }
                break;
            case R.id.call_btn5:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "5");
                } else {
                    callEt.setText("5");
                }
                break;
            case R.id.call_btn6:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "6");
                } else {
                    callEt.setText("6");
                }
                break;
            case R.id.call_btn7:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "7");
                } else {
                    callEt.setText("7");
                }
                break;
            case R.id.call_btn8:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "8");
                } else {
                    callEt.setText("8");
                }
                break;
            case R.id.call_btn9:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "9");
                } else {
                    callEt.setText("9");
                }
                break;
            case R.id.call_btn10:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "*");
                } else {
                    callEt.setText("*");
                }
                break;
            case R.id.call_btn11:
                if (callEt != null) {
                    num = callEt.getText().toString();
                    callEt.setText(num + "#");
                } else {
                    callEt.setText("#");
                }
                break;
            case R.id.call:
                num = callEt.getText().toString();
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + num));
                startActivity(intent);
                break;
            case R.id.call_delete_tv:
                num=callEt.getText().toString();
                if (num.length()==0){
                }else {
                    callEt.setText(num.substring(0,num.length()-1));
                }

        }
    }


}
