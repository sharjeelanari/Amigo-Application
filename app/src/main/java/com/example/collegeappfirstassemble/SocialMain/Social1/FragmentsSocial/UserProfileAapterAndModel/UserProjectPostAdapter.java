package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

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

public class UserProjectPostAdapter extends FirebaseRecyclerAdapter<ProjectaddpostModel, UserProjectPostAdapter.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    int a;
    public UserProjectPostAdapter(@NonNull FirebaseRecyclerOptions<ProjectaddpostModel> options, Context context, int a) {
        super(options);
        this.context = context;
        this.a = a;
    }

    Context context;
    DatabaseReference dbuserid, profiledb, deletedb, deletedb1;
    Dialog dialog;



    @Override
    protected void onBindViewHolder(@NonNull UserProjectPostAdapter.viewHolder holder, int position, @NonNull ProjectaddpostModel model) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogYes = dialog.findViewById(R.id.dialog_submit);
        dialogYes.setText("DELETE");

        TextView textdialog = dialog.findViewById(R.id.dialog_message);
        textdialog.setText("Are you sure?");

        Button dialogNo = dialog.findViewById(R.id.dialog_review);
        dialogNo.setText("No");

        String showuser = model.getShowuser();


        holder.briefideaprotv.setText(model.getMoreidea());

        holder.needforprotv.setText(model.getSkills());

        holder.briefreq.setText(model.getOtherdet());

        holder.basedon.setText(model.getBasedon());

        if (showuser.equals("Yes")) {

            holder.name.setText(model.getUid());
        } else {
            holder.name.setText("Anonymous");
        }
        dbuserid = FirebaseDatabase.getInstance().getReference("AllUsers")
                .child(holder.name.getText().toString());


        profiledb = FirebaseDatabase.getInstance().getReference("AllUsers");

        dbuserid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uid = snapshot.child("uid").getValue(String.class);

                Intent intent = new Intent(context, Reaches.class);
                intent.putExtra("userid", uid);

                if (showuser.equals("Yes")) {

                    profiledb.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String postkey = getRef(position).getKey();

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        deletedb = FirebaseDatabase.getInstance().getReference("UserProjectPosts").child(user)
                .child(postkey);
        deletedb1 = FirebaseDatabase.getInstance().getReference("ProjectPosts").child(postkey);

        if (a==1) {

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();


                }
            });
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
    public UserProjectPostAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_user_project_posts, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView briefideaprotv, needforprotv, briefreq, basedon, name;
        ImageView profile, delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profileimg);
            briefideaprotv = itemView.findViewById(R.id.briefideaprotv);
            needforprotv = itemView.findViewById(R.id.needforprotv);
            basedon = itemView.findViewById(R.id.protopicstv);
            briefreq = itemView.findViewById(R.id.briefrequirement);
            name = itemView.findViewById(R.id.nameuser);
            delete = itemView.findViewById(R.id.deleteproject);
        }
    }
}
