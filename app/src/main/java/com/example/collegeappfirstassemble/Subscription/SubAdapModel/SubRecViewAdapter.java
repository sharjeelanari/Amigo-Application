package com.example.collegeappfirstassemble.Subscription.SubAdapModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SubRecViewAdapter extends FirebaseRecyclerAdapter<SubRecViewModel,SubRecViewAdapter.viewHolder> {


    public SubRecViewAdapter(@NonNull FirebaseRecyclerOptions<SubRecViewModel> options, Context applicationContext) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull SubRecViewModel model) {

        holder.companynameid.setText(model.getAddcompanyname());
        holder.screentype.setText(model.getAddscreentype());
        holder.devicetype.setText(model.getAdddevices());
        holder.mem_no_available.setText(model.getAdd_mem_no_available());
        holder.mem_no_needed.setText(model.getAdd_mem_needed());
        holder.priceofsub.setText(model.getAdd_priceofsub());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Payments Page is due", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_display_sample,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView companynameid;
        TextView screentype;
        TextView devicetype;
        TextView mem_available;
        TextView mem_no_available;
        TextView mem_needed;
        TextView mem_no_needed;
        TextView priceofsub;
        Button button;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

           companynameid=itemView.findViewById(R.id.companynameid);
           screentype=itemView.findViewById(R.id.screentype);
           devicetype=itemView.findViewById(R.id.devicetype);
           mem_available=itemView.findViewById(R.id.mem_available);
           mem_no_available=itemView.findViewById(R.id.mem_no_available);
           mem_needed=itemView.findViewById(R.id.mem_needed);
           mem_no_needed=itemView.findViewById(R.id.mem_no_needed);
           priceofsub=itemView.findViewById(R.id.priceofsub);
           button = itemView.findViewById(R.id.btnsubs);


        }
    }
}
