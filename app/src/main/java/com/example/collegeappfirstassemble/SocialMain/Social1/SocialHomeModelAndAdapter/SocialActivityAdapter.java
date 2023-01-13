package com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ProfilePage;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;

public class SocialActivityAdapter extends FirebaseRecyclerAdapter<SocialAddpostModel, SocialActivityAdapter.viewHolder> {
    Context context;
    DatabaseReference approvereference;
    Boolean Clicked;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SocialActivityAdapter(@NonNull FirebaseRecyclerOptions<SocialAddpostModel> options, Context context) {
        super(options);
        this.context = context;
    }
    DatabaseReference dbuserid,dbprofile;

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull SocialAddpostModel model) {
        String imageurl = model.getImageurl();
        Picasso.get()
                .load(imageurl)
                .into(holder.image);
        String file = model.getFileuri();


        String userId = model.getUserid();


        if (imageurl == null){

            holder.image.setVisibility(View.GONE);
            holder.description1.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.GONE);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.attachments.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.description1);

            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) holder.approves.getLayoutParams();
            params1.addRule(RelativeLayout.BELOW, R.id.description1);

            holder.description1.setText(model.getPostDescription());


        }else{
            holder.image.setVisibility(View.VISIBLE);
            holder.description1.setVisibility(View.GONE);
            holder.description.setVisibility(View.VISIBLE);
        }

        dbuserid = FirebaseDatabase.getInstance().getReference("AllUsers").child(model.getUserid());

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbuserid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue(String.class);

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this post by "+ name + " on Amigo");
                        intent.putExtra(Intent.EXTRA_TEXT, "Check out this post by "+ name + " on Amigo" + "\n\n" +model.getPostDescription() + "\n\nGo to Amigo social for more.");
                        context.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



        if (file != null){
            holder.attachments.setVisibility(View.VISIBLE);

            holder.attachments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    intent.setDataAndType(Uri.parse(model.getFileuri()), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    Intent i = Intent.createChooser(intent, "Open file");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }

        holder.description.setText(model.getPostDescription());
        //here we are storing date in a format (Month DD, YYYY)
        DateFormat dateformat = DateFormat.getDateInstance();
        String formatteddate = dateformat.format(new Date(Long.valueOf(model.getTime())).getTime());

        holder.time.setText(formatteddate);
        holder.heading.setText(model.getHeading());
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userid = firebaseUser.getUid();
        String postid = getRef(position).getKey();

        holder.getapproveStatus(postid, userid);

        approvereference = FirebaseDatabase.getInstance().getReference("Approves");

        holder.approves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicked = true;

                approvereference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (Clicked == true) {

                            if (snapshot.child(postid).hasChild(userid)) {

                                approvereference.child(postid).child(userid).removeValue();
                                Clicked = false;

                            }else{

                                approvereference.child(postid).child(userid).setValue(true);
                                Clicked = false;

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        dbprofile = FirebaseDatabase.getInstance().getReference("AllUsers").child(model.getUserid());

        dbprofile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imguri = snapshot.child("ProfilePic").child("dp").getValue(String.class);

                Picasso.get().load(imguri).into(holder.profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        if (!userId.equals(uid)) {

            holder.reach.setVisibility(View.VISIBLE);
            holder.reach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uid = model.getUserid().toString().trim();

                    context.startActivity(new Intent(context, Reaches.class).putExtra("userid", uid));
                }
            });
        }else {
            holder.reach.setVisibility(View.INVISIBLE);
        }


        dbuserid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);

                holder.name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.name.getText().toString() != null) {

                    context.startActivity(new Intent(context, ProfilePage.class).putExtra("userid", userId)
                            .putExtra("username", holder.name.getText().toString()));
                }
            }
        });

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.name.getText().toString() != null) {

                    context.startActivity(new Intent(context, ProfilePage.class).putExtra("userid", userId)
                            .putExtra("username", holder.name.getText().toString()));
                }
            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_social_home, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image, approves, profile, share;
        TextView description, time, noofapproves, heading, reach, name, attachments, description1;
        DatabaseReference db;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            description1 = itemView.findViewById(R.id.description1);
            attachments = itemView.findViewById(R.id.attached);
            profile = itemView.findViewById(R.id.profile);
            reach = itemView.findViewById(R.id.comment);
            name = itemView.findViewById(R.id.name);
            share = itemView.findViewById(R.id.share);
            image = itemView.findViewById(R.id.addimg);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.uploadtime);
            approves = itemView.findViewById(R.id.approve);
            noofapproves = itemView.findViewById(R.id.check);
            heading = itemView.findViewById(R.id.headingtext);
        }


        //This method takes post id and user id then checks the user has liked the post or not and displays approve icon
        //accordingly and also gets the count of the approves

        public void getapproveStatus(final String postid, final String userid) {

            db = FirebaseDatabase.getInstance().getReference("Approves");
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.child(postid).hasChild(userid)) {

                        approves.setImageResource(R.drawable.ic_baseline_check_box_24);
                        int number = (int) snapshot.child(postid).getChildrenCount()-1;
                        noofapproves.setText("Approved by you and " + number + " others");

                    } else {
                        approves.setImageResource(R.drawable.ic_outline_check_box_24);
                        int number = (int) snapshot.child(postid).getChildrenCount();
                        noofapproves.setText("Approved by " + number + " others");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }


}
