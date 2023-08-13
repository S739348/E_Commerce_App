package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.HomeYouLikeBinding;
import com.example.tailor.model.ModelHomeYouLIke;

import java.util.ArrayList;

public class HomeYouLike extends RecyclerView.Adapter<HomeYouLike.ViewHolder> {
    Context context;
    ArrayList<ModelHomeYouLIke>list;

    public HomeYouLike(Context context, ArrayList<ModelHomeYouLIke> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeYouLike.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_you_like,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeYouLike.ViewHolder holder, int position) {
        ModelHomeYouLIke model=list.get(position);
        Glide.with(context).load(model.getImage()).placeholder(R.drawable.men).into(holder.binding.image);
        holder.binding.name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeYouLikeBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=HomeYouLikeBinding.bind(itemView);
        }
    }
}
