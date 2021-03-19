package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Movie.MovieData;
import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Session.SessionShort;
import com.dut.CinemaProject.services.interfaces.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@AllArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> getMovies(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "perPage", defaultValue = "20") Integer perPage){
        return movieService.getMovies(page, perPage);
    }

    @GetMapping("pages/{perPage}")
    @ResponseStatus(HttpStatus.OK)
    public Integer pageAmount(@PathVariable Integer perPage){
        return movieService.getPagesAmount(perPage);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto getById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@RequestBody MovieData newMovie){
        return movieService.createMovie(newMovie);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieData movie){
        return  movieService.updateMovie(id, movie);
    }

    @GetMapping("{id}/sessions")
    public List<SessionShort> getSessions(@PathVariable Long id){
        return movieService.getSessions(id);
    }
}
