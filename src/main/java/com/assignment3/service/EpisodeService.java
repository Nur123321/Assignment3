package com.assignment3.service;

import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Episode;
import com.assignment3.repository.interfaces.CrudRepository;
import com.assignment3.utils.SortingUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EpisodeService {
    private final CrudRepository<Episode, Integer> repository;

    public EpisodeService(CrudRepository<Episode, Integer> repository) {
        this.repository = repository;
    }

    public Episode create(Episode episode) {
        validate(episode);
        return repository.create(episode);
    }

    public Episode getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode " + id + " not found"));
    }

    public List<Episode> getAll() {
        return repository.findAll();
    }

    public List<Episode> getBySeriesId(int seriesId) {
        return repository.findAll().stream()
                .filter(episode -> episode.getSeriesId() == seriesId)
                .collect(Collectors.toList());
    }

    public List<Episode> getAllSortedByEpisodeNumber() {
        List<Episode> items = repository.findAll();
        SortingUtils.sortBy(items, Comparator.comparingInt(Episode::getEpisodeNumber));
        return items;
    }

    public Episode update(Episode episode) {
        validate(episode);
        return repository.update(episode);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(Episode episode) {
        try {
            episode.validate(episode);
            if (episode.getEpisodeNumber() <= 0) {
                throw new IllegalArgumentException("episodeNumber must be positive");
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException(ex.getMessage());
        }
    }
}
