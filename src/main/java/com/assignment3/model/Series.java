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
}
