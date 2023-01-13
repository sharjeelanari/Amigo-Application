package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ReachPageAdapter extends FirebaseRecyclerAdapter<ReachPageModel, ReachPageAdapter.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReachPageAdapter(@NonNull FirebaseRecyclerOptions<ReachPageModel> options, Context context) {
        super(options);
        this.context = context;
    }

    Context context;
    DatabaseReference userdb, profiledb;

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull ReachPageModel model) {
        holder.name.setText(model.getName());
        holder.message.setText(model.getMessage());
        userdb = FirebaseDatabase.getInstance().getReference("AllUsers")
                .child(holder.name.getText().toString());

        Intent intent = new Intent(context, Reaches.class);
        profiledb = FirebaseDatabase.getInstance().getReference("AllUsers");
        userdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uid = snapshot.child("uid").getValue(String.class);

                intent.putExtra("userid", uid);
                profiledb.child(uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String imguri = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                        Picasso.get().load(imguri).into(holder.dp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_reaches_page, parent, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView name, message;
        ImageView dp;
        CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            dp = itemView.findViewById(R.id.dptoreach);
            name = itemView.findViewById(R.id.nametoreach);
            message = itemView.findViewById(R.id.recentmsg);
            cardView = itemView.findViewById(R.id.reachPrel);
        }
    }
}
