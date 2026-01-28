# Movie Streaming Platform (Assignment 3)

## A. Project Overview
This project implements a **Movie Streaming Platform** API using Java, JDBC, and PostgreSQL. It demonstrates OOP (inheritance, interfaces, polymorphism), layered architecture (controller → service → repository), validation, and exception handling.

**Entities**:
- `MediaContent` (abstract base)
- `Movie`
- `Series`
- `Episode`

## B. OOP Design Documentation
### Abstract class and subclasses
- `MediaContent` defines shared fields (`id`, `title`, `genre`, `releaseYear`, `rating`) and an abstract method `getContentType()`.
- `Movie`, `Series`, and `Episode` extend `MediaContent` and override `getContentType()`.

### Interfaces
- `Rateable` — implemented by `MediaContent`.
- `Playable` — implemented by `Movie` and `Episode`.

### Composition / Aggregation
- `Series` aggregates `Episode` objects (composition-style list) to show related episodes in memory.

### Polymorphism example
- The controller iterates a list of `MediaContent` and prints `getContentType()` for each item.

### UML (text)
```
MediaContent (abstract)
  + getContentType(): String
  + rate(rating)
   /         |         \
Movie     Series     Episode
  |          |           |
Playable   (has)      Playable
Rateable            Rateable
```

## C. Database Description
**PostgreSQL schema** is located in `src/main/resources/schema.sql` and contains:
- `movies`
- `series`
- `episodes` (FK → `series.id`)

**Constraints**:
- Primary keys for all tables
- Unique titles for movies/series
- Foreign key + `ON DELETE CASCADE`
- CHECK constraints for ratings, durations, and release years

Sample inserts are included in the schema file.

## D. Controller (CRUD summary)
The `MediaController` demonstrates CRUD flows through services:
- `MovieService.create`, `getById`, `getAll`, `update`, `delete`
- `SeriesService.create`, `getById`, `getAll`, `update`, `delete`
- `EpisodeService.create`, `getById`, `getBySeriesId`, `update`, `delete`

**Example flow (from `MediaController.demoCrudFlow`)**:
1. Create a movie
2. Create a series
3. Create an episode for the series
4. Show polymorphism with `MediaContent` list

## E. Instructions to Compile and Run
1. **Create the database**:
   ```bash
   createdb moviestream
   psql -d moviestream -f src/main/resources/schema.sql
   ```

2. **Compile**:
   ```bash
   javac -d out $(find src/main/java -name "*.java")
   ```

3. **Run**:
   ```bash
   DB_URL=jdbc:postgresql://localhost:5432/moviestream \
   DB_USER=postgres DB_PASSWORD=postgres \
   java -cp out com.assignment3.Main
   ```

## F. Screenshots
Include screenshots in your submission that show:
- Successful CRUD operations
- Error handling (e.g., invalid input)

## G. Reflection
**What I learned**:
- How to structure a layered Java project with JDBC and exceptions.

**Challenges**:
- Designing validation rules that match both business rules and database constraints.

**Benefits of JDBC and multilayer design**:
- Clear separation of concerns and easier testing/maintenance.
