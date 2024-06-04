package Maryan1200861.Hamza1201619;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        // Set HomeFragment as the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();


        // ----------------- Navigation Drawer Menu -----------------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
                getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_logout) {

            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        /* Clear the back stack, so the user can't go back to the HomeActivity after logging out
        without logging in again*/
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(itemId == R.id.nav_pizza_menu) {
            PizzaMenuFragment pizzaMenuFragment = new PizzaMenuFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, pizzaMenuFragment) // Replace the current fragment with the new PizzaMenuFragment
                    .addToBackStack(null) // Add this transaction to the back stack
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        else if (itemId == R.id.nav_home && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof HomeFragment)){
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment) // Replace the current fragment with the new HomeFragment
                    .addToBackStack(null) // Add this transaction to the back stack
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        else if (itemId == R.id.nav_special_offers && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof SpecialOffersFragment)){
            SpecialOffersFragment specialOffersFragment = new SpecialOffersFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, specialOffersFragment) // Replace the current fragment with the new SpecialOffersFragment
                    .addToBackStack(null) // Add this transaction to the back stack
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        else if (itemId == R.id.nav_history && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof OrdersFragment)){
            OrdersFragment ordersFragment = new OrdersFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ordersFragment) // Replace the current fragment with the new OrdersFragment
                    .addToBackStack(null) // Add this transaction to the back stack
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        else if (itemId == R.id.nav_favorite && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FavoriteFragment)){
            FavoriteFragment favoritesFragment = new FavoriteFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, favoritesFragment) // Replace the current fragment with the new FavoritesFragment
                    .addToBackStack(null) // Add this transaction to the back stack
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}