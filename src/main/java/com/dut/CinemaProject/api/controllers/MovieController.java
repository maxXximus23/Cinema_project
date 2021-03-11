package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Session.SessionShort;
import com.dut.CinemaProject.services.interfaces.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
@AllArgsConstructor
public class MovieController {
    private final IMovieService movieService;

    @GetMapping("{id}/sessions")
    public List<SessionShort> getSessions(@PathVariable Long id){
        return movieService.getSessions(id);
    }
}