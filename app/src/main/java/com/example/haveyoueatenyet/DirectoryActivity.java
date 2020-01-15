package com.example.haveyoueatenyet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirectoryActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Meal> meals;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        setContentView((R.layout.activity_directory));

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.card_holder);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Populate meals
        //TODO: figure out how to pull from online aaa
        meals = new ArrayList<Meal>();
        meals.add(new Meal(new Account("AName", "AUser", "APass"), "MName", 0, 0, "MDesc"));
        meals.add(new Meal(new Account("AName1", "AUser1", "APass1"), "MName1", 0, 0, "MDesc1"));
        meals.add(new Meal(new Account("AName2", "AUser2", "APass2"), "MName2", 0, 0, "MDesc2"));
        meals.add(new Meal(new Account("AName3", "AUser3", "APass3"), "MName3", 0, 0, "MDesc3"));
        meals.add(new Meal(new Account("AName4", "AUser4", "APass4"), "MName4", 0, 0, "MDesc4"));


        adapter = new CustomAdapter(meals);
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;
        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //TODO: isn't working rn

            // Open bigger screen w more info
            expandItem(v);
        }

        private void expandItem(View v) {
            int selectedItemPos = recyclerView.getChildAdapterPosition(v);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(selectedItemPos);
            TextView mealIdText = (TextView) viewHolder.itemView.findViewById(R.id.mealId);
            Log.d("Meal ID", mealIdText.getText().toString());
            long mealId = Integer.parseInt(mealIdText.getText().toString());
            Meal meal = null;
            for(Meal m : meals) {
                if(mealId == m.getMealId()) {
                    meal = m;
                    break;
                }
            }
            if(meal == null) throw new NullPointerException("No meal with expected id found");
            Log.d("MyOnClickListener", "found meal with name " + meal.getMealName());


        }
    }
}
