package com.example.tailor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.IteamProductBinding;
import com.example.tailor.detail.ProductDetail;
import com.example.tailor.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(context).inflate(R.layout.iteam_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=products.get(position);
        holder.binding.lable.setText(product.getName());
        holder.binding.price.setText("₹"+product.getPrice());
        holder.binding.discount.setText(product.getDiscount()+"%Off");
      //  Picasso.get().load(product.getImages().get(0)).placeholder(R.drawable.men).into(holder.binding.image);
        Glide.with(context).load(product.getImages().get(0)).placeholder(R.drawable.men).into(holder.binding.image);

     /*   for (String imageUrl : product.getImages()) {
            holder.binding.image.addData(new CarouselItem(imageUrl,""));
           }
*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override

           public void onClick(View view) {
               navigateToProductDetail(product);
           }
       });
    /*   holder.binding.image.setCarouselListener(new CarouselListener() {
           @Nullable
           @Override
           public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
               return null;
           }

           @Override
           public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

           }

           @Override
           public void onClick(int i, @NonNull CarouselItem carouselItem) {
               navigateToProductDetail(product);

           }

           @Override
           public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

           }
       });
*/

    }

    private void navigateToProductDetail(Product product) {
        Intent intent=new Intent(context, ProductDetail.class);
        intent.putExtra("lable",product.getName());
        intent.putExtra("Price",product.getPrice());
        intent.putExtra("Status",product.getStatus());
        intent.putExtra("Category",product.getItemCategory());
        intent.putExtra("Discount",product.getDiscount());
        List<String> images = product.getImages();
        String[] imageArray = images.toArray(new String[images.size()]);
        intent.putExtra("Images", imageArray);
         intent.putExtra("node",product.getKey());
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        IteamProductBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=IteamProductBinding.bind(itemView);
        }
    }
}
