package com.example.collegeappfirstassemble.HomeMain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.EliteDivision.EliteDivisionActivity;
import com.example.collegeappfirstassemble.EliteDivision.FreelanceActivity;
import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.Subscription.SubscriptionActivity;
import com.example.collegeappfirstassemble.TechClubs.TechClubsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;

public class QuickLinksAdap extends FirebaseRecyclerAdapter<QuickLinksModel, QuickLinksAdap.viewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;

    public QuickLinksAdap(@NonNull FirebaseRecyclerOptions<QuickLinksModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickLinksAdap.viewHolder holder, int position, @NonNull QuickLinksModel model) {
        holder.head.setText(model.getHead());
        holder.body.setText(model.getBody());


        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = model.getContext();
               if (to.equals("freelance")){
                   context.startActivity(new Intent(context, FreelanceActivity.class));
               }else if (to.equals("subscription")){
                   context.startActivity(new Intent(context, SubscriptionActivity.class));
               }else if (to.equals("elite")){
                   context.startActivity(new Intent(context, EliteDivisionActivity.class));
               }else if (to.equals("home")){
                   context.startActivity(new Intent(context, HomePage.class));
               }else if (to.equals("social")){
                   context.startActivity(new Intent(context, SocialActivity.class));
               }else if (to.equals("techclubs")){
                   context.startActivity(new Intent(context, TechClubsActivity.class));

               }else{

               }

            }
        });

    }

    @NonNull
    @Override
    public QuickLinksAdap.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_quicklinks, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView head, body;
        CardView cd;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cd = itemView.findViewById(R.id.cardql);
            head = itemView.findViewById(R.id.headquicklink);
            body = itemView.findViewById(R.id.bodyquicklink);
        }
    }
}
