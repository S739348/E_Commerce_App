package com.example.tailor.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tailor.R;
import com.example.tailor.adapter.CategoryAdapter;
import com.example.tailor.adapter.MyPagerAdapter;
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
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseDatabase database;
    private TextView selectedTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database=FirebaseDatabase.getInstance();
        binding.menTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTextViewClick(binding.menTextView);
                // Handle click on Men
                MenFragment o=new MenFragment();
                onClickChange(o);

                // Navigate to MenFragment or perform the desired action
            }
        });

        binding.womenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTextViewClick(binding.womenTextView);
                WomenFragment o=new WomenFragment();
                onClickChange(o);
                // Handle click on Women
                // Navigate to WomenFragment or perform the desired action
            }
        });

        return root;
    }

    private void onClickChange(Fragment o) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.womens, o);
        transaction.commit();


        /*List<Fragment> fragments = new ArrayList<>();
        if(fragments.size()!=2) {
            fragments.add(o);

            MyPagerAdapter adapter = new MyPagerAdapter(getParentFragmentManager(), fragments);
            binding.women.setAdapter(adapter);
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.womens, o);
            transaction.commit();

        }*/
    }

    private void handleTextViewClick(TextView textView) {
        if (selectedTextView != null) {
            // Restore appearance of previously selected TextView
            selectedTextView.setTextColor(Color.parseColor("#DFE4D5D5")); // Original text color
            selectedTextView.setBackgroundColor(Color.TRANSPARENT); // No background color
            selectedTextView.setPaintFlags(selectedTextView.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG)); // Remove underline
        }

        if (textView != selectedTextView) {
            // Set appearance of the newly selected TextView
            textView.setTextColor(getResources().getColor(R.color.textcolor)); // New text color
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Add underline

            selectedTextView = textView; // Update the currently selected TextView
        } else {
            // No TextView is currently selected
            selectedTextView = null;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}