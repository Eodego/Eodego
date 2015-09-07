package com.example.user.application.datamanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by 보운 on 2015-09-07.
 */
public class ReviewData extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Review> data;
    private LayoutInflater inflater;

    public ReviewData(Context context, int layout, ArrayList<Review> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.reviewimg);
        img.setImageResource(data.get(position).getUserIcon());

        TextView review = (TextView) convertView.findViewById(R.id.review);
        review.setText(data.get(position).getReview());

        return convertView;
    }
}
