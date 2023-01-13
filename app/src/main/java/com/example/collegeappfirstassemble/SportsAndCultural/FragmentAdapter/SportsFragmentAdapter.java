package com.example.collegeappfirstassemble.SportsAndCultural.FragmentAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collegeappfirstassemble.SportsAndCultural.Fragments.LiveHltsFrgmnt;
import com.example.collegeappfirstassemble.SportsAndCultural.Fragments.SportsAddAnEvent;
import com.example.collegeappfirstassemble.SportsAndCultural.Fragments.SportsClubsFrgmnt;


public class SportsFragmentAdapter extends FragmentStateAdapter {


    public SportsFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch(position){
            case 1 : return new LiveHltsFrgmnt();
            default : return new SportsClubsFrgmnt();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
