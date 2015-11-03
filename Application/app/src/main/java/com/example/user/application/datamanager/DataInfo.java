package com.example.user.application.datamanager;

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
import com.example.user.application.food.FoodList;
import com.example.user.application.review.Review;
import com.example.user.application.review.ReviewData;

import java.util.ArrayList;

/**
 * Created by bw on 15. 11. 3.
 */
public class DataInfo extends Activity {
    private Data data;
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
        setContentView(R.layout.data_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.datainfobtn);
        menubtn = (ImageButton) findViewById(R.id.datamenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.datareviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.dataframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        DataInformation fi = new DataInformation();
        tr.add(R.id.dataframe, fi, "DataInfo");
        tr.commit();
    }

    public void dataClick(View v) {
        switch (v.getId()) {
            case R.id.datainfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                DataInformation fi = new DataInformation();
                tr1.replace(R.id.dataframe, fi, "DataInfo");
                tr1.commit();
                break;
            case R.id.datamenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                DataMenu fm = new DataMenu();
                tr2.replace(R.id.dataframe, fm, "DataMenu");
                tr2.commit();
                break;
            case R.id.datareviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));

                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                DataReview fr = new DataReview();
                tr3.replace(R.id.dataframe, fr, "DataReview");
                tr3.commit();
                break;
            default:
                break;
        }
    }

    class DataReview extends Fragment {
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
            ReviewData listAdapter = new ReviewData(DataInfo.this, R.layout.review_item, review);
            list.setAdapter(listAdapter);

            return root;
        }
    }

    class DataMenu extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.data_menu, container, false);
            return root;
        }
    }

    class DataInformation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.data_info, container, false);

            Intent intent = getIntent();
            data = (Data) intent.getSerializableExtra("Item");
            ArrayList<Data> dataList = new ArrayList<Data>();

            dataList.add(DataInfo.this.data);

            ListView listView = (ListView) root.findViewById(R.id.itemlist);
            FoodList listAdapter = new FoodList(DataInfo.this, R.layout.list_item, dataList);
            listView.setAdapter(listAdapter);

            time = (TextView) root.findViewById(R.id.datainfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.datainfouse);
            useInfo.setText("주된음식\n" + DataInfo.this.data.getClcdnm());

            addr = (TextView) root.findViewById(R.id.datainfoaddr);
            addr.setText("주소\n" + DataInfo.this.data.getAddr());

            telno = (TextView) root.findViewById(R.id.datainfotelno);
            telno.setText("연락처\n" + DataInfo.this.data.getTelno());

            return root;
        }
    }
}
