package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.DetailsProfileBModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.DetailsProfileProjectModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.Dpmodel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ProfileModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsRecAdap;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.UserProjectPostAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.UserprojectRecAdap;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter.SocialActivityAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter.ProjectModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.UserProjectShow;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfilePage extends AppCompatActivity {

    DatabaseReference club, userproject, userbio, socialdb, clubvision,
            userdp, clubprofile, clubmemdb, databaseReference, clubeventdb, skilldb;
    ImageView clubimage, profileimg;
    TextView ClubVision, clubname, username, bio;
    Button Reach, soicalposts, projectposts;
    RecyclerView projectrec, socialrec, projectpostrec, skillsrec;
    RecyclerView userprojectposts, clubmembers, events;

    TabLayout tb, clubpt;
    ViewPager2 clubvp;

    String uid;
    UserprojectRecAdap userprojectRecAdap;
    DetailsProfileBModel dpbm;
    ProfileModel pm;
    Dpmodel dpmodel;

    ProjectAdapter projectAdapter;
    SocialActivityAdapter socialActivityAdapter;
    UserProjectPostAdapter userProjectPostAdapter;
    ClubMembersAdap clubMembersAdap;
    SportsUpcomingRecAdap sportsUpcomingRecAdap;
    SkillsRecAdap skillsRecAdap;

    Intent intent;

    UserProjectShow userProjectShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userNm = getIntent().getStringExtra("username").trim();
        String userid = getIntent().getStringExtra("userid");

        club = FirebaseDatabase.getInstance().getReference("Users");
        clubvision = FirebaseDatabase.getInstance().getReference("ClubDetails");


        club.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(userNm).exists()) {

                    setContentView(R.layout.activity_profile_page);

                    clubname = findViewById(R.id.clubname);
                    clubimage = findViewById(R.id.clubImage);
                    ClubVision = findViewById(R.id.ClubVision);
                    clubmembers = findViewById(R.id.members);
                    events = findViewById(R.id.Events);
                    clubpt = findViewById(R.id.clubprofiletabs);
                    clubvp = findViewById(R.id.clubprofilevp);

//                    socialrec = findViewById(R.id.postsrecycler);
//                    projectpostrec = findViewById(R.id.projectpostrecycler);
//                    soicalposts = findViewById(R.id.socialpostsbtn);
//                    projectposts = findViewById(R.id.projectpostsbtn);



//                    socialdb = FirebaseDatabase.getInstance().getReference("UserSocialPosts").child(userid);
//
//                    FirebaseRecyclerOptions<SocialAddpostModel> options = new FirebaseRecyclerOptions.Builder<SocialAddpostModel>()
//                            .setQuery(socialdb, SocialAddpostModel.class).build();
//
//
//                    socialActivityAdapter = new SocialActivityAdapter(options, ProfilePage.this);
                    clubmemdb = FirebaseDatabase.getInstance().getReference("ClubDetails")
                            .child(userid).child("ClubMembers");
//
//                    socialrec.setLayoutManager(new LinearLayoutManager(ProfilePage.this));
//
//                    socialrec.setAdapter(socialActivityAdapter);
//
//
//                    LinearLayoutManager l1 = new LinearLayoutManager(ProfilePage.this);
//                    FirebaseRecyclerOptions<ProjectaddpostModel> options1=new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
//                            .setQuery(databaseReference,ProjectaddpostModel.class).build();
//
//                    projectAdapter = new ProjectAdapter(options1, ProfilePage.this);
//
//                    projectpostrec.setLayoutManager(l1);
//                    projectpostrec.setAdapter(projectAdapter);
//
//
//                    soicalposts.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            socialrec.setVisibility(View.VISIBLE);
//                            projectpostrec.setVisibility(View.INVISIBLE);
//                            projectposts.setVisibility(View.VISIBLE);
//                            soicalposts.setVisibility(View.INVISIBLE);
//                        }
//                    });
//
//                    projectposts.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            projectpostrec.setVisibility(View.VISIBLE);
//                            soicalposts.setVisibility(View.INVISIBLE);
//                            soicalposts.setVisibility(View.VISIBLE);
//                            projectposts.setVisibility(View.INVISIBLE);
//                        }
//                    });


                    clubpt.addTab(clubpt.newTab().setText("Social Posts"));
                    clubpt.addTab(clubpt.newTab().setText("Project Posts"));

                    clubpt.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            clubvp.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                    clubvp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            clubpt.selectTab(clubpt.getTabAt(position));
                        }
                    });

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    ClubVpAdap clubVpAdap = new ClubVpAdap(fragmentManager, getLifecycle(), userid);

                    clubvp.setAdapter(clubVpAdap);

                    clubeventdb = FirebaseDatabase.getInstance().getReference("ClubDetails").child(userid)
                            .child("Events");

                    LinearLayoutManager l2 = new LinearLayoutManager(ProfilePage.this);
                    events.setLayoutManager(l2);

                    FirebaseRecyclerOptions<AddEvent> options2 = new FirebaseRecyclerOptions
                            .Builder<AddEvent>().setQuery(clubeventdb, AddEvent.class).build();
