package com.mastek.training.Forum.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Comment {
    private String response;
    private int rating;
    private String userId;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Comment(String response, int rating, String userId) {
        this.response = response;
        this.rating = rating;
        this.userId = userId;
    }

}


