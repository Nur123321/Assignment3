package com.assignment3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Series extends MediaContent {
    private final List<Episode> episodes = new ArrayList<>();
    private int seasons;

    public Series(int id, String title, String genre, int releaseYear, double rating, int seasons) {
        super(id, title, genre, releaseYear, rating);
        this.seasons = seasons;
    }

    public static Builder builder(int id, String title, String genre, int releaseYear, int seasons) {
        return new Builder(id, title, genre, releaseYear, seasons);
    }

    public List<Episode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    @Override
    public String getContentType() {
        return "Series";
    }

    @Override
    public String getSummary() {
        return getTitle() + " (" + seasons + " seasons, " + episodes.size() + " episodes)";
    }

    public static class Builder {
        private final int id;
        private final String title;
        private final String genre;
        private final int releaseYear;
        private final int seasons;
        private double rating = 0.0;

        private Builder(int id, String title, String genre, int releaseYear, int seasons) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.releaseYear = releaseYear;
            this.seasons = seasons;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Series build() {
            return new Series(id, title, genre, releaseYear, rating, seasons);
        }
    }
}
