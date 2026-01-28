package com.assignment3.controller;

import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Episode;
import com.assignment3.model.MediaContent;
import com.assignment3.model.Movie;
import com.assignment3.model.Series;
import com.assignment3.service.EpisodeService;
import com.assignment3.service.MovieService;
import com.assignment3.service.SeriesService;

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
        try {
            Movie movie = new Movie(0, "Inception", "Sci-Fi", 2010, 9.0, 148, "Christopher Nolan");
            movieService.create(movie);

            Series series = new Series(0, "Stranger Things", "Sci-Fi", 2016, 8.7, 4);
            seriesService.create(series);

            Series persistedSeries = seriesService.getAll().stream().findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Series not found"));

            Episode episode = new Episode(0, "Chapter One", "Sci-Fi", 2016, 8.5,
                    persistedSeries.getId(), 1, 1, 48);
            episodeService.create(episode);

            List<MediaContent> contents = List.of(movie, series, episode);
            for (MediaContent content : contents) {
                System.out.println(content.getContentType() + ": " + content.getTitle());
            }
        } catch (InvalidInputException | ResourceNotFoundException exception) {
            System.err.println("Validation error: " + exception.getMessage());
        }
    }
}
