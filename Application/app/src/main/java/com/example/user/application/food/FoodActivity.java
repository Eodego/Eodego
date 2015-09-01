package com.example.user.application.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class FoodActivity extends Activity {
    public static ArrayList<Food> foodList = new ArrayList<Food>();
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(FoodActivity.this, FoodInfo.class);
            Food food = new Food(foodList.get(position).getIcon(), foodList.get(position).getName(),
                    foodList.get(position).getAddr(), foodList.get(position).getClcdnm(),
                    foodList.get(position).getTelno(), foodList.get(position).getxPos(), foodList.get(position).getyPos());
            info.putExtra("Item", food);
            startActivity(info);
        }
    };
    private ListView listView;
    private FoodList listAdapter;
    private ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        listView = (ListView) findViewById(R.id.foodlist);
        pro = (ProgressBar) findViewById(R.id.foodpro);
        listAdapter = new FoodList(this, R.layout.food_item, foodList);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }
}