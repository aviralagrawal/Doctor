package com.example.doctor.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.ui.adapter.AppointmentsAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.Appointments;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class MyAppointments extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener,RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AppointmentsAdapter adapter;

    private List<Appointments> listItems;
    private Appointments listItem;

    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);


        setTitle("My Appointments");



//        progressDialog=new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.isIndeterminate();
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                progressDialog.dismiss();
//            }
//        }, 3000);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        listItems=new ArrayList<>();

        listItems.add(0,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(1,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(2,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(3,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(4,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(5,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));
        listItems.add(6,new Appointments("Dr Smith","999999999","Ahmedabad","Heart Surgeon","Checkup","notes","23/05/17","12:00"));


        adapter=new AppointmentsAdapter(this,listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        onTouchListener=new RecyclerTouchListener(this,recyclerView);

        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //Toast.makeText(MyAppointments.this,"View",Toast.LENGTH_SHORT).show();
                        details(position);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyAppointments.this,"Button",Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyAppointments.this,"View long clicked!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

        refreshLayout = (PullRefreshLayout) findViewById(R.id.pullLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new TimerTask() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {
                    MyAppointments.this.getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    MyAppointments.this.getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener); }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }

    @Override
    public void onItemClick(int position, View v) {

        listItem=listItems.get(position);

        Intent intent=new Intent(MyAppointments.this,EditAppointments.class);
        intent.putExtra("appointment",listItem);
        startActivity(intent);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
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

    private void details(int position) {
        Appointments a = listItems.get(position);

        //
        Dialog dialog = new Dialog(this,R.style.Theme_AppCompat_DialogWhenLarge);
        dialog.setContentView(R.layout.display_appointments);
        //dialog.findViewById(R.id.imageZoom);
        //((ImageView)dialog.findViewById(R.id.imageZoom)).setImageURI(Uri.parse(person1.getUri()));
        ((TextView)dialog.findViewById(R.id.name_edit)).setText(a.getName());
        ((TextView)dialog.findViewById(R.id.phone_edit)).setText(a.getPhone());
        ((TextView)dialog.findViewById(R.id.address_edit)).setText(a.getAddress());
        ((TextView)dialog.findViewById(R.id.speciality_edit)).setText(a.getSpeciality());
        ((TextView)dialog.findViewById(R.id.date_edit)).setText(a.getDate());
        ((TextView)dialog.findViewById(R.id.time_edit)).setText(a.getTime());
        ((TextView)dialog.findViewById(R.id.reason_edit)).setText(a.getReason());
        ((TextView)dialog.findViewById(R.id.notes_edit)).setText(a.getNotes());
        dialog.show();
    }

}