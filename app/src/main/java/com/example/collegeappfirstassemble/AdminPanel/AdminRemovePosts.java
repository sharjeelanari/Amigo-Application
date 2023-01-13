package com.example.collegeappfirstassemble.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.collegeappfirstassemble.R;
import com.google.android.material.tabs.TabLayout;

public class AdminRemovePosts extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_posts);

        tabLayout = findViewById(R.id.removeposttab);
        viewPager2 = findViewById(R.id.viewpagerremovepost);

        FragmentManager fm = getSupportFragmentManager();

        AdminremoveTabAdapter adminremoveTabAdapter = new AdminremoveTabAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adminremoveTabAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Social"));
        tabLayout.addTab(tabLayout.newTab().setText("Projects"));
        tabLayout.addTab(tabLayout.newTab().setText("Freelance"));

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