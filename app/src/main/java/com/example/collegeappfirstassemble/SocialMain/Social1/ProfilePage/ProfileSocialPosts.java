package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappfirstassemble.AdminPanel.RemoveSocialAdapter;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileSocialPosts extends Fragment {

    RemoveSocialAdapter removeSocialAdapter;
    DatabaseReference db;
    RecyclerView recyclerView;


    public ProfileSocialPosts() {
        // Required empty public constructor
    }
    String user;

    public ProfileSocialPosts(String uid){
        this.user = uid;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile_social_posts, container, false);

        recyclerView = v.findViewById(R.id.profilesocialrec);

//        user = getActivity().getIntent().getStringExtra("uid");
        db = FirebaseDatabase.getInstance().getReference("UserSocialPosts").child(user);

        FirebaseRecyclerOptions<SocialAddpostModel> options = new FirebaseRecyclerOptions.Builder<SocialAddpostModel>()
                .setQuery(db, SocialAddpostModel.class).build();

        removeSocialAdapter = new RemoveSocialAdapter(options, getContext(), 2);

        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setStackFromEnd(true);

        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(removeSocialAdapter);


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        removeSocialAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        removeSocialAdapter.stopListening();
    }
}