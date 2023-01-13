package com.example.collegeappfirstassemble.AdminPanel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceModel;
import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RemoveFreelancePost extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference db;

    RemoveFreelanceAdapter removeFreelanceAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_remove_freelance_post, container, false);

        recyclerView = v.findViewById(R.id.freelancerecview1);

        db = FirebaseDatabase.getInstance().getReference("freelancetopic");

        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setStackFromEnd(true);

        FirebaseRecyclerOptions<FreelanceModel> options =new FirebaseRecyclerOptions.Builder<FreelanceModel>()
                .setQuery(db, FreelanceModel.class).build();

        removeFreelanceAdapter = new RemoveFreelanceAdapter(options, getContext());

        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(removeFreelanceAdapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        removeFreelanceAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        removeFreelanceAdapter.stopListening();
    }
}