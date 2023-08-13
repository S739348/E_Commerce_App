package com.example.tailor.detail;

import static androidx.fragment.app.FragmentManager.TAG;
import static androidx.navigation.ActivityKt.findNavController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tailor.MainActivity;
import com.example.tailor.R;
import com.example.tailor.adapter.ColorAdapter;
import com.example.tailor.adapter.ProductDetailAdapter;
import com.example.tailor.adapter.ReviewAdapter;
import com.example.tailor.adapter.SimilarAdapter;
import com.example.tailor.adapter.SizeAdapter;
import com.example.tailor.databinding.ActivityProductDetailBinding;
import com.example.tailor.model.CartModel;
import com.example.tailor.model.ColorClass;
import com.example.tailor.model.Product;
import com.example.tailor.model.ProductDetailModel;
import com.example.tailor.model.ReviewModel;
import com.example.tailor.model.SizeModel;
import com.example.tailor.ui.dashboard.DashboardFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
  ActivityProductDetailBinding  binding;
    ArrayList<Product>productArrayList;
    SimilarAdapter similarAdapter;

     ArrayList<String>detail;
     ArrayList<ProductDetailModel>detailModelsl1;
    ArrayList<ProductDetailModel>detailModelsl2;
  ProductDetailAdapter productDetailAdapter;
    ProductDetailAdapter productDetailAdapter2;
    ArrayList<SizeModel>sizeModels;
    ArrayList<ReviewModel>reviewModels;
    ReviewAdapter reviewAdapter;
    SizeAdapter sizeAdapter;
    ArrayList<ColorClass>list;

   public String images[];
  ColorAdapter colorAdapter;
  FirebaseDatabase database;
    String Category,node;
    boolean execute;
    boolean check=false,reviewCheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        setColor();
        setSize();
        setDetail();
        setReview();
        Intent intent = getIntent();
        Category=intent.getStringExtra("Category");
        node=intent.getStringExtra("node");
        execute=intent.getBooleanExtra("execute",false);
        if(execute){
            database.getReference().child("Product").child("men").child(Category).child(node).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Product product=snapshot.getValue(Product.class);
                    for(String uri:product.getImages())
                    binding.carousel.addData(new CarouselItem(uri,""));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        Toast.makeText(this, Category, Toast.LENGTH_SHORT).show();
        if(Category!=null){
            SetSimilar(Category);
        }

        if (intent != null) {
            Bundle extras = intent.getExtras();

            if (extras != null) {
              //  String[]
                images = extras.getStringArray("Images");
                if (images != null && images.length > 0) {
                    for (String imageuri:images)
                        binding.carousel.addData(new CarouselItem(imageuri,""));
                    // Process the images array or perform any necessary operations
                } else {
                    // Handle case when images array is null or empty
                }
            }
        }








        binding.viewfulldetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFullDetail();
            }
        });
        binding.viewfullbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHiddeDetail();
            }
        });

        binding.reviewfulldetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReviewFull();
            }
        });
        binding.reviewfullbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReviewHide();
            }
        });
        binding.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                intent.putExtra("navigate_to_dashboard", true);
                startActivity(intent);
                finishAffinity();
            }
        });
        binding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductDetail.this,MainActivity.class);
                intent.putExtra("cart",true);
                startActivity(intent);
            }
        });

        //setContentView(R.layout.activity_product_detail);
        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DynamicLink dynamicLink= FirebaseDynamicLinks.getInstance().createDynamicLink()
                        .setLink(Uri.parse("https://www.google.com")).setDynamicLinkDomain("tailoradmin.page.link")
                        .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build()).buildDynamicLink();
                Uri dynamicLinkUri=dynamicLink.getUri();
        //        Toast.makeText(ProductDetail.this, ""+dynamicLinkUri, Toast.LENGTH_SHORT).show();
                  String sharelink="https://tailoradmin.page.link/?"+
                          "link=https://www.google.com/"+"_"+Category+"_"+node+
                          "&apn="+getPackageName()+
                          "&st="+"My referal"+
                          "&sd="+"reward coin"+
                          "&si="+"https://m.media-amazon.com/images/I/71NbeYK99UL._AC_UY350_.jpg";
              //  Toast.makeText(ProductDetail.this, images[0], Toast.LENGTH_SHORT).show();
                Log.e("check",images[0]);
                Task<ShortDynamicLink> shortLinkTask =FirebaseDynamicLinks.getInstance().createDynamicLink().
                      //  setLongLink(dynamicLinkUri).
                              setLongLink(Uri.parse(sharelink)).
                        buildShortDynamicLink().addOnCompleteListener(new OnCompleteListener<ShortDynamicLink>() {
                            @Override
                            public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                                if (task.isSuccessful()) {
                                    // Short link created
                                    Uri shortLink = task.getResult().getShortLink();
                                   // Toast.makeText(ProductDetail.this, ""+shortLink, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,shortLink.toString());
                    intent.setType("text/plane");
                    startActivity(intent);
                                    Uri flowchartLink = task.getResult().getPreviewLink();
                                } else {
                                    // Error
                                    // ...
                                }
                            }
                        });

            }
        });


    }

