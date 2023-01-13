package com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.Reaches.Reaches;
import com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter.InsDomainAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstructorAdapter extends FirebaseRecyclerAdapter<InstructorModel,InstructorAdapter.viewHolder> {

Context context;
DatabaseReference db;

    public InstructorAdapter(@NonNull FirebaseRecyclerOptions<InstructorModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull InstructorModel model) {
        holder.ins_name.setText(model.getIns_name());
        holder.ins_bio.setText(model.getIns_bio());
        String uri = model.getDp();

        Picasso.get().load(uri).into(holder.profilePic);
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (!(user.equals(model.ins_button_reach))) {
            holder.reach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Reaches.class);
                    intent.putExtra("userid", model.getIns_button_reach());
                    context.startActivity(intent);
                }
            });
        }else{
            holder.reach.setVisibility(View.GONE);
        }

        holder.ins_domain.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        db = FirebaseDatabase.getInstance().getReference("TeacherDetails").child(model.getIns_name()).child("Skills");
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<SkillsModel>().setQuery(db, SkillsModel.class)
                .build();
        InsDomainAdapter insDomainAdapter = new InsDomainAdapter(options);

        holder.ins_domain.setAdapter(insDomainAdapter);

        insDomainAdapter.startListening();

        holder.progressBar.setVisibility(View.GONE);
        holder.relativeLayout.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.instructorsample,parent,false);
        return new viewHolder(view);

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView ins_name,ins_bio;
        RecyclerView ins_domain;
        CircleImageView profilePic;
        TextView reach;
        RelativeLayout relativeLayout;
        ProgressBar progressBar;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ins_name=itemView.findViewById(R.id.ins_name);
            ins_domain=itemView.findViewById(R.id.ins_domain);
            ins_bio=itemView.findViewById(R.id.ins_bio);
            profilePic = itemView.findViewById(R.id.teacherprofile);
            reach = itemView.findViewById(R.id.ins_button_reach);
            relativeLayout = itemView.findViewById(R.id.Relins);
            progressBar = itemView.findViewById(R.id.progressIns);

        }
    }
}
