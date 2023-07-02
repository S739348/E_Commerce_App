package com.example.tailor.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.widget.LinearLayout;

import com.example.tailor.R;
import com.example.tailor.adapter.ColorAdapter;
import com.example.tailor.adapter.MostViewAdapter;
import com.example.tailor.adapter.MostViewSecondAdapter;
import com.example.tailor.adapter.ProductDetailAdapter;
import com.example.tailor.adapter.SimilarAdapter;
import com.example.tailor.adapter.SizeAdapter;
import com.example.tailor.databinding.ActivityMainBinding;
import com.example.tailor.databinding.ActivityProductDetailBinding;
import com.example.tailor.model.ColorClass;
import com.example.tailor.model.Product;
import com.example.tailor.model.SizeModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
  ActivityProductDetailBinding  binding;
    ArrayList<Product>productArrayList;
    SimilarAdapter similarAdapter;

     ArrayList<String>detail;
  ProductDetailAdapter productDetailAdapter;
    ArrayList<SizeModel>sizeModels;
    SizeAdapter sizeAdapter;
    ArrayList<ColorClass>list;
    MostViewAdapter mostViewAdapter;
    MostViewSecondAdapter mostViewSecondAdapter;
  ColorAdapter colorAdapter;

  FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();

        setColor();
        setSize();
        setDetail();
        SetSimilar();

        //setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();

            if (extras != null) {
                String[] images = extras.getStringArray("Images");
                if (images != null && images.length > 0) {
                    for (String imageuri:images)
                        binding.carousel.addData(new CarouselItem(imageuri,""));
                    // Process the images array or perform any necessary operations
                } else {
                    // Handle case when images array is null or empty
                }
            }
        }


    }

    private void SetMostView(ArrayList<Product> li) {
        mostViewAdapter=new MostViewAdapter(this,li);
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
      binding.most.setAdapter(mostViewAdapter);
      binding.most.setLayoutManager(linearLayoutManager);
    mostViewSecondAdapter=new MostViewSecondAdapter(this,li);
    LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
    binding.most2.setAdapter(mostViewSecondAdapter);
    binding.most2.setLayoutManager(linearLayoutManager1);

    }

    private void SetSimilar() {
        productArrayList = new ArrayList<>();
        similarAdapter = new SimilarAdapter(this, productArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        binding.similarproduct.setLayoutManager(gridLayoutManager);
        binding.similarproduct.setAdapter(similarAdapter);

        database.getReference().child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear the productArrayList before adding new products
                productArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    productArrayList.add(product);
                }
                similarAdapter.notifyDataSetChanged();
                SetMostView(productArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event if needed
            }
        });

    }

    private void setDetail() {
        detail=new ArrayList<>();
        detail.add("Product Name: Men's Shirt");
        detail.add("Price: $29.99");
        detail.add("This stylish men's shirt is made from high-quality fabric and features a modern design. It is suitable for both casual and formal occasions.");
        detail.add("Size Options: Small, Medium, Large, XL");
        detail.add("Color Options: Black, White, Blue, Red");
        productDetailAdapter=new ProductDetailAdapter(this,detail);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.detail.setAdapter(productDetailAdapter);
        binding.detail.setLayoutManager(linearLayoutManager);

    }

    public  void setSize(){
     sizeModels=new ArrayList<>();
        SizeModel sizeClass=new SizeModel("https://img.freepik.com/free-photo/glad-young-woman-jacket-smiling-camera-studio-shot-attractive-brunette-lady-isolated-pink-background_197531-13698.jpg?w=740&t=st=1687696209~exp=1687696809~hmac=4ce4a103d8331d88a513f6500ec3b994a16229abd4c2801cb617b20f664a3a5b","XML");
        sizeModels.add(sizeClass);
        SizeModel sizeClass1=new SizeModel("https://img.freepik.com/free-photo/stylish-attractive-woman-with-brown-eyes-posing-portrait-cute-lady-tight-outfit_197531-10088.jpg?t=st=1687696209~exp=1687696809~hmac=054ff49320e6453668191de5266ee1976095949cb1675800c60e2cbe7336bbb3","L");
        sizeModels.add(sizeClass1);
        SizeModel sizeClass2=new SizeModel("https://img.freepik.com/free-photo/cheerful-young-men-plaid-blue-shirts-white-t-shirts-colorful-pants-pose-orange-wall-great-mood-smile_197531-23466.jpg","M");
        sizeModels.add(sizeClass2);

        sizeAdapter=new SizeAdapter(this,sizeModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        binding.size.setLayoutManager(linearLayoutManager);
        binding.size.setAdapter(sizeAdapter);

    }
    public  void setColor(){
        list=new ArrayList<>();
        ColorClass colorClass=new ColorClass("https://img.freepik.com/free-photo/glad-young-woman-jacket-smiling-camera-studio-shot-attractive-brunette-lady-isolated-pink-background_197531-13698.jpg?w=740&t=st=1687696209~exp=1687696809~hmac=4ce4a103d8331d88a513f6500ec3b994a16229abd4c2801cb617b20f664a3a5b");
        list.add(colorClass);
        ColorClass colorClass1=new ColorClass("https://img.freepik.com/free-photo/stylish-attractive-woman-with-brown-eyes-posing-portrait-cute-lady-tight-outfit_197531-10088.jpg?t=st=1687696209~exp=1687696809~hmac=054ff49320e6453668191de5266ee1976095949cb1675800c60e2cbe7336bbb3");
        list.add(colorClass1);
        ColorClass colorClass2=new ColorClass("https://img.freepik.com/free-photo/cheerful-young-men-plaid-blue-shirts-white-t-shirts-colorful-pants-pose-orange-wall-great-mood-smile_197531-23466.jpg");
        list.add(colorClass2);

        colorAdapter=new ColorAdapter(this,list);
        // GridLayoutManager linearLayoutManager=new GridLayoutManager(this,3);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this ,RecyclerView.HORIZONTAL,false);
        binding.color.setLayoutManager(linearLayoutManager);
        binding.color.setAdapter(colorAdapter);


    }

}