private  void addToCart(){
    CartModel cartModel=new CartModel("men",Category,node);
        database.getReference().child("7803841753").child(node).setValue(cartModel);
       Toast.makeText(this, "Successfully added to cart", Toast.LENGTH_SHORT).show();
}
    private void SetMostView(ArrayList<Product> li) {
     /*   mostViewAdapter=new MostViewAdapter(this,li);
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
      binding.most.setAdapter(mostViewAdapter);
      binding.most.setLayoutManager(linearLayoutManager);
    mostViewSecondAdapter=new MostViewSecondAdapter(this,li);
    LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
    binding.most2.setAdapter(mostViewSecondAdapter);
    binding.most2.setLayoutManager(linearLayoutManager1);*/
        similarAdapter = new SimilarAdapter(this, li);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        binding.most.setAdapter(similarAdapter);
        binding.most.setLayoutManager(gridLayoutManager);

    }

    private void SetSimilar(String Category) {
        productArrayList = new ArrayList<>();
        similarAdapter = new SimilarAdapter(this, productArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        binding.similarproduct.setLayoutManager(gridLayoutManager);
        binding.similarproduct.setAdapter(similarAdapter);

            database.getReference().child("Product").child("men").child(Category).addValueEventListener(new ValueEventListener() {
                // Rest of your code
         //   });
     //   }

       // database.getReference().child("Product").child("men").child(Category).addValueEventListener(new ValueEventListener() {
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
        });}

    private void setReview(){
reviewModels=new ArrayList<>();
ArrayList<String>list=new ArrayList<>();
list.add("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
        list.add("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
        list.add("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80");

          reviewModels.add(new ReviewModel("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80",
        "Sourabh Patel","Good item",
        " product description is a form of marketing copy used to describe " +
                "and explain the benefits of your product. In other words, it provides all " +
                "the information and details of your product on your ecommerce site. These product details" +
                " can be one sentence, a short paragraph or bulleted. They can be serious, funny or quirky.","4.5",list));
             reviewAdapter=new ReviewAdapter(this,reviewModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.userReview.setAdapter(reviewAdapter);
        binding.userReview.setLayoutManager(linearLayoutManager);

    }

    private void setDetail() {
        detailModelsl1=new ArrayList<>();
        detailModelsl1.add(new ProductDetailModel("OCCASION","Casual Wear"));
        detailModelsl1.add(new ProductDetailModel("COLOR","Black"));
        detailModelsl1.add(new ProductDetailModel("MATERIAL","Cotton"));
        detailModelsl1.add(new ProductDetailModel("TYPE","Polo-T-Shirt"));
        detailModelsl1.add(new ProductDetailModel("NECK","Polo Neck"));
        detailModelsl1.add(new ProductDetailModel("SLEEVES","Short Sleeves"));
        productDetailAdapter=new ProductDetailAdapter(this,detailModelsl1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.producthighlightitem1.setLayoutManager(linearLayoutManager);
        binding.producthighlightitem1.setAdapter(productDetailAdapter);

        detailModelsl2=new ArrayList<>();
        detailModelsl2.add(new ProductDetailModel("PRINT AND PATTERN","Solid"));
        detailModelsl2.add(new ProductDetailModel("NO OF PCS","1"));
        detailModelsl2.add(new ProductDetailModel("RETURN POLICY","7 Days"));
        detailModelsl2.add(new ProductDetailModel("BRAND NAME","Zara"));
        detailModelsl2.add(new ProductDetailModel("COUNTRY OF ORIGIN","INDIA"));
        detailModelsl2.add(new ProductDetailModel("LENGTH","63.5"));
        productDetailAdapter2=new ProductDetailAdapter(this,detailModelsl2);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        binding.producthighlightitem2.setLayoutManager(linearLayoutManager1);
        binding.producthighlightitem2.setAdapter(productDetailAdapter2);
        if(check==false){
            animateRecyclerViewExpansion(productDetailAdapter, 3);
            animateRecyclerViewExpansion(productDetailAdapter2, 3);

            }
        else {
            animateRecyclerViewExpansion(productDetailAdapter, 6);
            animateRecyclerViewExpansion(productDetailAdapter2, 6);

        }
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
    private void setFullDetail(){
        binding.viewfullbind.setVisibility(View.VISIBLE);
        binding.viewfulldetail.setVisibility(View.GONE);
        check=true;
        setDetail();
    }
    private void setHiddeDetail(){
        binding.viewfullbind.setVisibility(View.GONE);
        binding.viewfulldetail.setVisibility(View.VISIBLE);
        check=false;
        setDetail();
    }
    private void setReviewFull(){
        binding.reviewfullbind.setVisibility(View.VISIBLE);
        binding.reviewfulldetail.setVisibility(View.GONE);
        reviewCheck=true;
        setReview();
    }
    private void setReviewHide(){
        binding.reviewfullbind.setVisibility(View.GONE);
        binding.reviewfulldetail.setVisibility(View.VISIBLE);
        reviewCheck=false;
        setReview();
    }


    private void animateRecyclerViewExpansion(ProductDetailAdapter adapter, int targetItemCount) {
        ValueAnimator animator = ValueAnimator.ofInt(adapter.getItemCount(), targetItemCount);
        int val=targetItemCount*100;
        animator.setDuration(val); // Set the animation duration in milliseconds
        animator.addUpdateListener(animation -> {
            int itemCount = (int) animation.getAnimatedValue();
            adapter.setItemCount(itemCount);
            adapter.notifyDataSetChanged();
        });
        animator.start();
    }

}