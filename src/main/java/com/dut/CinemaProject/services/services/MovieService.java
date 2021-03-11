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
            throw new BadRequestException("Information can`t be empty");
        if(newMovie.getDuration()<=0)
            throw new BadRequestException("Time can`t be less than 1");

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
    public MovieDto updateMovie(Long id, UpdateMovieData movie) {
        Movie updateMovie = movieRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Movie not found"));

        if(movie.getTitle()!=null) {
            if(movie.getTitle().isBlank())
                throw new BadRequestException("Title can`t be empty!");
            else
                updateMovie.setTitle(movie.getTitle());
        }
        if(movie.getDescription()!=null) {
            if(movie.getDescription().isBlank())
                throw new BadRequestException("Description can`t be empty!");
            else
                updateMovie.setDescription(movie.getDescription());
        }
        if(movie.getTrailerPath()!=null) {
            if(movie.getTrailerPath().isBlank())
                throw new BadRequestException("TrailerPath can`t be empty!");
            else
                updateMovie.setTrailerPath(movie.getTrailerPath());
        }
        if(movie.getPosterPath()!=null) {
            if(movie.getPosterPath().isBlank())
                throw new BadRequestException("PosterPath can`t be empty!");
            else
                updateMovie.setPosterPath(movie.getPosterPath());
        }
        if(movie.getDuration()!=null) {
            if(movie.getDuration()<=0)
                throw new BadRequestException("Time can`t be less than 1!");
            else
                updateMovie.setDuration(movie.getDuration());
        }

        return new MovieDto(movieRepository.save(updateMovie));
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return new MovieDto(movieRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Movie not found")));
    }
}
