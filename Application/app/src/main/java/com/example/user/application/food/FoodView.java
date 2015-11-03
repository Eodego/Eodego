package com.example.user.application.food;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataInfo;
import com.example.user.application.food.FoodList;

import java.util.ArrayList;

/**
 * Created by bw on 15. 11. 3.
 */
public class FoodView extends Fragment {
    private FoodList listAdapter;
    private ListView listView;
    private Context context;
    private ArrayList<Data> dataList;

    public FoodView(Context context, ArrayList<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_view, container, false);

        listView = (ListView) root.findViewById(R.id.listview);
        listAdapter = new FoodList(context, R.layout.list_item, dataList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);

        return root;
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(context, DataInfo.class);
            Data data = dataList.get(position);
            info.putExtra("Item", data);
            startActivity(info);
        }
    };
}
