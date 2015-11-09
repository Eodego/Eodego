package com.example.user.application.mainacvitity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataList;
import com.example.user.application.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by bw on 15. 11. 9.
 */
public class DataViewAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Data> allData;

    public DataViewAdapter(LayoutInflater inflater, Context context, ArrayList<Data> allData) {
        this.context = context;
        this.inflater = inflater;
        this.allData = allData;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View root = inflater.inflate(R.layout.list_view, container, false);
        DataManager data = DataManager.getInstance();

        ListView listView = (ListView) root.findViewById(R.id.listview);
        DataList dataListAdapter;
        if (position == 0) {
            dataListAdapter = new DataList(context, R.layout.list_item, allData);
            listView.setAdapter(dataListAdapter);
        } else if (position == 1) {
            dataListAdapter = new DataList(context, R.layout.list_item, data.getFood());
            listView.setAdapter(dataListAdapter);
        } else if (position == 2) {
            DataList perListAdapter = new DataList(context, R.layout.list_item, data.getPerformance());
            listView.setAdapter(perListAdapter);
        } else if (position == 3) {
            DataList specListAdapter = new DataList(context, R.layout.list_item, data.getSpectacle());
            listView.setAdapter(specListAdapter);
        }
        //listView.setOnItemClickListener(listener);
        container.addView(root);
        return root;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        // TODO Auto-generated method stub
        return v == obj;
    }
}