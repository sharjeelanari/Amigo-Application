package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegeappfirstassemble.OtpSignup.MainActivity;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.UserProjectPosts;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ClubVisModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.DetailsProfileBModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.DetailsProfileProjectModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.Dpmodel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ProfileModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsRecAdap;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.UserViewPAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.UserprojectRecAdap;
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ClubMemModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ClubMembersAdap;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter.InstructorModel;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.SportsUpcomingRecAdap;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.example.collegeappfirstassemble.SportsAndCultural.Fragments.SportsAddAnEvent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashMap;


public class UserFragment extends Fragment {
    TextView userName, skills, bio, aboutclub, projecttextv, events, addskills,
            addproject, addbio, addaboutclub, eventadd, biotext, aboutclubtext, logout;
    ImageButton addnewevent, skilladd, projectadd, bioadd, aboutclubadd;
    EditText addskillname, addprojectName, addprojectDet, updatebio, updateaboutclub, projectlink, addskilldet;
    ImageView ProfileImage;
    RecyclerView projectsrec, eventsrec, skillsrec;

    TabLayout tb;
    ViewPager2 vp;

    ProgressBar progressBar;

    RelativeLayout relativeLayout;

    FrameLayout studentposts, addevent;

    DatabaseReference clubmemdb, userdb1, userskills, userproject, userdetails, userbio, userdetails1,
            clubdb, clubdet, eventdb, alluserdb, clubcheckdb, teacherdet, teacherdb, allusersdp, instructor;
    StorageReference dpref;
    Intent intent;

    Uri dpuri;

    Button confirmdp;

    SkillsModel dpm;
    UserprojectRecAdap userprojectRecAdap;
    DetailsProfileProjectModel dppm;
    DetailsProfileBModel dpbm;
    SkillsRecAdap skillsRecAdap;
    ProfileModel pm;
    ClubVisModel cvm;
    Dpmodel dpmodel;
    ClubMemModel clubMemModel;
    InstructorModel instructorModel;

    ClubMembersAdap clubMembersAdap;
    SportsUpcomingRecAdap sportsUpcomingRecAdap;

    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        //textviews
        userName = view.findViewById(R.id.usernameprofile);
        skills = view.findViewById(R.id.editskills);
        bio = view.findViewById(R.id.editbio);
        aboutclub = view.findViewById(R.id.aboutclub);
        projecttextv = view.findViewById(R.id.editprojects);
        events = view.findViewById(R.id.Yourevents);
        biotext = view.findViewById(R.id.biotxt);
        aboutclubtext = view.findViewById(R.id.aboutclubtext);
        logout = view.findViewById(R.id.logout);

        // Buttons
        addproject = view.findViewById(R.id.addprojectbtn);
        addskills = view.findViewById(R.id.addskillbtn);
        addbio = view.findViewById(R.id.addbio);
        addaboutclub = view.findViewById(R.id.addaboutclub);
        skilladd = view.findViewById(R.id.skilladd);
        projectadd = view.findViewById(R.id.projectadd);
        bioadd = view.findViewById(R.id.bioadd);
        aboutclubadd = view.findViewById(R.id.aboutclubadd);
        confirmdp = view.findViewById(R.id.changedpconfirm);
        eventadd = view.findViewById(R.id.addevent);
        //edit texts
        addskillname = view.findViewById(R.id.addskillname);
        addskilldet = view.findViewById(R.id.addskilldetails);
        addprojectName = view.findViewById(R.id.addprojectName);
        addprojectDet = view.findViewById(R.id.addprojectdetails);
        updatebio = view.findViewById(R.id.updatebio);
        projectlink = view.findViewById(R.id.addprojectlink);
        updateaboutclub = view.findViewById(R.id.updateaboutclub);
        //profile image
        ProfileImage = view.findViewById(R.id.editprofileimg);
        //recycler views
        projectsrec = view.findViewById(R.id.editprojectsrecycler);
        studentposts = view.findViewById(R.id.studentposts);
        eventsrec = view.findViewById(R.id.eventsrec);
        skillsrec = view.findViewById(R.id.skillsrec);

