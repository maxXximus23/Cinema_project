package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.repos.MovieRepository;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dto.Session.SessionShort;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final SessionRepository sessionRepository;

    @Override
    public List<SessionShort> getSessions(Long id) {
        if (!movieRepository.existsById(id))
                throw new ItemNotFoundException("Movie not found");

        return sessionRepository.getActualSessionsByMovieId(id)
                .stream()
                .sorted((e1, e2) -> e1.getDate().isBefore(e2.getDate()) ? -1 : 1)
                .map(SessionShort::new)
                .collect(Collectors.toList());
    }
}
