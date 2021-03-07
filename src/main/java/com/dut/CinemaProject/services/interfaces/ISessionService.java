package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Session.NewSession;
import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.dto.Session.UpdateSessionData;

import java.util.List;

public interface ISessionService {
    SessionTicketsList getSessionTicketsData(Long sessionId);
    SessionDto getSession(Long id);
    List<SessionDto> getActualSessions();

    Long createSession(NewSession sessionData);
    void removeSession(Long id);
    SessionDto updateSession(Long id, UpdateSessionData sessionData);
}
