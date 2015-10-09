package com.example.user.application.lodge;

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
public class LodgeInfo extends Activity {
    private Data lodge;
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
        setContentView(R.layout.lodge_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.lodgeinfobtn);
        menubtn = (ImageButton) findViewById(R.id.lodgemenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.lodgereviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.lodgeframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        LodgeInfomation li = new LodgeInfomation();
        tr.add(R.id.lodgeframe, li, "LodgeInfo");
        tr.commit();
    }

    public void lodgeClick(View v) {
        switch (v.getId()) {
            case R.id.lodgeinfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.charge));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                LodgeInfomation li = new LodgeInfomation();
                tr1.replace(R.id.lodgeframe, li, "LodgeInfo");
                tr1.commit();
                break;
            case R.id.lodgemenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.charge_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                LodgeUse lm = new LodgeUse();
                tr2.replace(R.id.lodgeframe, lm, "LodgeMenu");
                tr2.commit();
                break;
            case R.id.lodgereviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.charge));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));
                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                LodgeReview lr = new LodgeReview();
                tr3.replace(R.id.lodgeframe, lr, "LodgeReview");
                tr3.commit();
                break;
            default:
                break;
        }
    }

    @SuppressLint("ValidFragment")
    class LodgeReview extends Fragment {
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
            ReviewData listAdapter = new ReviewData(LodgeInfo.this, R.layout.review_item, review);
            list.setAdapter(listAdapter);

            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class LodgeUse extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_use, container, false);
            return root;
        }
    }

    @SuppressLint("ValidFragment")
    class LodgeInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_info, container, false);

            Intent intent = getIntent();
            lodge = (Data) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.lodgeinfoname);
            name.setText(lodge.getName());

            time = (TextView) root.findViewById(R.id.lodgeinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.lodgeinfouse);
            useInfo.setText("숙박업\n" + lodge.getClcdnm());

            addr = (TextView) root.findViewById(R.id.lodgeinfoaddr);
            addr.setText("주소\n" + lodge.getAddr());

            telno = (TextView) root.findViewById(R.id.lodgeinfotelno);
            telno.setText("연락처\n" + lodge.getTelno());

            return root;
        }
    }
}