package com.example.user.application.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.beauty.BeautyActivity;
import com.example.user.application.food.Food;
import com.example.user.application.food.FoodActivity;
import com.example.user.application.food.FoodList;
import com.example.user.application.health.HealthActivity;
import com.example.user.application.lodge.LodgeActivity;
import com.example.user.application.performance.PerformanceActivity;

import java.util.ArrayList;

/**
 * Created by user on 15. 9. 1.
 */
public class SearchActivity extends Activity {
    private String name;
    private EditText search;
    private int size;
    private ArrayList<Food> food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        findViewById(R.id.findbtn).setOnClickListener(listener);

        size = FoodActivity.foodList.size() + HealthActivity.hospitalList.size() + BeautyActivity.beautyList.size() +
                LodgeActivity.lodgesList.size() + PerformanceActivity.persList.size();
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            search = (EditText) findViewById(R.id.searchtext);
            name = search.getText().toString();

            food = new ArrayList<Food>();
            for (int i = 0; i < FoodActivity.foodList.size(); i++) {
                if (name.equals(FoodActivity.foodList.get(i).getName())) {
                    food.add(new Food(FoodActivity.foodList.get(i).getIcon(), FoodActivity.foodList.get(i).getName(),
                            FoodActivity.foodList.get(i).getAddr(), FoodActivity.foodList.get(i).getClcdnm(),
                            FoodActivity.foodList.get(i).getTelno(), FoodActivity.foodList.get(i).getxPos(),
                            FoodActivity.foodList.get(i).getyPos()));
                }
            }

            switch (v.getId()) {
                case R.id.findbtn:
                    View root = View.inflate(SearchActivity.this, R.layout.search_view, null);
                    ListView listView = (ListView) root.findViewById(R.id.searchlist);
                    FoodList listAdapter = new FoodList(SearchActivity.this, R.layout.food_item, food);
                    listView.setAdapter(listAdapter);
                    break;
                default:
                    break;
            }
        }
    };
}
