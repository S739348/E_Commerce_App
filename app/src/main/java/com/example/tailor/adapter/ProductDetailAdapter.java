package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tailor.R;
import com.example.tailor.databinding.ActivityProductDetailBinding;
import com.example.tailor.databinding.DetailActivityBinding;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {
    Context context;
    ArrayList<String>list;

    public ProductDetailAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_activity,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.ViewHolder holder, int position) {
     String str=list.get(position);
     holder.binding.label.setText("*"+str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DetailActivityBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DetailActivityBinding.bind(itemView);
        }
    }
}
