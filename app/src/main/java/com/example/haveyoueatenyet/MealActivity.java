package com.example.haveyoueatenyet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MealActivity extends AppCompatActivity {
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        meal = (Meal) intent.getParcelableExtra("meal");
        setContentView((R.layout.activity_meal));
    }
}
