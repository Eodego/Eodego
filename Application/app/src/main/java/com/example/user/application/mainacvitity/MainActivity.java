package com.example.user.application.mainacvitity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.example.user.application.performance.EnjoyView;
import com.example.user.application.spectacle.SpectacleView;

import java.util.ArrayList;

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
        AllView allView = new AllView(this, allData);
        tr.replace(R.id.mainlistview, allView).commit();
    }

    public void init() {
        data = DataManager.getInstance();

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.mainlistview);

        findViewById(R.id.all).setOnClickListener(listener);
        findViewById(R.id.food).setOnClickListener(listener);
        findViewById(R.id.spectacle).setOnClickListener(listener);
        findViewById(R.id.enjoy).setOnClickListener(listener);

        allData = new ArrayList<Data>();

        for (int i = 0; i < data.getFood().size(); i++)
            allData.add(data.getFood().get(i));
        for (int i = 0; i < data.getPerformance().size(); i++)
            allData.add(data.getPerformance().get(i));
        for (int i = 0; i < data.getSpectacle().size(); i++)
            allData.add(data.getSpectacle().get(i));
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.all:
                    FragmentTransaction tr1 = fragmentManager.beginTransaction();
                    AllView allView = new AllView(MainActivity.this, allData);
                    tr1.replace(R.id.mainlistview, allView).commit();
                    break;
                case R.id.food:
                    FragmentTransaction tr2 = fragmentManager.beginTransaction();
                    FoodView foodView = new FoodView(MainActivity.this, data.getFood());
                    tr2.replace(R.id.mainlistview, foodView).commit();
                    break;
                case R.id.enjoy:
                    FragmentTransaction tr3 = fragmentManager.beginTransaction();
                    EnjoyView enjoyView = new EnjoyView(MainActivity.this, data.getPerformance());
                    tr3.replace(R.id.mainlistview, enjoyView).commit();
                    break;
                case R.id.spectacle:
                    FragmentTransaction tr4 = fragmentManager.beginTransaction();
                    SpectacleView spectacleView = new SpectacleView(MainActivity.this, data.getSpectacle());
                    tr4.replace(R.id.mainlistview, spectacleView).commit();
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
