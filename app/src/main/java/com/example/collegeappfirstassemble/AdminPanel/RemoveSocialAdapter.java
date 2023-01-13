package com.example.collegeappfirstassemble.AdminPanel;

import android.app.Dialog;
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
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter.SocialActivityAdapter;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter.SocialActivityModel;
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

public class RemoveSocialAdapter extends FirebaseRecyclerAdapter<SocialAddpostModel, RemoveSocialAdapter.viewHolder> {
    Context context;
    DatabaseReference approvereference, dbuserid, dbprofile, deletedb, deletedb1;
    Boolean Clicked;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    int a;
    public RemoveSocialAdapter(@NonNull FirebaseRecyclerOptions<SocialAddpostModel> options, Context context, int a) {
        super(options);
        this.context = context;
        this.a = a;
    }

    Dialog dialog;

    @Override
    protected void onBindViewHolder(@NonNull RemoveSocialAdapter.viewHolder holder, int position, @NonNull SocialAddpostModel model) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        Button dialogYes = dialog.findViewById(R.id.dialog_submit);
        dialogYes.setText("DELETE");

        TextView textdialog = dialog.findViewById(R.id.dialog_message);
        textdialog.setText("Are you sure?");

        Button dialogNo = dialog.findViewById(R.id.dialog_review);
        dialogNo.setText("No");

        String imageurl = model.getImageurl();
        Picasso.get()
                .load(imageurl)
                .into(holder.image);
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

                            } else {

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

        if (userid != uid) {

            holder.reach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uid = model.getUserid().toString().trim();

                    context.startActivity(new Intent(context, Reaches.class).putExtra("userid", uid));
                }
            });
        } else {
            holder.reach.setVisibility(View.INVISIBLE);
        }

        dbuserid = FirebaseDatabase.getInstance().getReference("AllUsers").child(model.getUserid());
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

        if (a==1) {

            holder.delete.setVisibility(View.VISIBLE);

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
                }
            });
        }
        deletedb = FirebaseDatabase.getInstance().getReference("SocialPosts").child(postid);
        deletedb1 = FirebaseDatabase.getInstance().getReference("UserSocialPosts").child(uid)
                .child(postid);


        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deletedb.removeValue();

                deletedb1.removeValue();
                dialog.dismiss();

            }
        });

        dialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
        ImageView image, approves, profile, delete;
        TextView description, time, noofapproves, heading, reach, name;
        DatabaseReference db;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.profile);
            reach = itemView.findViewById(R.id.comment);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.addimg);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.uploadtime);
            approves = itemView.findViewById(R.id.approve);
            noofapproves = itemView.findViewById(R.id.check);
            heading = itemView.findViewById(R.id.headingtext);
            delete = itemView.findViewById(R.id.deletepost);
        }

        public void getapproveStatus(final String postid, final String userid) {

            db = FirebaseDatabase.getInstance().getReference("Approves");
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.child(postid).hasChild(userid)) {

                        approves.setImageResource(R.drawable.ic_baseline_check_box_24);
                        int number = (int) snapshot.child(postid).getChildrenCount() - 1;
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
