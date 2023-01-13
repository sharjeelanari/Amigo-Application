package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ClubMembersAdap extends FirebaseRecyclerAdapter<ClubMemModel, ClubMembersAdap.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public ClubMembersAdap(@NonNull FirebaseRecyclerOptions<ClubMemModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ClubMembersAdap.viewHolder holder, int position, @NonNull ClubMemModel model) {
        holder.name.setText(model.getMemName());
        holder.designation.setText(model.getMemdesignation());

//        holder.cd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = model.getMemName();
//                context.startActivity(new Intent(context, ProfilePage.class)
//                        .putExtra("username", username));
//            }
//        });
    }

    @NonNull
    @Override
    public ClubMembersAdap.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_club_members, parent, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, designation;
        CardView cd;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.memname);
            designation = itemView.findViewById(R.id.memdesignation);
            cd = itemView.findViewById(R.id.clickmemcard);

        }
    }
}
