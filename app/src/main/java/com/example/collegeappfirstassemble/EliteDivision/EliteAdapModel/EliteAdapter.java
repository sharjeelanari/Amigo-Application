package com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel;

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
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;

import java.util.ArrayList;

public class EliteAdapter extends RecyclerView.Adapter<EliteAdapter.viewHolder> {

    ArrayList<EliteModel>list;
    Context context;

    public EliteAdapter(ArrayList<EliteModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elitedisplaysample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        EliteModel eliteModel = list.get(position);
        holder.text.setText(eliteModel.getText());

        holder.elitecdview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SocialActivity.class);
               // intent.putExtra()
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView text;
        CardView elitecdview;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            text=itemView.findViewById(R.id.elitedivtextview);
            elitecdview=itemView.findViewById(R.id.elitecdview);

        }
    }
}
