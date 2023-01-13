package com.example.collegeappfirstassemble.Attendance;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.AddAllFieldsAdapter;
import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.DisplayclassFragment;
import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.StudentModel;
import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Attendanceactivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    DatabaseReference databaseReference, attndncedb, batchdb;
    AddAllFieldsAdapter attendanceAdapter;
    String clickedBatch;
    Dialog dialog;

    Calendar calendar;
    String formatteddate;

//    RecyclerView recyclerView;

    public static FragmentManager fragmentManager;

    Button addstudentbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendanceactivity);

        dialog = new Dialog(Attendanceactivity.this);
        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSubmit = dialog.findViewById(R.id.dialog_submit);
        dialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBatch = null;
                Intent intent = new Intent(Attendanceactivity.this, Attendanceactivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button dialogReview = dialog.findViewById(R.id.dialog_review);
        dialogReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        clickedBatch = getIntent().getStringExtra("Batch");

//        recyclerView = findViewById(R.id.recv);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH");
        formatteddate = df.format(calendar.getTime());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DisplayclassFragment displayclassFragment = new DisplayclassFragment();
        fragmentTransaction.add(R.id.fragment_container, displayclassFragment, null);
        fragmentTransaction.commit();

//        recyclerView.setLayoutManager(new LinearLayoutManager(Attendanceactivity.this));

        addstudentbtn = (Button) findViewById(R.id.addstudentbutton);
        addstudentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendanceactivity.this, AddAllFieldsActivity.class);
                startActivity(intent);
            }
        });

        if (clickedBatch != null) {

            viewPager2 = (ViewPager2) findViewById(R.id.viewpager2);

            databaseReference = FirebaseDatabase.getInstance().getReference("teacherName").child(clickedBatch);
            FirebaseRecyclerOptions<StudentModel> options = new FirebaseRecyclerOptions.Builder<StudentModel>().setQuery(databaseReference, StudentModel.class).build();

            attendanceAdapter = new AddAllFieldsAdapter(options, Attendanceactivity.this);
            viewPager2.setAdapter(attendanceAdapter);

            attndncedb = FirebaseDatabase.getInstance().getReference(clickedBatch).child(formatteddate);

            ShowDialog();

        }
    }

    @Override
    protected void onStart() {
        if (clickedBatch != null) {
            attendanceAdapter.startListening();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (clickedBatch != null) {
            attendanceAdapter.stopListening();
        }
        super.onStop();
    }

    private void ShowDialog(){
        attndncedb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long count1 = snapshot.getChildrenCount();

                        if(count == count1){
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}