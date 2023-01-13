package com.example.collegeappfirstassemble.HomeMain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.AdminPanel.AdminPanel;
import com.example.collegeappfirstassemble.Attendance.Attendanceactivity;
import com.example.collegeappfirstassemble.EliteDivision.EliteDivisionActivity;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural.SportsMain;
import com.example.collegeappfirstassemble.Subscription.SubscriptionActivity;
import com.example.collegeappfirstassemble.TechClubs.TechClubsActivity;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.viewHolder> {
    ArrayList<HomePageModel> list;
    Context context;

    public HomePageAdapter(ArrayList<HomePageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_home, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HomePageModel model = list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getHeadline());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.textView.getText().toString().equals("Subscription")) {
                    context.startActivity(new Intent(context, SubscriptionActivity.class));
                } else if (holder.textView.getText().toString().equals("TechClubs")) {
                    context.startActivity(new Intent(context, TechClubsActivity.class));
                } else if (holder.textView.getText().toString().equals("SportsClub")) {
                    context.startActivity(new Intent(context, SportsMain.class));
                } else if (holder.textView.getText().toString().equals("Social")) {
                    context.startActivity(new Intent(context, SocialActivity.class));
                }
            }


        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.headline);
            imageView = itemView.findViewById(R.id.samplefoodimgviewid);

        }
    }
}
