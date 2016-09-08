package cn.lidongdong.weChatTelBook.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import cn.lidongdong.weChatTelBook.R;

import java.util.ArrayList;
import java.util.List;

import cn.lidongdong.weChatTelBook.fragment.CallFragment;
import cn.lidongdong.weChatTelBook.fragment.CalllogFragment;
import cn.lidongdong.weChatTelBook.fragment.ContactFragment;
import cn.lidongdong.weChatTelBook.fragment.MessageFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainVp;
    private TabLayout mainTl;
    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        //去掉顶部的通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        mainTl = (TabLayout) findViewById(R.id.main_tl);
        fragments=new ArrayList<>();
        fragments.add(new CallFragment());
        fragments.add(new CalllogFragment());
        fragments.add(new ContactFragment());
        fragments.add(new MessageFragment());


        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
         mainTl.setupWithViewPager(mainVp);
        //设置Tab
//        View callTab=inflater.inflate(R.layout.tab_call,null);
//        View calllogTab=inflater.inflate(R.layout.tab_calllog,null);
//        View contactTab=inflater.inflate(R.layout.tab_contact,null);
//        View messageTab=inflater.inflate(R.layout.tab_message,null);
        //推荐方法
        mainTl.getTabAt(0).setCustomView(R.layout.tab_call);
        mainTl.getTabAt(1).setCustomView(R.layout.tab_calllog);
        mainTl.getTabAt(2).setCustomView(R.layout.tab_contact);
        mainTl.getTabAt(3).setCustomView(R.layout.tab_message);

        //设置引导线和文字颜色
        mainTl.setSelectedTabIndicatorColor(Color.BLUE);
        mainTl.setTabTextColors(Color.GRAY,Color.GREEN);

    }


}