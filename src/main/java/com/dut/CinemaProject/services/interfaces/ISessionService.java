package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Session.SessionDto;

import java.util.List;

public interface ISessionService {
    List<SessionDto> getActualSessions();
}
