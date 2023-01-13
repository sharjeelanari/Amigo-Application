package com.example.collegeappfirstassemble.HomeMain;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegeappfirstassemble.AdminPanel.AdminPanel;
import com.example.collegeappfirstassemble.Attendance.Attendanceactivity;
import com.example.collegeappfirstassemble.CrowdTracker.Tracker;
import com.example.collegeappfirstassemble.EliteDivision.EliteDivisionActivity;
import com.example.collegeappfirstassemble.EliteDivision.FreelanceActivity;
import com.example.collegeappfirstassemble.Home;
import com.example.collegeappfirstassemble.OtpSignup.MainActivity;
import com.example.collegeappfirstassemble.OtpSignup.SignUp;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.HomemainFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialSplashActivity;
import com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural.CulturalClubActivity;
import com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural.SportsMain;
import com.example.collegeappfirstassemble.TechClubs.TechClubsActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    RecyclerView recyclerView, quicklinks, homeproject;
    ScrollView scrollView;
    ProgressBar progressBar;
    Button socialbtn;
    TextView attendance, admin;
    DatabaseReference db, admindb, quicklinkdb, projectdb, userCheck, calEventsDb, Sliderdb;
    CardView cd;
    LinearLayout l;
    CalendarView calendarView;
    ViewPager2 CalEventVP;
    FloatingActionButton fab;
    QuickLinksAdap quickLinksAdap;
    CalEventsAdapVp calEventsAdapVp;
    CalEventsAdapRv calEventsAdapRv;

    ViewPager2 sliderVp;

    SliderAdapter sliderAdapter;

    BottomNavigationView bottomNavigationView;

    Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        scrollView = findViewById(R.id.homescroll);
        progressBar = findViewById(R.id.progress_home);
//        imageSlider=(ImageSlider)findViewById(R.id.imageslider);
//        recyclerView=(RecyclerView)findViewById(R.id.itemsrecyclerview);
//        quicklinks = findViewById(R.id.quicklinks);
//        homeproject = findViewById(R.id.homeproject);
//        cd = findViewById(R.id.cardhome);
        fab = findViewById(R.id.fabSocialHome);
        CalEventVP = findViewById(R.id.calEventsVp);
        bottomNavigationView = findViewById(R.id.bottombarHome);
        sliderVp = findViewById(R.id.homepageslider);
        l = findViewById(R.id.linearHome);
        //        calendarView = findViewById(R.id.calendarHome);
//        CalEventRec = findViewById(R.id.calEventsRec);


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                progressBar.setVisibility(View.GONE);
////                scrollView.setVisibility(View.VISIBLE);
//
//            }
//        }, 10000);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){

                    case R.id.sportsIcon: startActivity(new Intent(HomePage.this, SportsMain.class));
                    break;

                    case R.id.culturalIcon: startActivity(new Intent(HomePage.this, CulturalClubActivity.class));
                    break;

                    case R.id.icodeIcon: startActivity(new Intent(HomePage.this, EliteDivisionActivity.class));
                    break;

                    case R.id.TechIcon: startActivity(new Intent(HomePage.this, TechClubsActivity.class));
                    break;

                }
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, SocialSplashActivity.class);
                startActivity(intent);
            }
        });

        calEventsDb = FirebaseDatabase.getInstance().getReference("Calendar").child("Months");

        FirebaseRecyclerOptions<CalEventsModelVP> options = new FirebaseRecyclerOptions.Builder<CalEventsModelVP>()
                .setQuery(calEventsDb, CalEventsModelVP.class).build();

        calEventsAdapVp = new CalEventsAdapVp(options, HomePage.this);

        CalEventVP.setAdapter(calEventsAdapVp);



//        Sliderdb = FirebaseDatabase.getInstance().getReference("SliderImages").child("Images");
//
//
//        Sliderdb.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int count = (int) snapshot.getChildrenCount();
//
//                for(int i = 0; i<count; i++){
//
//                    String uri = snapshot.getValue().toString();
//                    sliderModels.add(new SliderModel(uri));
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        List<SliderModel>sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(R.drawable.icode1));
        sliderModels.add(new SliderModel(R.drawable.icode2));
        sliderModels.add(new SliderModel(R.drawable.icode3));
        sliderModels.add(new SliderModel(R.drawable.icode4));
        sliderModels.add(new SliderModel(R.drawable.icode5));

        sliderVp.setAdapter(new SliderAdapter(sliderModels,sliderVp));

        sliderVp.setClipToPadding(false);
        sliderVp.setClipChildren(false);
        sliderVp.setOffscreenPageLimit(3);
        sliderVp.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        sliderVp.setPageTransformer(compositePageTransformer);

        sliderVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,5000);

            }
        });
