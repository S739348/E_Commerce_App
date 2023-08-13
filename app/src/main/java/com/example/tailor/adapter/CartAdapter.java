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
import com.example.tailor.databinding.CartItemBinding;
import com.example.tailor.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Product>productArrayList;

    public CartAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
Product product=productArrayList.get(position);
        Glide.with(context).load(product.getImages().get(0)).placeholder(R.drawable.men).into(holder.binding.image);
        holder.binding.lable.setText(product.getName());

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CartItemBinding.bind(itemView);
        }
    }
}
