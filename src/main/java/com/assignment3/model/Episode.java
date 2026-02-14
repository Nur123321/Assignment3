package com.assignment3.model;

import com.assignment3.interfaces.Playable;

public class Episode extends MediaContent implements Playable {
    private final int seriesId;
    private int seasonNumber;
    private int episodeNumber;
    private int durationMinutes;

    public Episode(int id, String title, String genre, int releaseYear, double rating, int seriesId, int seasonNumber,
                   int episodeNumber, int durationMinutes) {
        super(id, title, genre, releaseYear, rating);
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.durationMinutes = durationMinutes;
    }

    public static Builder builder(int id, String title, String genre, int releaseYear, int seriesId, int seasonNumber,
                                  int episodeNumber) {
        return new Builder(id, title, genre, releaseYear, seriesId, seasonNumber, episodeNumber);
    }

    public int getSeriesId() {
        return seriesId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String getContentType() {
        return "Episode";
    }

    @Override
    public String getSummary() {
        return getTitle() + " (S" + seasonNumber + "E" + episodeNumber + ")";
    }

    @Override
    public void play() {
        System.out.println("Playing episode: " + getTitle());
    }

    public static class Builder {
        private final int id;
        private final String title;
        private final String genre;
        private final int releaseYear;
        private final int seriesId;
        private final int seasonNumber;
        private final int episodeNumber;
        private double rating = 0.0;
        private int durationMinutes = 0;

        private Builder(int id, String title, String genre, int releaseYear, int seriesId, int seasonNumber,
                        int episodeNumber) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.releaseYear = releaseYear;
            this.seriesId = seriesId;
            this.seasonNumber = seasonNumber;
            this.episodeNumber = episodeNumber;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder durationMinutes(int durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        public Episode build() {
            return new Episode(id, title, genre, releaseYear, rating, seriesId, seasonNumber, episodeNumber,
                    durationMinutes);
        }
    }
}
