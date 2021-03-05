package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.dto.Session.SessionDto;

import java.util.List;

public interface ISessionService {
    SessionTicketsList getSessionTicketsData(Long sessionId);
    List<SessionDto> getActualSessions();
}
