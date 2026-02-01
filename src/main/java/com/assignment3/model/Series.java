package com.assignment3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Series extends MediaContent {
    private final List<Episode> episodes = new ArrayList<>();

    public Series(int id, String title, String genre, int releaseYear, double rating) {
        super(id, title, genre, releaseYear, rating);
    }

    public List<Episode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    @Override
    public String getContentType() {
        return "Series";
    }

    @Override
    public String getSummary() {
        return getTitle() + " (" + episodes.size() + " episodes)";
    }
}
