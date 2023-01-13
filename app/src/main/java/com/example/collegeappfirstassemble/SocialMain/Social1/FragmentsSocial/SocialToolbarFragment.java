package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Add_PostActivity;
import com.example.collegeappfirstassemble.SocialMain.AddPost.Project_add;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.ReachesPage;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SocialToolbarFragment extends Fragment {

    ImageView imageView;
    TextView Reach;

    DatabaseReference db;
    Dialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_social_toolbar, container, false);

        imageView =  v.findViewById(R.id.addanpost);
        Reach = v.findViewById(R.id.socialbutton);

        db = FirebaseDatabase.getInstance().getReference("Users");
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button post = dialog.findViewById(R.id.dialog_submit);
        post.setText("Social Post");

        Button projectpost = dialog.findViewById(R.id.dialog_review);
        projectpost.setText("Project Post");

        TextView text = dialog.findViewById(R.id.dialog_message);
        text.setText("What are you posting?");


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getContext(), Add_PostActivity.class);
                startActivity(intent);
                dialog.dismiss();

            }
        });

        projectpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Project_add.class));
                dialog.dismiss();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            startActivity(new Intent(getContext(), Project_add.class));
                        }else{
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });


        Reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ReachesPage.class));
            }
        });

        return v;
    }
}