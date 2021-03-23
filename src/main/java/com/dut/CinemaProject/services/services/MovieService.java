package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Movie;
import com.dut.CinemaProject.dao.repos.MovieRepository;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dto.Movie.MovieData;
import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Movie.MovieTitle;
import com.dut.CinemaProject.dto.Session.SessionShort;
import com.dut.CinemaProject.exceptions.BadRequestException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final SessionRepository sessionRepository;

    @Override
    public MovieDto createMovie(MovieData newMovie) {
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
        movie.setActors(newMovie.getActors());
        movie.setCountry(newMovie.getCountry());
        movie.setGenres(newMovie.getGenres());

        return new MovieDto(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Movie not found"));
        movieRepository.delete(movie);
    }

    @Override
    public MovieDto updateMovie(Long id, MovieData movie) {
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

        updateMovie.setGenres(movie.getGenres());
        updateMovie.setCountry(movie.getCountry());
        updateMovie.setActors(movie.getActors());

        return new MovieDto(movieRepository.save(updateMovie));
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return new MovieDto(movieRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Movie not found")));
    }

    @Override
    public List<SessionShort> getSessions(Long id) {
        if (!movieRepository.existsById(id))
            throw new ItemNotFoundException("Movie not found");

        return sessionRepository.getActualSessionsByMovieId(id)
                .stream()
                .map(SessionShort::new)
                .sorted((e1, e2) -> e1.getDate().isBefore(e2.getDate()) ? -1 : 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMovies(Integer page, Integer perPage, String genre, String title) {
        if (page < 1)
            throw new BadRequestException("Page can not be less than 1!");

        if (perPage < 1)
            throw new BadRequestException("On page must be at list one element!");

        List<Movie> movies =  movieRepository.findAll();

        if (!genre.equals("") && movies.size() != 0)
            movies = movies.stream()
                    .filter(el -> el.getGenres()
                            .toLowerCase()
                            .contains(genre.toLowerCase()))
                    .collect(Collectors.toList());

        if (!title.equals("") && movies.size() != 0)
            movies = movies.stream()
                    .filter(el -> el.getTitle()
                            .toLowerCase()
                            .contains(title.toLowerCase()))
                    .collect(Collectors.toList());

        if (movies.size() < (page - 1) * perPage)
            throw new BadRequestException("Requested page does not exist!");

        if (movies.size() >= page * perPage)
            return movies.subList((page - 1) * perPage, perPage * page)
                        .stream()
                        .map(MovieDto::new)
                        .collect(Collectors.toList());
        else
            return movies.subList((page - 1) * perPage, movies.size())
                    .stream()
                    .map(MovieDto::new)
                    .collect(Collectors.toList());

    }

    @Override
    public Integer getPagesAmount(Integer perPage, String genre, String title) {
        if (perPage < 1)
            throw new BadRequestException("On page must be at list one element!");

        List<Movie> movies = movieRepository.findAll();

        if (!genre.equals("") && movies.size() != 0)
            movies = movies.stream()
                    .filter(el -> el.getGenres()
                            .toLowerCase()
                            .contains(genre.toLowerCase()))
                    .collect(Collectors.toList());

        if (!title.equals("") && movies.size() != 0)
            movies = movies.stream()
                    .filter(el -> el.getTitle()
                            .toLowerCase()
                            .contains(title.toLowerCase()))
                    .collect(Collectors.toList());

        double pages =  (double)movies.size()/((double) perPage);

        if (pages == (int) pages)
            return (int) pages;
        else
            return  (int) (pages + 1);
    }

    @Override
    public List<MovieTitle> getTitles() {
        return movieRepository.findAll()
                .stream()
                .map(el -> new MovieTitle(el.getId(), el.getTitle()))
                .collect(Collectors.toList());
    }
}
