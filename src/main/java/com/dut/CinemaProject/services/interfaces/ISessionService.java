package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.Session.SessionTicketsList;

public interface ISessionService {
    SessionTicketsList getSessionTicketsData(Long sessionId);
}
