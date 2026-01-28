package com.assignment3.repository;

import com.assignment3.exception.DatabaseOperationException;
import com.assignment3.model.Episode;
import com.assignment3.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EpisodeRepository {
    private final DatabaseConnection databaseConnection;

    public EpisodeRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void create(Episode episode) {
        String sql = "INSERT INTO episodes (series_id, title, genre, release_year, rating, season_number, "
                + "episode_number, duration_minutes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, episode.getSeriesId());
            statement.setString(2, episode.getTitle());
            statement.setString(3, episode.getGenre());
            statement.setInt(4, episode.getReleaseYear());
            statement.setDouble(5, episode.getRating());
            statement.setInt(6, episode.getSeasonNumber());
            statement.setInt(7, episode.getEpisodeNumber());
            statement.setInt(8, episode.getDurationMinutes());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to create episode", exception);
        }
    }

    public Optional<Episode> getById(int id) {
        String sql = "SELECT * FROM episodes WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch episode", exception);
        }
    }

    public List<Episode> getBySeriesId(int seriesId) {
        String sql = "SELECT * FROM episodes WHERE series_id = ? ORDER BY season_number, episode_number";
        List<Episode> episodes = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, seriesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    episodes.add(mapRow(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch episodes", exception);
        }
        return episodes;
    }

    public List<Episode> getAll() {
        String sql = "SELECT * FROM episodes";
        List<Episode> episodes = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                episodes.add(mapRow(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch episodes", exception);
        }
        return episodes;
    }

    public void update(int id, Episode episode) {
        String sql = "UPDATE episodes SET series_id = ?, title = ?, genre = ?, release_year = ?, rating = ?, "
                + "season_number = ?, episode_number = ?, duration_minutes = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, episode.getSeriesId());
            statement.setString(2, episode.getTitle());
            statement.setString(3, episode.getGenre());
            statement.setInt(4, episode.getReleaseYear());
            statement.setDouble(5, episode.getRating());
            statement.setInt(6, episode.getSeasonNumber());
            statement.setInt(7, episode.getEpisodeNumber());
            statement.setInt(8, episode.getDurationMinutes());
            statement.setInt(9, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to update episode", exception);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM episodes WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to delete episode", exception);
        }
    }

    private Episode mapRow(ResultSet resultSet) throws SQLException {
        return new Episode(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("genre"),
                resultSet.getInt("release_year"),
                resultSet.getDouble("rating"),
                resultSet.getInt("series_id"),
                resultSet.getInt("season_number"),
                resultSet.getInt("episode_number"),
                resultSet.getInt("duration_minutes")
        );
    }
}
