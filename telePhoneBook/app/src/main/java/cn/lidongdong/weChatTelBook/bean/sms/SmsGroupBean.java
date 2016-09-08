package cn.lidongdong.weChatTelBook.bean.sms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/25.
 * 短信的分组类
 * 包含号码和号码对应的短信集合
 */
public class SmsGroupBean implements Serializable{
    private String num;
    //装载基本短信的集合
    private List<SmsBean> smsBeanList;

    //在构造方法中初始化集合对象
    public SmsGroupBean() {
        smsBeanList = new ArrayList<>();

    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    //提供一个方法向集合中添加数据(短信)
    public void addSmsIntoGroup(SmsBean smsBean) {
        //新加入分组的短信都是最近加入的
        //因此要让他在集合的最上方

        smsBeanList.add(0, smsBean);

    }

    //获取集合中的最后一行值
    //用来显示在列表中
    public SmsBean getLastSms() {
        return smsBeanList.get(smsBeanList.size() - 1);
    }

    public List<SmsBean> getSmsBeanList() {
        return smsBeanList;
    }
}
