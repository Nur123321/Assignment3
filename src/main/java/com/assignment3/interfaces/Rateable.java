package com.assignment3.interfaces;

public interface Rateable {
    void setRating(double rating);

    default boolean isTopRated(double threshold) {
        return threshold >= 0 && getRating() >= threshold;
    }

    double getRating();
}
