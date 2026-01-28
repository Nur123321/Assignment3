package com.assignment3.repository;

import com.assignment3.exception.DatabaseOperationException;
import com.assignment3.model.Movie;
import com.assignment3.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private final DatabaseConnection databaseConnection;

    public MovieRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void create(Movie movie) {
        String sql = "INSERT INTO movies (title, genre, release_year, rating, duration_minutes, director) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3, movie.getReleaseYear());
            statement.setDouble(4, movie.getRating());
            statement.setInt(5, movie.getDurationMinutes());
            statement.setString(6, movie.getDirector());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to create movie", exception);
        }
    }

    public Optional<Movie> getById(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
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
            throw new DatabaseOperationException("Failed to fetch movie", exception);
        }
    }

    public Optional<Movie> getByTitle(String title) {
        String sql = "SELECT * FROM movies WHERE title = ?";
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
            throw new DatabaseOperationException("Failed to fetch movie by title", exception);
        }
    }

    public List<Movie> getAll() {
        String sql = "SELECT * FROM movies";
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                movies.add(mapRow(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to fetch movies", exception);
        }
        return movies;
    }

    public void update(int id, Movie movie) {
        String sql = "UPDATE movies SET title = ?, genre = ?, release_year = ?, rating = ?, duration_minutes = ?, "
                + "director = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3, movie.getReleaseYear());
            statement.setDouble(4, movie.getRating());
            statement.setInt(5, movie.getDurationMinutes());
            statement.setString(6, movie.getDirector());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to update movie", exception);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseOperationException("Failed to delete movie", exception);
        }
    }

    private Movie mapRow(ResultSet resultSet) throws SQLException {
        return new Movie(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("genre"),
                resultSet.getInt("release_year"),
                resultSet.getDouble("rating"),
                resultSet.getInt("duration_minutes"),
                resultSet.getString("director")
        );
    }
}
