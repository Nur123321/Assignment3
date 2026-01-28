package com.assignment3.service;

import com.assignment3.exception.DuplicateResourceException;
import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Movie;
import com.assignment3.repository.MovieRepository;

import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void create(Movie movie) {
        validateMovie(movie);
        movieRepository.getByTitle(movie.getTitle()).ifPresent(existing -> {
            throw new DuplicateResourceException("Movie title already exists: " + movie.getTitle());
        });
        movieRepository.create(movie);
    }

    public Movie getById(int id) {
        return movieRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + id));
    }

    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    public void update(int id, Movie movie) {
        validateMovie(movie);
        Movie existing = getById(id);
        if (!existing.getTitle().equalsIgnoreCase(movie.getTitle())) {
            movieRepository.getByTitle(movie.getTitle()).ifPresent(other -> {
                throw new DuplicateResourceException("Movie title already exists: " + movie.getTitle());
            });
        }
        movieRepository.update(id, movie);
    }

    public void delete(int id) {
        getById(id);
        movieRepository.delete(id);
    }

    private void validateMovie(Movie movie) {
        if (movie == null) {
            throw new InvalidInputException("Movie cannot be null");
        }
        validateCommon(movie.getTitle(), movie.getGenre(), movie.getReleaseYear(), movie.getRating());
        if (movie.getDurationMinutes() <= 0) {
            throw new InvalidInputException("Duration must be greater than 0");
        }
        if (movie.getDirector() == null || movie.getDirector().isBlank()) {
            throw new InvalidInputException("Director is required");
        }
    }

    private void validateCommon(String title, String genre, int releaseYear, double rating) {
        if (title == null || title.isBlank()) {
            throw new InvalidInputException("Title is required");
        }
        if (genre == null || genre.isBlank()) {
            throw new InvalidInputException("Genre is required");
        }
        if (releaseYear < 1888) {
            throw new InvalidInputException("Release year must be realistic (>= 1888)");
        }
        if (rating < 0 || rating > 10) {
            throw new InvalidInputException("Rating must be between 0 and 10");
        }
    }
}
