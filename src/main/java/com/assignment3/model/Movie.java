package com.assignment3.model;

import com.assignment3.interfaces.Playable;

public class Movie extends MediaContent implements Playable {
    private int durationMinutes;
    private String director;

    public Movie(int id, String title, String genre, int releaseYear, double rating, int durationMinutes, String director) {
        super(id, title, genre, releaseYear, rating);
        this.durationMinutes = durationMinutes;
        this.director = director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String getContentType() {
        return "Movie";
    }

    @Override
    public String getSummary() {
        return getTitle() + " (" + getReleaseYear() + ")";
    }

    @Override
    public void play() {
        System.out.println("Playing movie: " + getTitle());
    }
}
