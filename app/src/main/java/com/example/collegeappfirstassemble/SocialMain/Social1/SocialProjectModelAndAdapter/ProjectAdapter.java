package com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProjectAdapter extends FirebaseRecyclerAdapter<ProjectaddpostModel, ProjectAdapter.viewHolder> {

    //this is the firebase recycler adapter.


    public ProjectAdapter(@NonNull FirebaseRecyclerOptions<ProjectaddpostModel> options, Context context) {
        super(options);
        this.context = context;
    }

    Context context;
    DatabaseReference dbuserid, profiledb;


    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull ProjectaddpostModel model) {


        String showuser = model.getShowuser();


        holder.briefideaprotv.setText(model.getMoreidea());

        holder.needforprotv.setText(model.getSkills());

        holder.briefreq.setText(model.getOtherdet());

        holder.basedon.setText(model.getBasedon());

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Check this post on " + model.getBasedon());
                intent.putExtra(Intent.EXTRA_TEXT, "Check this post on " + model.getBasedon() + "\nbased on " + model.getSkills()
                + " on Amigo");
                context.startActivity(intent);
            }
        });

        if (showuser.equals("Yes")) {

            holder.name.setText(model.getUid());
        } else {
            holder.name.setText("Anonymous");
        }
        dbuserid = FirebaseDatabase.getInstance().getReference("AllUsers")
                .child(model.getUid());


        profiledb = FirebaseDatabase.getInstance().getReference("AllUsers");

        dbuserid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uid = snapshot.child("uid").getValue(String.class);
                Intent intent = new Intent(context, Reaches.class);
                intent.putExtra("userid", uid);

                if (showuser.equals("Yes")) {

                    profiledb.child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String imguri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                            Picasso.get().load(imguri).into(holder.profile);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Picasso.get().load("@drawable/user.png").into(holder.profile);
                }


                holder.reach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
                            context.startActivity(intent);
                        }

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        holder.shareimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT,"This is all details about project" );
//                intent.setType("text/plain");
//                context.startActivity(Intent.createChooser(intent,"share"));
//
//            }
//        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleavailableprojectlayout, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView briefideaprotv, needforprotv, briefreq, basedon, name;
        Button reach;
        ImageView profile, share;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profileimg);
            share = itemView.findViewById(R.id.share);
            briefideaprotv = itemView.findViewById(R.id.briefideaprotv);
            needforprotv = itemView.findViewById(R.id.needforprotv);
            basedon = itemView.findViewById(R.id.protopicstv);
            briefreq = itemView.findViewById(R.id.briefrequirement);
            reach = itemView.findViewById(R.id.reachproject);
            name = itemView.findViewById(R.id.nameuser);
        }
    }
}
