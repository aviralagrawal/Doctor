package com.example.doctor.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.AppointmentsAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.Appointments;

import java.util.ArrayList;
import java.util.List;

public class MyAppointments extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener {

    private RecyclerView recyclerView;
    private AppointmentsAdapter adapter;

    private List<Appointments> listItems;
    private Appointments listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        setTitle("My Appointments");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();

        listItems.add(0,new Appointments("Aaa","999999999","address","speciality","qwerty","notes","23/05/17","12:00"));
        listItems.add(1,new Appointments("Bbb","999999999","address","speciality","qwerty","notes","23/05/17","12:00"));
        listItems.add(2,new Appointments("Ccc","999999999","address","speciality","qwerty","notes","23/05/17","12:00"));
        listItems.add(3,new Appointments("Ddd","999999999","address","speciality","qwerty","notes","23/05/17","12:00"));
        listItems.add(4,new Appointments("Eee","999999999","address","speciality","qwerty","notes","23/05/17","12:00"));

        adapter=new AppointmentsAdapter(this,listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position, View v) {

        listItem=listItems.get(position);

        Intent intent=new Intent(MyAppointments.this,EditAppointments.class);
        intent.putExtra("appointment",listItem);
        startActivity(intent);

    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }

        else{

            Intent intent=new Intent(MyAppointments.this,EditAppointments.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }

}