//                    clubmemdb.keepSynced(true);

                    sportsUpcomingRecAdap = new SportsUpcomingRecAdap(options2);
                    events.setAdapter(sportsUpcomingRecAdap);
                    clubeventdb.keepSynced(true);

                    LinearLayoutManager l = new LinearLayoutManager(ProfilePage.this);
                    clubmembers.setLayoutManager(l);

                    FirebaseRecyclerOptions<ClubMemModel> options3 = new FirebaseRecyclerOptions.Builder
                            <ClubMemModel>().setQuery(clubmemdb, ClubMemModel.class).build();

                    clubMembersAdap = new ClubMembersAdap(options3, ProfilePage.this);

                    clubmembers.setAdapter(clubMembersAdap);


                    clubprofile = FirebaseDatabase.getInstance().getReference("ClubDetails").child(userid);

                    clubname.setText(userNm);

                    ValueEventListener dplistener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Imageuri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                            Picasso.get().load(Imageuri).into(clubimage);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    clubprofile.addValueEventListener(dplistener);

//            user.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String ProfileUri;
//
////                    Picasso.with(ProfilePage.this).load(ProfileUri).into(clubimage);
//
////                    ClubVision.setText();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

                    clubvision.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String CV = snapshot.child(userid).child("ClubVision").child("clubVision").getValue(String.class);
                            ClubVision.setText(CV);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } else {
                    setContentView(R.layout.activity_profile_page_user);

                    username = findViewById(R.id.usernameprofile);
                    profileimg = findViewById(R.id.dp);
                    projectrec = findViewById(R.id.projectrec);
                    userprojectposts = findViewById(R.id.userprojects);
                    Reach = findViewById(R.id.reachprofile);
                    bio = findViewById(R.id.Bio);
                    tb = findViewById(R.id.profiletab);
                    skillsrec = findViewById(R.id.skillrec);


                    dpbm = new DetailsProfileBModel();
                    pm = new ProfileModel();
                    dpmodel = new Dpmodel();
                    databaseReference = FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(userid);

                    username.setText(userNm);

                    ValueEventListener dplistener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Imageuri = snapshot.child("dp").getValue(String.class);
                            Picasso.get().load(Imageuri).into(profileimg);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };

                    skilldb = FirebaseDatabase.getInstance().getReference("UserDetails").child(userNm)
                            .child("Skills");

                    skillsrec.setLayoutManager(new LinearLayoutManager(ProfilePage.this));

                    FirebaseRecyclerOptions skilloptions = new FirebaseRecyclerOptions.Builder<SkillsModel>()
                            .setQuery(skilldb, SkillsModel.class).build();

                    skillsRecAdap = new SkillsRecAdap(skilloptions, ProfilePage.this, 2);
                    skillsrec.setAdapter(skillsRecAdap);


                    LinearLayoutManager l = new LinearLayoutManager(ProfilePage.this);
                    FirebaseRecyclerOptions<ProjectaddpostModel> options = new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
                            .setQuery(databaseReference, ProjectaddpostModel.class).build();

                    userProjectPostAdapter = new UserProjectPostAdapter(options, ProfilePage.this, 2);

                    userprojectposts.setLayoutManager(l);
                    userprojectposts.setAdapter(userProjectPostAdapter);


