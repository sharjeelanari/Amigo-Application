package com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PFTabAdapter extends FragmentStateAdapter {


    public PFTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position){

            case 1:
                return new InstructorFragment();

        }
        return new AvailableprojectFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
