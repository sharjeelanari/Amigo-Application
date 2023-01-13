package com.example.collegeappfirstassemble.SportsAndCultural.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.SprtPstModel;

import java.util.ArrayList;

public class SportsPstEvntAdap extends RecyclerView.Adapter<SportsPstEvntAdap.viewHolder> {

    ArrayList<SprtPstModel> list;
    Context context;
    public SportsPstEvntAdap(ArrayList<SprtPstModel> list, Context context){
        this.context=context;
        this.list=list;

    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sprts_evnts_list, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SprtPstModel SPM = list.get(position);
        holder.pastevents.setText(SPM.getPasteventname());
        holder.pastdescription.setText(SPM.getPastdescription());
        holder.pastextra.setText(SPM.getPasrextra());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView pastevents;
        TextView pastdescription;
        TextView pastextra;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            pastevents = (TextView)itemView.findViewById(R.id.psteventname);
            pastdescription = (TextView)itemView.findViewById(R.id.psteventdescription);
            pastextra = (TextView)itemView.findViewById(R.id.pstextra);

        }
    }
}
