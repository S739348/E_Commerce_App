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
import com.example.tailor.databinding.ReviewImageBinding;

import java.util.ArrayList;

public class ImageReviewAdapter extends RecyclerView.Adapter<ImageReviewAdapter.ViewHolder> {
    Context context;
    ArrayList<String>list;

    public ImageReviewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.review_image,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageReviewAdapter.ViewHolder holder, int position) {
String str=list.get(position);
        Glide.with(context).load(str).into(holder.binding.reviewImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ReviewImageBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ReviewImageBinding.bind(itemView);
        }
    }
}
