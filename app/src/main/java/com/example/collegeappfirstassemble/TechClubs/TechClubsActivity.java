package com.example.collegeappfirstassemble.TechClubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.example.collegeappfirstassemble.TechClubs.TechAdapterModel.TechclubModel;
import com.example.collegeappfirstassemble.TechClubs.TechAdapterModel.TechclubRecAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TechClubsActivity extends AppCompatActivity {

    DatabaseReference databaseReference,eventdb;
    TechclubRecAdapter techclubRecAdapter;
    TechclubModel techclubModel;

    SportsUpcomingRecAdap sportsUpcomingRecAdap;

    RecyclerView techclubrecyclerview, upcoming;
    Button addaclub;

    public  TechClubsActivity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.techclubsmain_activity);


        upcoming = findViewById(R.id.upcomingrec);

        techclubrecyclerview=(RecyclerView)findViewById(R.id.techclubrecview);
        techclubrecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        //database referencing .here database reference is a pointer which points to the location new clubs as mentioned.
        databaseReference= FirebaseDatabase.getInstance().getReference("Clubs").child("Tech");

        //adapter setting
        FirebaseRecyclerOptions<TechclubModel>options=new FirebaseRecyclerOptions.Builder<TechclubModel>().setQuery(databaseReference,TechclubModel.class).build();
        techclubRecAdapter=new TechclubRecAdapter(options,getApplicationContext());
        techclubrecyclerview.setAdapter(techclubRecAdapter);

        upcoming.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        eventdb = FirebaseDatabase.getInstance().getReference("AddEvent").child("Tech");

        FirebaseRecyclerOptions<AddEvent> op = new FirebaseRecyclerOptions.Builder<AddEvent>()
                .setQuery(eventdb, AddEvent.class).build();

        sportsUpcomingRecAdap = new SportsUpcomingRecAdap(op);
        upcoming.setAdapter(sportsUpcomingRecAdap);


        //button part
//        addaclub=(Button)findViewById(R.id.addclubbtn);
//        addaclub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(TechClubsActivity.this, AddClubActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    protected void onStart() {
     techclubRecAdapter.startListening();
     sportsUpcomingRecAdap.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        techclubRecAdapter.stopListening();
        sportsUpcomingRecAdap.stopListening();
        super.onStop();
    }
}