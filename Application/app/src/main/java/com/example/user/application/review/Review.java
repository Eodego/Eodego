package com.example.user.application.review;

import java.io.Serializable;

/**
 * Created by 보운 on 2015-09-07.
 */
public class Review implements Serializable {
    private String review;
    private String name;
    private int userIcon;

    public Review() {
        this.review = null;
        this.name = null;
        this.userIcon = 0;
    }
    public Review(int userIcon, String name, String review) {
        this.review = review;
        this.name = name;
        this.userIcon = userIcon;
    }

    public int getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(int userIcon) {
        this.userIcon = userIcon;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
