package com.example.haveyoueatenyet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MealActivity extends AppCompatActivity {
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        meal = (Meal) intent.getParcelableExtra("meal");
        setContentView((R.layout.activity_meal));

        TextView name = (TextView) findViewById(R.id.mealName);
        TextView id = (TextView) findViewById(R.id.mealId);
        TextView desc = (TextView) findViewById(R.id.mealDesc);
        TextView tags = (TextView) findViewById(R.id.mealTags);

        name.setText(meal.getMealName());
        id.setText("" + meal.getMealId());
        desc.setText(meal.getMealDesc());
        tags.setText("#food");

    }

    public void register(View view) {
        //TODO: online
        Toast.makeText(this, "No online connection", Toast.LENGTH_SHORT).show();
    }
}
