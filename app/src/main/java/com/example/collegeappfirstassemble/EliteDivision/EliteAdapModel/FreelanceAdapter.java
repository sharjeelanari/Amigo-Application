package com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class FreelanceAdapter extends FirebaseRecyclerAdapter<FreelanceModel, FreelanceAdapter.viewHolder> {

    Context context;
    DatabaseReference db;


    public FreelanceAdapter(@NonNull FirebaseRecyclerOptions<FreelanceModel> options, Context applicationcontext) {
        super(options);
        this.context = applicationcontext;

    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull FreelanceModel freelanceModel) {

        holder.f_topic.setText(freelanceModel.getFreelance_topic());
        holder.f_desc.setText(freelanceModel.getFreelance_desc());
        holder.f_work.setText(freelanceModel.getFreelance_work());
        holder.f_salary.setText("Rs."+freelanceModel.getFreelance_price());
        holder.f_deadline.setText(freelanceModel.getFreelance_deadline());
        holder.f_skills.setText(freelanceModel.getFreelance_skills());
        String name = freelanceModel.getFreelance_name().toString();

        db = FirebaseDatabase.getInstance().getReference("AllUsers").child(name);

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


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        }
    }
}
