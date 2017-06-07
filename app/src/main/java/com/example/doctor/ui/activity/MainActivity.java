package com.example.doctor.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.doctor.R;
import com.example.doctor.ui.fragment.Health_Acc_Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

    }

    private void bindView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame,new Health_Acc_Fragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        android.app.FragmentManager fm = getFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.health_acc) {
            setTitle("My Health Account");
            fm.beginTransaction().replace(R.id.content_frame,new Health_Acc_Fragment()).commit();

        } else if (id == R.id.symptoms) {
            setTitle("Symptoms");

        } else if (id == R.id.find_docs) {
            setTitle("Find Doctors");

        } else if (id == R.id.emergency) {
            setTitle("Emergency Contacts");
        } else if (id == R.id.procedure) {
            setTitle("Procedures");

        } else if (id == R.id.notifications) {
            setTitle("Notifications");

        } else if (id == R.id.meds) {
            setTitle("Medicines");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}