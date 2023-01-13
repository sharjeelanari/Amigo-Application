package com.example.collegeappfirstassemble.SportsAndCultural.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.SportsModel;
import com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural.SportDetails;

import java.util.ArrayList;

public class SportsRecyclerAdapter extends RecyclerView.Adapter<SportsRecyclerAdapter.viewHolder> {

    ArrayList<SportsModel> list1;
    Context context;
    public SportsRecyclerAdapter(ArrayList<SportsModel> list1, Context context){
        this.list1=list1;
        this.context=context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sports_list_items, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SportsModel sportsModel = list1.get(position);
        holder.sportName.setText(sportsModel.getSportName());
        holder.capName.setText(sportsModel.getCapName());


        holder.onclickcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SportDetails.class);
                intent.putExtra("sportname", sportsModel.getSportName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView sportName;
        TextView capName;
        CardView onclickcard;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            sportName=(TextView)itemView.findViewById(R.id.SportName);
            capName=(TextView)itemView.findViewById(R.id.CapName);
            onclickcard=(CardView)itemView.findViewById(R.id.cardonclick);

        }
    }
}
