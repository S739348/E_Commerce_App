package com.example.tailor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tailor.R;
import com.example.tailor.databinding.IteamCategoryBinding;
import com.example.tailor.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.iteam_category,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category=categories.get(position);

        holder.binding.label.setText(category.getName());

            if(position==0)
            Glide.with(context).load(category.getIcon()).placeholder(R.drawable.men).into(holder.binding.image);
            else if(position==1)
                Glide.with(context).load(category.getIcon()).placeholder(R.drawable.women).into(holder.binding.image);
            else
                Glide.with(context).load(category.getIcon()).placeholder(R.drawable.boy).into(holder.binding.image);


    }
        //  holder.binding.image.setBackgroundColor(Color.parseColor(category.getColor()));
        //    holder.binding.image.setImageBitmap(category.getIcon());

        // holder.binding.image.setImageBitmap(Bitmap.createBitmap(category.getIcon()));



    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        IteamCategoryBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = IteamCategoryBinding.bind(itemView);

        }
    }
}
