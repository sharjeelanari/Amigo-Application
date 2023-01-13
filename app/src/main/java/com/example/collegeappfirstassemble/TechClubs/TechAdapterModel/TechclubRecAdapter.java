package com.example.collegeappfirstassemble.TechClubs.TechAdapterModel;

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
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ProfilePage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TechclubRecAdapter extends FirebaseRecyclerAdapter<TechclubModel, TechclubRecAdapter.viewHolder> {


    Context context;


    public TechclubRecAdapter(@NonNull FirebaseRecyclerOptions<TechclubModel> options, Context applicationContext) {
        super(options);
        this.context = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull TechclubModel model) {


        holder.clubname.setText(model.getName());
//        holder.leadname.setText(model.getLead());

        holder.cdview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String techcl1 = model.getName();

                Intent intent = new Intent(context, ProfilePage.class);
                //clubname is the key here and techclubmodel is the value which we want to show there.
                intent.putExtra("username", techcl1);
                intent.putExtra("userid", model.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tcsampledisplaylayout, parent, false);
        return new viewHolder(view);


    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView clubname;
//        TextView leadname;
        CardView cdview;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            clubname = itemView.findViewById(R.id.clubname);
//            leadname = itemView.findViewById(R.id.leadname);
            cdview = itemView.findViewById(R.id.cdview);

        }
    }
}