        //Tab layout and viewpager
        tb = view.findViewById(R.id.usertabs);
        vp = view.findViewById(R.id.uservp);

        progressBar = view.findViewById(R.id.progress_user);
        relativeLayout = view.findViewById(R.id.relUser);

        addevent = view.findViewById(R.id.addeventframe);

        pd = new ProgressDialog(getContext());


        dpm = new SkillsModel();
        dppm = new DetailsProfileProjectModel();
        dpbm = new DetailsProfileBModel();
        pm = new ProfileModel();
        cvm = new ClubVisModel();
        dpmodel = new Dpmodel();


        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();


        userdb1 = FirebaseDatabase.getInstance().getReference("Users");
        userdetails = FirebaseDatabase.getInstance().getReference("UserDetails").child(user);
        userdetails1 = FirebaseDatabase.getInstance().getReference("UserDetails");
        userbio = userdetails.child("Bio");
        userskills = userdetails.child("Skills");
        userproject = userdetails.child("Projects");
        alluserdb = FirebaseDatabase.getInstance().getReference("AllUsers");
        instructor = FirebaseDatabase.getInstance().getReference("Instructor");

        dpref = FirebaseStorage.getInstance().getReference();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

            }
        });

        userdb1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(user).exists()) {

                    userdetails.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Target target = new Target() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    ProfileImage.setImageBitmap(bitmap);

                                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                        @Override
                                        public void onGenerated(@Nullable Palette palette) {

                                            Palette.Swatch swatch = palette.getMutedSwatch();
                                            Palette.Swatch swatch1 = palette.getDominantSwatch();


                                            if (swatch1 != null) {
                                                int rgb = swatch1.getRgb();
                                                int title = swatch1.getTitleTextColor();

                                                userName.setBackgroundColor(rgb);
                                                userName.setTextColor(title);
                                                getActivity().getWindow().setStatusBarColor(rgb);
                                            } else {
                                                int rgb = swatch.getRgb();
                                                int title = swatch.getTitleTextColor();

                                                userName.setBackgroundColor(rgb);
                                                userName.setTextColor(title);
                                                getActivity().getWindow().setStatusBarColor(rgb);
                                            }
                                        }
                                    });

                                }

                                @Override
                                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {

                                }
                            };

                            String imageuri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                            if (imageuri != null) {
                                Picasso.get()
                                        .load(imageuri).into(target);

                                progressBar.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);

                            } else {
                                Picasso.get().load("@res/@drawable/social_user_icon").into(target);

                                progressBar.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    ProfileImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImagePicker.with(UserFragment.this)
                                    .crop(16f, 14f)
                                    .galleryOnly()
                                    .compress(1024)
                                    .maxResultSize(1080, 1080)
                                    .start();

                            confirmdp.setVisibility(View.VISIBLE);
                            confirmdp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (dpuri != null) {
                                        dptodb(dpuri, 1);
                                    }
                                    confirmdp.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    });


                    skillsrec.setLayoutManager(new LinearLayoutManager(getContext()));

                    FirebaseRecyclerOptions skillOptions = new FirebaseRecyclerOptions.Builder<SkillsModel>()
                            .setQuery(userskills, SkillsModel.class).build();


                    skillsRecAdap = new SkillsRecAdap(skillOptions, getContext(), 1);

                    skillsrec.setAdapter(skillsRecAdap);


                    userdb1.child(user).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name = snapshot.child("name").getValue(String.class);
                            userName.setText(name);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    addskills.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addskillname.setVisibility(View.VISIBLE);
                            skilladd.setVisibility(View.VISIBLE);
                            addskills.setVisibility(View.GONE);
                            addskilldet.setVisibility(View.VISIBLE);

                            addskillname.setVerticalScrollBarEnabled(true);
                            addskillname.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                            addskillname.setMovementMethod(new ScrollingMovementMethod());

                            addskilldet.setVerticalScrollBarEnabled(true);
                            addskilldet.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                            addskilldet.setMovementMethod(new ScrollingMovementMethod());

                            skilladd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String skillname = addskillname.getText().toString().trim();
                                    String skilldet = addskilldet.getText().toString().trim();
                                    if (!skillname.isEmpty() && !skilldet.isEmpty()) {
                                        addskilltodb(skillname, skilldet, 1);
                                    }


                                    addskillname.setText("");
                                    addskilldet.setText("");
                                    addskillname.setVisibility(View.GONE);
                                    addskilldet.setVisibility(View.GONE);
                                    skilladd.setVisibility(View.GONE);
                                    addskills.setVisibility(View.VISIBLE);

                                }
                            });
                        }
                    });

                    projectsrec.setLayoutManager(new LinearLayoutManager(getContext()));

                    FirebaseRecyclerOptions<DetailsProfileProjectModel> options = new FirebaseRecyclerOptions.Builder<DetailsProfileProjectModel>()
                            .setQuery(userproject, DetailsProfileProjectModel.class).build();

                    userprojectRecAdap = new UserprojectRecAdap(options, getContext(), 1);

                    projectsrec.setAdapter(userprojectRecAdap);
                    userproject.keepSynced(true);

                    addproject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addprojectName.setVisibility(View.VISIBLE);
                            projectadd.setVisibility(View.VISIBLE);
                            addproject.setVisibility(View.GONE);
                            addprojectDet.setVisibility(View.VISIBLE);
                            projectlink.setVisibility(View.VISIBLE);

                            addprojectName.setVerticalScrollBarEnabled(true);
                            addprojectName.setMaxLines(2);
                            projectlink.setVerticalScrollBarEnabled(true);
                            projectlink.setMaxLines(1);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                            addprojectName.setMovementMethod(new ScrollingMovementMethod());

                            addprojectDet.setVerticalScrollBarEnabled(true);
                            addprojectDet.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                            addprojectDet.setMovementMethod(new ScrollingMovementMethod());


                            projectadd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String projectnm = addprojectName.getText().toString().trim();
                                    String projectdet = addprojectDet.getText().toString().trim();
                                    String link = projectlink.getText().toString().trim();
                                    if (!projectnm.isEmpty() && !projectdet.isEmpty()) {
                                        addprojecttodb(projectnm, projectdet, link);
                                    }

                                    userproject = userproject.child(projectnm);

                                    addprojectName.setText("");
                                    addprojectDet.setText("");

                                    addprojectName.setVisibility(View.GONE);
                                    addprojectDet.setVisibility(View.GONE);
                                    projectadd.setVisibility(View.GONE);
                                    projectlink.setVisibility(View.GONE);
                                    addproject.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    });


                    userbio.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String biotxt = snapshot.child("bio").getValue(String.class);
                            biotext.setText(biotxt);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    addbio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updatebio.setVisibility(View.VISIBLE);
                            bioadd.setVisibility(View.VISIBLE);
                            addbio.setVisibility(View.GONE);
                            biotext.setVisibility(View.INVISIBLE);
                            String text = biotext.getText().toString().trim();
                            updatebio.setText(text);

                            updatebio.setVerticalScrollBarEnabled(true);
                            updatebio.setMaxLines(2);
