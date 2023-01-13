package com.example.collegeappfirstassemble.AdminPanel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceModel;
import com.example.collegeappfirstassemble.EliteDivision.FreelanceActivity;
import com.example.collegeappfirstassemble.HomeMain.CalEventsModelVP;
import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.example.collegeappfirstassemble.HomeMain.QuickLinksModel;
import com.example.collegeappfirstassemble.HomeMain.RvCalEventsModel;
import com.example.collegeappfirstassemble.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminPanel extends AppCompatActivity {

    EditText clubemail, clubname, clublead, clubpassword, slider_pos, headql, bodyql, qlpos, qlcontext
            , slider_link;
    Button submit,removeposts, qlConfirm, EventAdd;
    FirebaseAuth auth;
    ClubAddModel clubAddModel;
    DatabaseReference db, dball, dbsearch, dbql, dballclub;

    EditText freelance_topic , freelance_work
            ,  freelance_desc , freelance_deadline, freelance_skills
            , freelance_price, freelance_name, clubType;
    DatabaseReference databaseReference,dbSlider, clubdb, dbSlider1, CalendarofEventsdb;
    StorageReference s;
    Button freelance_postbtn, confirm_input;
    FreelanceModel freelanceModel;
    ImageView sliderimg;

    EditText CalMonth, CalDate, CalEvent;

    ProgressDialog progressDialog;

    Uri uri;

    CalEventsModelVP calEventsModelVP;
    RvCalEventsModel rvCalEventsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        clubemail = findViewById(R.id.clubemail);
        clubname = findViewById(R.id.ClubName);
        clublead = findViewById(R.id.ClubLead);
        clubpassword = findViewById(R.id.clubPass);
        submit = findViewById(R.id.clubsignupsubmit);
        removeposts = findViewById(R.id.removeposts);
        sliderimg = findViewById(R.id.inputimg);
        confirm_input = findViewById(R.id.confirminput);
        slider_pos = findViewById(R.id.sliderpos);
        headql = findViewById(R.id.headqladd);
        bodyql = findViewById(R.id.bodyqladd);
        qlpos = findViewById(R.id.qlpos);
        qlcontext = findViewById(R.id.qlcontext);
        qlConfirm = findViewById(R.id.confirmql);
        slider_link = findViewById(R.id.sliderLink);
        CalMonth = findViewById(R.id.MonthInput);
        CalDate = findViewById(R.id.DateInput);
        CalEvent = findViewById(R.id.EventInput);
        EventAdd = findViewById(R.id.confirmCal);
        clubType = findViewById(R.id.ClubType);

        auth = FirebaseAuth.getInstance();
        clubAddModel = new ClubAddModel();
        db = FirebaseDatabase.getInstance().getReference("Clubs");
        dballclub = FirebaseDatabase.getInstance().getReference("AllClubs");
        dball = FirebaseDatabase.getInstance().getReference("AllUsers");
        dbsearch = FirebaseDatabase.getInstance().getReference("SearchProfile");
        dbSlider = FirebaseDatabase.getInstance().getReference("SliderImages");
        dbSlider1 = FirebaseDatabase.getInstance().getReference("SliderImages");
        clubdb = FirebaseDatabase.getInstance().getReference("TechClubs");
        s = FirebaseStorage.getInstance().getReference("SliderImages");
        CalendarofEventsdb = FirebaseDatabase.getInstance().getReference("Calendar");

        calEventsModelVP = new CalEventsModelVP();
        rvCalEventsModel = new RvCalEventsModel();


        //Calendar of Events
        EventAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Month = CalMonth.getText().toString().trim();
                String Date = CalDate.getText().toString().trim();
                String Event = CalEvent.getText().toString().trim();

                calendarOfEvents(Month, Date, Event);
            }
        });


        progressDialog = new ProgressDialog(AdminPanel.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = clubemail.getText().toString().trim();
                String name = clubname.getText().toString().trim();
                String lead = clublead.getText().toString().trim();
                String password = clubpassword.getText().toString().trim();
                String type = clubType.getText().toString().trim();

                signup(email, password, name, lead, type);
                startActivity(new Intent(AdminPanel.this, HomePage.class));
            }
        });

        removeposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanel.this, AdminRemovePosts.class));
            }
        });


        //Adding freelance post

        freelance_topic = (EditText)findViewById(R.id.freelance_topic);
        freelance_work = (EditText)findViewById(R.id.freelance_work);
        freelance_desc = (EditText)findViewById(R.id.freelance_desc);
        freelance_deadline = (EditText)findViewById(R.id.freelance_deadline);
        freelance_skills = (EditText) findViewById(R.id.freelance_skills);
        freelance_price = (EditText)findViewById(R.id.freelance_price);
        freelance_postbtn = (Button)findViewById(R.id.freelance_postbtn);
        freelance_name = findViewById(R.id.freelance_name);


        freelanceModel= new FreelanceModel();

        freelance_postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ftopic= freelance_topic.getText().toString().trim();
                String fwork= freelance_work.getText().toString().trim();
                String fdesc=  freelance_desc.getText().toString().trim();
                String fdeadline=  freelance_deadline.getText().toString().trim();
                String fskills=  freelance_skills.getText().toString().trim();
                String fprice=  freelance_price.getText().toString().trim();
                String fname = freelance_name.getText().toString().trim();

                if(ftopic.trim().isEmpty()&&fwork.trim().isEmpty()&&fdesc.trim().isEmpty()&&fdeadline.trim().isEmpty()
                        &&fskills.trim().isEmpty()&&fprice.trim().isEmpty()&&fname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_LONG).show();
                }

                else {
                    databaseReference= FirebaseDatabase.getInstance().getReference("freelancetopic");

                    addtodatabasefreelance( ftopic,fwork,fdesc,fdeadline,fskills,fprice,fname);

                    Intent intent = new Intent(AdminPanel.this, FreelanceActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }


            }
        });


        //Adding slider image


        sliderimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AdminPanel.this).compress(1024).crop(9f, 7f)
                        .maxResultSize(1080, 1080).start();
                confirm_input.setVisibility(View.VISIBLE);
            }
        });

        confirm_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String link = slider_link.getText().toString().trim();
                String pos = slider_pos.getText().toString().trim();
                uploadSliderImg(uri, pos);
                confirm_input.setVisibility(View.INVISIBLE);
            }
        });

        QuickLinksModel quickLinksModel = new QuickLinksModel();

        dbql = FirebaseDatabase.getInstance().getReference("QuickLinks");
        qlConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = headql.getText().toString().trim();
                String body = bodyql.getText().toString().trim();
                String pos = qlpos.getText().toString().trim();
                String context = qlcontext.getText().toString().trim();

                quickLinksModel.setHead(head);
                quickLinksModel.setBody(body);
                quickLinksModel.setPosition(pos);
                quickLinksModel.setContext(context);

                dbql.child(pos).setValue(quickLinksModel);


            }
        });



    }

    private void calendarOfEvents(String month, String date, String event) {

        rvCalEventsModel.setMonth(month);
        rvCalEventsModel.setDate(date);
        rvCalEventsModel.setEventDes(event);

        CalendarofEventsdb.child("CalEvents").child(month).child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CalendarofEventsdb.child("CalEvents").child(month).child(date).setValue(rvCalEventsModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        sliderimg.setImageURI(uri);
    }

    private void uploadSliderImg(Uri uri, String pos) {
        progressDialog.setMessage("Updating home slider");
        progressDialog.show();

        StorageReference file = s.child(System.currentTimeMillis()+"."+getFileExt(uri));

        file.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String key = dbSlider.push().getKey();
                        dbSlider = dbSlider.child("Images").child(pos);
//                        dbSlider1 = dbSlider1.child("links").child(pos);
//                        dbSlider1.setValue(link);
                        dbSlider.setValue(uri.toString());


                        progressDialog.dismiss();
                        Toast.makeText(AdminPanel.this, "Slider Updated", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AdminPanel.this, "Failed to update", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getFileExt(Uri uri) {

        ContentResolver cr = AdminPanel.this.getContentResolver();
        MimeTypeMap m = MimeTypeMap.getSingleton();
        return m.getMimeTypeFromExtension(cr.getType(uri));
    }


    private void adddetailstodb(String email, String name, String lead, String user, String type) {
        clubAddModel.setEmail(email);
        clubAddModel.setName(name);
        clubAddModel.setLead(lead);
        clubAddModel.setUid(user);

        clubdb.child(user).setValue(clubAddModel);

        dballclub.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dballclub.child(user).setValue(clubAddModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        db.child(type+"uid").child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                db.child(type+"uid").child(user).setValue(clubAddModel);
                db.child(type+"uid").child(name).setValue(clubAddModel);

                db.child(type).child(name).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child(type).child(name).setValue(clubAddModel);

                        dbsearch.child(name).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                dbsearch.child(name).setValue(clubAddModel);

                                dball.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        dball.child(user).setValue(clubAddModel);
                                        dball.child(name).setValue(clubAddModel);
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void signup(String email, String password, String name, String lead, String type) {

        if (TextUtils.isEmpty(email)){
            clubemail.setError("Email cannot be empty");
            clubemail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            clubpassword.setError("password cannot be empty");
        }else{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        auth.signInWithEmailAndPassword(email,password);
                        String user = auth.getCurrentUser().getUid();
                        adddetailstodb(email, name, lead, user,type);
                        Toast.makeText(AdminPanel.this, "Club Created", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AdminPanel.this, "Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    //Adding freelance post

    private void addtodatabasefreelance(String ftopic, String fwork, String fdesc, String fdeadline, String fskills
            , String fprice,String fname) {

        freelanceModel.setFreelance_topic(ftopic);
        freelanceModel.setFreelance_work(fwork);
        freelanceModel.setFreelance_desc(fdesc);
        freelanceModel.setFreelance_deadline(fdeadline);
        freelanceModel.setFreelance_skills(fskills);
        freelanceModel.setFreelance_price(fprice);
        freelanceModel.setFreelance_name(fname);

        String key = databaseReference.push().getKey();

        databaseReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(key).setValue(freelanceModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}