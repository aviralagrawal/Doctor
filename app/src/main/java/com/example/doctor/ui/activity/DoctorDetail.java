package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.find_doctor_model;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetail extends AppCompatActivity implements View.OnClickListener{

    private TextView name, mobile_number, speciality, address, timings, description;
    private CircleImageView display_picture;
    private Button add_to_my_doctors;
    private find_doctor_model doctorDetailModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
//        doctorDetailModel = new find_doctor_model();
        bindViews();
//        setDummyData();
        if(getIntent().hasExtra("details")){
            doctorDetailModel = (find_doctor_model) getIntent().getSerializableExtra("details");
        }
        setData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDummyData() {
        doctorDetailModel.setName("Dr. Him Pass");
        doctorDetailModel.setMobile_number("46848435\n687684");
        doctorDetailModel.setSpeciality("Dentist");
        doctorDetailModel.setAddress("23 CP Street,\nCouunaight Place,\nDelhi-828888");
        doctorDetailModel.setTimings("8.00 AM - 2.00 PM\n5.00 PM - 9.30 PM");
        doctorDetailModel.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    }

    private void setData() {
        setTitle(doctorDetailModel.getName());
        name.setText(doctorDetailModel.getName());
        mobile_number.setText(doctorDetailModel.getMobile_number());
        speciality.setText(doctorDetailModel.getSpeciality());
        address.setText(doctorDetailModel.getAddress());
        timings.setText(doctorDetailModel.getTimings());
        description.setText(doctorDetailModel.getDescription());
        display_picture.setImageResource(doctorDetailModel.getId());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }
    private void bindViews() {
        name = (TextView) findViewById(R.id.name);
        mobile_number = (TextView) findViewById(R.id.mobile_number);
        speciality = (TextView) findViewById(R.id.speciality);
        address = (TextView) findViewById(R.id.address);
        timings = (TextView) findViewById(R.id.timings);
        description = (TextView) findViewById(R.id.description);
        display_picture = (CircleImageView) findViewById(R.id.display_picture);
        add_to_my_doctors = (Button) findViewById(R.id.add_to_my_doctors);

//        display_picture.setOnClickListener(this);
//        add_to_my_doctors.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
//            case R.id.display_picture:
//                break;
            case R.id.add_to_my_doctors:
                break;
        }
    }
}
