package com.example.tailor.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tailor.adapter.CategoryAdapter;
import com.example.tailor.adapter.ProductAdapter;
import com.example.tailor.databinding.FragmentHomeBinding;
import com.example.tailor.model.Category;
import com.example.tailor.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product>products;
    FirebaseDatabase database;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database=FirebaseDatabase.getInstance();
        initCategory();
        initProduct();
        initSlider();
        return root;
    }
    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/glad-young-woman-jacket-smiling-camera-studio-shot-attractive-brunette-lady-isolated-pink-background_197531-13698.jpg?w=740&t=st=1687696209~exp=1687696809~hmac=4ce4a103d8331d88a513f6500ec3b994a16229abd4c2801cb617b20f664a3a5b","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/stylish-attractive-woman-with-brown-eyes-posing-portrait-cute-lady-tight-outfit_197531-10088.jpg?t=st=1687696209~exp=1687696809~hmac=054ff49320e6453668191de5266ee1976095949cb1675800c60e2cbe7336bbb3","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/cheerful-young-men-plaid-blue-shirts-white-t-shirts-colorful-pants-pose-orange-wall-great-mood-smile_197531-23466.jpg","Some caption is here"));

    }
    void initCategory(){
        categories=new ArrayList<>();
        categories.add(new Category("Men", " ","#FF000000","Some descreption",1));
        categories.add(new Category("Women","","#FF000000","Some descreption",1));
        categories.add(new Category("Boys", " ","#FF000000","Some descreption",1));
        categories.add(new Category("Women","","#FF000000","Some descreption",1));
        categories.add(new Category("Boys", " ","#FF000000","Some descreption",1));
        categories.add(new Category("Boys", " ","#FF000000","Some descreption",1));

        categoryAdapter =new CategoryAdapter(getContext(),categories);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);


    }

    @SuppressLint("SuspiciousIndentation")
    void initProduct(){
        products=new ArrayList<>();
        productAdapter=new ProductAdapter(getContext(),products);
        GridLayoutManager productlayoutmanager=new GridLayoutManager(getContext(),2);
      /*  productLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // Return 1 for regular items and 2 for header or footer items (if applicable)
                // Adjust this logic based on your item view types and positions
                return (position % 3 == 0) ? 2 : 1;
            }
        });*/
      //binding.productList.setLayoutManager(productLayoutManager);

        binding.productList.setLayoutManager(productlayoutmanager);
        binding.productList.setAdapter(productAdapter);
        database.getReference().child("Product").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                     Product product=dataSnapshot.getValue(Product.class);
                     products.add(product);
                 }
                 productAdapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
         //   products.add(new Product("Ass you want","image","","50","10","10","1"));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}