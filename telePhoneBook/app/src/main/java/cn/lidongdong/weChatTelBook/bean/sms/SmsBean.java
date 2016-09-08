package cn.lidongdong.weChatTelBook.bean.sms;

import java.io.Serializable;

/**
 * Created by dllo on 16/8/25.
 * 短信的基本类,
 * 只含有短信的内容和日期
 */
public class SmsBean implements Serializable{
    private String body;
    private String date;

    public SmsBean() {
    }

    public SmsBean(String body, String date) {
        this.body = body;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
