package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ProfilePage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserprojectRecAdap extends FirebaseRecyclerAdapter<DetailsProfileProjectModel, UserprojectRecAdap.viewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    int a;
    Dialog dialog;
    DatabaseReference deletedb, namedb, deletedb2;

    public UserprojectRecAdap(@NonNull FirebaseRecyclerOptions<DetailsProfileProjectModel> options, Context context, int a) {
        super(options);
        this.context = context;
        this.a = a;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull DetailsProfileProjectModel model) {
        holder.projectnm.setText("  " + model.getProjectnm());
        holder.projectdet.setText(model.getProjectdet());

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        deletedb = FirebaseDatabase.getInstance().getReference("UserDetails").child(user).child("Projects")
                .child(model.getProjectnm());

        namedb = FirebaseDatabase.getInstance().getReference("Users").child(user).child("name");
        deletedb2 = FirebaseDatabase.getInstance().getReference("UserDetails");

        dialog = new Dialog(context);

        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogYes = dialog.findViewById(R.id.dialog_submit);
        dialogYes.setText("DELETE");

        TextView textdialog = dialog.findViewById(R.id.dialog_message);
        textdialog.setText("Are you sure?");

        Button dialogNo = dialog.findViewById(R.id.dialog_review);
        dialogNo.setText("No");


        if (a == 1){
            holder.delete.setVisibility(View.VISIBLE);
        }else{
            holder.delete.setVisibility(View.GONE);
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namedb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.getValue(String.class);

                        deletedb2 = deletedb2.child(name).child("Projects").child(model.getProjectnm());

                        deletedb2.removeValue();
                        deletedb.removeValue();
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        dialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        holder.link.setText(model.getLink());
        String link = model.getLink();
        if (link == null){
            holder.link.setVisibility(View.GONE);
        }



        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));

            }
        });
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userprojects_sample, parent, false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView projectnm, projectdet, link;
        ImageView delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.projectdelete);
            link = itemView.findViewById(R.id.projectlink);
            projectnm = itemView.findViewById(R.id.nameofproject);
            projectdet = itemView.findViewById(R.id.detailsofproject);

        }
    }
}
