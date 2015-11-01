package com.example.user.application.mainacvitity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.beauty.BeautyList;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;

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

        FragmentTransaction tr1 = fragmentManager.beginTransaction();
        MainView mainView = new MainView();
        tr1.replace(R.id.mainlistview, mainView, "AllData");
        tr1.commit();
    }

    class MainView extends Fragment {
        private BeautyList listAdapter;
        private ListView listView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);

            listView = (ListView) root.findViewById(R.id.listview);
            listAdapter = new BeautyList(MainActivity.this, R.layout.list_item, allData);
            listView.setAdapter(listAdapter);

            return root;
        }
    }

    public void init() {
        data = DataManager.getInstance();

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.mainlistview);

        allData = new ArrayList<Data>();

        for (int i = 0; i < data.getBeauty().size(); i++)
            allData.add(data.getBeauty().get(i));
        for (int i = 0; i < data.getFood().size(); i++)
            allData.add(data.getFood().get(i));
        for (int i = 0; i < data.getHealth().size(); i++)
            allData.add(data.getHealth().get(i));
        for (int i = 0; i < data.getLodge().size(); i++)
            allData.add(data.getLodge().get(i));
        for (int i = 0; i < data.getPerformance().size(); i++)
            allData.add(data.getPerformance().get(i));
        for (int i = 0; i < data.getSpectacle().size(); i++)
            allData.add(data.getSpectacle().get(i));
    }

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
