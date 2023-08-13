package com.example.tailor.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tailor.adapter.CartAdapter;
import com.example.tailor.databinding.FragmentDashboardBinding;
import com.example.tailor.model.CartModel;
import com.example.tailor.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    FirebaseDatabase database;
    ArrayList<CartModel>cartModels;
    ArrayList<Product>productArrayList;
CartAdapter cartAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       database=FirebaseDatabase.getInstance();
       productArrayList=new ArrayList<>();

       userCart();
        return root;
    }



    private void userCart(){
        cartModels=new ArrayList<>();
        database.getReference().child("7803841753").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
           for(DataSnapshot snapshot1:snapshot.getChildren()){
               CartModel cartModel=snapshot1.getValue(CartModel.class);
               cartModels.add(cartModel);

           }
                itemsInCart();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

    public void itemsInCart(){
        if(cartModels.size()>=0) {
            for (int i = 0; i < cartModels.size(); i++) {
                database.getReference().child("Product").child("men").child(cartModels.get(i).getCategory()).child(cartModels.get(i).getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Product product = snapshot.getValue(Product.class);
                        productArrayList.add(product);
                        userCartRecycle();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        }
    }
    private void userCartRecycle() {
        if (productArrayList.size() >= 0) {
            cartAdapter = new CartAdapter(getContext(), productArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            binding.cartItem.setLayoutManager(linearLayoutManager);
            binding.cartItem.setAdapter(cartAdapter);

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}