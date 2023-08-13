package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.ReviewLayoutBinding;
import com.example.tailor.model.ReviewModel;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    ArrayList<ReviewModel>list;
    ImageReviewAdapter imageReviewAdapter;
    //ArrayList<String>imageList=new ArrayList<>();


    public ReviewAdapter(Context context, ArrayList<ReviewModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.review_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        ReviewModel reviewModel=list.get(position);
        Glide.with(context).load(reviewModel.getProfileImage()).placeholder(R.drawable.baseline_person_24).into(holder.binding.profileImage);
         holder.binding.usernamereview.setText(reviewModel.getName());
         holder.binding.title.setText(reviewModel.getTitle());
         holder.binding.reviewdescription.setText(reviewModel.getDescription());
         holder.binding.ratings.setText(reviewModel.getRating());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        imageReviewAdapter=new ImageReviewAdapter(context,reviewModel.getItemImage());
        holder.binding.reviewImageRecycle.setLayoutManager(linearLayoutManager);
        holder.binding.reviewImageRecycle.setAdapter(imageReviewAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ReviewLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ReviewLayoutBinding.bind(itemView);
        }
    }
}
