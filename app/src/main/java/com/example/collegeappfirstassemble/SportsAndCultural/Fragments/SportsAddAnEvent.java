package com.example.collegeappfirstassemble.SportsAndCultural.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.SportsAndCultural.AddEvent;
import com.example.collegeappfirstassemble.SportsAndCultural.SportsCultural.SportsMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SportsAddAnEvent extends Fragment {

    RadioGroup EventType,Streaming;
    EditText EventName,StartDate,Time, EndDate, MatchNo,Venue,Typename;
    Button AddEvnt;
    RadioButton Eventtypechecked,Streamchecked;
    AddEvent addEvent;

    DatabaseReference databaseReference;
    DatabaseReference db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports_add_an_event, container, false);

        EventType = (RadioGroup)view.findViewById(R.id.typeevnt);
        Streaming = (RadioGroup)view.findViewById(R.id.livestream);
        EventName = (EditText)view.findViewById(R.id.nameofevent);
        StartDate = (EditText)view.findViewById(R.id.date);
        Time = (EditText)view.findViewById(R.id.time);
        EndDate = (EditText)view.findViewById(R.id.dateend);
        MatchNo = (EditText)view.findViewById(R.id.matchno);
        Venue = (EditText)view.findViewById(R.id.venue);
        Typename = (EditText)view.findViewById(R.id.typename);
        AddEvnt = (Button)view.findViewById(R.id.addevnt);

        addEvent = new AddEvent();


        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

        AddEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Eventtypeid = EventType.getCheckedRadioButtonId();
                Eventtypechecked = (RadioButton)view.findViewById(Eventtypeid);

                int StreammId = Streaming.getCheckedRadioButtonId();
                Streamchecked = (RadioButton)view.findViewById(StreammId);

                String eventtypechecked = Eventtypechecked.getText().toString();
                String eventname = EventName.getText().toString();
                String startdate = StartDate.getText().toString();
                String time = Time.getText().toString();
                String enddate = EndDate.getText().toString();
                String matchno = MatchNo.getText().toString();
                String venue = Venue.getText().toString();
                String streaming = Streamchecked.getText().toString();
                String typename = Typename.getText().toString();

                if(eventtypechecked.trim().isEmpty() && eventname.trim().isEmpty() && startdate.trim().isEmpty()
                        && time.trim().isEmpty() && enddate.trim().isEmpty() && matchno.trim().isEmpty()
                        && venue.trim().isEmpty() && streaming.trim().isEmpty()){

                    Toast.makeText(getContext(), "Enter Required Info", Toast.LENGTH_LONG).show();

                }else{
                                databaseReference = FirebaseDatabase.getInstance().getReference("AddEvent").child(eventtypechecked)
                                .child(eventname);
                                db = FirebaseDatabase.getInstance().getReference("ClubDetails").child(user).child("Events");

                                addtodb(eventname, eventtypechecked, startdate, time, enddate, matchno,
                                        venue, streaming,typename);
                                Intent intent = new Intent(getContext(), SocialActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                    EventName.setText("");
                    StartDate.setText("");
                    Time.setText("");
                    EndDate.setText("");
                    MatchNo.setText("");
                    Venue.setText("");
                    EventType.clearCheck();
                    Streaming.clearCheck();
                    Typename.setText("");




//                                Toast.makeText(getContext(), "Event Scheduled", Toast.LENGTH_SHORT).show();



                }

            }
        });

        return view;
    }

    private void addtodb(String eventname, String eventType, String startdate, String time, String enddate, String matchno,
                            String venue, String streaming, String typename){
        addEvent.setEventName(eventname);
        addEvent.setEventType(eventType);
        addEvent.setStartDate(startdate);
        addEvent.setTime(time);
        addEvent.setEndDate(enddate);
        addEvent.setMatchNo(matchno);
        addEvent.setVENUE(venue);
        addEvent.setTypename(typename);
        addEvent.setStreamLive(streaming);

        String key = db.push().getKey();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.setValue(addEvent);
                    db.child(key).setValue(addEvent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}