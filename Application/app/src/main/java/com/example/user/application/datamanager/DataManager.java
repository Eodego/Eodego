package com.example.user.application.datamanager;

import com.example.user.application.food.FoodThread;
import com.example.user.application.performance.PerformanceThread;
import com.example.user.application.review.Review;
import com.example.user.application.review.ReviewThread;
import com.example.user.application.spectacle.SpectacleThread;

import java.util.ArrayList;

/**
 * Created by user on 15. 9. 4.
 */
public class DataManager {
    private static DataManager instance;
    private ArrayList<Data> food;
    private ArrayList<Data> per;
    private ArrayList<Data> spec;
    private ArrayList<Data> bookmark;
    private ArrayList<Review> review;

    private DataManager() {
        instance = null;
        food = new ArrayList<Data>();
        per = new ArrayList<Data>();
        spec = new ArrayList<Data>();
        bookmark = new ArrayList<Data>();
        review = new ArrayList<Review>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            return instance;
        }

        return instance;
    }

    public void executeThread() {
        new FoodThread(food).execute();
        new PerformanceThread(per).execute();
        new SpectacleThread(spec).execute();
        new ReviewThread(review).execute();
//        new BookMarkThread(bookmark).execute();
    }

    public ArrayList<Data> getFood() {
        return food;
    }

    public ArrayList<Data> getPerformance() {
        return per;
    }

    public ArrayList<Data> getSpectacle() {
        return spec;
    }

    public ArrayList<Data> getBookmark() {
        return bookmark;
    }

    public ArrayList<Review> getReview() {
        return review;
    }
}
