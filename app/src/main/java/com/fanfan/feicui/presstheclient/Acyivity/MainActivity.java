package com.fanfan.feicui.presstheclient.Acyivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fanfan.feicui.presstheclient.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private static final String SPLASH_CONFIG = "slpash_config";
    private static final String IS_FIRST_RUN  = "isFirstRun";
    private ViewPager mvp_pic;
    private ArrayList<View> mlist;
    private Button mbtn_skip;
    //定义数组图片
    int[] pic={R.mipmap.bd,R.mipmap.wy,R.mipmap.welcome,R.mipmap.small};

    private ImageView icon1,icon2,icon3,icon4;
    ImageView[] icons={icon1,icon2,icon3,icon4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次运行程序
        SharedPreferences preferences = getSharedPreferences(SPLASH_CONFIG, MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean(IS_FIRST_RUN, true);
        if (!isFirstRun) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
            initView();
        }
    }
    private void initView() {
        //获取mList对象
        mlist=new ArrayList<>();
        //初始化页面  关联id
        icons[0]= (ImageView) findViewById(R.id.icon1);
        icons[1]= (ImageView) findViewById(R.id.icon2);
        icons[2]= (ImageView) findViewById(R.id.icon3);
        icons[3]= (ImageView) findViewById(R.id.icon4);
        icons[0].setImageResource(R.drawable.adware_style_selected);
        mvp_pic= (ViewPager) findViewById(R.id.vp_pic);
//        mbtn_skip= (Button) findViewById(R.id.btn_skip);
//        mbtn_skip.setOnClickListener(this);
        //遍历图片数组
        for (int i = 0; i <pic.length ; i++) {
            ImageView iv=new ImageView(this);
            iv.setImageResource(pic[i]);
            mlist.add(iv);
        }
        mvp_pic.addOnPageChangeListener(this);
        mvp_pic.setAdapter(new MypagerAdapter(mlist));
    }

    //正在滚动的时候 调用的方法 会反复调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==3) {
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);

        }
        //更新下标图标
        for (int i = 0; i <icons.length ; i++) {
            icons[i].setImageResource(R.drawable.adware_style_default);
        }
        icons[position].setImageResource(R.drawable.adware_style_selected);
    }
    //当ViewPager在滚动的时候调用第一个方法
    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public void onClick(View view) {
//
//
//    }

    private class MypagerAdapter extends PagerAdapter {
        ArrayList<View> mlist;

        public MypagerAdapter(ArrayList<View> list) {
            mlist = list;
        }
        //初始化position 展现到界面上来
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mlist.get(position),0);
            return mlist.get(position);
        }
        //当不可见时  销毁position

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mlist.get(position));
        }

        @Override
        public int getCount() {
            if (mlist!=null){
            return mlist.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