//                    updatebio.setScroller(new Scroller(updateaboutclub.getContext()));
                            updatebio.setMovementMethod(new ScrollingMovementMethod());

                            bioadd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String biotxt = updatebio.getText().toString().trim();
                                    if (!biotxt.isEmpty()) {
                                        addbiotodb(biotxt, 1);
                                    }

                                    updatebio.setText("");

                                    updatebio.setVisibility(View.GONE);
                                    bioadd.setVisibility(View.GONE);
                                    biotext.setVisibility(View.VISIBLE);
                                    addbio.setVisibility(View.VISIBLE);


                                }
                            });
                        }
                    });

                    tb.addTab(tb.newTab().setText("Your Projects"));

                    UserProjectPosts f = new UserProjectPosts();
                    FragmentManager fm = getChildFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.studentposts, f).commit();

                } else {

                    clubcheckdb = FirebaseDatabase.getInstance().getReference("AllClubs");

                    clubcheckdb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(user).exists()) {


                                skills.setVisibility(View.INVISIBLE);
                                bio.setVisibility(View.INVISIBLE);
                                addbio.setVisibility(View.INVISIBLE);
                                addskills.setVisibility(View.INVISIBLE);
                                studentposts.setVisibility(View.INVISIBLE);
                                skillsrec.setVisibility(View.INVISIBLE);
                                biotext.setVisibility(View.INVISIBLE);
                                aboutclubadd.setVisibility(View.INVISIBLE);


                                aboutclubtext.setVisibility(View.VISIBLE);
                                aboutclub.setVisibility(View.VISIBLE);
                                addaboutclub.setVisibility(View.VISIBLE);
                                updateaboutclub.setVisibility(View.GONE);
                                eventsrec.setVisibility(View.VISIBLE);
                                projectsrec.setVisibility(View.VISIBLE);
                                eventadd.setVisibility(View.VISIBLE);
                                addevent.setVisibility(View.VISIBLE);
                                events.setVisibility(View.VISIBLE);
                                vp.setVisibility(View.VISIBLE);


                                clubdb = FirebaseDatabase.getInstance().getReference("Clubs").child(user);

                                clubdb.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String name = snapshot.child("name").getValue(String.class);
                                        userName.setText(name);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                clubdet = FirebaseDatabase.getInstance().getReference("ClubDetails").child(user);

                                projecttextv.setText("Members");
                                eventdb = clubdet.child("Events");
                                clubmemdb = clubdet.child("ClubMembers");

                                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) projecttextv.getLayoutParams();
                                p.addRule(RelativeLayout.BELOW, R.id.aboutclubadd);

                                RelativeLayout.LayoutParams p1 = (RelativeLayout.LayoutParams) addproject.getLayoutParams();
                                p1.addRule(RelativeLayout.BELOW, R.id.aboutclubadd);


                                clubMemModel = new ClubMemModel();

                                projectsrec.setLayoutManager(new LinearLayoutManager(getContext()));

                                FirebaseRecyclerOptions<ClubMemModel> options =
                                        new FirebaseRecyclerOptions.Builder<ClubMemModel>()
                                                .setQuery(clubmemdb, ClubMemModel.class).build();

                                clubMembersAdap = new ClubMembersAdap(options, getContext());

                                projectsrec.setAdapter(clubMembersAdap);
                                clubmemdb.keepSynced(true);

                                addproject.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addprojectName.setVisibility(View.VISIBLE);
                                        projectadd.setVisibility(View.VISIBLE);
                                        addproject.setVisibility(View.GONE);
                                        addprojectDet.setVisibility(View.VISIBLE);

                                        addprojectName.setHint("Member Name");
                                        addprojectDet.setHint("Member Designation");

                                        addprojectName.setVerticalScrollBarEnabled(true);
                                        addprojectName.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                                        addprojectName.setMovementMethod(new ScrollingMovementMethod());

                                        addprojectDet.setVerticalScrollBarEnabled(true);
                                        addprojectDet.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                                        addprojectDet.setMovementMethod(new ScrollingMovementMethod());


                                        projectadd.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                String membername = addprojectName.getText().toString().trim();
                                                String memberdesignation = addprojectDet.getText().toString().trim();
                                                if (!membername.isEmpty() && !memberdesignation.isEmpty()) {
                                                    addmembertodb(membername, memberdesignation);
                                                }

                                                addprojectName.setText("");
                                                addprojectDet.setText("");

                                                addprojectName.setVisibility(View.GONE);
                                                addprojectDet.setVisibility(View.GONE);
                                                projectadd.setVisibility(View.GONE);
                                                addproject.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                });


                                eventadd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SportsAddAnEvent sportsAddAnEvent = new SportsAddAnEvent();
                                        FragmentManager fm = getChildFragmentManager();
                                        FragmentTransaction ft = fm.beginTransaction();
                                        ft.add(R.id.addeventframe, sportsAddAnEvent).commit();
                                        eventadd.setVisibility(View.INVISIBLE);
                                    }
                                });


                                FirebaseRecyclerOptions<AddEvent> options1 = new FirebaseRecyclerOptions
                                        .Builder<AddEvent>().setQuery(eventdb, AddEvent.class).build();

                                eventsrec.setLayoutManager(new LinearLayoutManager(getContext()));

                                sportsUpcomingRecAdap = new SportsUpcomingRecAdap(options1);

                                eventsrec.setAdapter(sportsUpcomingRecAdap);

                                clubdet.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        Target target = new Target() {
                                            @Override
                                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                ProfileImage.setImageBitmap(bitmap);

                                                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                                    @Override
                                                    public void onGenerated(@Nullable Palette palette) {

                                                        Palette.Swatch swatch = palette.getMutedSwatch();
                                                        Palette.Swatch swatch1 = palette.getDominantSwatch();


                                                        if (swatch1 != null) {
                                                            int rgb = swatch1.getRgb();
                                                            int title = swatch1.getTitleTextColor();

                                                            userName.setBackgroundColor(rgb);
                                                            userName.setTextColor(title);
                                                            getActivity().getWindow().setStatusBarColor(rgb);
                                                        } else {
                                                            int rgb = swatch.getRgb();
                                                            int title = swatch.getTitleTextColor();

                                                            userName.setBackgroundColor(rgb);
                                                            userName.setTextColor(title);
                                                            getActivity().getWindow().setStatusBarColor(rgb);
                                                        }
                                                    }
                                                });

                                            }

                                            @Override
                                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                            }

                                            @Override
                                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                                            }
                                        };

                                        String imageuri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                                        if (imageuri != null) {
                                            Picasso.get()
                                                    .load(imageuri).into(target);
                                            progressBar.setVisibility(View.GONE);
                                            relativeLayout.setVisibility(View.VISIBLE);

                                        } else {
                                            Picasso.get()
                                                    .load(R.drawable.social_user_icon).into(target);

                                            progressBar.setVisibility(View.GONE);
                                            relativeLayout.setVisibility(View.VISIBLE);

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                                ProfileImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ImagePicker.with(UserFragment.this)
                                                .crop(16f, 14f)
                                                .galleryOnly()
                                                .compress(1024)
                                                .maxResultSize(1080, 1080)
                                                .start();

                                        confirmdp.setVisibility(View.VISIBLE);
                                        confirmdp.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (dpuri != null) {
                                                    dptodbclub(dpuri);
                                                }
                                                confirmdp.setVisibility(View.INVISIBLE);
                                            }
                                        });
                                    }
                                });

                                addaboutclub.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addaboutclub.setVisibility(View.GONE);
                                        updateaboutclub.setVisibility(View.VISIBLE);
                                        aboutclubadd.setVisibility(View.VISIBLE);
                                        aboutclubtext.setVisibility(View.INVISIBLE);

                                        updateaboutclub.setVerticalScrollBarEnabled(true);
                                        updateaboutclub.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                                        updateaboutclub.setMovementMethod(new ScrollingMovementMethod());

                                        aboutclubadd.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String upabclub = updateaboutclub.getText().toString().trim();
                                                if (!upabclub.isEmpty()) {
                                                    addclubvisiontodb(upabclub);
                                                }
                                                updateaboutclub.setText("");

                                                updateaboutclub.setVisibility(View.GONE);
                                                aboutclubadd.setVisibility(View.INVISIBLE);
                                                addaboutclub.setVisibility(View.VISIBLE);
                                                aboutclubtext.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                });

                                clubdet.child("ClubVision").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String cluvis = snapshot.child("clubVision").getValue(String.class);

                                        aboutclubtext.setText(cluvis);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                FragmentManager fm = getChildFragmentManager();

                                UserViewPAdapter userViewPAdapter = new UserViewPAdapter(fm, getLifecycle());

                                vp.setAdapter(userViewPAdapter);

                                tb.addTab(tb.newTab().setText("Social Posts"));
                                tb.addTab(tb.newTab().setText("Project Posts"));

                                tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        vp.setCurrentItem(tab.getPosition());
                                    }

                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {

                                    }
                                });

                                vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                    @Override
                                    public void onPageSelected(int position) {
                                        tb.selectTab(tb.getTabAt(position));
                                    }
                                });


                            } else {

                                vp.setVisibility(View.VISIBLE);
                                studentposts.setVisibility(View.INVISIBLE);

                                projecttextv.setVisibility(View.GONE);
                                addproject.setVisibility(View.GONE);
                                projectsrec.setVisibility(View.INVISIBLE);


                                teacherdb = FirebaseDatabase.getInstance().getReference("Teachers");
                                teacherdet = FirebaseDatabase.getInstance().getReference("TeacherDetails");

                                skills.setText("Interested to work on");
                                addskillname.setHint("TeachStack/Skill/Platform");
                                addskilldet.setHint("Describe type of project");

                                teacherdet.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        Target target = new Target() {
                                            @Override
                                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                ProfileImage.setImageBitmap(bitmap);

                                                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                                    @Override
                                                    public void onGenerated(@Nullable Palette palette) {

                                                        Palette.Swatch swatch = palette.getMutedSwatch();
                                                        Palette.Swatch swatch1 = palette.getDominantSwatch();


                                                        if (swatch1 != null) {
                                                            int rgb = swatch1.getRgb();
                                                            int title = swatch1.getTitleTextColor();

                                                            userName.setBackgroundColor(rgb);
                                                            userName.setTextColor(title);
                                                            getActivity().getWindow().setStatusBarColor(rgb);
                                                        } else {
                                                            int rgb = swatch.getRgb();
                                                            int title = swatch.getTitleTextColor();

                                                            userName.setBackgroundColor(rgb);
                                                            userName.setTextColor(title);
                                                            getActivity().getWindow().setStatusBarColor(rgb);
                                                        }
                                                    }
                                                });

                                            }

                                            @Override
                                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                            }

                                            @Override
                                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                                            }
                                        };

                                        String imageuri = snapshot.child(user).child("ProfilePic").child("dp").getValue(String.class);
                                        if (imageuri != null) {
                                            Picasso.get()
                                                    .load(imageuri).into(target);
                                            progressBar.setVisibility(View.GONE);
                                            relativeLayout.setVisibility(View.VISIBLE);

                                        } else {
                                            Picasso.get().load(R.drawable.social_user_icon).into(target);

                                            progressBar.setVisibility(View.GONE);
                                            relativeLayout.setVisibility(View.VISIBLE);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                ProfileImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ImagePicker.with(UserFragment.this)
                                                .crop(16f, 14f)
                                                .galleryOnly()
                                                .compress(1024)
                                                .maxResultSize(1080, 1080)
                                                .start();

                                        confirmdp.setVisibility(View.VISIBLE);
                                        confirmdp.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (dpuri != null) {
                                                    dptodb(dpuri, 2);
                                                }
                                                confirmdp.setVisibility(View.INVISIBLE);
                                            }
                                        });
                                    }
                                });


                                skillsrec.setLayoutManager(new LinearLayoutManager(getContext()));

                                DatabaseReference teacherskills = teacherdet.child(user)
                                        .child("Skills");

                                FirebaseRecyclerOptions skillOptions = new FirebaseRecyclerOptions.Builder<SkillsModel>()
                                        .setQuery(teacherskills, SkillsModel.class).build();


                                skillsRecAdap = new SkillsRecAdap(skillOptions, getContext(), 1);

                                skillsrec.setAdapter(skillsRecAdap);


                                teacherdb.child(user).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String name = snapshot.child("name").getValue(String.class);
                                        userName.setText(name);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                addskills.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addskillname.setVisibility(View.VISIBLE);
                                        skilladd.setVisibility(View.VISIBLE);
                                        addskills.setVisibility(View.GONE);
                                        addskilldet.setVisibility(View.VISIBLE);

                                        addskillname.setVerticalScrollBarEnabled(true);
                                        addskillname.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                                        addskillname.setMovementMethod(new ScrollingMovementMethod());

                                        addskilldet.setVerticalScrollBarEnabled(true);
                                        addskilldet.setMaxLines(2);
