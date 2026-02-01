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
}
