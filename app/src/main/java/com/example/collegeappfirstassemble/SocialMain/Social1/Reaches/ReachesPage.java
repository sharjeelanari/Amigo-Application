package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReachesPage extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference db;
    ReachPageAdapter reachPageAdapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaches_page);

        recyclerView = findViewById(R.id.reachesrecycler);
        back = findViewById(R.id.reachback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReachesPage.this , SocialActivity.class));
            }
        });


        String user = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        db = FirebaseDatabase.getInstance().getReference("ReachesPerPerson").child(user);

        recyclerView.setLayoutManager(new LinearLayoutManager(ReachesPage.this));

        FirebaseRecyclerOptions<ReachPageModel> options = new FirebaseRecyclerOptions.Builder<ReachPageModel>()
                .setQuery(db, ReachPageModel.class).build();

        reachPageAdapter = new ReachPageAdapter(options, ReachesPage.this);

        recyclerView.setAdapter(reachPageAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        reachPageAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        reachPageAdapter.stopListening();
    }
}