//                    updateaboutclub.setScroller(new Scroller(updateaboutclub.getContext()));
                                        addskilldet.setMovementMethod(new ScrollingMovementMethod());

                                        skilladd.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                String skillname = addskillname.getText().toString().trim();
                                                String skilldet = addskilldet.getText().toString().trim();
                                                if (!skillname.isEmpty() && !skilldet.isEmpty()) {
                                                    addskilltodb(skillname, skilldet, 2);
                                                }


                                                addskillname.setText("");
                                                addskilldet.setText("");
                                                addskillname.setVisibility(View.GONE);
                                                addskilldet.setVisibility(View.GONE);
                                                skilladd.setVisibility(View.GONE);
                                                addskills.setVisibility(View.VISIBLE);

                                            }
                                        });
                                    }
                                });


                                teacherdet.child(user).child("Bio").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String biotxt = snapshot.child("bio").getValue(String.class);
                                        biotext.setText(biotxt);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                addbio.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        updatebio.setVisibility(View.VISIBLE);
                                        bioadd.setVisibility(View.VISIBLE);
                                        addbio.setVisibility(View.GONE);
                                        biotext.setVisibility(View.INVISIBLE);
                                        String text = biotext.getText().toString().trim();
                                        updatebio.setText(text);

                                        updatebio.setVerticalScrollBarEnabled(true);
                                        updatebio.setMaxLines(2);
