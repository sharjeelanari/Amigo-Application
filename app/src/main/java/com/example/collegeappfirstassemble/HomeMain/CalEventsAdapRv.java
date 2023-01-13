package com.example.collegeappfirstassemble.HomeMain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CalEventsAdapRv extends FirebaseRecyclerAdapter<RvCalEventsModel, CalEventsAdapRv.viewHolder> {

    public CalEventsAdapRv(@NonNull FirebaseRecyclerOptions<RvCalEventsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull RvCalEventsModel rvCalEventsModel) {

        viewHolder.date.setText(rvCalEventsModel.getDate());
        viewHolder.EventDesc.setText(rvCalEventsModel.getEventDes());

        viewHolder.progressBar.setVisibility(View.GONE);
        viewHolder.coordinatorLayout.setVisibility(View.VISIBLE);

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_cal_events_rv,
                 viewGroup, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView date, EventDesc;
        ProgressBar progressBar;
        CoordinatorLayout coordinatorLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.fabDateTV);
            EventDesc = itemView.findViewById(R.id.calEventsDes);
            progressBar = itemView.findViewById(R.id.progressCalRv);
            coordinatorLayout = itemView.findViewById(R.id.coordinatorCal);

        }
    }
}
