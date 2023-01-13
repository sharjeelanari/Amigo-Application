package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SkillsRecAdap extends FirebaseRecyclerAdapter<SkillsModel, SkillsRecAdap.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    int a;
    DatabaseReference deletedb, deletedb2, namedb;
    Dialog dialog;

    public SkillsRecAdap(@NonNull FirebaseRecyclerOptions<SkillsModel> options, Context context, int a) {
        super(options);
        this.context = context;
        this.a = a;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull SkillsModel model) {

        holder.skillname.setText("   " + model.getSkillname());
        holder.skilldet.setText(model.getSkilldet());

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        deletedb = FirebaseDatabase.getInstance().getReference("UserDetails").child(user).child("Skills")
                .child(model.getSkillname());

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
            holder.delete.setVisibility(View.GONE);
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

                        deletedb2 = deletedb2.child(name).child("Skills").child(model.getSkillname());

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

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_skills, parent, false);
        return new viewHolder(v);

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView skillname, skilldet;
        ImageView delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.deleteskill);
            skillname = itemView.findViewById(R.id.skillname);
            skilldet = itemView.findViewById(R.id.skilldet);

        }
    }
}
