package cn.lidongdong.weChatTelBook.bean;

/**
 * Created by Yu on 16/8/13.
 */
public class CalllogBean {
    private String num;
    private String time;

    public CalllogBean() {
    }

    public CalllogBean( String num, String time) {
        this.num = num;
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}