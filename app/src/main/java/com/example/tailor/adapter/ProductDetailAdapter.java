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
import com.example.tailor.databinding.ProductDetailItemBinding;
import com.example.tailor.model.ProductDetailModel;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductDetailModel>list;
    int itemCount;

    public ProductDetailAdapter(Context context, ArrayList<ProductDetailModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.ViewHolder holder, int position) {
     ProductDetailModel model=list.get(position);
     holder.binding.title.setText(model.getTitle());
     holder.binding.type.setText(model.getInfo());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ProductDetailItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ProductDetailItemBinding.bind(itemView);
        }
    }
    public void setItemCount(int count) {
        itemCount = count;
    }
}
