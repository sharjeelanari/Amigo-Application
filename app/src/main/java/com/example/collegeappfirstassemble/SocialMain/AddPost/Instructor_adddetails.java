package com.example.collegeappfirstassemble.SocialMain.AddPost;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter.InstructorModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Instructor_adddetails extends Fragment {

    DatabaseReference databaseReference;

    //declaration of fileds;
    EditText instructor_name,instructor_domain, instructor_bio;
    Button instructor_reach;

    // model class
    InstructorModel instructorModel;





    public Instructor_adddetails() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_instructor_adddetails, container, false);

        instructor_name = view.findViewById(R.id.ins_name);
        instructor_domain=view.findViewById(R.id.ins_domain);
        instructor_bio=view.findViewById(R.id.ins_bio);
        instructor_reach=view.findViewById(R.id.ins_reach);

        instructorModel = new InstructorModel();

        instructor_reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ins_name1=instructor_name.getText().toString();
                String ins_domain1=instructor_domain.getText().toString();
                String ins_bio1=instructor_bio.getText().toString();


                if(ins_name1.trim().isEmpty()&&ins_domain1.trim().isEmpty()&&ins_bio1.trim().isEmpty()){
                    Toast.makeText(getContext(),"Enter the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    databaseReference= FirebaseDatabase.getInstance().getReference("Instructoradded").child(ins_name1);
                    addtodb(ins_name1,ins_domain1,ins_bio1);
                    Intent intent=new Intent(getContext(), SocialActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });


        return view;
    }

    public void addtodb(String ins_name1, String ins_domain1, String ins_bio1){
        instructorModel.setIns_name(ins_name1);
        instructorModel.setIns_domain(ins_domain1);
        instructorModel.setIns_bio(ins_bio1);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(instructorModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}