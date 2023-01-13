package com.example.collegeappfirstassemble.Subscription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.Subscription.SubAdapModel.SubRecViewAdapter;
import com.example.collegeappfirstassemble.Subscription.SubAdapModel.SubRecViewModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubscriptionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    SubRecViewAdapter subRecViewAdapter;
    SubRecViewModel subRecViewModel;

    Button postbtn;

    public  SubscriptionActivity(){

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscriptionactivity_main);

        recyclerView=(RecyclerView)findViewById(R.id.sub_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        databaseReference= FirebaseDatabase.getInstance().getReference("Subscriptionpostadded");

        FirebaseRecyclerOptions<SubRecViewModel>options=new FirebaseRecyclerOptions.Builder<SubRecViewModel>().setQuery(databaseReference,SubRecViewModel.class).build();
        subRecViewAdapter=new SubRecViewAdapter(options,getApplicationContext());
        recyclerView.setAdapter(subRecViewAdapter);

        postbtn=(Button)findViewById(R.id.postsub_buttonid);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SubscriptionActivity.this,PostSubAddActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        subRecViewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        subRecViewAdapter.stopListening();
    }

}