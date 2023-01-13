package com.example.collegeappfirstassemble.Attendance.AdapterAndModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddAllFieldsAdapter extends FirebaseRecyclerAdapter<StudentModel, AddAllFieldsAdapter.viewHolder> {

    Context context;
    String batch, USN, formatteddate;
    DatabaseReference attendanceRef, attendanceRef1;
    Calendar calendar;

    public AddAllFieldsAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull StudentModel model) {

        holder.showstudentname.setText(model.getAddstudentname());
        holder.showstudentusn.setText(model.getAddstudentusn());
        holder.Batch.setText(model.getAddBatch());

        batch = holder.Batch.getText().toString();
        USN = holder.showstudentusn.getText().toString();



        calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH");
        formatteddate = df.format(calendar.getTime());

        holder.attendanceStatus(batch, USN, formatteddate);

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceRef = FirebaseDatabase.getInstance().getReference(holder.Batch.getText().toString())
                        .child(formatteddate);
                attendanceRef1 = FirebaseDatabase.getInstance().getReference(holder.Batch.getText().toString())
                        .child(USN).child(formatteddate);


                attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(holder.showstudentusn.getText().toString())) {
                            attendanceRef.child(holder.showstudentusn.getText().toString()).child(holder.showstudentusn
                                    .getText().toString()).setValue("Present");

                            holder.present.setVisibility(View.INVISIBLE);
                            holder.absent.setVisibility(View.VISIBLE);

                        } else {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put(holder.showstudentusn.getText().toString(), "Present");
                            attendanceRef.child(holder.showstudentusn.getText().toString()).setValue(hashMap);

                            holder.present.setVisibility(View.INVISIBLE);
                            holder.absent.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceRef = FirebaseDatabase.getInstance().getReference(holder.Batch.getText().toString())
                        .child(formatteddate);
                attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(holder.showstudentusn.getText().toString())) {
                            attendanceRef.child(holder.showstudentusn.getText().toString()).child(holder
                                    .showstudentusn.getText().toString()).setValue("Absent");

                            holder.absent.setVisibility(View.INVISIBLE);
                            holder.present.setVisibility(View.VISIBLE);

                        } else {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put(holder.showstudentusn.getText().toString(), "Absent");
                            attendanceRef.child(holder.showstudentusn.getText().toString()).setValue(hashMap);

                            holder.absent.setVisibility(View.INVISIBLE);
                            holder.present.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplestudattendancedisplay,
                parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView showstudentname, showattper;
        TextView showstudentusn, Batch;
        CardView cardView;
        Button present, absent;
        DatabaseReference db;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Batch = itemView.findViewById(R.id.studbatch);

            showattper = itemView.findViewById(R.id.showattperc);
            showstudentname = itemView.findViewById(R.id.showstudentname);
            showstudentusn = itemView.findViewById(R.id.showstudentusn);
            present = itemView.findViewById(R.id.present);
            absent = itemView.findViewById(R.id.absent);
            cardView = itemView.findViewById(R.id.addstudentcdview);

        }

        public void attendanceStatus(final String batch, final String USN, final String formattedDate) {
            db = FirebaseDatabase.getInstance().getReference(batch).child(formattedDate);

            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChild(USN)) {
                        String status = snapshot.child(USN).child(USN).getValue().toString();

                        if (status.equals("Present")) {
                            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.present));
                            present.setVisibility(View.INVISIBLE);
                        }else{
                            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.absent));
                            absent.setVisibility(View.INVISIBLE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

         public void attendancePercentage(TextView percentage, TextView Attended){


         }
    }


}
