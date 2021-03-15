package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Movie.MovieDto;
import com.dut.CinemaProject.dto.Movie.NewMovie;
import com.dut.CinemaProject.dto.Movie.UpdateMovieData;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto getById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addMovie(@RequestBody NewMovie newMovie){
        return movieService.createMovie(newMovie);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody UpdateMovieData movie){
        return  movieService.updateMovie(id, movie);
    }

    @GetMapping("{id}/sessions")
    public List<SessionShort> getSessions(@PathVariable Long id){
        return movieService.getSessions(id);
    }
}
