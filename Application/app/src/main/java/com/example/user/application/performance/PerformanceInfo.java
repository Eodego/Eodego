package com.example.user.application.performance;

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
public class PerformanceInfo extends Activity {
    private Data performance;
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
        setContentView(R.layout.performance_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.perinfobtn);
        menubtn = (ImageButton) findViewById(R.id.permenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.perreviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.perframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        PerformanceInfomation pi = new PerformanceInfomation();
        tr.add(R.id.perframe, pi, "PerformanceInfo");
        tr.commit();
    }

    public void perClick(View v) {
        switch (v.getId()) {
            case R.id.perinfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.charge));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                PerformanceInfomation pi = new PerformanceInfomation();
                tr1.replace(R.id.perframe, pi, "PerformanceInfo");
                tr1.commit();
                break;
            case R.id.permenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.charge_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                PerformanceMenu pm = new PerformanceMenu();
                tr2.replace(R.id.perframe, pm, "PerformanceMenu");
                tr2.commit();
                break;
            case R.id.perreviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.charge));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));
                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                PerformanceReview pr = new PerformanceReview();
                tr3.replace(R.id.perframe, pr, "PerformanceReview");
                tr3.commit();
                break;
            default:
                break;

        }
    }

    @SuppressLint("ValidFragment")
    class PerformanceReview extends Fragment {
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
            ReviewData listAdapter = new ReviewData(PerformanceInfo.this, R.layout.review_item, review);
            list.setAdapter(listAdapter);

            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class PerformanceMenu extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.performance_use, container, false);
            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class PerformanceInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.performance_info, container, false);

            Intent intent = getIntent();
            performance = (Data) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.perinfoname);
            name.setText(performance.getName());

            time = (TextView) root.findViewById(R.id.perinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.perinfouse);
            useInfo.setText("극장\n" + performance.getClcdnm());

            addr = (TextView) root.findViewById(R.id.perinfoaddr);
            addr.setText("주소\n" + performance.getAddr());

            telno = (TextView) root.findViewById(R.id.perinfotelno);
            telno.setText("연락처\n" + performance.getTelno());

            return root;
        }
    }
}
