package com.example.collegeappfirstassemble.SportsAndCultural.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.SportsModel;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsRecyclerAdapter;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SportsClubsFrgmnt extends Fragment {
    RecyclerView recyclerView, recyclerView1;
    DatabaseReference databaseReference;
    SportsUpcomingRecAdap sportsUpcomingRecAdap;

    public SportsClubsFrgmnt(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports_clubs_frgmnt, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.SportsRecycler);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.upcomingrecyclerview);

        //These recycler views will fetch data from database.

        databaseReference = FirebaseDatabase.getInstance().getReference("AddEvent").child("Sport/Clutural");

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<AddEvent> options = new FirebaseRecyclerOptions.Builder<AddEvent>()
                .setQuery(databaseReference,AddEvent.class).build();

        sportsUpcomingRecAdap = new SportsUpcomingRecAdap(options);
        recyclerView1.setAdapter(sportsUpcomingRecAdap);


        ArrayList<SportsModel> list2 = new ArrayList<>();
        list2.add(new SportsModel("Cricket", "Captain Name"));
        list2.add(new SportsModel("Football", "Captain Name"));
        list2.add(new SportsModel("Basketball", "Captain Name"));
        list2.add(new SportsModel("Kabaddi", "Captain Name"));
        list2.add(new SportsModel("Badminton", "Captain Name"));
        list2.add(new SportsModel("Basketball Girls", "Captain Name"));
        list2.add(new SportsModel("Volleyball", "Captain Name"));
        list2.add(new SportsModel("Volleyball Girls", "Captain Name"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SportsRecyclerAdapter(list2, getContext()));


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        sportsUpcomingRecAdap.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        sportsUpcomingRecAdap.stopListening();
    }
}