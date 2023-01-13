package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter.SocialActivityAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HomemainFragment extends Fragment {

    RecyclerView socialrecyclrviewid ;
    SocialActivityAdapter socialActivityAdapter;
    ContentLoadingProgressBar progressBar;

    DatabaseReference db;

    SocialToolbarFragment socialToolbarFragment;
    ArrayList<SocialAddpostModel> list;


    //empty constructor
    public  HomemainFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homemain, container, false);


        socialrecyclrviewid = view.findViewById(R.id.socialrecyclerviewid);
        socialToolbarFragment = new SocialToolbarFragment();

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.toolbarsoicalhome, socialToolbarFragment).commit();



        db = FirebaseDatabase.getInstance().getReference("SocialPosts");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
        layoutManager.setStackFromEnd(true);
        socialrecyclrviewid.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<SocialAddpostModel> options = new FirebaseRecyclerOptions.Builder<SocialAddpostModel>()
                .setQuery(db, SocialAddpostModel.class).build();

        socialActivityAdapter = new SocialActivityAdapter(options, getContext());
        socialrecyclrviewid.setAdapter(socialActivityAdapter);


//        progressBar.setVisibility(View.GONE);
//        socialrecyclrviewid.setVisibility(View.VISIBLE);

//        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(0);
//        socialrecyclrviewid.addItemDecoration(spacingItemDecorator);
//
//        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        socialrecyclrviewid.setLayoutManager(linearLayoutManager);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        socialActivityAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        socialActivityAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        socialActivityAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        socialActivityAdapter.stopListening();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        socialActivityAdapter.startListening();
    }
}
