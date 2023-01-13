package com.example.collegeappfirstassemble;

import android.app.Application;
import android.content.Intent;

import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Home extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null){
            startActivity(new Intent(Home.this, HomePage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        
    }

}
