package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.UserProjectPostAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileProjectPosts extends Fragment {

    RecyclerView recyclerView;

    //database reference used for pointing database.
    DatabaseReference databaseReference;
    //project adapater (recyclerview)
    UserProjectPostAdapter projectAdapter;
    ProjectModel projectModel;

    public ProfileProjectPosts() {
        // Required empty public constructor
    }

    String user;
    public ProfileProjectPosts(String uid){
        this.user = uid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile_project_posts, container, false);
        recyclerView=v.findViewById(R.id.profileprojectposts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        user = getActivity().getIntent().getStringExtra("uid");


//        String user = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        databaseReference= FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(user);

        //we are calling to the options.

        FirebaseRecyclerOptions<ProjectaddpostModel> options=new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
                .setQuery(databaseReference,ProjectaddpostModel.class).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        projectAdapter = new UserProjectPostAdapter(options,getContext(), 1);
        recyclerView.setAdapter(projectAdapter);
        databaseReference.keepSynced(true);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        projectAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        projectAdapter.stopListening();
    }


}