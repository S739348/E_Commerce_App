package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.tailor.databinding.ColorActivityBinding;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tailor.R;
import com.example.tailor.model.ColorClass;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    Context context;
    ArrayList<ColorClass>list;

    public ColorAdapter(Context context, ArrayList<ColorClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(context).inflate(R.layout.color_activity,parent,false);
     return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ViewHolder holder, int position) {
        ColorClass colorClass=list.get(position);
        Glide.with(context).load(colorClass.getImage()).placeholder(R.drawable.men).into(holder.colorActivityBinding.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ColorActivityBinding colorActivityBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorActivityBinding=ColorActivityBinding.bind(itemView);

        }
    }
}
