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

public class AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // Set AdminProfileFragment as the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AdminProfileFragment())
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
            Intent intent = new Intent(AdminHomeActivity.this, AdminLoginActivity.class);
            // Clear the back stack, so the user can't go back to the HomeActivity after logging out without logging in again
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (itemId == R.id.nav_admin_profile && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof AdminProfileFragment)) {
            AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, adminProfileFragment)
                    .addToBackStack(null)
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        } else if (itemId == R.id.nav_add_admin && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof AddAdminFragment)) {
            AddAdminFragment addAdminFragment = new AddAdminFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addAdminFragment)
                    .addToBackStack(null)
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        } else if (itemId == R.id.nav_view_all_orders && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof ViewAllOrdersFragment)) {
            ViewAllOrdersFragment viewAllOrdersFragment = new ViewAllOrdersFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, viewAllOrdersFragment)
                    .addToBackStack(null)
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        } else if (itemId == R.id.nav_add_special_order && !(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof AddSpecialOrderFragment)) {
            AddSpecialOrderFragment addSpecialOrderFragment = new AddSpecialOrderFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addSpecialOrderFragment)
                    .addToBackStack(null)
                    .commit();

            Log.d("NAVIGATION", "Menu item selected: " + itemId);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}

