package com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.CulturalClubAdapter;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.CulturalClubModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CulturalClubActivity extends AppCompatActivity {


    ImageView culturallogo;
    RecyclerView culturalrecview;
    com.example.collegeappfirstassemble.SportsAndCultural.Adapter.CulturalClubAdapter culturalClubAdapter;

    DatabaseReference db;

    ArrayList<CulturalClubModel> list;
    private Object CulturalClubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_club);

        culturallogo=findViewById(R.id.culturallogo);
        culturalrecview = findViewById(R.id.culturalrecview);
        ArrayList<CulturalClubModel>list=new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference("Clubs").child("Cultural");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false);
        culturalrecview.setLayoutManager(gridLayoutManager);
        FirebaseRecyclerOptions<CulturalClubModel> options = new FirebaseRecyclerOptions.Builder<CulturalClubModel>()
                .setQuery(db, CulturalClubModel.class).build();

        CulturalClubAdapter = new CulturalClubAdapter(options, getApplicationContext());
        culturalrecview.setAdapter(culturalClubAdapter);

    }
}