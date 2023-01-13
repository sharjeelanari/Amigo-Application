package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ClubVpAdap extends FragmentStateAdapter {

    String uid;

    public ClubVpAdap(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String uid) {
        super(fragmentManager, lifecycle);
        this.uid = uid;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new ProfileSocialPosts(uid);
        }
        return new ProfileProjectPosts(uid);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
