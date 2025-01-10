package com.example.movieapi.controller;

import com.example.movieapi.model.Movie;
import com.example.movieapi.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movies", description = "Movie management operations")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a movie by Id", description = "Fetch a specific movie record from the database by using Id.")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable("id") Long movieId) {
        if (Objects.isNull(movieId) || movieId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        return movieService.getMovieById(movieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Retrieve all movies", description = "Fetch a list of all movies in the database.")
    public ResponseEntity<List<Movie>> getAllMovieDetails() {
        List<Movie> movieList = movieService.getAllMovieList();
        if (movieList == null || movieList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movieList);
    }

    @PostMapping
    @Operation(summary = "Add a new movie record", description = "Add a new movie record to the database.")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        if (movie.getMovieId() != null) {
            throw new IllegalArgumentException("ID should not be provided for creating a new Movie");
        }
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.ok(createdMovie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing movie record", description = "Update an existing movie record to the database by using Id.")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable("id") Long movieId) {
        if (Objects.isNull(movieId) || movieId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        return movieService.updateMovie(movie, movieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie record", description = "Delete a movie record from the database by using Id")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long movieId) {
        if (Objects.isNull(movieId) || movieId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (movieService.getMovieById(movieId).isPresent()) {
            movieService.deleteMovie(movieId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
