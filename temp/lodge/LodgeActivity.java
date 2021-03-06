package com.example.user.application.lodge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataInfo;
import com.example.user.application.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class LodgeActivity extends Activity {
    private ArrayList<Data> lodgesList;
    private ListView listView;
    private LodgeList listAdapter;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.list_view);
        data = DataManager.getInstance();
        lodgesList = data.getLodge();
        listView = (ListView) findViewById(R.id.listview);
        listAdapter = new LodgeList(this, R.layout.list_item, lodgesList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(LodgeActivity.this, DataInfo.class);
            Data lodge = lodgesList.get(position);
            info.putExtra("Item", lodge);
            startActivity(info);
        }
    };
}
