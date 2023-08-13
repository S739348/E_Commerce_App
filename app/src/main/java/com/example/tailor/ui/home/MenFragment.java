package com.example.tailor.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tailor.adapter.CategoryAdapter;
import com.example.tailor.adapter.HomeYouLike;
import com.example.tailor.adapter.OtherDesigne;
import com.example.tailor.adapter.ProductAdapter;
import com.example.tailor.adapter.SimilarAdapter;
import com.example.tailor.databinding.FragmentMenBinding;
import com.example.tailor.model.Category;
import com.example.tailor.model.ModelHomeYouLIke;
import com.example.tailor.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MenFragment extends Fragment {

    private FragmentMenBinding binding;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;
    SimilarAdapter similarAdapter;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products;
    ArrayList<ModelHomeYouLIke>list;
    HomeYouLike  homeYouLikeAdapter;
    OtherDesigne otherDesigneAdapter;
    OtherDesigne adapter;
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        products = new ArrayList<>();

        // Initialize Firebase database
        database = FirebaseDatabase.getInstance();
        initCategory();
        //initProduct();
        initSlider();
        initCasualTrousers();
        initFormalShirts();
        initsteal();
        initKurtas();
        initFormalTrousers();
        initEthnicWearSets();
        initCasualShits();
       // setHomeYouLike();

        return rootView;
    }

  /*  private void setOther(ArrayList<Product>products) {
        otherDesigneAdapter=new OtherDesigne(products,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.others.setLayoutManager(layoutManager);
        binding.others.setAdapter(otherDesigneAdapter);

    }*/

    private void setHomeYouLike() {
        list=new ArrayList<>();

        list.add(new ModelHomeYouLIke("https://m.media-amazon.com/images/I/71O1QaI-sbL._UY741_.jpg","T-Shirts"));
        list.add(new ModelHomeYouLIke("https://thehouseofrare.com/cdn/shop/products/HERO_eecd85c1-c3fe-4ff2-a356-4e2eaf4a6244_765x.jpg?v=1668780210","Trousers"));
        list.add(new ModelHomeYouLIke("https://images1.yosari.com/88836-thickbox_default/black-exclusive-readymade-indo-western-style-kurta-pajama.jpg","Ethings Sets"));
        list.add(new ModelHomeYouLIke("https://imagescdn.planetfashion.in/img/app/product/6/664420-6812170.jpg?auto=format","Shirts"));
        list.add(new ModelHomeYouLIke("https://www.bonsoir.co.in/cdn/shop/products/A-112-3_large.jpg?v=1611295827","Men Suits"));
        list.add(new ModelHomeYouLIke("https://images1.yosari.com/91303-thickbox_default/sea-green-designer-mens-wear-jacquard-kurta-pajama.jpg","Kurtas"));
       list.add(new ModelHomeYouLIke( "https://m.media-amazon.com/images/I/51Xrnmm4eOL._UX679_.jpg ","Trousers"));
        list.add(new ModelHomeYouLIke("https://m.media-amazon.com/images/I/71NbeYK99UL._AC_UY350_.jpg","Casual-Shirts"));
        list.add(new ModelHomeYouLIke("https://3.imimg.com/data3/SE/WJ/MY-1643354/fancy-islamic-men-clothes.jpg","Night-Suit"));

        homeYouLikeAdapter =new HomeYouLike(getContext(),list);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
       // binding.grid.setAdapter(homeYouLikeAdapter);
        //binding.grid.setLayoutManager(gridLayoutManager);
        adapter=new OtherDesigne(list,getContext());
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext());
        binding.others.setAdapter(adapter);
        binding.others.setLayoutManager(layoutManager1);

    }

    private void initCasualTrousers() {
        ArrayList<Product>products=new ArrayList<>();
        products=initdatabase("casualtrousers",products);
        productAdapter=new ProductAdapter(getContext(),products);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
        binding.CasualTrousers.setLayoutManager(layoutManager);
        binding.CasualTrousers.setAdapter(productAdapter);


    }

        private void initFormalShirts() {
            ArrayList<Product>products=new ArrayList<>();
            products=initdatabase("formalshirts",products);
            productAdapter=new ProductAdapter(getContext(),products);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.FormalShirts.setLayoutManager(layoutManager);
            binding.FormalShirts.setAdapter(productAdapter);

        }


        private void initKurtas() {
            ArrayList<Product>products=new ArrayList<>();

            products=initdatabase("kurtas",products);
            productAdapter=new ProductAdapter(getContext(),products);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.Kurtas.setLayoutManager(layoutManager);
            binding.Kurtas.setAdapter(productAdapter);
           // setOther(products);

        }


        private void initFormalTrousers() {
            ArrayList<Product>products=new ArrayList<>();

            products=initdatabase("formaltrousers",products);

            productAdapter=new ProductAdapter(getContext(),products);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.FormalTrousers.setLayoutManager(layoutManager);
            binding.FormalTrousers.setAdapter(productAdapter);

        }


        private void initEthnicWearSets() {
            ArrayList<Product>products=new ArrayList<>();

            products=initdatabase("ethnicwearsets",products);

            productAdapter=new ProductAdapter(getContext(),products);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.EthnicWearSets.setLayoutManager(layoutManager);
            binding.EthnicWearSets.setAdapter(productAdapter);

        }

        private void initCasualShits() {
            ArrayList<Product>products=new ArrayList<>();
            products=initdatabase("casualshits",products);



            productAdapter=new ProductAdapter(getContext(),products);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.CasualShit.setLayoutManager(layoutManager);
            binding.CasualShit.setAdapter(productAdapter);


        }

        private void initsteal() {

            ArrayList<Product>products=new ArrayList<>();
            products=initdatabase("casualtrousers",products);
           // similarAdapter=new SimilarAdapter(getContext(),products);
           productAdapter=new ProductAdapter(getContext(),products);
           LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
            binding.stealdeal.setLayoutManager(layoutManager);
            binding.stealdeal.setAdapter(productAdapter);

        }
    private void initSlider() {
      /* binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/glad-young-woman-jacket-smiling-camera-studio-shot-attractive-brunette-lady-isolated-pink-background_197531-13698.jpg?w=740&t=st=1687696209~exp=1687696809~hmac=4ce4a103d8331d88a513f6500ec3b994a16229abd4c2801cb617b20f664a3a5b","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/stylish-attractive-woman-with-brown-eyes-posing-portrait-cute-lady-tight-outfit_197531-10088.jpg?t=st=1687696209~exp=1687696809~hmac=054ff49320e6453668191de5266ee1976095949cb1675800c60e2cbe7336bbb3","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-photo/cheerful-young-men-plaid-blue-shirts-white-t-shirts-colorful-pants-pose-orange-wall-great-mood-smile_197531-23466.jpg","Some caption is here"));
*/
      binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/flyer-design-items-clothing-shopping-600w-1780534835.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/sale-banner-template-design-discount-600w-1071395273.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/clothing-fashion-accessories-beauty-promotional-600w-1190458771.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/big-sale-banner-discounts-vector-600w-311461589.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-photo/female-stylist-near-rack-modern-600w-2135889599.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-photo/banner-horizontal-crop-text-background-600w-551997871.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/ad-banner-design-kids-clothes-600w-2191568211.jpg","Some caption is here"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/summer-sale-layout-banner-vector-600w-1111396808.jpg","Some caption is here"));
       // binding.carousel.addData(new CarouselItem("","Some caption is here"));
      //  binding.carousel.addData(new CarouselItem("","Some caption is here"));

    }

    void initCategory(){
        categories=new ArrayList<>();
        categories.add(new Category("Shirts", "https://m.media-amazon.com/images/I/71NbeYK99UL._AC_UY350_.jpg","#FF000000","Some descreption",1));
        categories.add(new Category("Ethnic Sets","https://images1.yosari.com/88836-thickbox_default/black-exclusive-readymade-indo-western-style-kurta-pajama.jpg","#FF000000","Some descreption",1));
        categories.add(new Category("Kurtas","https://images1.yosari.com/91303-thickbox_default/sea-green-designer-mens-wear-jacquard-kurta-pajama.jpg","#FF000000","Some descreption",1));
        categories.add(new Category("Trousers", "https://m.media-amazon.com/images/I/51Xrnmm4eOL._UX679_.jpg ","#FF000000","Some descreption",1));
        categories.add(new Category("Shirts", "https://m.media-amazon.com/images/I/71NbeYK99UL._AC_UY350_.jpg","#FF000000","Some descreption",1));

        categoryAdapter =new CategoryAdapter(getContext(),categories);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);



    }


   public ArrayList<Product> initdatabase(String category,ArrayList<Product>products){

       database.getReference().child("Product").child("men").child(category).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                   Product product=dataSnapshot.getValue(Product.class);
                   product.setKey(dataSnapshot.getKey());
                   products.add(product);
               }
               productAdapter.notifyDataSetChanged();
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
return  products;
   }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding
    }
}