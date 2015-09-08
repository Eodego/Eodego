package com.example.user.application.spectacle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 30.
 */
public class SpectacleActivity extends Activity {
    private ArrayList<Data> specList;
    private ListView listView;
    private ProgressBar pro;
    private SpectacleList listAdapter;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        data = DataManager.getInstance();
        specList = data.getSpectacle();
        listView = (ListView) findViewById(R.id.listview);
        pro = (ProgressBar) findViewById(R.id.listpro);
        listAdapter = new SpectacleList(this, R.layout.list_item, specList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(SpectacleActivity.this, SpectacleInfo.class);
            Data per = new Data(specList.get(position).getIcon(), specList.get(position).getName(),
                    specList.get(position).getAddr(), specList.get(position).getClcdnm(),
                    specList.get(position).getTelno(), specList.get(position).getCinema(),
                    specList.get(position).getxPos(), specList.get(position).getyPos());
            info.putExtra("Item", per);
            startActivity(info);
        }
    };
}
