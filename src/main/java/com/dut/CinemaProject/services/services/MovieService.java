package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Movie;
import com.dut.CinemaProject.dao.repos.MovieRepository;
import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Movie.NewMovie;
import com.dut.CinemaProject.dto.Movie.UpdateMovieData;
import com.dut.CinemaProject.exceptions.BadRequestException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public Long createMovie(NewMovie newMovie) {
        if(newMovie.getTitle().isBlank() || newMovie.getDescription().isBlank() ||newMovie.getPosterPath().isBlank() ||newMovie.getTrailerPath().isBlank())
            throw new BadRequestException("No information");
        if(newMovie.getDuration()<=0)
            throw new BadRequestException("Time cannot be less than 0");

        Movie movie = new Movie();
        movie.setTitle(newMovie.getTitle());
        movie.setDescription(newMovie.getDescription());
        movie.setTrailerPath(newMovie.getTrailerPath());
        movie.setPosterPath(newMovie.getPosterPath());
        movie.setDuration(newMovie.getDuration());

        return movieRepository.save(movie).getId();
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Movie not found"));
        movieRepository.delete(movie);
    }

    @Override
    public MovieDto updateMovie(UpdateMovieData movie) {
        Movie updateMovie = movieRepository.findById(movie.getId()).orElseThrow(() -> new ItemNotFoundException("Movie not found"));

        updateMovie.setTitle(movie.getTitle());
        updateMovie.setDescription(movie.getDescription());
        updateMovie.setTrailerPath(movie.getTrailerPath());
        updateMovie.setPosterPath(movie.getPosterPath());
        updateMovie.setDuration(movie.getDuration());

        return new MovieDto(movieRepository.save(updateMovie));
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return new MovieDto(movieRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Movie not found")));
    }
}
