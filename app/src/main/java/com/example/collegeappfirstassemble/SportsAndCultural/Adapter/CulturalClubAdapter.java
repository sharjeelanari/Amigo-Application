package com.example.collegeappfirstassemble.SportsAndCultural.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model.CulturalClubModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CulturalClubAdapter extends FirebaseRecyclerAdapter<CulturalClubModel, CulturalClubAdapter.viewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    DatabaseReference databaseReference;

    public CulturalClubAdapter(@NonNull FirebaseRecyclerOptions<CulturalClubModel> options, Context applicationContext) {
        super(options);
        this.context=applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull CulturalClubAdapter.viewHolder holder, int position, @NonNull CulturalClubModel model) {

        databaseReference = FirebaseDatabase.getInstance().getReference("ClubDetails").child(model.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageurl = snapshot.child("ProfilePic").child("dp").getValue(String.class);
                Picasso.get()
                        .load(imageurl)
                        .into(holder.culturallogo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.culturalclubname.setText(model.getName());
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public CulturalClubAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.culturalclubdisplaysample,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView culturallogo;
        TextView culturalclubname;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            culturallogo = itemView.findViewById(R.id.culturallogo);
            culturalclubname = itemView.findViewById(R.id.clubname);
        }
    }
}
