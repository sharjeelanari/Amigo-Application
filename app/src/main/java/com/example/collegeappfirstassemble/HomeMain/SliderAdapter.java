package com.example.collegeappfirstassemble.HomeMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegeappfirstassemble.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.viewHolder>{

    private List<SliderModel> sliderModels;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderModel> sliderModels, ViewPager2 viewPager2) {
        this.sliderModels = sliderModels;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.sample_slider_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

//        Picasso.get().load(sliderModels.get(position).getImage()).into(holder.roundedImageView);

        holder.setRoundedImageView(sliderModels.get(position));
        if (position == sliderModels.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView roundedImageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.slideimg);


        }

        void setRoundedImageView(SliderModel sliderModel) {
            roundedImageView.setImageResource(sliderModel.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderModels.addAll(sliderModels);
            notifyDataSetChanged();
        }
    };
}
