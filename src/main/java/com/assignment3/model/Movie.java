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

    public static Builder builder(int id, String title, String genre, int releaseYear) {
        return new Builder(id, title, genre, releaseYear);
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

    public static class Builder {
        private final int id;
        private final String title;
        private final String genre;
        private final int releaseYear;
        private double rating = 0.0;
        private int durationMinutes = 0;
        private String director = "Unknown";

        private Builder(int id, String title, String genre, int releaseYear) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.releaseYear = releaseYear;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder durationMinutes(int durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        public Builder director(String director) {
            this.director = director;
            return this;
        }

        public Movie build() {
            return new Movie(id, title, genre, releaseYear, rating, durationMinutes, director);
        }
    }
}
