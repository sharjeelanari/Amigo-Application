package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reaches extends AppCompatActivity {
    RecyclerView reachRecycler;
    EditText typeMessage;
    FloatingActionButton sendText;
    ImageView profile;
    TextView name;
    LinearLayout layout;

    DatabaseReference chatdatabase, chatdatabase1, reachesdb, namedb, reachesdb2, users, profiledb;
    FirebaseUser firebaseUser;

    ReachAdapter reachAdapter;
    ReachModel reachModel;
    ReachPageModel reachPageModel;

    ReachPageModel1 reachPageModel1;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaches);

        reachRecycler = findViewById(R.id.chatrecycler);
        typeMessage = findViewById(R.id.textsend);
        sendText = findViewById(R.id.sendtext);
        profile = findViewById(R.id.user);
        name = findViewById(R.id.Details);
        layout = findViewById(R.id.linearreach);

        reachModel = new ReachModel();
        reachPageModel = new ReachPageModel();
        reachPageModel1 = new ReachPageModel1();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String Userid = firebaseUser.getUid();

        String ProfileUid;
        ProfileUid = getIntent().getStringExtra("userid");



        String sendersEnd = Userid + ProfileUid;
        String recieversEnd = ProfileUid + Userid;

        chatdatabase = FirebaseDatabase.getInstance().getReference("Reaches").child("Messages").child(sendersEnd);
        chatdatabase1 = FirebaseDatabase.getInstance().getReference("Reaches").child("Messages").child(recieversEnd);
        reachesdb = FirebaseDatabase.getInstance().getReference("ReachesPerPerson").child(Userid);
        reachesdb2 = FirebaseDatabase.getInstance().getReference("ReachesPerPerson").child(ProfileUid);
        namedb = FirebaseDatabase.getInstance().getReference("AllUsers").child(ProfileUid);

        namedb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        profile.setImageBitmap(bitmap);


                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {

                                Palette.Swatch swatch = palette.getMutedSwatch();
                                Palette.Swatch swatch1 = palette.getDominantSwatch();


                                if (swatch1 != null) {
                                    int rgb = swatch1.getRgb();
                                    int title = swatch1.getTitleTextColor();

                                    layout.setBackgroundColor(rgb);
                                    name.setTextColor(title);
                                    getWindow().setStatusBarColor(rgb);
                                } else {
                                    int rgb = swatch.getRgb();
                                    int title = swatch.getTitleTextColor();

                                    layout.setBackgroundColor(rgb);
                                    name.setTextColor(title);
                                    getWindow().setStatusBarColor(rgb);
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


                String Name = snapshot.child("name").getValue(String.class);
                String imguri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                Picasso.get().load(imguri).into(target);
                name.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        if (getIntent().getStringExtra("text")!=null){
            String text = getIntent().getStringExtra("text");

            typeMessage.setText(text);
        }

        reachRecycler.setLayoutManager(new LinearLayoutManager(Reaches.this));

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ReachModel>()
                .setQuery(chatdatabase, ReachModel.class).build();

        reachAdapter = new ReachAdapter(options, Reaches.this);
        reachRecycler.setAdapter(reachAdapter);
        chatdatabase.keepSynced(true);

        //TODO: Get the profile images and details and also display time of the text.


        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String textsend = typeMessage.getText().toString().trim();


                if (!textsend.isEmpty()) {



                    users = FirebaseDatabase.getInstance().getReference("AllUsers");
                    users.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name = snapshot.child(ProfileUid).child("name").getValue(String.class);
                            String username = snapshot.child(Userid).child("name").getValue(String.class);

                            calendar = Calendar.getInstance();
                            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yy HH:MM");
                            String time = s.format(calendar.getTime());


                            addtodb1(name, textsend);
                            addtodb2(username, textsend);
                            addtoChatdb(textsend, Userid, username, time);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } else {

                }

                typeMessage.setText("");
            }
        });


    }

    private void addtoChatdb(String text, String Uid, String name, String time) {


        reachModel.setSendertext(text);
        reachModel.setCurrentUid(Uid);
        reachModel.setCurrentname(name);
        reachModel.setTime(time);


        String key = chatdatabase.push().getKey();
        String key1 = chatdatabase1.push().getKey();

                chatdatabase.child(key).setValue(reachModel);

                chatdatabase1.child(key1).setValue(reachModel);



    }

    private void addtodb1(String name, String message) {

        reachPageModel1.setMessage(message);
        reachPageModel1.setName(name);



                reachesdb.child(name).setValue(reachPageModel1);



    }

    private void addtodb2(String username, String message) {
        reachPageModel.setMessage(message);
        reachPageModel.setName(username);


                reachesdb2.child(username).setValue(reachPageModel);


    }

    @Override
    protected void onStart() {
        super.onStart();
        reachAdapter.startListening();

        FirebaseAuth.getInstance().getCurrentUser().getUid();

    }


    @Override
    protected void onStop() {
        super.onStop();
        reachAdapter.stopListening();
    }
}