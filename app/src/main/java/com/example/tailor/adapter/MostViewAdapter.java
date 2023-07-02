package com.example.tailor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.MostSaleBinding;
import com.example.tailor.detail.ProductDetail;
import com.example.tailor.model.Product;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MostViewAdapter extends RecyclerView.Adapter<MostViewAdapter.ViewHolder> {
Context context;
ArrayList<Product>products;

    public MostViewAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MostViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.most_sale,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewAdapter.ViewHolder holder, int position) {
Product product=products.get(position);
holder.mostSaleBinding.lable.setText(product.getName());
holder.mostSaleBinding.price.setText("RS "+product.getPrice());
        holder.mostSaleBinding.discount.setText(product.getDiscount()+" %Off");

        Glide.with(context).load(product.getImages().get(0)).placeholder(R.drawable.women).into(holder.mostSaleBinding.image);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navigateToProductDetail(product);
        }
    });
    }
    private void navigateToProductDetail(Product product) {
        Intent intent=new Intent(context, ProductDetail.class);
        intent.putExtra("lable",product.getName());
        intent.putExtra("Price",product.getPrice());
        intent.putExtra("Status",product.getStatus());
        intent.putExtra("Discount",product.getDiscount());
        List<String> images = product.getImages();
        String[] imageArray = images.toArray(new String[images.size()]);
        intent.putExtra("Images", imageArray);


        context.startActivity(intent);

    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MostSaleBinding mostSaleBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mostSaleBinding=MostSaleBinding.bind(itemView);
        }
    }

}
