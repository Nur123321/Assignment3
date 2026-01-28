package com.assignment3.model;

import java.util.ArrayList;
import java.util.List;

public class Series extends MediaContent {
    private int seasons;
    private final List<Episode> episodes = new ArrayList<>();

    public Series(int id, String title, String genre, int releaseYear, double rating, int seasons) {
        super(id, title, genre, releaseYear, rating);
        this.seasons = seasons;
    }

    @Override
    public String getContentType() {
        return "Series";
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public List<Episode> getEpisodes() {
        return new ArrayList<>(episodes);
    }

    public void addEpisode(Episode episode) {
        if (episode != null) {
            episodes.add(episode);
        }
    }
}
