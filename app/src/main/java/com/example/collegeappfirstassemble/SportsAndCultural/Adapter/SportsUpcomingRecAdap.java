package com.example.collegeappfirstassemble.SportsAndCultural.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SportsUpcomingRecAdap extends FirebaseRecyclerAdapter<AddEvent, SportsUpcomingRecAdap.viewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SportsUpcomingRecAdap(@NonNull FirebaseRecyclerOptions<AddEvent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull AddEvent model) {
        holder.name.setText(model.getEventName());
        holder.date.setText(model.getStartDate());
        holder.time.setText(model.getTime());
        holder.matchno.setText(model.getMatchNo());
        holder.venue.setText(model.getVENUE());
        holder.typename.setText(model.getTypename());
        holder.live.setText(model.getStreamLive());
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sprts_upcmg_list,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView name, date,time,matchno,live,venue,typename;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.eventname);
            date = itemView.findViewById(R.id.eventdescription);
            time = itemView.findViewById(R.id.eventdescription2);
            matchno = itemView.findViewById(R.id.extra);
            live = itemView.findViewById(R.id.streaming);
            venue = itemView.findViewById(R.id.venueat);
            typename = itemView.findViewById(R.id.eventtypename);
        }
    }
}
