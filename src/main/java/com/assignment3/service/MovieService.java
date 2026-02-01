package com.assignment3.service;

import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Movie;
import com.assignment3.repository.interfaces.CrudRepository;
import com.assignment3.utils.SortingUtils;

import java.util.Comparator;
import java.util.List;

public class MovieService {
    private final CrudRepository<Movie, Integer> repository;

    public MovieService(CrudRepository<Movie, Integer> repository) {
        this.repository = repository;
    }

    public Movie create(Movie movie) {
        validate(movie);
        return repository.create(movie);
    }

    public Movie getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie " + id + " not found"));
    }

    public List<Movie> getAll() {
        return repository.findAll();
    }

    public List<Movie> getAllSortedByRatingDesc() {
        List<Movie> movies = repository.findAll();
        SortingUtils.sortBy(movies, Comparator.comparingDouble(Movie::getRating).reversed());
        return movies;
    }

    public Movie update(Movie movie) {
        validate(movie);
        return repository.update(movie);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(Movie movie) {
        try {
            movie.validate(movie);
            if (movie.getDurationMinutes() <= 0) {
                throw new IllegalArgumentException("durationMinutes must be positive");
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException(ex.getMessage());
        }
    }
}
