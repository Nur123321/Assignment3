package com.assignment3.service;

import com.assignment3.exception.InvalidInputException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.model.Episode;
import com.assignment3.repository.EpisodeRepository;
import com.assignment3.repository.SeriesRepository;

import java.util.List;

public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final SeriesRepository seriesRepository;

    public EpisodeService(EpisodeRepository episodeRepository, SeriesRepository seriesRepository) {
        this.episodeRepository = episodeRepository;
        this.seriesRepository = seriesRepository;
    }

    public void create(Episode episode) {
        validateEpisode(episode);
        episodeRepository.create(episode);
    }

    public Episode getById(int id) {
        return episodeRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Episode not found: " + id));
    }

    public List<Episode> getBySeriesId(int seriesId) {
        if (seriesRepository.getById(seriesId).isEmpty()) {
            throw new ResourceNotFoundException("Series not found: " + seriesId);
        }
        return episodeRepository.getBySeriesId(seriesId);
    }

    public List<Episode> getAll() {
        return episodeRepository.getAll();
    }

    public void update(int id, Episode episode) {
        validateEpisode(episode);
        getById(id);
        episodeRepository.update(id, episode);
    }

    public void delete(int id) {
        getById(id);
        episodeRepository.delete(id);
    }

    private void validateEpisode(Episode episode) {
        if (episode == null) {
            throw new InvalidInputException("Episode cannot be null");
        }
        if (episode.getTitle() == null || episode.getTitle().isBlank()) {
            throw new InvalidInputException("Title is required");
        }
        if (episode.getSeriesId() <= 0) {
            throw new InvalidInputException("Series ID is required");
        }
        if (seriesRepository.getById(episode.getSeriesId()).isEmpty()) {
            throw new InvalidInputException("Series must exist before adding episodes");
        }
        if (episode.getSeasonNumber() <= 0) {
            throw new InvalidInputException("Season number must be positive");
        }
        if (episode.getEpisodeNumber() <= 0) {
            throw new InvalidInputException("Episode number must be positive");
        }
        if (episode.getDurationMinutes() <= 0) {
            throw new InvalidInputException("Duration must be greater than 0");
        }
        if (episode.getRating() < 0 || episode.getRating() > 10) {
            throw new InvalidInputException("Rating must be between 0 and 10");
        }
    }
}
