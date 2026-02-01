# Movie Streaming Platform — Assignment 4 (SOLID + Advanced OOP)

## 1) Project Overview
This project implements a **Movie Streaming Platform** API in Java using a layered architecture (controller → service → repository → data) and demonstrates SOLID principles, advanced OOP features, and JDBC-ready schema design. The domain includes movies, series, and episodes with composition and CRUD workflows.

**Entities**:
- `MediaContent` (abstract base class)
- `Movie` (extends `MediaContent`, includes `durationMinutes` and `director`)
- `Series` (extends `MediaContent`, includes `seasons`)
- `Episode` (extends `MediaContent`, includes `seasonNumber`, `episodeNumber`, `durationMinutes`)

**Relationships**:
- `Series` aggregates a list of `Episode` objects (composition in-memory).

## 2) Repository Structure
```
src/main/java/com/assignment3
├── Main.java
├── controller/
│   └── MediaController.java
├── exception/
│   ├── DatabaseOperationException.java
│   ├── DuplicateResourceException.java
│   ├── InvalidInputException.java
│   └── ResourceNotFoundException.java
├── interfaces/
│   ├── Playable.java
│   ├── Rateable.java
│   └── Validatable.java
├── model/
│   ├── Episode.java
│   ├── MediaContent.java
│   ├── Movie.java
│   └── Series.java
├── repository/
│   └── InMemoryRepository.java
├── repository/interfaces/
│   ├── CrudRepository.java
│   └── IdExtractor.java
├── service/
│   ├── EpisodeService.java
│   ├── MovieService.java
│   └── SeriesService.java
└── utils/
    ├── ReflectionUtils.java
    └── SortingUtils.java
```

## 3) SOLID Documentation
**SRP (Single Responsibility)**
- Controller handles I/O + orchestration only; services handle validation and business rules; repositories handle storage.

**OCP (Open/Closed)**
- `MediaContent` is open for extension by new content types without changing existing logic.

**LSP (Liskov Substitution)**
- `Movie`, `Series`, and `Episode` can be used anywhere a `MediaContent` is expected.

**ISP (Interface Segregation)**
- `Playable`, `Rateable`, and `Validatable` are small, focused interfaces for specific behavior.

**DIP (Dependency Inversion)**
- Services depend on `CrudRepository` abstractions (interfaces), not concrete repository implementations.

## 4) Advanced OOP Features (Where Used)
- **Generics**: `CrudRepository<T, ID>` and `InMemoryRepository<T, ID>` provide generic CRUD operations.
- **Lambdas**: Sorting and filtering use lambda expressions in service layer.
- **Reflection**: `ReflectionUtils` inspects class metadata for demonstration.
- **Interface default/static methods**: `Rateable.isTopRated()` and `Playable.supportsOffline()`.

## 5) Database Schema (PostgreSQL)
Schema and sample inserts are defined in:
- `src/main/resources/schema.sql`

**Tables**:
- `movies`
- `series`
- `episodes` (FK → `series.id`)

**Constraints**:
- Primary keys for all tables
- Unique titles for movies/series
- Foreign key + `ON DELETE CASCADE`
- CHECK constraints for ratings, durations, and release years

## 6) Controller + Service + Repository Responsibilities
**Controller Layer**
- Handles simple input orchestration.
- Delegates operations to services.

**Service Layer**
- Validates entities, applies business rules.
- Uses repository interface (DIP) and lambdas.

**Repository Layer**
- Generic CRUD interface and in-memory implementation.
- Ready for JDBC repository implementations.

## 7) Demonstration & Output Expectations
The `MediaController.demoCrudFlow()` demonstrates:
- Creating multiple entities
- Updating entities
- Deleting entities
- Validation + exceptions
- Polymorphism using a `MediaContent` list
- Reflection utility output
- Lambda sorting utilities

## 8) Execution Instructions
**Compile**:
```bash
javac -d out $(find src/main/java -name "*.java")
```

**Run**:
```bash
java -cp out com.assignment3.Main
```

## 9) Screenshots Checklist
Include screenshots in your submission showing:
- Successful CRUD operations
- Validation failures
- Reflection output
- Sorted lists using lambdas

## 10) Reflection
**What I learned**:
- Applying SOLID in layered Java applications with clean boundaries.
- Using generics and lambdas to build reusable utilities.

**Challenges**:
- Balancing validation rules across service and repository layers.

**Value of SOLID architecture**:
- Easier testing, extensibility, and maintainability.

## 11) GitHub Workflow Reminder
- Push the repo to GitHub and ensure it is **public** for submission.
- Paste the GitHub URL in Moodle as the official submission.
