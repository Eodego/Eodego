package com.example.user.application.datamanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 15.
 */
public class DataList extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Data> foodsList;
    private int layout;

    public DataList(Context context, int layout, ArrayList<Data> foodsList) {
        this.layout = layout;
        this.foodsList = foodsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.listimg);
        img.setImageResource(foodsList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.listname);
        name.setText(foodsList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.listaddr);
        addr.setText(foodsList.get(position).getAddr());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.listclcdnm);
        clcdnm.setText(foodsList.get(position).getClcdnm());

        ImageView order = (ImageView) convertView.findViewById(R.id.listorder);
//        order.setImageResource(foodsList.get(position).getIcon());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.listrating);
        star.setRating(foodsList.get(position).getStar());

        return convertView;
    }
}