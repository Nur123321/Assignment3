package com.assignment3.factory;

import com.assignment3.model.Episode;
import com.assignment3.model.Movie;
import com.assignment3.model.Series;

public class MediaContentFactory {
    public Movie createMovie(int id, String title, String genre, int releaseYear, double rating,
                             int durationMinutes, String director) {
        return new Movie(id, title, genre, releaseYear, rating, durationMinutes, director);
    }

    public Series createSeries(int id, String title, String genre, int releaseYear, double rating, int seasons) {
        return new Series(id, title, genre, releaseYear, rating, seasons);
    }

    public Episode createEpisode(int id, String title, String genre, int releaseYear, double rating, int seriesId,
                                 int seasonNumber, int episodeNumber, int durationMinutes) {
        return new Episode(id, title, genre, releaseYear, rating, seriesId, seasonNumber, episodeNumber,
                durationMinutes);
    }
}
