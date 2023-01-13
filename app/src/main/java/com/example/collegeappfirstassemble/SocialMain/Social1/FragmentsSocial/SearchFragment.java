package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel.ProfileModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.SearchComponents.SearchRecyclerAdap;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference userdb;
    SearchRecyclerAdap searchRecyclerAdap;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclersearch);
        searchView = view.findViewById(R.id.socialsearch);

        userdb = FirebaseDatabase.getInstance().getReference("SearchProfile");

        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>()
                .setQuery(userdb, ProfileModel.class).build();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchRecyclerAdap = new SearchRecyclerAdap(options, getContext());
        recyclerView.setAdapter(searchRecyclerAdap);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
    private void search(String s){
        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("SearchProfile").orderByChild("name")
                        .startAt(s).endAt(s+"\uf8ff"), ProfileModel.class).build();

        searchRecyclerAdap = new SearchRecyclerAdap(options,getContext());
        searchRecyclerAdap.startListening();
        recyclerView.setAdapter(searchRecyclerAdap);

    }
}