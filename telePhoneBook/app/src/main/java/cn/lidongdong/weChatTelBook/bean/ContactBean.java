package cn.lidongdong.weChatTelBook.bean;

import java.io.Serializable;

/**
 * Created by dllo on 16/8/17.
 */
public class ContactBean implements Serializable{
    private String name;
    private String telNum;

    public ContactBean() {
    }

    public ContactBean(String name, String telNum) {
        this.name = name;
        this.telNum = telNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
