package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ReachAdapter extends FirebaseRecyclerAdapter<ReachModel, RecyclerView.ViewHolder> {

    Context context;
    int send = 1;
    int recieve = 2;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReachAdapter(@NonNull FirebaseRecyclerOptions<ReachModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull ReachModel model) {


        if (holder.getClass() == viewHolder.class){
            viewHolder holder1 = (viewHolder) holder;

            holder1.sname.setText(model.getCurrentname());
            holder1.stime.setText(model.getTime());
            holder1.sendersText.setText(model.getSendertext());
        }else{
            recieverVh holder2 = (recieverVh) holder;

            holder2.rname.setText(model.getCurrentname());
            holder2.rtime.setText(model.getTime());
            holder2.recieversText.setText(model.getSendertext());
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == send){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_reaches_send, parent, false);
            return new viewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_reaches_recieve, parent, false);
            return new recieverVh(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        ReachModel reachModel = getItem(position);

        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(reachModel.getCurrentUid())){
            return send;
        }else {
            return recieve;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView sendersText, sname, stime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            sendersText = itemView.findViewById(R.id.sendertext);
            sname = itemView.findViewById(R.id.textsendname);
            stime = itemView.findViewById(R.id.timesend);

        }

    }

    public class recieverVh extends RecyclerView.ViewHolder{

        TextView recieversText,rname, rtime;
        public recieverVh(@NonNull View itemView) {
            super(itemView);
            recieversText = itemView.findViewById(R.id.recievertext);
            rname = itemView.findViewById(R.id.textrecievename);
            rtime = itemView.findViewById(R.id.timerecieve);
        }
    }
}
