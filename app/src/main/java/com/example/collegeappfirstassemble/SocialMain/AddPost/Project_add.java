package com.example.collegeappfirstassemble.SocialMain.AddPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Project_add extends AppCompatActivity {
    EditText basedon, skills, detaiils, otherdet;
    RadioButton box;
    RadioGroup usershow;
    Button submit;
    DatabaseReference db, userdb, userprojectdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        basedon = findViewById(R.id.projectHeadline);
        skills = findViewById(R.id.needDetails);
        detaiils = findViewById(R.id.MoreIdea);
        otherdet = findViewById(R.id.Otherdet);
        submit = findViewById(R.id.addprojectbtn);
        usershow = findViewById(R.id.usershow);

        db = FirebaseDatabase.getInstance().getReference("ProjectPosts");
        userdb = FirebaseDatabase.getInstance().getReference("AllUsers");


        ProjectaddpostModel projectaddpostModel = new ProjectaddpostModel();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checked = "";
                int id = usershow.getCheckedRadioButtonId();

                switch(id){
                    case R.id.Radioyes:
                        checked = "Yes";
                        break;
                    case R.id.RadioNo:
                        checked = "No";
                        break;
                }

                String basedOn = basedon.getText().toString().trim();
                String Skills = skills.getText().toString().trim();
                String Otherdet = otherdet.getText().toString().trim();
                String Details = detaiils.getText().toString().trim();
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                userprojectdb = FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(userid);


                if (basedon != null && skills!=null && otherdet!=null){
                    projectaddpostModel.setBasedon(basedOn);
                    projectaddpostModel.setMoreidea(Details);
                    projectaddpostModel.setOtherdet(Otherdet);
                    projectaddpostModel.setSkills(Skills);
                    projectaddpostModel.setShowuser(checked);


                    String key = db.push().getKey();
                    String key1 = userprojectdb.push().getKey();

                    userdb.child(userid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name = snapshot.child("name").getValue(String.class);
                            projectaddpostModel.setUid(name);

                            db.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    db.child(key).setValue(projectaddpostModel);

                                    userprojectdb.child(key1).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            userprojectdb.child(key1).setValue(projectaddpostModel);

                                            basedon.setText("");
                                            skills.setText("");
                                            otherdet.setText("");
                                            detaiils.setText("");


                                            Intent intent = new Intent(Project_add.this, SocialActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
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

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(Project_add.this, "Please put every required detail", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}