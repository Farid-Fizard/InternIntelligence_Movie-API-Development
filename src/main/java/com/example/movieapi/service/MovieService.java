package com.example.movieapi.service;

import com.example.movieapi.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie createMovie(Movie movie);

    Optional<Movie> updateMovie(Movie updatedMovie, Long movieId);

    public void deleteMovie(Long movieId);
    public Optional<Movie> getMovieById(Long movieId);
    public List<Movie>  getAllMovieList();
}
