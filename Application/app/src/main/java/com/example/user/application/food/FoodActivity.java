package com.example.user.application.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class FoodActivity extends Activity {
    private ArrayList<Data> foodList;
    private ListView listView;
    private FoodList listAdapter;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_view);

        LinearLayout imgView = (LinearLayout) findViewById(R.id.listimg);
        ImageButton[] imgBtn = new ImageButton[9];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        for (int i = 0; i < imgBtn.length; i++)
            imgBtn[i] = new ImageButton(this);

        imgBtn[0].setBackgroundResource(R.drawable.all);
        imgBtn[1].setBackgroundResource(R.drawable.koreafood);
        imgBtn[2].setBackgroundResource(R.drawable.japanfood);
        imgBtn[3].setBackgroundResource(R.drawable.chinafood);
        imgBtn[4].setBackgroundResource(R.drawable.down);
//        imgBtn[5].setBackgroundResource(R.drawable.boonsik);
//        imgBtn[6].setBackgroundResource(R.drawable.cafe);
//        imgBtn[7].setBackgroundResource(R.drawable.restaurant);
//        imgBtn[8].setBackgroundResource(R.drawable.pizza);

        for (int i = 0; i < 5; i++) {
            imgView.addView(imgBtn[i], params);
        }

//        TextView[] text = new TextView[str.length];
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
//
//        for (int i = 0; i < text.length; i++) {
//            text[i] = new TextView(this);
//            text[i].setText(str[i]);
//            text[i].setGravity(Gravity.CENTER);
//            text[i].setBackgroundColor(Color.WHITE);
//            text[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, 60f);
//            textView.addView(text[i], params);
//        }

        data = DataManager.getInstance();
        foodList = data.getFood();
        listView = (ListView) findViewById(R.id.listview);
        listAdapter = new FoodList(this, R.layout.list_item, foodList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(FoodActivity.this, FoodInfo.class);
            Data food = foodList.get(position);
            info.putExtra("Item", food);
            startActivity(info);
        }
    };
}
