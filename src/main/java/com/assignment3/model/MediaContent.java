package com.assignment3.model;

import com.assignment3.model.contracts.Rateable;

public abstract class MediaContent implements Rateable {
    private int id;
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

    public abstract String getContentType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public void rate(double rating) {
        this.rating = rating;
    }
}
