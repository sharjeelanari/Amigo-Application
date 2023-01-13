package com.example.collegeappfirstassemble.AdminPanel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceAdapter;
import com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel.FreelanceModel;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RemoveFreelanceAdapter extends FirebaseRecyclerAdapter<FreelanceModel, RemoveFreelanceAdapter.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RemoveFreelanceAdapter(@NonNull FirebaseRecyclerOptions<FreelanceModel> options, Context context) {
        super(options);
        this.context = context;
    }

    Context context;
    DatabaseReference db, deletedb;
    Dialog dialog;

    @Override
    protected void onBindViewHolder(@NonNull RemoveFreelanceAdapter.viewHolder holder, int position, @NonNull FreelanceModel model) {


        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_attendance_activity);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        Button dialogYes = dialog.findViewById(R.id.dialog_submit);
        dialogYes.setText("DELETE");

        TextView textdialog = dialog.findViewById(R.id.dialog_message);
        textdialog.setText("Are you sure?");

        Button dialogNo = dialog.findViewById(R.id.dialog_review);
        dialogNo.setText("No");

        holder.f_topic.setText(model.getFreelance_topic());
        holder.f_desc.setText(model.getFreelance_desc());
        holder.f_work.setText(model.getFreelance_work());
        holder.f_salary.setText(model.getFreelance_price());
        holder.f_deadline.setText(model.getFreelance_deadline());
        holder.f_skills.setText(model.getFreelance_skills());
        String name = model.getFreelance_name().toString();

        db = FirebaseDatabase.getInstance().getReference("Users").child(name);
        String key = getRef(position).getKey();
        deletedb = FirebaseDatabase.getInstance().getReference("freelancetopic").child(key);

        holder.delete.setVisibility(View.VISIBLE);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        holder.freelance_applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.f_whychoosed.setVisibility(View.VISIBLE);
                holder.freelance_applybtn.setVisibility(View.GONE);
                holder.submit.setVisibility(View.VISIBLE);
            }
        });

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String uid = snapshot.child("uid").getValue(String.class);
                        if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(uid)) {
                            String choosetext = holder.f_whychoosed.getText().toString().trim();
                            if (choosetext.isEmpty()) {
                                holder.submit.setVisibility(View.GONE);
                                holder.freelance_applybtn.setVisibility(View.VISIBLE);
                                holder.f_whychoosed.setVisibility(View.GONE);
                            } else

                                context.startActivity(new Intent(context, Reaches.class).putExtra("userid", uid)
                                        .putExtra("text", choosetext));

                            holder.submit.setVisibility(View.GONE);
                            holder.freelance_applybtn.setVisibility(View.VISIBLE);
                            holder.f_whychoosed.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(context, "You could have done it yourself", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
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
    public RemoveFreelanceAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplefreelancedisplay, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView f_work;
        TextView f_desc;
        TextView f_topic;
        TextView f_salary;
        TextView f_deadline;
        TextView f_skills;
        TextView f_whychoosed;
        Button freelance_applybtn, submit;
        ImageView delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            f_topic = itemView.findViewById(R.id.f_topic);
            f_desc = itemView.findViewById(R.id.f_desc);
            f_work = itemView.findViewById(R.id.f_work);
            f_skills = itemView.findViewById(R.id.f_skills);
            f_deadline = itemView.findViewById(R.id.f_deadline);
            f_salary = itemView.findViewById(R.id.f_salary);
            f_whychoosed = itemView.findViewById(R.id.f_whychoosed);
            freelance_applybtn = itemView.findViewById(R.id.freelanceapplybtn);
            submit = itemView.findViewById(R.id.freelancesubmit);
            delete = itemView.findViewById(R.id.deletefreelance);

        }
    }
}
