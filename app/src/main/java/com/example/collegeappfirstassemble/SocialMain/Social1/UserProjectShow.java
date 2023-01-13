package com.example.collegeappfirstassemble.SocialMain.Social1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Project_add;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserProjectShow extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    ProjectAdapter projectAdapter;

    public UserProjectShow(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_project_show, container, false);

        recyclerView=view.findViewById(R.id.projectrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getArguments();
        String uid = b.getString("name");


        databaseReference= FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(uid);

        FirebaseRecyclerOptions<ProjectaddpostModel> options=new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>().setQuery(databaseReference,ProjectaddpostModel.class).build();

        projectAdapter = new ProjectAdapter(options,getContext());
        recyclerView.setAdapter(projectAdapter);
        databaseReference.keepSynced(true);

        //we are calling to the options.


        return view;
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