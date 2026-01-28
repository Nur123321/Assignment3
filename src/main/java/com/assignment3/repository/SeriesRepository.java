package com.assignment3.repository;

import com.assignment3.exception.DatabaseOperationException;
import com.assignment3.model.Series;
import com.assignment3.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeriesRepository {
    private final DatabaseConnection databaseConnection;

    public SeriesRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void create(Series series) {
        String sql = "INSERT INTO series (title, genre, release_year, rating, seasons) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, series.getTitle());
            statement.setString(2, series.getGenre());
            statement.setInt(3, series.getReleaseYear());
            statement.setDouble(4, series.getRating());
            statement.setInt(5, series.getSeasons());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to create series", exception);
        }
    }

    public Optional<Series> getById(int id) {
        String sql = "SELECT * FROM series WHERE id = ?";
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
            throw new DatabaseOperationException("Failed to fetch series", exception);
        }
    }

    public Optional<Series> getByTitle(String title) {
        String sql = "SELECT * FROM series WHERE title = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch series by title", exception);
        }
    }

    public List<Series> getAll() {
        String sql = "SELECT * FROM series";
        List<Series> seriesList = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                seriesList.add(mapRow(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch series", exception);
        }
        return seriesList;
    }

    public void update(int id, Series series) {
        String sql = "UPDATE series SET title = ?, genre = ?, release_year = ?, rating = ?, seasons = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, series.getTitle());
            statement.setString(2, series.getGenre());
            statement.setInt(3, series.getReleaseYear());
            statement.setDouble(4, series.getRating());
            statement.setInt(5, series.getSeasons());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to update series", exception);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM series WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to delete series", exception);
        }
    }

    private Series mapRow(ResultSet resultSet) throws SQLException {
        return new Series(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("genre"),
                resultSet.getInt("release_year"),
                resultSet.getDouble("rating"),
                resultSet.getInt("seasons")
        );
    }
}
