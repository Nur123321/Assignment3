package com.assignment3.model;

import com.assignment3.interfaces.Rateable;
import com.assignment3.interfaces.Validatable;

public abstract class MediaContent implements Rateable, Validatable<MediaContent> {
    private final int id;
    private String title;
    private String genre;
    private int releaseYear;
    private double rating;

    protected MediaContent(int id, String title, String genre, int releaseYear, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public abstract String getContentType();

    public abstract String getSummary();

    @Override
    public void validate(MediaContent value) {
        requireNonBlank(value.title, "title");
        requireNonBlank(value.genre, "genre");
        requireInRange(value.rating, 0.0, 10.0, "rating");
        requireInRange(value.releaseYear, 1900, 2100, "releaseYear");
    }
}