//                    updatebio.setScroller(new Scroller(updateaboutclub.getContext()));
                                        updatebio.setMovementMethod(new ScrollingMovementMethod());

                                        bioadd.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String biotxt = updatebio.getText().toString().trim();
                                                if (!biotxt.isEmpty()) {
                                                    addbiotodb(biotxt, 2);
                                                }

                                                updatebio.setText("");

                                                updatebio.setVisibility(View.GONE);
                                                bioadd.setVisibility(View.GONE);
                                                biotext.setVisibility(View.VISIBLE);
                                                addbio.setVisibility(View.VISIBLE);


                                            }
                                        });
                                    }
                                });


                                FragmentManager fm = getChildFragmentManager();

                                UserViewPAdapter userViewPAdapter = new UserViewPAdapter(fm, getLifecycle());

                                vp.setAdapter(userViewPAdapter);

                                tb.addTab(tb.newTab().setText("Social Posts"));
                                tb.addTab(tb.newTab().setText("Project Posts"));

                                tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        vp.setCurrentItem(tab.getPosition());
                                    }

                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {

                                    }
                                });

                                vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                    @Override
                                    public void onPageSelected(int position) {
                                        tb.selectTab(tb.getTabAt(position));
                                    }
                                });


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    private void addmembertodb(String membername, String memberdesignation) {
        clubMemModel.setMemName(membername);
        clubMemModel.setMemdesignation(memberdesignation);

        clubmemdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clubmemdb.child(membername).setValue(clubMemModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        dpuri = data.getData();
        ProfileImage.setImageURI(dpuri);
    }

    //Adding skills to db
    private void addskilltodb(String skillName, String skillDet, int a) {
        dpm.setSkillname(skillName);
        dpm.setSkilldet(skillDet);

        if (a == 1) {
            userskills.child(skillName).setValue(dpm);

            userdb1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String name = snapshot.child(user).child("name").getValue(String.class);
                    userdetails1.child(name).child("Skills").child(skillName).setValue(dpm);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } else {

            String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
            teacherdet.child(user).child("Skills").child(skillName).setValue(dpm);


            teacherdb.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("name").getValue(String.class);

                    teacherdet.child(name).child("Skills").child(skillName).setValue(dpm);
                    instructor.child(user).child("ins_name").setValue(name);
                    instructor.child(user).child("Skills").child(skillName).child("ins_domain").setValue(skillName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }
    //Adding projects to database
    // Added project,skills and bio in two fields under UserDetails 1) uid 2) name

    private void addprojecttodb(String projectname, String projectdet, String link) {
        dppm.setProjectnm(projectname);
        dppm.setProjectdet(projectdet);
        dppm.setLink(link);

        userproject.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userproject.setValue(dppm);

                userdb1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String name = snapshot.child(user).child("name").getValue(String.class);
                        userdetails1.child(name).child("Projects").child(projectname).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                userdetails1.child(name).child("Projects").child(projectname).setValue(dppm);
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


    //Sending profile pic to database

    private void dptodb(Uri dpuri, int a) {

        pd.setMessage("Updating your Profile picture");
        pd.show();

        StorageReference dpref1 = dpref.child(System.currentTimeMillis() + "." + FileEx(dpuri));

        dpref1.putFile(dpuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                dpref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        dpmodel.setDp(uri.toString());

                        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();


                        if (a == 1) {
                            allusersdp = FirebaseDatabase.getInstance().getReference("AllUsers")
                                    .child(user);
                            userdetails1.child(user).child("ProfilePic").setValue(dpmodel);

                            allusersdp.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    allusersdp.child("ProfilePic").setValue(dpmodel);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            pd.dismiss();
                        } else {
                            allusersdp = FirebaseDatabase.getInstance().getReference("AllUsers")
                                    .child(user);

                            teacherdet.child(user).child("ProfilePic").setValue(dpmodel);

                            allusersdp.child("ProfilePic").setValue(dpmodel);
                            instructor.child(user).child("dp").setValue(uri.toString());

                            pd.dismiss();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });
    }


    private void dptodbclub(Uri dpuri) {

        pd.setMessage("Updating your Profile picture");
        pd.show();

        StorageReference dpref1 = dpref.child(System.currentTimeMillis() + "." + FileEx(dpuri));

        dpref1.putFile(dpuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                dpref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        dpmodel.setDp(uri.toString());

                        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        clubdet.child("ProfilePic").setValue(dpmodel);
                        alluserdb.child(user).child("ProfilePic").setValue(dpmodel);

                        pd.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });
    }


    private String FileEx(Uri dpuri) {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(dpuri));
    }

    // Updating bio in database
    private void addbiotodb(String bio, int a) {
        dpbm.setBio(bio);

        if (a == 1) {
            userbio.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userbio.setValue(dpbm);


                    userdb1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            String name = snapshot.child(user).child("name").getValue(String.class);
                            userdetails1.child(name).child("Bio").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    userdetails1.child(name).child("Bio").setValue(dpbm);
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
        } else {

            String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

            teacherdet.child(user).child("Bio").setValue(dpbm);
            instructor.child(user).child("ins_bio").setValue(bio);
            instructor.child(user).child("ins_button_reach").setValue(user);


            teacherdb.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("name").getValue(String.class);

                    teacherdet.child(name).child("Bio").setValue(dpbm);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    private void addclubvisiontodb(String upabcl) {
        cvm.setClubVision(upabcl);

        clubdet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clubdet.child("ClubVision").setValue(cvm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userdb1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(user).exists()) {
                    userprojectRecAdap.startListening();
                    skillsRecAdap.startListening();
                } else {

                    clubcheckdb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(user).exists()) {
                                clubMembersAdap.startListening();
                                sportsUpcomingRecAdap.startListening();
                            } else {

                                skillsRecAdap.startListening();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
//        userdb1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                if (snapshot.child(user).exists()) {
//                    userprojectRecAdap.startListening();
//                    skillsRecAdap.stopListening();
//                } else {
//                    clubcheckdb.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.child(user).exists()) {
//                                clubMembersAdap.stopListening();
//                                sportsUpcomingRecAdap.stopListening();
//                            } else {
//                                skillsRecAdap.stopListening();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
//
//    private int isClub(String user) {
//
//        final int[] retvalue = new int[1];
//        userdb1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.child(user).exists()) {
//
//                }else{
//                    retvalue[0] = 2;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        if (retvalue[0] == 1) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }
}
