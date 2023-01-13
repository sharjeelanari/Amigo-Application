package com.example.collegeappfirstassemble.SocialMain.Social1;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.R;

public class SocialSplashActivity extends AppCompatActivity {

    Animation topanimation;
    ImageView amigosociallogoimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.socialsplashactivity);
        amigosociallogoimg=(ImageView)findViewById(R.id.amigosociallogo);
        topanimation= AnimationUtils.loadAnimation(this,R.anim.topanim);
        // bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        amigosociallogoimg.setAnimation(topanimation);


        Thread thread=new Thread() {

            public void run() {
                try {
                    sleep(2300);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                   Intent intent = new Intent(SocialSplashActivity.this, SocialActivity.class);

                   startActivity(intent);
                   finish();
                }
            }
        };thread.start();
    }
}