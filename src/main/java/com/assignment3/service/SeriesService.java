package com.assignment3.service;

import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Episode;
import com.assignment3.model.Series;
import com.assignment3.repository.interfaces.CrudRepository;
import com.assignment3.utils.SortingUtils;

import java.util.Comparator;
import java.util.List;

public class SeriesService {
    private final CrudRepository<Series, Integer> repository;

    public SeriesService(CrudRepository<Series, Integer> repository) {
        this.repository = repository;
    }

    public Series create(Series series) {
        validate(series);
        return repository.create(series);
    }

    public Series getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series " + id + " not found"));
    }

    public List<Series> getAll() {
        return repository.findAll();
    }

    public List<Series> getAllSortedByTitle() {
        List<Series> items = repository.findAll();
        SortingUtils.sortBy(items, Comparator.comparing(Series::getTitle));
        return items;
    }

    public Series update(Series series) {
        validate(series);
        return repository.update(series);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void addEpisode(int seriesId, Episode episode) {
        Series series = getById(seriesId);
        series.addEpisode(episode);
        repository.update(series);
    }

    private void validate(Series series) {
        try {
            series.validate(series);
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException(ex.getMessage());
        }
    }
}
