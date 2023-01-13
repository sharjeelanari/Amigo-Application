package com.example.collegeappfirstassemble.AdminPanel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RemoveSocialPost extends Fragment {
    RemoveSocialAdapter removeSocialAdapter;
    DatabaseReference db;
    public RemoveSocialPost() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_remove_social_post, container, false);

        recyclerView = v.findViewById(R.id.removesocialpost);

        db = FirebaseDatabase.getInstance().getReference("SocialPosts");

        FirebaseRecyclerOptions<SocialAddpostModel> options = new FirebaseRecyclerOptions.Builder<SocialAddpostModel>()
                .setQuery(db, SocialAddpostModel.class).build();

        removeSocialAdapter = new RemoveSocialAdapter(options, getContext(), 1);

        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setStackFromEnd(true);

        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(removeSocialAdapter);


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        removeSocialAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        removeSocialAdapter.stopListening();
    }
}