package com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural;

import static com.example.collegeappfirstassemble.R.layout.activity_sports_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.FragmentAdapter.SportsFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class SportsMain extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    SportsFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_sports_main);

        tabLayout=(TabLayout) findViewById(R.id.bottomtablayout);
        viewPager2=(ViewPager2) findViewById(R.id.viewpagersports);


        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new SportsFragmentAdapter(fm, getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Sports & Clubs"));
//        tabLayout.addTab(tabLayout.newTab().setText("Live/Highlights"));

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

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}