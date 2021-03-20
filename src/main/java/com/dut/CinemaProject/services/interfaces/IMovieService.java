package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Movie.MovieData;
import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Session.SessionShort;

import java.util.List;

public interface IMovieService {
    MovieDto createMovie(MovieData newMovie);
    void deleteMovie(Long id);
    MovieDto updateMovie(Long id, MovieData movie);
    MovieDto getMovieById(Long id);
    List<SessionShort> getSessions(Long id);
    List<MovieDto> getMovies(Integer page, Integer perPage, String genre, String title);
    Integer getPagesAmount(Integer perPage, String genre, String title);
}
