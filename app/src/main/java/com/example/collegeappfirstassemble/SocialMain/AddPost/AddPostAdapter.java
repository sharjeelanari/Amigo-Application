package com.example.collegeappfirstassemble.SocialMain.AddPost;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AddPostAdapter extends FragmentStateAdapter {


     //this one is the third one amomg the list.(constructor matching super list)
    public AddPostAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    //here switch case is given.to return the specific page when position changes.
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return new Social_addpost();
    }

    //return 3-> there are 3 fragments , page therefore it is written 3.
    @Override
    public int getItemCount() {
        return 1;
    }
}
