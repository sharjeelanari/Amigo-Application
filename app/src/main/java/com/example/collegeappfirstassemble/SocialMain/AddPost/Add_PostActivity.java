package com.example.collegeappfirstassemble.SocialMain.AddPost;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegeappfirstassemble.R;
import com.google.android.material.tabs.TabLayout;

public class Add_PostActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    AddPostAdapter addPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post);

        tabLayout=(TabLayout)findViewById(R.id.tabLayoutaddpost);
        viewPager2=(ViewPager2)findViewById(R.id.viewpageraddpost);

        FragmentManager fragmentManager=getSupportFragmentManager();

        addPostAdapter = new AddPostAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(addPostAdapter);


        tabLayout.addTab(tabLayout.newTab().setText("Social_addpost"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //for tabbed activity swipe this below  function/method is created

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });






    }
}