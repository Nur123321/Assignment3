CREATE TABLE IF NOT EXISTS movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE,
    genre VARCHAR(100) NOT NULL,
    release_year INT NOT NULL CHECK (release_year >= 1888),
    rating NUMERIC(3, 1) NOT NULL CHECK (rating >= 0 AND rating <= 10),
    duration_minutes INT NOT NULL CHECK (duration_minutes > 0),
    director VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS series (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL UNIQUE,
    genre VARCHAR(100) NOT NULL,
    release_year INT NOT NULL CHECK (release_year >= 1940),
    rating NUMERIC(3, 1) NOT NULL CHECK (rating >= 0 AND rating <= 10),
    seasons INT NOT NULL CHECK (seasons > 0)
);

CREATE TABLE IF NOT EXISTS episodes (
    id SERIAL PRIMARY KEY,
    series_id INT NOT NULL REFERENCES series(id) ON DELETE CASCADE,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    release_year INT NOT NULL CHECK (release_year >= 1940),
    rating NUMERIC(3, 1) NOT NULL CHECK (rating >= 0 AND rating <= 10),
    season_number INT NOT NULL CHECK (season_number > 0),
    episode_number INT NOT NULL CHECK (episode_number > 0),
    duration_minutes INT NOT NULL CHECK (duration_minutes > 0),
    UNIQUE (series_id, season_number, episode_number)
);

INSERT INTO movies (title, genre, release_year, rating, duration_minutes, director)
VALUES ('Interstellar', 'Sci-Fi', 2014, 8.6, 169, 'Christopher Nolan');

INSERT INTO series (title, genre, release_year, rating, seasons)
VALUES ('The Expanse', 'Sci-Fi', 2015, 8.5, 6);

INSERT INTO episodes (series_id, title, genre, release_year, rating, season_number, episode_number, duration_minutes)
VALUES (1, 'Dulcinea', 'Sci-Fi', 2015, 8.1, 1, 1, 45);
