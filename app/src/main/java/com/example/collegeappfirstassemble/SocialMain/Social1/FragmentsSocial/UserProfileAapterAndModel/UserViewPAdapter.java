package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.collegeappfirstassemble.AdminPanel.RemoveSocialPost;
import com.example.collegeappfirstassemble.SocialMain.AddPost.UserProjectPosts;

public class UserViewPAdapter extends FragmentStateAdapter {
    public UserViewPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1: return new UserProjectPosts();
        }
        return new UserSocialPosts();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
