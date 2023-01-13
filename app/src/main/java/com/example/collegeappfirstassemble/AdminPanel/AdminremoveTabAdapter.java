package com.example.collegeappfirstassemble.AdminPanel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collegeappfirstassemble.SocialMain.AddPost.UserProjectPosts;

public class AdminremoveTabAdapter extends FragmentStateAdapter {
    public AdminremoveTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new UserProjectPosts();
            case 2: return new RemoveFreelancePost();
        }
        return new RemoveSocialPost();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
