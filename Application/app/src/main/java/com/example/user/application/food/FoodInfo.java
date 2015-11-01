package com.example.user.application.food;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;
import com.example.user.application.datamanager.Review;
import com.example.user.application.datamanager.ReviewData;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 20.
 */
public class FoodInfo extends Activity {
    private Data food;
    private TextView name;
    private TextView time;
    private TextView useInfo;
    private TextView addr;
    private TextView telno;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private ImageButton infobtn;
    private ImageButton menubtn;
    private ImageButton reviewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.food_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.foodinfobtn);
        menubtn = (ImageButton) findViewById(R.id.foodmenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.foodreviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.foodframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        FoodInformation fi = new FoodInformation();
        tr.add(R.id.foodframe, fi, "FoodInfo");
        tr.commit();
    }

    public void foodClick(View v) {
        switch (v.getId()) {
            case R.id.foodinfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                FoodInformation fi = new FoodInformation();
                tr1.replace(R.id.foodframe, fi, "FoodInfo");
                tr1.commit();
                break;
            case R.id.foodmenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                FoodMenu fm = new FoodMenu();
                tr2.replace(R.id.foodframe, fm, "FoodMenu");
                tr2.commit();
                break;
            case R.id.foodreviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));

                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                FoodReview fr = new FoodReview();
                tr3.replace(R.id.foodframe, fr, "FoodReview");
                tr3.commit();
                break;
            default:
                break;
        }
    }

    @SuppressLint("ValidFragment")
    class FoodReview extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            DataManager data = DataManager.getInstance();
            ArrayList<Review> review = new ArrayList<Review>();

            Intent intent = getIntent();
            Data item = (Data) intent.getSerializableExtra("Item");

            for (int i = 0; i < data.getReview().size(); i++) {
                if (item.getName().equals(data.getReview().get(i).getName()))
                    review.add(data.getReview().get(i));
            }

            View root = inflater.inflate(R.layout.review_list, container, false);

            ListView list = (ListView) root.findViewById(R.id.reviewlist);
            ReviewData listAdapter = new ReviewData(FoodInfo.this, R.layout.review_item, review);
            list.setAdapter(listAdapter);

            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class FoodMenu extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.food_menu, container, false);
            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class FoodInformation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.food_info, container, false);

            Intent intent = getIntent();
            food = (Data) intent.getSerializableExtra("Item");
            ArrayList<Data> data = new ArrayList<Data>();

            data.add(food);

            ListView listView = (ListView) root.findViewById(R.id.itemlist);
            FoodList listAdapter = new FoodList(FoodInfo.this, R.layout.list_item, data);
            listView.setAdapter(listAdapter);

            time = (TextView) root.findViewById(R.id.foodinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.foodinfouse);
            useInfo.setText("주된음식\n" + food.getClcdnm());

            addr = (TextView) root.findViewById(R.id.foodinfoaddr);
            addr.setText("주소\n" + food.getAddr());

            telno = (TextView) root.findViewById(R.id.foodinfotelno);
            telno.setText("연락처\n" + food.getTelno());

            return root;
        }
    }
}
