package cn.lidongdong.weChatTelBook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lidongdong.weChatTelBook.R;
import cn.lidongdong.weChatTelBook.fragment.YellowPageContectFragment;
import cn.lidongdong.weChatTelBook.fragment.YellowPageYeFragment;
import cn.lidongdong.weChatTelBook.fragment.YellowpageCallLogFragment;
import cn.lidongdong.weChatTelBook.fragment.YellowpageMessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/24.
 * 黄页
 * 子类执行前,先执行
 */
public class YellowActivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView contectTv, yePageTv;
    private ImageView backIv;
    private List<Fragment> fragments;
    private ViewPager yeVP;
    private TabLayout yeTl;
    private ImageView yeBackIv;

    @Override
    protected int setLayout() {
        return R.layout.activity_yellowpage;
    }

    @Override
    protected void initViews() {
        backIv = (ImageView) findViewById(R.id.ye_back_iv);
        yeTl = (TabLayout) findViewById(R.id.ye_tl);
        yeVP = (ViewPager) findViewById(R.id.ye_vp);
        yeBackIv = (ImageView) findViewById(R.id.ye_back_iv);
        yeBackIv.setOnClickListener(this);
        fragments = new ArrayList<>();

        fragments.add(new YellowPageYeFragment());
        fragments.add(new YellowPageContectFragment());
        fragments.add(new YellowpageCallLogFragment());
        fragments.add(new YellowpageMessageFragment());


        yeVP.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        yeTl.setupWithViewPager(yeVP);

        //设置Tab
        LayoutInflater inflater = getLayoutInflater();
        View yeYeTab = inflater.inflate(R.layout.tab_ye_ye, null);
        View yeContactTab=inflater.inflate(R.layout.tab_ye_contact,null);
        View yeCalllogTab=inflater.inflate(R.layout.tab_ye_calllog,null);
        View yeMessagetab=inflater.inflate(R.layout.tab_ye_message,null);

        yeTl.getTabAt(0).setCustomView(yeYeTab);
        yeTl.getTabAt(1).setCustomView(yeContactTab);
        yeTl.getTabAt(2).setCustomView(yeCalllogTab);
        yeTl.getTabAt(3).setCustomView(yeMessagetab);
        yeTl.setSelectedTabIndicatorColor(Color.BLUE);
    }
    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ye_back_iv:
                Intent intent =new Intent();
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
        }
    }
}
