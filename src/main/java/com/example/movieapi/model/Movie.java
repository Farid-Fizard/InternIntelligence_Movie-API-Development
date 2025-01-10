package com.example.movieapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movie_info")
@Data
@Schema(description = "Entity for Movie operations")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID of the movie, auto-generated", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long movieId;

    @Schema(description = "Title of the movie", required = true, maxLength = 255, example = "Harry Potter and the Philosopher's Stone")
    private String title;

    @Schema(description = "Director of the movie", maxLength = 255, example = "Chris Columbus")
    private String director;

    @Schema(description = "Year the movie was released", minimum = "1895", example = "2001")
    private int releaseYear;

    @Schema(description = "Genre of the movie", maxLength = 50, example = "Fantasy, Adventure, Family")
    private String genre;

    @Schema(description = "IMDB rating of the movie (float)", minimum = "0", maximum = "10", example = "8.1")
    private float imdbRating;

}
