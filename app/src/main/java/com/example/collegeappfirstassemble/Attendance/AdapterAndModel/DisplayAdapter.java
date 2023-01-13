package com.example.collegeappfirstassemble.Attendance.AdapterAndModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DisplayAdapter extends FragmentStateAdapter {
    public DisplayAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return null;
//                new DisplayclassFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
