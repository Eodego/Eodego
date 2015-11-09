package com.example.user.application.mainacvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;
import com.example.user.application.maps.MapActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        DataManager data = DataManager.getInstance();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        findViewById(R.id.home).setOnClickListener(staticMenu);
        findViewById(R.id.browse).setOnClickListener(staticMenu);
        findViewById(R.id.location).setOnClickListener(staticMenu);
        findViewById(R.id.mypage).setOnClickListener(staticMenu);

        ArrayList<Data> allData = new ArrayList<Data>();

        for (int i = 0; i < data.getFood().size(); i++)
            allData.add(data.getFood().get(i));
        for (int i = 0; i < data.getPerformance().size(); i++)
            allData.add(data.getPerformance().get(i));
        for (int i = 0; i < data.getSpectacle().size(); i++)
            allData.add(data.getSpectacle().get(i));

        DataViewAdapter adapter = new DataViewAdapter(getLayoutInflater(), MainActivity.this, allData);
        pager.setAdapter(adapter);
    }

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
