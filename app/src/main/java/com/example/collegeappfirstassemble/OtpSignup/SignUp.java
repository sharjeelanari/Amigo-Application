package com.example.collegeappfirstassemble.OtpSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ProfileModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private EditText inputemail;
    private EditText inputname;
    private EditText inputUSN;
    private EditText inputcollege, teacherId;
    private TextView forteacher, forstudent;
    private Button Signup;
    StudentInfo studentInfo;
    TeacherModel teacherModel;

    Boolean isTeacher;

    ProfileModel profileModel;

    //stating databases and references

    DatabaseReference databaseReference, userReference, userReference1, userReference2, USNcheck, Teacherdb, Allusers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initiating input fields

        inputemail = (EditText) findViewById(R.id.email);
        inputname = (EditText) findViewById(R.id.Name);
        inputUSN = (EditText) findViewById(R.id.usn);
        inputcollege = (EditText) findViewById(R.id.college);
        teacherId = findViewById(R.id.TeacherId);
        Signup = (Button) findViewById(R.id.signupbtn);
        forteacher = findViewById(R.id.signupforteacher);
        forstudent = findViewById(R.id.forstudent);

        USNcheck = FirebaseDatabase.getInstance().getReference("AllUsers");


        //initiating StudentInfo class variable

        studentInfo = new StudentInfo();
        profileModel = new ProfileModel();
        teacherModel = new TeacherModel();

        //Switching input fields for teacher and student
        forteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherId.setVisibility(View.VISIBLE);
                inputUSN.setVisibility(View.INVISIBLE);
                forstudent.setVisibility(View.VISIBLE);
                forteacher.setVisibility(View.GONE);
            }
        });

        forstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherId.setVisibility(View.GONE);
                inputUSN.setVisibility(View.VISIBLE);
                forstudent.setVisibility(View.GONE);
                forteacher.setVisibility(View.VISIBLE);

            }
        });


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!teacherId.getText().toString().trim().isEmpty()) {
                    //Putting input data into string (including mobile number which was sent from last activity)

                    String mobile = getIntent().getStringExtra("mobile");
                    String email = inputemail.getText().toString();
                    String name = inputname.getText().toString();
                    String teacherid = teacherId.getText().toString();
                    String college = inputcollege.getText().toString();

                    //Condition if input remains empty

                    if (inputemail.getText().toString().trim().isEmpty() && inputname.getText().toString().trim().isEmpty()
                            && inputcollege.getText().toString().trim().isEmpty() && teacherid.trim().isEmpty()) {

                        Toast.makeText(SignUp.this, "Please input required info", Toast.LENGTH_LONG).show();
                    } else {
                        if (email.contains("@jainuniversity.ac.in")) {
                            char a = email.charAt(0);
                            char b = email.charAt(1);
                            char c = email.charAt(0);
                            if (!Character.isDigit(a) && !Character.isDigit(b)) {


                                //getting firebase database's reference using path StudentInfo java class
                                String Userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                Teacherdb = FirebaseDatabase.getInstance().getReference("TeacherInfo").child(mobile);
                                Allusers = FirebaseDatabase.getInstance().getReference("AllUsers");
                                userReference = FirebaseDatabase.getInstance().getReference("Teachers").child(name);

                                userReference1 = FirebaseDatabase.getInstance().getReference("Teachers").child(Userid);

                                userReference2 = FirebaseDatabase.getInstance().getReference("SearchProfile").child(name);


                                //If input fields are filled then take data to addDatatofirebaseDB and login

                                addteacherdatatodb(mobile, email, name, teacherid, college, Userid);
                                Intent intent = new Intent(SignUp.this, HomePage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);


                            }else{
                                inputemail.setError("Not a teachers email");
                                inputemail.requestFocus();
                            }
                        } else {


                            inputemail.setError("Not a teachers email");
                            inputemail.requestFocus();

                        }


                    }
                } else {

//setting on click listene

                    //Putting input data into string (including mobile number which was sent from last activity)

                    String mobile = getIntent().getStringExtra("mobile");
                    String email = inputemail.getText().toString().trim();
                    String name = inputname.getText().toString().trim();
                    String USN = inputUSN.getText().toString().trim();
                    String college = inputcollege.getText().toString().trim();
                    String username = name + " (" + USN + ")";


                    //Condition if input remains empty


                    if (inputemail.getText().toString().trim().isEmpty() && inputname.getText().toString().trim().isEmpty()
                            && inputcollege.getText().toString().trim().isEmpty() && inputUSN.getText().toString().trim().isEmpty()) {

                        Toast.makeText(SignUp.this, "Please input required info", Toast.LENGTH_LONG).show();
                    } else {

                        if (email.contains("@jainuniversity.ac.in") && email.contains(USN)) {

                            //getting firebase database's reference using path StudentInfo java class
                            String Userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference("StudentInfo").child(mobile);

                            Allusers = FirebaseDatabase.getInstance().getReference("AllUsers");

                            userReference = FirebaseDatabase.getInstance().getReference("Users").child(name);

                            userReference1 = FirebaseDatabase.getInstance().getReference("Users").child(Userid);

                            userReference2 = FirebaseDatabase.getInstance().getReference("SearchProfile").child(name);


                            //If input fields are filled then take data to addDatatofirebaseDB and login

                            addDatatoFirebaseDB(mobile, email, username, USN, college, Userid);
                            Intent intent = new Intent(SignUp.this, HomePage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{

                            Toast.makeText(SignUp.this, "Incorrect Info, Please check and re-enter", Toast.LENGTH_LONG).show();

                        }
                    }
                }

            }
        });


    }


    //This method is taking all input values in string form and setting them to Student info fields in StudentInfo Class

    private void addDatatoFirebaseDB(String mobile, String email, String name, String USN, String college, String Uid) {

        studentInfo.setMobile(mobile);
        studentInfo.setStudentEmail(email);
        studentInfo.setName(name);
        studentInfo.setStudentUSN(USN);
        studentInfo.setCollegeName(college);
        studentInfo.setUid(Uid);

        profileModel.setName(name);
        profileModel.setUSN(USN);
        profileModel.setCollege(college);
        profileModel.setUid(Uid);

        String Userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(studentInfo);

                userReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userReference.setValue(profileModel);


                        userReference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                userReference1.setValue(profileModel);

                                userReference2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        userReference2.setValue(profileModel);

                                        Allusers.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Allusers.child(Userid).setValue(studentInfo);
                                                Allusers.child(name).setValue(studentInfo);


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
                                //remove this after
                                Toast.makeText(SignUp.this, "data added", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignUp.this, "Failed to add data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addteacherdatatodb(String mobile, String email, String name, String teacherid, String college, String Uid) {

        teacherModel.setMobile(mobile);
        teacherModel.setTeacherEmail(email);
        teacherModel.setName(name);
        teacherModel.setTeacherId(teacherid);
        teacherModel.setCollege(college);
        teacherModel.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());

        profileModel.setName(name);
        profileModel.setUSN(teacherid);
        profileModel.setCollege(college);
        profileModel.setUid(Uid);

        String Userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Teacherdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Teacherdb.setValue(teacherModel);

                userReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userReference.setValue(profileModel);


                        userReference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                userReference1.setValue(profileModel);

                                userReference2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        userReference2.setValue(profileModel);

                                        Allusers.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Allusers.child(Userid).setValue(teacherModel);
                                                Allusers.child(name).setValue(teacherModel);
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
                                //remove this after
//                                Toast.makeText(SignUp.this, "data added", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignUp.this, "Failed to add data", Toast.LENGTH_LONG).show();
            }
        });

    }
}