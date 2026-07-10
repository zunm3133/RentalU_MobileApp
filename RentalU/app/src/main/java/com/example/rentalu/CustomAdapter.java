package com.example.rentalu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList id,listing,property,bedroom,date,time,monthly_rent,furniture,remark,name,contact;
    Animation animation;
    CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList listing, ArrayList property, ArrayList bedroom,
                  ArrayList date, ArrayList time, ArrayList monthly_rent, ArrayList furniture,
                  ArrayList remark, ArrayList name, ArrayList contact){
        this.activity=activity;
        this.context=context;
        this.id=id;
        this.listing=listing;
        this.property=property;
        this.bedroom=bedroom;
        this.date=date;
        this.time=time;
        this.monthly_rent=monthly_rent;
        this.furniture=furniture;
        this.remark=remark;
        this.name=name;
        this.contact=contact;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.property.setText(String.valueOf(property.get(position)));
        holder.bedroom.setText(String.valueOf(bedroom.get(position)));
        holder.monthly_rent.setText(String.valueOf(monthly_rent.get(position)));
        holder.furniture.setText(String.valueOf(furniture.get(position)));
        holder.main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("listing",String.valueOf(listing.get(position)));
                intent.putExtra("property",String.valueOf(property.get(position)));
                intent.putExtra("bedroom",String.valueOf(bedroom.get(position)));
                intent.putExtra("date",String.valueOf(date.get(position)));
                intent.putExtra("time",String.valueOf(time.get(position)));
                intent.putExtra("monthly_rent",String.valueOf(monthly_rent.get(position)));
                intent.putExtra("furniture",String.valueOf(furniture.get(position)));
                intent.putExtra("remark",String.valueOf(remark.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("contact",String.valueOf(contact.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,property,bedroom,monthly_rent,furniture;
        LinearLayout main_page;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            property=itemView.findViewById(R.id.property);
            bedroom=itemView.findViewById(R.id.bedroom);
            monthly_rent=itemView.findViewById(R.id.monthly_rent);
            furniture=itemView.findViewById(R.id.furniture);
            main_page=itemView.findViewById(R.id.main_page);
            animation= AnimationUtils.loadAnimation(context,R.anim.animation_page);
            main_page.setAnimation(animation);
        }
    }
}
