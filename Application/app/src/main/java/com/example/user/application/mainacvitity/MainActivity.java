package com.example.user.application.mainacvitity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;
import com.example.user.application.food.FoodView;
import com.example.user.application.maps.MapActivity;
import com.example.user.application.performance.EnjoyView;
import com.example.user.application.spectacle.SpectacleView;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends Activity {
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private DataManager data;
    private ArrayList<Data> allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();

        FragmentTransaction tr = fragmentManager.beginTransaction();
        findViewById(R.id.all).setBackground(getDrawable(R.drawable.all_click));
        AllView allView = new AllView(this, allData);
        tr.replace(R.id.mainlistview, allView).commit();
    }

    public void init() {
        data = DataManager.getInstance();

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.mainlistview);

        findViewById(R.id.all).setOnClickListener(type);
        findViewById(R.id.food).setOnClickListener(type);
        findViewById(R.id.spectacle).setOnClickListener(type);
        findViewById(R.id.enjoy).setOnClickListener(type);
        findViewById(R.id.bookmark).setOnClickListener(type);
        findViewById(R.id.home).setOnClickListener(staticMenu);
        findViewById(R.id.browse).setOnClickListener(staticMenu);
        findViewById(R.id.location).setOnClickListener(staticMenu);
        findViewById(R.id.mypage).setOnClickListener(staticMenu);

        allData = new ArrayList<Data>();

        for (int i = 0; i < data.getFood().size(); i++)
            allData.add(data.getFood().get(i));
        for (int i = 0; i < data.getPerformance().size(); i++)
            allData.add(data.getPerformance().get(i));
        for (int i = 0; i < data.getSpectacle().size(); i++)
            allData.add(data.getSpectacle().get(i));
    }

    Button.OnClickListener type = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.all:
                    findViewById(R.id.all).setBackground(getDrawable(R.drawable.all_click));
                    findViewById(R.id.food).setBackground(getDrawable(R.drawable.food));
                    findViewById(R.id.spectacle).setBackground(getDrawable(R.drawable.spectacle));
                    findViewById(R.id.enjoy).setBackground(getDrawable(R.drawable.enjoy));
                    findViewById(R.id.bookmark).setBackground(getDrawable(R.drawable.bookmark));
                    FragmentTransaction tr1 = fragmentManager.beginTransaction();
                    AllView allView = new AllView(MainActivity.this, allData);
                    tr1.replace(R.id.mainlistview, allView).commit();
                    break;
                case R.id.food:
                    findViewById(R.id.all).setBackground(getDrawable(R.drawable.all));
                    findViewById(R.id.food).setBackground(getDrawable(R.drawable.food_click));
                    findViewById(R.id.spectacle).setBackground(getDrawable(R.drawable.spectacle));
                    findViewById(R.id.enjoy).setBackground(getDrawable(R.drawable.enjoy));
                    findViewById(R.id.bookmark).setBackground(getDrawable(R.drawable.bookmark));
                    FragmentTransaction tr2 = fragmentManager.beginTransaction();
                    FoodView foodView = new FoodView(MainActivity.this, data.getFood());
                    tr2.replace(R.id.mainlistview, foodView).commit();
                    break;
                case R.id.enjoy:
                    findViewById(R.id.all).setBackground(getDrawable(R.drawable.all));
                    findViewById(R.id.food).setBackground(getDrawable(R.drawable.food));
                    findViewById(R.id.spectacle).setBackground(getDrawable(R.drawable.spectacle));
                    findViewById(R.id.enjoy).setBackground(getDrawable(R.drawable.enjoy_click));
                    findViewById(R.id.bookmark).setBackground(getDrawable(R.drawable.bookmark));
                    FragmentTransaction tr3 = fragmentManager.beginTransaction();
                    EnjoyView enjoyView = new EnjoyView(MainActivity.this, data.getPerformance());
                    tr3.replace(R.id.mainlistview, enjoyView).commit();
                    break;
                case R.id.spectacle:
                    findViewById(R.id.all).setBackground(getDrawable(R.drawable.all));
                    findViewById(R.id.food).setBackground(getDrawable(R.drawable.food));
                    findViewById(R.id.spectacle).setBackground(getDrawable(R.drawable.spectacle_click));
                    findViewById(R.id.enjoy).setBackground(getDrawable(R.drawable.enjoy));
                    findViewById(R.id.bookmark).setBackground(getDrawable(R.drawable.bookmark));
                    FragmentTransaction tr4 = fragmentManager.beginTransaction();
                    SpectacleView spectacleView = new SpectacleView(MainActivity.this, data.getSpectacle());
                    tr4.replace(R.id.mainlistview, spectacleView).commit();
                    break;
                case R.id.bookmark:
                    findViewById(R.id.all).setBackground(getDrawable(R.drawable.all));
                    findViewById(R.id.food).setBackground(getDrawable(R.drawable.food));
                    findViewById(R.id.spectacle).setBackground(getDrawable(R.drawable.spectacle));
                    findViewById(R.id.enjoy).setBackground(getDrawable(R.drawable.enjoy));
                    findViewById(R.id.bookmark).setBackground(getDrawable(R.drawable.bookmark_click));
                    FragmentTransaction tr5 = fragmentManager.beginTransaction();
                    BookMarkView bookMarkView = new BookMarkView(MainActivity.this, data.getBookmark());
                    tr5.replace(R.id.mainlistview, bookMarkView).commit();
                    break;
                default:
                    break;
            }
        }
    };

    Button.OnClickListener staticMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home:
                    break;
                case R.id.browse:
                    break;
                case R.id.location:
                    Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(mapIntent);
                    break;
                case R.id.mypage:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
