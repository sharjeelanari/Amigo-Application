package com.example.collegeappfirstassemble.Attendance.AdapterAndModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DisplayclassFragment extends Fragment {

    DatabaseReference databaseReference;
    AddClassAdapter addClassAdapter;

    Button addstudentbutton;

    Button addclassbtn;

    RecyclerView recyclerView;



    public DisplayclassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_displayclass, container, false);

        addstudentbutton=view.findViewById(R.id.addstudentbutton);
        recyclerView=view.findViewById(R.id.classdisplayrecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference=FirebaseDatabase.getInstance().getReference("TeacherBatches").child("teacherName");

        FirebaseRecyclerOptions<BatchModel>options=new FirebaseRecyclerOptions.Builder<BatchModel>().setQuery(databaseReference, BatchModel.class).build();
        addClassAdapter = new AddClassAdapter(options,getContext());
        recyclerView.setAdapter(addClassAdapter);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        addClassAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        addClassAdapter.stopListening();
    }
}