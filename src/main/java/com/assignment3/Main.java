package com.assignment3;

import com.assignment3.controller.MediaController;
import com.assignment3.model.Episode;
import com.assignment3.model.Movie;
import com.assignment3.model.Series;
import com.assignment3.repository.InMemoryRepository;
import com.assignment3.service.EpisodeService;
import com.assignment3.service.MovieService;
import com.assignment3.service.SeriesService;

public class Main {
    public static void main(String[] args) {
        MovieService movieService = new MovieService(new InMemoryRepository<>(Movie::getId));
        SeriesService seriesService = new SeriesService(new InMemoryRepository<>(Series::getId));
        EpisodeService episodeService = new EpisodeService(new InMemoryRepository<>(Episode::getId));

        MediaController controller = new MediaController(movieService, seriesService, episodeService);
        controller.demoCrudFlow();
    }
}
