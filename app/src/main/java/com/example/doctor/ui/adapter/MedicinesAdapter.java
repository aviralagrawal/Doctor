package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Medicines;

import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 05-06-2017.
 */

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.ViewHolder>{

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private List<Medicines> grid;
    private Context context;

    public MedicinesAdapter(Context context, List<Medicines> grid) {

        this.context = context;
        this.grid = grid;

    }

    public void setOnItemClickListener(My_Health_Acc_Adapter.MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicines_custom_box,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Medicines listItem=grid.get(position);

        holder.med_name.setText(listItem.getMedName());
        holder.unit.setText(listItem.getUnit());
        holder.method.setText(listItem.getMethod());
        holder.frequency.setText(listItem.getFrequency());

    }

    @Override
    public int getItemCount() {

        return grid.size();

    }

    public interface MyClickListener{

        void onItemClick(int position, View v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView med_name,unit,method,frequency;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {

            super(itemView);

            med_name=(TextView)itemView.findViewById(R.id.med_name);
            unit=(TextView)itemView.findViewById(R.id.unit);
            method=(TextView)itemView.findViewById(R.id.method);
            frequency=(TextView)itemView.findViewById(R.id.frequency);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.MedicinesLayout);

            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(myClickListener!=null){

                myClickListener.onItemClick(getLayoutPosition(),v);

            }

        }

    }

}