package com.example.haveyoueatenyet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<Meal> meals;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView tags;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.mealName);
            this.tags = (TextView) itemView.findViewById(R.id.tags);
        }
    }

    public CustomAdapter(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_layout, parent, false);
        view.setOnClickListener(DirectoryActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPos) {
        TextView name = holder.name;
        TextView tags = holder.tags;

        name.setText(meals.get(listPos).getMealName());
        tags.setText("#food");
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
