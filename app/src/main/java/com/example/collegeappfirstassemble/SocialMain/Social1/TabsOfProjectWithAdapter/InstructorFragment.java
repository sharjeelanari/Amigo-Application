package com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter.InstructorAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter.InstructorModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InstructorFragment extends Fragment {


    RecyclerView ins_recyclrview;
    DatabaseReference databaseReference;
    InstructorModel instructorModel;
    InstructorAdapter instructorAdapter;


    public InstructorFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instructor,container,false);

        ins_recyclrview=view.findViewById(R.id.ins_recyclerview);
        ins_recyclrview.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference= FirebaseDatabase.getInstance().getReference("Instructor");

//        here we are calling to the options
        FirebaseRecyclerOptions<InstructorModel> options=new FirebaseRecyclerOptions.Builder<InstructorModel>().setQuery(databaseReference,InstructorModel.class).build();

        instructorAdapter=new InstructorAdapter(options, getContext());
        ins_recyclrview.setAdapter(instructorAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        instructorAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        instructorAdapter.stopListening();
    }
}