package com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Project_add;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AvailableprojectFragment extends Fragment {

    RecyclerView recyclerView;

    //database reference used for pointing database.
    DatabaseReference databaseReference;
    //project adapater (recyclerview)
    ProjectAdapter projectAdapter;
    ProjectModel projectModel;


    public AvailableprojectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_availableproject, container, false);

        recyclerView = view.findViewById(R.id.projectrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        databaseReference = FirebaseDatabase.getInstance().getReference("ProjectPosts");

        //we are calling to the options.

        FirebaseRecyclerOptions<ProjectaddpostModel> options = new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>().setQuery(databaseReference, ProjectaddpostModel.class).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        projectAdapter = new ProjectAdapter(options, getContext());
        recyclerView.setAdapter(projectAdapter);
        databaseReference.keepSynced(true);
        projectAdapter.notifyDataSetChanged();


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