package com.dut.CinemaProject.dto.Movie;

import com.dut.CinemaProject.dao.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieDto {

    public MovieDto(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.posterPath = movie.getPosterPath();
        this.trailerPath = movie.getTrailerPath();
        this.duration = movie.getDuration();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.country = movie.getCountry();
    }

    private Long id;
    private String title;
    private String description;
    private String posterPath;
    private String trailerPath;
    private Integer duration;
    private String actors;
    private String genres;
    private String country;
}
