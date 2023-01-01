package com.example.buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class tabs<drawerLayout, navigationView, toolbar> extends AppCompatActivity {
    BottomNavigationView bview;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        bview= findViewById(R.id.bottomNavigationView);
        bview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.nav_home)
                {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();
                    ft.add(R.id.container,new fragment_a());
                    ft.commit();
                    ft.add(R.id.container,new fragment_a());
                }
                else if(id ==R.id.nav_profile)
                {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();
                    ft.add(R.id.container,new fragment_b());
                    ft.commit();
                    ft.replace(R.id.container,new fragment_b());
                }
                else if(id ==R.id.nav_auction)
                {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();
                    ft.add(R.id.container,new fragment_c());
                    ft.commit();
                    ft.replace(R.id.container,new fragment_c());
                }
                else
                {

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();
                    ft.add(R.id.container,new fragment_d());
                    ft.commit();
                    ft.replace(R.id.container,new fragment_d());
                }
                return true;
            }
        });
        bview.setSelectedItemId(R.id.nav_home);

    drawerLayout = findViewById(R.id.drawerLayout);
    navigationView = findViewById(R.id.navigationView2);
    toolbar =  findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this ,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            drawerLayout.closeDrawer(GravityCompat.START);
            if(id ==R.id.about_nav) {

                loadFragment(new about_fragment(), false);
            }

            else if(id==R.id.your_nav)
            {
                loadFragment(new your_fragment(),false);
            }
            else if(id == R.id.company_nav)
            {
                Toast.makeText(tabs.this, "working", Toast.LENGTH_SHORT).show();
                loadFragment(new company_fragment(),false);
            }
            else if(id== R.id.premium_nav)
            {
                loadFragment(new premium_fragment(),false);
            }
            else
            {
                loadFragment(new logout_fragment(),false);
            }

            return true;
        }

    });

}

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    private void loadFragment(Fragment fragment, boolean bool)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
        if(bool)
        {
            ft.add(R.id.container,fragment);
        }
        else
        {
            ft.replace(R.id.container,fragment);
        }
    }

}