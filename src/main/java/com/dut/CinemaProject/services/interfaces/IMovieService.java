package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Movie.NewMovie;
import com.dut.CinemaProject.dto.Movie.UpdateMovieData;

public interface IMovieService {
    Long createMovie(NewMovie newMovie);
    void deleteMovie(Long id);
    MovieDto updateMovie(Long id, UpdateMovieData movie);
    MovieDto getMovieById(Long id);
}