//        cd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomePage.this, EliteDivisionActivity.class));
//            }
//        });




        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        db = FirebaseDatabase.getInstance().getReference("Teachers").child(user);
//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    attendance.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        socialbtn = findViewById(R.id.socialbtn);
//        attendance = findViewById(R.id.takeattendance);
        admin = findViewById(R.id.adminpanel);



        admindb = FirebaseDatabase.getInstance().getReference("Admin").child(user);

        admindb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    admin.setVisibility(View.VISIBLE);

                }else{
                    admin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,AdminPanel.class));
            }
        });

//        attendance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomePage.this, Attendanceactivity.class));
//            }
//        });


//        socialbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomePage.this, SocialSplashActivity.class));
//            }
//        });



//        LinearLayoutManager l = new LinearLayoutManager(HomePage.this, RecyclerView.HORIZONTAL, false);
//
//
//        quicklinks.setLayoutManager(l);
//
//        quicklinkdb = FirebaseDatabase.getInstance().getReference("QuickLinks");
//
//        FirebaseRecyclerOptions<QuickLinksModel> op = new FirebaseRecyclerOptions.Builder<QuickLinksModel>()
//                .setQuery(quicklinkdb, QuickLinksModel.class).build();
//
//        quickLinksAdap = new QuickLinksAdap(op, HomePage.this);
//
//        quicklinks.setAdapter(quickLinksAdap);
//
//        LinearLayoutManager lp = new LinearLayoutManager(HomePage.this, RecyclerView.HORIZONTAL, false);
//        lp.setStackFromEnd(true);
//
//        homeproject.setLayoutManager(lp);
//
//        projectdb = FirebaseDatabase.getInstance().getReference("ProjectPosts");
//
//        FirebaseRecyclerOptions<ProjectaddpostModel> optionsp = new FirebaseRecyclerOptions.Builder<ProjectaddpostModel>()
//                .setQuery(projectdb, ProjectaddpostModel.class).build();
//
//
//        homeProjectAdap = new HomeProjectAdap(optionsp, HomePage.this);
//
//        homeproject.setAdapter(homeProjectAdap);
//
//        ArrayList<HomePageModel>list=new ArrayList<>();
//        list.add(new HomePageModel(R.drawable.subscription,"Subscription"));
//        list.add(new HomePageModel(R.drawable.techclubs,"TechClubs"));
//        list.add(new HomePageModel(R.drawable.sportsclub,"SportsClub"));
//        list.add(new HomePageModel(R.drawable.social,"Social"));
//
//
//
//        HomePageAdapter homePageAdapter=new HomePageAdapter(list,this);
//        recyclerView.setAdapter(homePageAdapter);
//
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(gridLayoutManager);
//

//        final List<SlideModel> slideModel = new ArrayList<>();
        // for image slider

//        FirebaseDatabase.getInstance().getReference("SliderImages").child("Images").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data:dataSnapshot.getChildren()){
//                    slideModel.add(new SlideModel(data.getValue().toString(), ScaleTypes.FIT));
//                    imageSlider.setImageList(slideModel,ScaleTypes.FIT);
//                    progressBar.setVisibility(View.GONE);
//                    scrollView.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        progressBar.setVisibility(View.GONE);
        l.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        userCheck = FirebaseDatabase.getInstance().getReference("AllUsers");
//        userCheck.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
//                   Intent intent = new Intent(HomePage.this, SignUp.class);
//                   startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        quickLinksAdap.startListening();
//        homeProjectAdap.startListening();
        calEventsAdapVp.startListening();
//        calEventsAdapVp.calEventsAdapRv.startListening();

//        sliderAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        quickLinksAdap.stopListening();
//        homeProjectAdap.stopListening();
        calEventsAdapVp.stopListening();

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            sliderVp.setCurrentItem(sliderVp.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,5000);
    }

}