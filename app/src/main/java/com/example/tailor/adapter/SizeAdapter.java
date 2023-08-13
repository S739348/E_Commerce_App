package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.SizetogetBinding;
import com.example.tailor.model.SizeModel;

import java.util.ArrayList;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {
    Context context;
    ArrayList<SizeModel>list;

    public SizeAdapter(Context context, ArrayList<SizeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.sizetoget,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.ViewHolder holder, int position) {
    SizeModel model=list.get(position);
    holder.sizetogetBinding.label.setText(model.getSize());
       // Glide.with(context).load(model.getImage()).placeholder(R.drawable.men).into(holder.sizetogetBinding.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SizetogetBinding sizetogetBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sizetogetBinding=SizetogetBinding.bind(itemView);
        }
    }
}
