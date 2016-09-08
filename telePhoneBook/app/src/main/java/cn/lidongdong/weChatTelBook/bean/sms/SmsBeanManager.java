package cn.lidongdong.weChatTelBook.bean.sms;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/25.
 * 短信实体类的管理者
 */
public class SmsBeanManager {
    //短信的分组集合
    //用来给RecycleView用
    private List<SmsGroupBean> smsGroupBeanList;

    public SmsBeanManager(){
        smsGroupBeanList=new ArrayList<>();
    }
    //添加数据方法
    //参数是:短信号,短信内容,短信日期
    public void addData(String num,String body,String date){
     //在添加短信时,首先要判断是否有该分组
        SmsGroupBean smsGroupBean=null;
        //循环遍历分组集合,瞎看是否有重复的分组
        for(SmsGroupBean eachGroup:smsGroupBeanList){
            //判断当前的号码是否与分组号码一致
            if (num.equals(eachGroup.getNum())){
                //如果一致,证明有该分组
                //结束循环
                smsGroupBean=eachGroup;
             //   Log.d("SmsBeanManager", "有分组");
                break;
            }
        }
        //如果循环结束,分组对象还是空,
        //证明没有该分组,创建分组
        if (smsGroupBean==null) {
            smsGroupBean=new SmsGroupBean();
            smsGroupBean.setNum(num);
            //加入集合
            smsGroupBeanList.add(smsGroupBean);
        }
        //分组存入短信
        smsGroupBean.addSmsIntoGroup(new SmsBean(body,date));
    }
    //适配器getCount用
    public int getSize(){
        return  smsGroupBeanList.size();
    }
    //getItem用
    public SmsGroupBean getItemData(int pos){
        return smsGroupBeanList.get(pos);
    }
}
