package com.assignment3.service;

import com.assignment3.exception.DuplicateResourceException;
import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Series;
import com.assignment3.repository.SeriesRepository;

import java.util.List;

public class SeriesService {
    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public void create(Series series) {
        validateSeries(series);
        seriesRepository.getByTitle(series.getTitle()).ifPresent(existing -> {
            throw new DuplicateResourceException("Series title already exists: " + series.getTitle());
        });
        seriesRepository.create(series);
    }

    public Series getById(int id) {
        return seriesRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found: " + id));
    }

    public List<Series> getAll() {
        return seriesRepository.getAll();
    }

    public void update(int id, Series series) {
        validateSeries(series);
        Series existing = getById(id);
        if (!existing.getTitle().equalsIgnoreCase(series.getTitle())) {
            seriesRepository.getByTitle(series.getTitle()).ifPresent(other -> {
                throw new DuplicateResourceException("Series title already exists: " + series.getTitle());
            });
        }
        seriesRepository.update(id, series);
    }

    public void delete(int id) {
        getById(id);
        seriesRepository.delete(id);
    }

    private void validateSeries(Series series) {
        if (series == null) {
            throw new InvalidInputException("Series cannot be null");
        }
        if (series.getTitle() == null || series.getTitle().isBlank()) {
            throw new InvalidInputException("Title is required");
        }
        if (series.getGenre() == null || series.getGenre().isBlank()) {
            throw new InvalidInputException("Genre is required");
        }
        if (series.getReleaseYear() < 1940) {
            throw new InvalidInputException("Release year must be 1940 or later");
        }
        if (series.getRating() < 0 || series.getRating() > 10) {
            throw new InvalidInputException("Rating must be between 0 and 10");
        }
        if (series.getSeasons() <= 0) {
            throw new InvalidInputException("Series must have at least 1 season");
        }
    }
}
