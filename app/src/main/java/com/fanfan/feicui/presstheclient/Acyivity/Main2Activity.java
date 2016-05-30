package com.fanfan.feicui.presstheclient.Acyivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.fanfan.feicui.presstheclient.R;

public class Main2Activity extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        mImageView= (ImageView) findViewById(R.id.iv_pic);
        mImageView.setAnimation(animation);
        Animation.AnimationListener animationListener=new Animation.AnimationListener() {
            //动画开始
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(Main2Activity.this,SlpashActivity.class);
                startActivity(intent);
            }
            //动画重复
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        animation.setAnimationListener(animationListener);
    }
}
