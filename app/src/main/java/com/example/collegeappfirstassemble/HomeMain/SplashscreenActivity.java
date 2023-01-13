package com.example.collegeappfirstassemble.HomeMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.OtpSignup.MainActivity;
import com.example.collegeappfirstassemble.R;

public class SplashscreenActivity extends AppCompatActivity {

    Animation topanim,bottomanim;
    ImageView logoimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
       // bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logoimg=findViewById(R.id.logoid);
        logoimg.setAnimation(topanim);


        Thread thread=new Thread() {

            public void run() {
                try {
                    sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };thread.start();


    }
}