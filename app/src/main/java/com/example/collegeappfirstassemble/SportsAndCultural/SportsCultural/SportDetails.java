package com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.SportDetailsModel;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportDetailsAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SportDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    TextView sportname;
    String namesport;

    SportDetailsAdap sportDetailsAdap;
    SportsUpcomingRecAdap sportsUpcomingRecAdap;

    DatabaseReference db;
    DatabaseReference db1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_details);

        recyclerView = (RecyclerView)findViewById(R.id.teamrecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = (RecyclerView)findViewById(R.id.upcmngrecycler);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        sportname = (TextView)findViewById(R.id.nameofsport);
        sportname.setText(getIntent().getStringExtra("sportname"));

        namesport = getIntent().getStringExtra("sportname").trim();
        db = FirebaseDatabase.getInstance().getReference().child(namesport);

        db1 = FirebaseDatabase.getInstance().getReference("TypeWiseEvents").child(namesport);

        //Here firebase UI class is used which is inflated from SportDetailsAdap (which is again a Firebase Adapter).

        FirebaseRecyclerOptions<SportDetailsModel> options = new FirebaseRecyclerOptions.Builder<SportDetailsModel>()
                .setQuery(db,SportDetailsModel.class).build();

        sportDetailsAdap = new SportDetailsAdap(options);
        recyclerView.setAdapter(sportDetailsAdap);

                    FirebaseRecyclerOptions<AddEvent> options1 = new FirebaseRecyclerOptions.Builder<AddEvent>()
                            .setQuery(db1, AddEvent.class).build();

                    sportsUpcomingRecAdap = new SportsUpcomingRecAdap(options1);
                    recyclerView1.setAdapter(sportsUpcomingRecAdap);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sportDetailsAdap.startListening();
        sportsUpcomingRecAdap.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sportDetailsAdap.stopListening();
        sportsUpcomingRecAdap.stopListening();
    }
}