package com.example.collegeappfirstassemble.SocialMain.Social1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Add_PostActivity;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Project_add;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.HomemainFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.NotificationFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.ProjectFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.SearchFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.ReachesPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SocialActivity extends AppCompatActivity {




    //for navigation view
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
       // here the default fragment while opening is neede to be set up. only instead of temp the one which
        //we want to fix as the first fragment need to be written there.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframelayout, new HomemainFragment()).commit();

        //for adding an post




        //setup of bottom navigation view.


       bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottombar);
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {

              Fragment temp = null;

              switch (item.getItemId()){
                  case R.id.homeicon :temp = new HomemainFragment();
                  break;
                  case R.id.projecticon :temp = new ProjectFragment();
                  break;
                  case R.id.searchicon :temp = new SearchFragment();
                  break;
                  case R.id.notifyicon :temp = new NotificationFragment();
                  break;
                  case R.id.profileicon :temp = new UserFragment();
                  break;

              }
              getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframelayout,temp).commit();

              return true;
          }
      });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    }

}