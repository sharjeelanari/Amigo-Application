package com.example.collegeappfirstassemble.SocialMain.Social1.TabsOfProjectWithAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.SkillsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class InsDomainAdapter extends FirebaseRecyclerAdapter<SkillsModel, InsDomainAdapter.viewHolder> {
    public InsDomainAdapter(@NonNull FirebaseRecyclerOptions<SkillsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull SkillsModel skillsModel) {
        viewHolder.textView.setText(skillsModel.getSkillname());
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_ins_domain, parent, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.domainName);
        }
    }
}
