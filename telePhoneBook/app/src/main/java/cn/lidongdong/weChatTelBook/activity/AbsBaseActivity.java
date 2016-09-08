package cn.lidongdong.weChatTelBook.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import cn.lidongdong.weChatTelBook.datebases.SQLTool;

/**
 * Created by dllo on 16/8/24.
 * Activity的基类
 * Abs是抽象的缩写 abstract
 * Base:基础:基类:父类:超类
 *
 *
 * 在这个Activity的基类中:定制一些Activity的代码书写流程,精简下findViewById和intent跳转
 * 手机电量信号栏去掉等的属性
 */
public abstract class AbsBaseActivity extends AppCompatActivity{
    protected Context context;
    protected SQLTool sqlTool;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉手机电量状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //定制电话界面书写流程
        //1.绑定布局
        //抽象方法返回值是整型 R.layout.xxx的id地址
        setContentView(setLayout());
        context=this;
        //2.初始化组件
        initViews();
        //3.使用数据
        sqlTool=new SQLTool(this);
        initDatas();

    }

    //设置布局
    //抽象方法:返回值是R.layout.xxx的id

    /**
     * @param resId资源Id,eg:R.id.xx
     * @param <T>泛型(泛指一系列类型)
     *           限制条件:要是View的子类
     * @return
     */
    protected  abstract int setLayout();
    protected  abstract void initViews();
    protected abstract void initDatas();
    //findViewById
    protected <T extends View> T byView(int resId){
        T t= (T) findViewById(resId);
        return t;
    }

    /**
     * 精简跳转
     *@param from xx,this 跳转的启始界面
     * @param to 类型,某某类,要求继承自Activity
     */
    protected void goTo(Context from, Class<? extends Activity> to){
        //YellowPageActivity.class的类型是class
        //void 的类型是void
        Intent intent=new Intent(from,to);
        startActivity(intent);
    }
}
