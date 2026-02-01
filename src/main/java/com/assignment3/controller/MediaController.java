package com.assignment3.controller;

import com.assignment3.model.Episode;
import com.assignment3.model.MediaContent;
import com.assignment3.model.Movie;
import com.assignment3.model.Series;
import com.assignment3.service.EpisodeService;
import com.assignment3.service.MovieService;
import com.assignment3.service.SeriesService;
import com.assignment3.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MediaController {
    private final MovieService movieService;
    private final SeriesService seriesService;
    private final EpisodeService episodeService;

    public MediaController(MovieService movieService, SeriesService seriesService, EpisodeService episodeService) {
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.episodeService = episodeService;
    }

    public void demoCrudFlow() {
        Movie movie = new Movie(1, "Nebula Drift", "Sci-Fi", 2021, 8.5, 124, "Ava Cho");
        movieService.create(movie);

        Series series = new Series(100, "City Lights", "Drama", 2020, 9.1, 3);
        seriesService.create(series);

        Episode episode = new Episode(1001, "Pilot", "Drama", 2020, 8.7, 100, 1, 1, 52);
        episodeService.create(episode);
        seriesService.addEpisode(series.getId(), episode);

        Movie updated = new Movie(1, "Nebula Drift", "Sci-Fi", 2021, 9.0, 124, "Ava Cho");
        movieService.update(updated);

        movieService.getAllSortedByRatingDesc().forEach(item ->
                System.out.println("Movie: " + item.getSummary()));

        seriesService.getAllSortedByTitle().forEach(item ->
                System.out.println("Series: " + item.getSummary()));

        episodeService.getAllSortedByEpisodeNumber().forEach(item ->
                System.out.println("Episode: " + item.getSummary()));

        List<MediaContent> mediaContents = new ArrayList<>();
        mediaContents.add(movie);
        mediaContents.add(series);
        mediaContents.add(episode);

        for (MediaContent content : mediaContents) {
            System.out.println("Type: " + content.getContentType());
        }

        System.out.println("Reflection fields: " + ReflectionUtils.listFieldNames(movie));
        System.out.println("Reflection description: " + ReflectionUtils.describeClass(movie));

        movieService.delete(movie.getId());
        episodeService.delete(episode.getId());
        seriesService.delete(series.getId());
    }
}
