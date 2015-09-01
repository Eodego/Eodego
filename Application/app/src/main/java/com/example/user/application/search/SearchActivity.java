package com.example.user.application.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.beauty.BeautyActivity;
import com.example.user.application.food.FoodActivity;
import com.example.user.application.food.FoodList;
import com.example.user.application.health.HealthActivity;
import com.example.user.application.lodge.LodgeActivity;
import com.example.user.application.performance.PerformanceActivity;

/**
 * Created by user on 15. 9. 1.
 */
public class SearchActivity extends Activity {
    private String name;
    private EditText search;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        search = (EditText) findViewById(R.id.searchtext);
        name = search.getText().toString();

        size = FoodActivity.foodList.size() + HealthActivity.hospitalList.size() + BeautyActivity.beautyList.size() +
                LodgeActivity.lodgesList.size() + PerformanceActivity.persList.size();

        for (int i = 0; i < FoodActivity.foodList.size(); i++) {
            if (name.equals(FoodActivity.foodList.get(i).getName())) {
                View root = View.inflate(this, R.layout.search_list, null);

//                View root = inflater.inflate(R.layout.food_list, container, false);
//                ListView listView = (ListView) root.findViewById(R.id.foodlist);
//                FoodList listAdapter = new FoodList(CourseActivity.this, R.layout.food_item, FoodActivity.foodList);
//                listView.setAdapter(listAdapter);
//                listView.setOnItemClickListener(Click);
            }
        }
    }
}
