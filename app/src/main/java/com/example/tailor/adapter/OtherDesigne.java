package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.OtherDesigneBinding;
import com.example.tailor.model.ModelHomeYouLIke;
import com.example.tailor.model.Product;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class OtherDesigne extends RecyclerView.Adapter<OtherDesigne.ViewHolder> {
    ArrayList<ModelHomeYouLIke>list;
    Context context;

    public OtherDesigne(ArrayList<ModelHomeYouLIke> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OtherDesigne.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.other_designe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OtherDesigne.ViewHolder holder, int position) {
          ModelHomeYouLIke product = list.get(position);
        Glide.with(context).load(product.getImage()).placeholder(R.drawable.men).into(holder.binding.image1);
        Glide.with(context).load(product.getImage()).placeholder(R.drawable.men).into(holder.binding.image2);
        Glide.with(context).load(product.getImage()).placeholder(R.drawable.men).into(holder.binding.image3);
        Glide.with(context).load(product.getImage()).placeholder(R.drawable.men).into(holder.binding.image4);
        holder.binding.name.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        OtherDesigneBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=OtherDesigneBinding.bind(itemView);
        }
    }
}
