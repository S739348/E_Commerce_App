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
import com.example.tailor.databinding.YoumayLikeBinding;
import com.example.tailor.detail.ProductDetail;
import com.example.tailor.model.Product;
import java.util.ArrayList;
import java.util.List;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder> {
    Context context;
    ArrayList<Product>productArrayList;

    public SimilarAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public SimilarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.youmay_like,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAdapter.ViewHolder holder, int position) {
Product product=productArrayList.get(position);
        holder.youmayLikeBinding.lable.setText(product.getName());
        holder.youmayLikeBinding.price.setText("₹"+product.getPrice());
        holder.youmayLikeBinding.discount.setText(product.getDiscount()+"%Off");

        Glide.with(context).load(product.getImages().get(0)).placeholder(R.drawable.women).into(holder.youmayLikeBinding.image);
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
        if (productArrayList == null) {
            return 0;
        }
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        YoumayLikeBinding youmayLikeBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            youmayLikeBinding =YoumayLikeBinding.bind(itemView);
        }
    }
}
