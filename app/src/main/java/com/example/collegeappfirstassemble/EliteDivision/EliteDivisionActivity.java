
package com.example.collegeappfirstassemble.EliteDivision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.EliteAdapter;
import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.EliteModel;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EliteDivisionActivity extends AppCompatActivity {

    RecyclerView recyclerView, upcoming;
    Button freelancebtn;

    DatabaseReference eventdb;
    SportsUpcomingRecAdap sportsUpcomingRecAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elitedivisionactivity);

//        recyclerView=(RecyclerView)findViewById(R.id.eliterecview);
        upcoming = findViewById(R.id.fmlayoutelite);

//        ArrayList<EliteModel>list=new ArrayList<>();
//        list.add(new EliteModel("ELITE TECH"));
//        list.add(new EliteModel("ELITE ECE"));
//        list.add(new EliteModel("ELITE ASE"));
//        list.add(new EliteModel("ELITE CSE"));
//        list.add(new EliteModel("ELITE MECH"));
//        list.add(new EliteModel("ELITE CIVIL"));

        //freelance btn on click
//        freelancebtn = (Button)findViewById(R.id.freelancebtn);
//        freelancebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EliteDivisionActivity.this,FreelanceActivity.class);
//                startActivity(intent);
//            }
//        });

        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.textgreenish));

        upcoming.setLayoutManager(new LinearLayoutManager(EliteDivisionActivity.this));
        eventdb = FirebaseDatabase.getInstance().getReference("AddEvent").child("Tech");

        FirebaseRecyclerOptions<AddEvent> op = new FirebaseRecyclerOptions.Builder<AddEvent>()
                .setQuery(eventdb, AddEvent.class).build();

        sportsUpcomingRecAdap = new SportsUpcomingRecAdap(op);
        upcoming.setAdapter(sportsUpcomingRecAdap);

//        EliteAdapter eliteAdapter = new EliteAdapter(list,this);
//        recyclerView.setAdapter(eliteAdapter);

//        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        sportsUpcomingRecAdap.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sportsUpcomingRecAdap.stopListening();
    }
}


