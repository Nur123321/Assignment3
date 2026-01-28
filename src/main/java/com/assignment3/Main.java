package com.assignment3;

import com.assignment3.controller.MediaController;
import com.assignment3.repository.EpisodeRepository;
import com.assignment3.repository.MovieRepository;
import com.assignment3.repository.SeriesRepository;
import com.assignment3.service.EpisodeService;
import com.assignment3.service.MovieService;
import com.assignment3.service.SeriesService;
import com.assignment3.util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection(
                System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/moviestream"),
                System.getenv().getOrDefault("DB_USER", "postgres"),
                System.getenv().getOrDefault("DB_PASSWORD", "postgres")
        );

        MovieRepository movieRepository = new MovieRepository(databaseConnection);
        SeriesRepository seriesRepository = new SeriesRepository(databaseConnection);
        EpisodeRepository episodeRepository = new EpisodeRepository(databaseConnection);

        MovieService movieService = new MovieService(movieRepository);
        SeriesService seriesService = new SeriesService(seriesRepository);
        EpisodeService episodeService = new EpisodeService(episodeRepository, seriesRepository);

        MediaController controller = new MediaController(movieService, seriesService, episodeService);
        controller.demoCrudFlow();
    }
}
