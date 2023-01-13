package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter.PFTabAdapter;
import com.google.android.material.tabs.TabLayout;


public class ProjectFragment extends Fragment {


    //For tabbed activity for project
    TabLayout tabLayout1;
    ViewPager2 viewPager2;
    PFTabAdapter adapter1;
    SocialToolbarFragment socialToolbarFragment;

    //this much declaration.

    //EMPTY CONSTRUCTOR


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_project, container, false);

        socialToolbarFragment = new SocialToolbarFragment();

        FragmentManager fm1 = getChildFragmentManager();
        FragmentTransaction ft = fm1.beginTransaction();
        ft.add(R.id.toolbarproject, socialToolbarFragment).commit();

        //This for tab layout.
        viewPager2 = view.findViewById(R.id.viewpager2);
        tabLayout1 =view.findViewById(R.id.tblayout1);

        FragmentManager fm = getChildFragmentManager();

        adapter1= new PFTabAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter1);

        //these are the name of the tabs
        tabLayout1.addTab(tabLayout1.newTab().setText("Available projects"));
        tabLayout1.addTab(tabLayout1.newTab().setText("Instructor"));

        // tabbed activity on selected listener
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                tabLayout1.selectTab(tabLayout1.getTabAt(position));
            }
        });

        return view;

    }
}