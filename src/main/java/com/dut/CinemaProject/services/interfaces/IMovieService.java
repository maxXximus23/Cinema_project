package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Session.SessionShort;

import java.util.List;

public interface IMovieService {
    List<SessionShort> getSessions(Long id);
}