//                            Bundle b = new Bundle();
//                            b.putString("userid", uid);
//                            userProjectShow.setArguments(b);


                    intent = new Intent(ProfilePage.this, Reaches.class);
                    intent.putExtra("userid", userid);
                    Reach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
                                startActivity(intent);

                            } else {

                            }

                        }
                    });

                    userdp = FirebaseDatabase.getInstance().getReference("UserDetails").child(userid).child("ProfilePic");
                    userdp.addValueEventListener(dplistener);

                    userbio = FirebaseDatabase.getInstance().getReference("UserDetails").child(userid).child("Bio");


                    userbio.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String biotext = snapshot.child("bio").getValue(String.class);
                            bio.setText(biotext);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                    userproject = FirebaseDatabase.getInstance().getReference("UserDetails").child(userNm).child("Projects");
                    userproject.keepSynced(true);
                    FirebaseRecyclerOptions<DetailsProfileProjectModel> options2 = new FirebaseRecyclerOptions.Builder<DetailsProfileProjectModel>()
                            .setQuery(userproject, DetailsProfileProjectModel.class).build();

                    userprojectRecAdap = new UserprojectRecAdap(options2, ProfilePage.this, 2);
                    projectrec.setAdapter(userprojectRecAdap);


                    projectrec.setLayoutManager(new LinearLayoutManager(ProfilePage.this));

                    tb.addTab(tb.newTab().setText("Project Posts"));


                    LinearLayoutManager l1 = new LinearLayoutManager(ProfilePage.this);
                    FirebaseRecyclerOptions<ProjectaddpostModel> options1=new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
                            .setQuery(databaseReference,ProjectaddpostModel.class).build();

                    projectAdapter = new ProjectAdapter(options1, ProfilePage.this);

                    userprojectposts.setLayoutManager(l1);
                    userprojectposts.setAdapter(projectAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        String user = getIntent().getStringExtra("uid");
//        databaseReference= FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(user);
//
//        FirebaseRecyclerOptions<ProjectaddpostModel> options=new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
//                .setQuery(databaseReference,ProjectaddpostModel.class).build();
//
//        userprojectposts.setLayoutManager(new LinearLayoutManager(ProfilePage.this));
//
//        projectAdapter = new ProjectAdapter(options,ProfilePage.this);
//        userprojectposts.setAdapter(projectAdapter);
//        databaseReference.keepSynced(true);

//        userProjectShow = new UserProjectShow();
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.userprojects, userProjectShow).commit();


    }


    @Override
    protected void onStart() {
        super.onStart();
        String userNm = getIntent().getStringExtra("username").trim();
        club.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userNm).exists()) {
                    userprojectRecAdap.startListening();
//                    userProjectPostAdapter.startListening();

                    skillsRecAdap.startListening();
                    projectAdapter.startListening();
                } else {
                    clubMembersAdap.startListening();
                    sportsUpcomingRecAdap.startListening();
//                    socialActivityAdapter.startListening();
//                    projectAdapter.startListening();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        String userNm = getIntent().getStringExtra("username").trim();
        club.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userNm).exists()) {
                    userprojectRecAdap.stopListening();
//                    userProjectPostAdapter.stopListening();
                    skillsRecAdap.stopListening();
                    projectAdapter.stopListening();
                } else {
                    clubMembersAdap.stopListening();
                    sportsUpcomingRecAdap.stopListening();
//                    socialActivityAdapter.stopListening();
//                    projectAdapter.stopListening();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }


}