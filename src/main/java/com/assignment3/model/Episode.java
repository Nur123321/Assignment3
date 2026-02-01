package com.assignment3.model;

import com.assignment3.interfaces.Playable;

public class Episode extends MediaContent implements Playable {
    private final int seriesId;
    private int episodeNumber;

    public Episode(int id, String title, String genre, int releaseYear, double rating, int seriesId, int episodeNumber) {
        super(id, title, genre, releaseYear, rating);
        this.seriesId = seriesId;
        this.episodeNumber = episodeNumber;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String getContentType() {
        return "Episode";
    }

    @Override
    public String getSummary() {
        return getTitle() + " (E" + episodeNumber + ")";
    }

    @Override
    public void play() {
        System.out.println("Playing episode: " + getTitle());
    }
}
