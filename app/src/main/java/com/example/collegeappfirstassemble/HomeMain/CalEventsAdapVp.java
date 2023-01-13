package com.example.collegeappfirstassemble.HomeMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalEventsAdapVp extends FirebaseRecyclerAdapter<CalEventsModelVP, CalEventsAdapVp.viewHolder> {


    Context context;
    DatabaseReference calEventsdb;
    CalEventsAdapRv calEventsAdapRv;

    public CalEventsAdapVp(@NonNull FirebaseRecyclerOptions<CalEventsModelVP> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull CalEventsModelVP calEventsModelVP) {

        viewHolder.month.setText(calEventsModelVP.getMonth());
        LinearLayoutManager lp = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        lp.setStackFromEnd(true);
        viewHolder.recyclerView.setLayoutManager(lp);


        calEventsdb = FirebaseDatabase.getInstance().getReference("Calendar").child("CalEvents").child(calEventsModelVP.getMonth());

        FirebaseRecyclerOptions<RvCalEventsModel> options = new FirebaseRecyclerOptions.Builder<RvCalEventsModel>()
                .setQuery(calEventsdb, RvCalEventsModel.class).build();

        calEventsAdapRv = new CalEventsAdapRv(options);

        viewHolder.recyclerView.setAdapter(calEventsAdapRv);

        calEventsAdapRv.startListening();

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_cal_events_vp,
                parent, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView month;
        RecyclerView recyclerView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.monthName);
            recyclerView = itemView.findViewById(R.id.calEventsRv);
        }
    }


}