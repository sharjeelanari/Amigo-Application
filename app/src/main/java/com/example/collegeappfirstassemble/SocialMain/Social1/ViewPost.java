package com.example.collegeappfirstassemble.SocialMain.Social1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewPost extends AppCompatActivity {

    TextView briefideaprotv, needforprotv, briefreq, basedon, name;
    Button reach;
    ImageView profile;

    DatabaseReference dbuserid, profiledb, projectref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        profile = findViewById(R.id.profileimgvp);
        briefideaprotv = findViewById(R.id.briefideaprotvvp);
        needforprotv = findViewById(R.id.needforprotvvp);
        basedon = findViewById(R.id.protopicstvvp);
        briefreq = findViewById(R.id.briefrequirementvp);
        reach = findViewById(R.id.reachprojectvp);
        name = findViewById(R.id.nameuservp);

        Intent intent = getIntent();
        String postid = intent.getStringExtra("postid");

        ProjectaddpostModel model = new ProjectaddpostModel();

        projectref = FirebaseDatabase.getInstance().getReference("ProjectPosts").child(postid);

        projectref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String showuser = snapshot.child("showuser").getValue(String.class);
                String moreidea = snapshot.child("moreidea").getValue(String.class);
                String skills = snapshot.child("skills").getValue(String.class);
                String otherdet = snapshot.child("otherdet").getValue(String.class);
                String based = snapshot.child("basedon").getValue(String.class);
                String uid1 = snapshot.child("uid").getValue(String.class);


                briefideaprotv.setText(moreidea);

                needforprotv.setText(skills);

                briefreq.setText(otherdet);
                basedon.setText(based);

                if (showuser.equals("Yes")) {

                    name.setText(uid1);
                } else {
                    name.setText("Anonymous");
                }
                dbuserid = FirebaseDatabase.getInstance().getReference("AllUsers")
                        .child(uid1);


                profiledb = FirebaseDatabase.getInstance().getReference("AllUsers");

                dbuserid.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String uid = snapshot.child("uid").getValue(String.class);
                        Intent intent = new Intent(ViewPost.this, Reaches.class);
                        intent.putExtra("userid", uid);

                        if (showuser.equals("Yes")) {

                            profiledb.child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String imguri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                                    Picasso.get().load(imguri).into(profile);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Picasso.get().load("@drawable/user.png").into(profile);
                        }


                        reach.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
                                    ViewPost.this.startActivity(intent);
                                }

                            }
                        });


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