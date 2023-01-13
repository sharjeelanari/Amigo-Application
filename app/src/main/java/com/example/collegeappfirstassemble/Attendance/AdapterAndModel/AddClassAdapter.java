package com.example.collegeappfirstassemble.Attendance.AdapterAndModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.Attendance.Attendanceactivity;
import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddClassAdapter extends FirebaseRecyclerAdapter<BatchModel,AddClassAdapter.viewHolder> {

    Context context;

    public AddClassAdapter(@NonNull FirebaseRecyclerOptions<BatchModel> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull BatchModel model) {


        holder.showbatch.setText(model.getAddbatch());
       // holder.show.setText(model.getAddsem());

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String batch = holder.showbatch.getText().toString();
                Intent intent=new Intent(context, Attendanceactivity.class);
                intent.putExtra("Batch",batch);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleaddclassdisplay,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView showbatch;
        TextView showsubject;
        FrameLayout frameLayout;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            showbatch=itemView.findViewById(R.id.showbatch);
           // showsubject=itemView.findViewById(R.id.shows);

            frameLayout=itemView.findViewById(R.id.classfmlayout);

        }
    }
}
