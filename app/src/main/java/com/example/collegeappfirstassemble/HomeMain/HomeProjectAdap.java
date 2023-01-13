package com.example.collegeappfirstassemble.HomeMain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.ProjectaddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.ViewPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeProjectAdap extends FirebaseRecyclerAdapter<ProjectaddpostModel, HomeProjectAdap.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public HomeProjectAdap(@NonNull FirebaseRecyclerOptions<ProjectaddpostModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull HomeProjectAdap.viewHolder holder, int position, @NonNull ProjectaddpostModel model) {
        holder.briefideaprotv.setText(model.getMoreidea());

        holder.needforprotv.setText(model.getSkills());

        holder.briefreq.setText(model.getOtherdet());

        holder.basedon.setText(model.getBasedon());
        String postid = getRef(position).getKey();


        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, ViewPost.class).putExtra("postid", postid));
            }
        });

    }

    @NonNull
    @Override
    public HomeProjectAdap.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_homeproject, parent, false);

        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView briefideaprotv, needforprotv, briefreq, basedon;
        CardView cd;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            cd = itemView.findViewById(R.id.cardhp);
            briefideaprotv = itemView.findViewById(R.id.briefideaprotvhp);
            needforprotv = itemView.findViewById(R.id.needforprotvhp);
            basedon = itemView.findViewById(R.id.protopicstvhp);
            briefreq = itemView.findViewById(R.id.briefrequirementhp);


        }
    }
}
