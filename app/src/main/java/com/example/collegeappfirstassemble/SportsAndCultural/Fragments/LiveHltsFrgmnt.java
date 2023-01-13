package com.example.collegeappfirstassemble.SportsAndCultural.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.SprtPstModel;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsPstEvntAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class LiveHltsFrgmnt extends Fragment {

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;

    SportsUpcomingRecAdap sportsUpcomingRecAdap;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_live_hlts_frgmnt, container, false);

        recyclerView1 = (RecyclerView)view.findViewById(R.id.upcomingrecyclerview);
        
        //These recycler views will fetch data from database.

        databaseReference = FirebaseDatabase.getInstance().getReference("AddEvent").child("Sport/Clutural");

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<AddEvent> options = new FirebaseRecyclerOptions.Builder<AddEvent>()
                .setQuery(databaseReference,AddEvent.class).build();

        sportsUpcomingRecAdap = new SportsUpcomingRecAdap(options);
        recyclerView1.setAdapter(sportsUpcomingRecAdap);

        recyclerView2 = (RecyclerView)view.findViewById(R.id.pastevntrecyclerview);
        ArrayList<SprtPstModel> list1 = new ArrayList<>();
        list1.add(new SprtPstModel("Cultural Dance", "Completed on 1st Feb (SET)", "Neutral Event"));
        list1.add(new SprtPstModel("RV football tournament", "Completed on 3st Feb (RVCE)", "Match 2(Lost)"));

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(new SportsPstEvntAdap(list1, getContext()));



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
