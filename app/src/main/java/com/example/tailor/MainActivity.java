package com.example.tailor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tailor.databinding.ActivityMainBinding;
import com.example.tailor.detail.ProductDetail;
import com.example.tailor.ui.dashboard.DashboardFragment;
import com.example.tailor.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        getSupportActionBar().hide();

        boolean shouldNavigateToDashboard = getIntent().getBooleanExtra("navigate_to_dashboard", false);
        boolean cartValue=getIntent().getBooleanExtra("cart",false);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (shouldNavigateToDashboard) {
            NavGraph navGraph = navController.getGraph();
            navGraph.setStartDestination(R.id.navigation_notifications); // Set DashboardFragment as the start destination
            navController.navigate(R.id.navigation_notifications);
            navController.setGraph(navGraph);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);

        }
        if(cartValue){
            NavGraph navGraph = navController.getGraph();
            navGraph.setStartDestination(R.id.navigation_dashboard); // Set DashboardFragment as the start destination
            navController.navigate(R.id.navigation_dashboard);
            navController.setGraph(navGraph);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        }



        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink= null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            String link=deepLink.toString();
                        //    Log.e("link",link);
                            link=link.substring(link.indexOf("_")+1);
                         String  Category=link.substring(0,link.indexOf("_"));
                         String  node=link.substring(link.indexOf("_")+1);
                            Toast.makeText(MainActivity.this,Category, Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this,node, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,ProductDetail.class);
                            intent.putExtra("Category",Category);
                            intent.putExtra("node",node);
                            intent.putExtra("execute",true);
                            startActivity(intent);
                        }

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("j", "getDynamicLink:onFailure", e);
                    }
                });



    }

}