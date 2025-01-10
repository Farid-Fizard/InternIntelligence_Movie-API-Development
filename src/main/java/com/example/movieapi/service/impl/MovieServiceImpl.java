package com.example.movieapi.service.impl;

import com.example.movieapi.model.Movie;
import com.example.movieapi.repository.MovieRepository;
import com.example.movieapi.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> updateMovie(Movie updatedMovie, Long movieId) {
        return movieRepository.findById(movieId).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setGenre(updatedMovie.getGenre());
            movie.setImdbRating(updatedMovie.getImdbRating());
            return  movieRepository.save(movie);
        } );
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
       return movieRepository.findById(movieId);
    }

    @Override
    public List<Movie> getAllMovieList() {
        return movieRepository.findAll();
    }
}
