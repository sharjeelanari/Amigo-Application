package com.example.collegeappfirstassemble.SocialMain.Social1.SearchComponents;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ProfileModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage.ProfilePage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchRecyclerAdap extends FirebaseRecyclerAdapter<ProfileModel, SearchRecyclerAdap.viewHolder> {
    Context context;
    DatabaseReference db;

//    UserFragment userFragment = new UserFragment();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SearchRecyclerAdap(@NonNull FirebaseRecyclerOptions<ProfileModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull ProfileModel model) {
        holder.name.setText(model.getName());

        String uid = model.getUid();
        String name = model.getName();

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, ProfilePage.class);
                intent.putExtra("username", name);
                intent.putExtra("userid", uid);
                context.startActivity(intent);

//                db = FirebaseDatabase.getInstance().getReference("Users").child(name);
//                db.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                       String uid = snapshot.child("uid").getValue(String.class);
//                       intent.putExtra("uid", uid);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


//


//                SearchFragment searchFragment = new SearchFragment();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("username", name);
//                searchFragment.setArguments(bundle);
//
//                searchFragment.getParentFragmentManager().beginTransaction().replace(R.id.rel, userFragment).commit();

            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_search_recycler, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout relativeLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userNamesearch);
            relativeLayout = itemView.findViewById(R.id.relativesearch);
        }
    }
}