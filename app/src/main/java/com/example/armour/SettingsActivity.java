package com.example.armour;

import static com.example.armour.MainActivity.redirectActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView myAccount,aboutArmour,logOut;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        myAccount = findViewById(R.id.myAccount);
        aboutArmour = findViewById(R.id.aboutArmour);
        logOut = findViewById(R.id.logOut);


        aboutArmour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,AboutArmourActivity.class);
                startActivity(intent);
            }
        });
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,MyAccountActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    //Intent function for activities
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    //Opening & closing of drawer
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //Drawer menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            redirectActivity(this, com.example.armour.MainActivity.class);
        }else if (itemId == R.id.nav_location) {
            redirectActivity(this,com.example.armour.MapActivity.class);
        }else if (itemId == R.id.nav_alert) {
            redirectActivity(this,com.example.armour.MainActivity.class);
        } else if (itemId == R.id.nav_self_defence) {
            redirectActivity(this, SelfDefenceActivity.class);
        } else if (itemId == R.id.nav_about) {
            redirectActivity(this, AboutArmourActivity.class);
        }else if (itemId == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Women Security");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } else if (itemId == R.id.nav_log_out) {
            redirectActivity(this, LoginActivity.class);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //Toolbar menu
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.settings) {
            redirectActivity(this, SettingsActivity.class);

        } else if (itemId == R.id.log_out) {
            redirectActivity(this, LoginActivity.class);

        } else if (itemId == R.id.home) {
            redirectActivity(this,MainActivity.class);

        } else if (itemId == R.id.about) {
            redirectActivity(this, AboutArmourActivity.class);

        } else if (itemId == R.id.share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Women Security");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        return true;
}
}