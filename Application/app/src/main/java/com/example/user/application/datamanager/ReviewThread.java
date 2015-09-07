package com.example.user.application.datamanager;

import android.os.AsyncTask;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by 보운 on 2015-09-07.
 */
public class ReviewThread extends AsyncTask<ArrayList<Review>, Void, ArrayList<Review>> {
    private ArrayList<Review> review;

    public ReviewThread(ArrayList<Review> review) {
        super();
        this.review = review;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Review> reviews) {
        super.onPostExecute(reviews);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Review> reviews) {
        super.onCancelled(reviews);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<Review> doInBackground(ArrayList<Review>... params) {
        ReviewParser parser = new ReviewParser();
        ArrayList<Review> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Review entity : DTOList) {
            review.add(new Review(R.drawable.nonregistered, entity.getName(), entity.getReview()));
        }
        return review;
    }
}
