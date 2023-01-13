package com.example.collegeappfirstassemble.EliteDivision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.EliteModel;
import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceAdapter;
import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceModel;
import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreelanceActivity extends AppCompatActivity {


    DatabaseReference databaseReference;
    FreelanceAdapter freelanceAdapter;
    RecyclerView freelancerecview;

    TextView addpostbtn;

    public  FreelanceActivity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelance);





        addpostbtn= findViewById(R.id.addf_postbtn);
        addpostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(
                    "https://docs.google.com/forms/d/e/1FAIpQLSe9LyBlEBcDg7sK10qZpY" +
                            "-_IYgnqqLTdrnlqQSyZkGlS8oZvQ/viewform?embedded=true"
                ));
                startActivity(intent);
            }
        });

        freelancerecview = (RecyclerView)findViewById(R.id.freelancerecview);
        freelancerecview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        databaseReference= FirebaseDatabase.getInstance().getReference("freelancetopic");

        FirebaseRecyclerOptions<FreelanceModel>options=new FirebaseRecyclerOptions.Builder<FreelanceModel>().setQuery(databaseReference,FreelanceModel.class).build();
        freelanceAdapter = new FreelanceAdapter(options,FreelanceActivity.this);
        freelancerecview.setAdapter(freelanceAdapter);

    }

    @Override
    protected void onStart() {
        freelanceAdapter.startListening();
        super.onStart();

    }

    @Override
    protected void onStop() {
        freelanceAdapter.stopListening();
        super.onStop();

    }
}